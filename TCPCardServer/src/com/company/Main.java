package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/*
    Name: Joshua Lambert
    ID: 815007658
    Course: COMP 6601
*/


/*
    Documentation => TCPServer

    * Server accepts connections on port 12009

    The TCPCardServer performs the following roles:
    * Opens connection on port 12009 to accept requests from client machines
    * On receipt of a connection it creates a new instance of the CardServer class
        => The CardServer class extends the Thread super class to enable multi-threaded workloads

    For more information on server functionality see documentation at => CardServer.java

 */
public class Main {

    public static void main(String[] args) {
        try {
            int serverPort = 12009;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            System.out.println("Name: Joshua Lambert");
            System.out.println("ID: 815007658");
            System.out.println("Card server running on port: " + serverPort);
            System.out.println("Listening for connections...");


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
