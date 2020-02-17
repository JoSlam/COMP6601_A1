package com.NetworkEntities;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class TCPClient {
    public static void main(String args[]) {

        Socket s = null;
        try {
            int serverPort = 12009;
            String hostname = "";
            Scanner scan = new Scanner(System.in);
            DataInputStream in = null;
            DataOutputStream out = null;

            System.out.printf("Enter hostname: ");
            hostname = scan.next();

            s = new Socket(hostname, serverPort);
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());

            System.out.printf("Enter card option: ");
            String userInput = scan.next();

            while (!userInput.equals("") && !userInput.equalsIgnoreCase("quit")) {
                out.writeUTF(userInput);
                String data = in.readUTF();
                System.out.println("Received: " + data);

                System.out.printf("\nEnter card option: ");
                userInput = scan.next();
            }

            if (userInput.equalsIgnoreCase("quit")) {
                out.writeUTF(userInput);
                scan.close();

                if (s != null) {
                    try {
                        s.close();
                    } catch (IOException e) {
                        System.out.println("Close: " + e.getMessage().toString());
                    }
                }
            }

        } catch (UnknownHostException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF: " + e.getMessage().toString());
        } catch (IOException e) {
            System.out.println("Readline: " + e.getMessage().toString());
        } finally {

        }
    }
}
