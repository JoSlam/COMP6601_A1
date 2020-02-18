package com.company;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


/*
    Name: Joshua Lambert
    ID: 815007658
    Course: COMP 6601
*/


/*
    Documentation => CardServer
    Card server logic

    The CardServer instance initializes a new thread for handling client requests
    The following are done by the CardServer class:
    * Loads all image resources into memory for searching
    * Accepts card data
    * Searches image directory for corresponding card image
    * Renders the image if found
    * Prints all recorded cards and their card counts on the screen and in console
        when a quit is issued from the client.

    Specific documentation is provided within the class

    NB: The ImageLoader, DrawImages, CardInfo and CardImage classes are support classes for rendering images to the screen
    For this case they need not be explained but some of their roles also include:
    * Loading image resources from target directory
    * Drawing images to the screen
    * Recording card image and count data

 */
public class CardServer extends Thread {
    DataInputStream inStream;
    DataOutputStream outStream;
    Socket clientSocket;
    String cardDirectory;
    ImageLoader loader;
    Iterable<CardImage> cardImageList;
    DrawImages imageHandler;


    public CardServer(Socket clientSocket) {

        try {
            this.loader = new ImageLoader();
            this.cardDirectory = "resources/PNG";
            this.imageHandler = new DrawImages(new Dimension(720, 640), new BorderLayout());

            // Load images into memory
            this.cardImageList = loader.getImagesFromDir(this.cardDirectory);

            // Initialize client socket and socket I/O streams
            this.clientSocket = clientSocket;
            inStream = new DataInputStream(this.clientSocket.getInputStream());
            outStream = new DataOutputStream(this.clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Stacktrace:" + e.getStackTrace());
        }
    }

    // Accept connection and start thread
    public void run() {
        try {

            //taking data in the form of "suite value" EG. "4H"
            ArrayList<CardInfo> cardInfoList = new ArrayList<>();
            String data = inStream.readUTF();

            // Checks client data for quit message
            while (!data.equals("") && !data.equalsIgnoreCase("quit")) {
                String cardName = data;
                CardInfo toPrint = null;

                // If card found increment count
                for (CardInfo info : cardInfoList) {
                    if (info.getCardName().equalsIgnoreCase(cardName)) {
                        info.incrementCardCount();
                        toPrint = info;
                        break;
                    }
                }

                // If not found then find and create new card info
                // and add to existing list
                if (toPrint == null) {
                    //create card info
                    CardImage foundImage = findCardImage(cardName);

                    if (foundImage != null) {
                        CardInfo cardInfo = new CardInfo(cardName.toUpperCase(), 1);
                        cardInfo.setCardImage(foundImage);
                        toPrint = cardInfo;
                        cardInfoList.add(cardInfo);
                    }
                }

                // Print card sent by client
                toPrint.formatMessage("Card count: ");
                this.imageHandler.addImageToFrame(toPrint);
                this.imageHandler.displayFrame(true);

                // Send response to client
                outStream.writeUTF("Card processed: " + toPrint.getCardName() + "\t| Count: " + toPrint.getCardCount());
                System.out.println("Card processed: " + toPrint.getCardName() + "\t| Count: " + toPrint.getCardCount());

                // Get next request => Blocking read
                data = inStream.readUTF();
            }

            // If client issues QUIT request print all images and count data
            if (data.equalsIgnoreCase("quit")) {
                System.out.println("\n\nQUIT ISSUED: The following cards were processed:");
                cardInfoList.forEach(info -> {
                    info.formatMessage("Card count: ");
                    System.out.println("Card processed: " + info.getCardName() + "\t| Count: " + info.getCardCount());
                });
                this.imageHandler.addImagesToFrame(cardInfoList);
                this.imageHandler.displayFrame(true);

                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.out.println("Failed to close clientSocket:" + e.getStackTrace());
                }
            }
        } catch (EOFException e) {
            System.out.println("EOF stacktrace:" + e.getStackTrace());
        } catch (IOException e) {
            System.out.println("IOException:" + e.getStackTrace());
        }
    }

    public CardImage findCardImage(String name) {
        CardImage found = null;
        for (CardImage image : this.cardImageList) {
            if (image.getCardName().equalsIgnoreCase(name)) {
                found = image;
                break;
            }
        }
        return found;
    }
}