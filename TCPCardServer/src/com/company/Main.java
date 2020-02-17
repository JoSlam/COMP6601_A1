package com.company;

import com.tests.ImageLoaderTests;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
//        ImageLoaderTests loader = new ImageLoaderTests("resources\\JPEG");
//        loader.tryDisplayImages();
        try {
            int serverPort = 12009;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            System.out.println("Name: Joshua Lambert");
            System.out.println("ID: 815007658");

            //listen for connections
            while (true) {
                //create new cardServer thread on client message
                Socket clientSocket = listenSocket.accept();
                CardServer cardServer = new CardServer(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }

    }
}
