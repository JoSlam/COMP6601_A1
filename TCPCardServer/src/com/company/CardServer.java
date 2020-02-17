package com.company;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

//card server logic
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
            this.cardImageList = loader.getImagesFromDir(this.cardDirectory);

            this.clientSocket = clientSocket;
            inStream = new DataInputStream(this.clientSocket.getInputStream());
            outStream = new DataOutputStream(this.clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Stacktrace:" + e.getStackTrace());
        }
    }

    //on receipt of connection
    public void run() {
        try {

            //taking data in the form of "suite value" EG. "4H"
            ArrayList<CardInfo> cardInfoList = new ArrayList<>();
            String data = inStream.readUTF();

            while (!data.equals("") && !data.equalsIgnoreCase("quit")) {
                String cardName = data;
                CardInfo toPrint = null;

                //if found increment count
                for (CardInfo info : cardInfoList) {
                    if (info.getCardName().equalsIgnoreCase(cardName)) {
                        info.incrementCardCount();
                        toPrint = info;
                        break;
                    }
                }

                //if not found then find and create new
                if (toPrint == null) {
                    //create card info
                    CardImage foundImage = findCardImage(cardName);

                    if (foundImage != null) {
                        CardInfo cardInfo = new CardInfo(cardName, 1);
                        cardInfo.setCardImage(foundImage);
                        toPrint = cardInfo;
                        cardInfoList.add(cardInfo);
                    }
                }

                //print image
                toPrint.formatMessage("Card count: ");
                this.imageHandler.addImageToFrame(toPrint);
                this.imageHandler.displayFrame(true);

                outStream.writeUTF("Card processed: " + toPrint.getCardName() + "\t| Count: " + toPrint.getCardCount());
                System.out.println("Card processed: " + toPrint.getCardName() + "\t| Count: " + toPrint.getCardCount());

                //get next request
                data = inStream.readUTF();
            }

            if (data.equalsIgnoreCase("quit")) {
                System.out.println("\n\nQUIT ISSUED: The following cards were processed:");
                cardInfoList.forEach(info -> {
                    info.formatMessage("Card count: ");
                    System.out.println("Card processed: " + info.getCardName() + "\t|Count: " + info.getCardCount());
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