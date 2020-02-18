import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;



/*
    Name: Joshua Lambert
    ID: 815007658
    Course: COMP 6601
*/

/*
    Documentation => TCPClient

    * Client communicates via port 12009
    
    
    The TCPClient class serves the following roles:
    * Initiating connection to server
    * Prompting user for input through use of menu
    * Sending and receiving data through the socketed connection

    Specific documentation is listed throughout the class
*/



public class TCPClient {
    public static void main(String args[]) {
        Socket socket = null;
        String delimiter = "-";
        String cardValueString = "1,2,3,4,5,6,7,8,9,10,J,Q,K,A";
        String cardSuitString = "S,H,D,C";
        ArrayList<String> cardValues = new ArrayList<>(Arrays.asList(cardValueString.split(",")));
        ArrayList<String> cardSuites = new ArrayList<>(Arrays.asList(cardSuitString.split(",")));

        try {
            int serverPort = 12009;
            String hostname = "";
            Scanner scan = new Scanner(System.in);
            DataInputStream in = null;
            DataOutputStream out = null;
            String input = "";
            Integer action = null;
            TCPClientMenu menuHandler = new TCPClientMenu(delimiter, cardValues, cardSuites);


            //Prompt for target host machine
            System.out.printf("Enter hostname: ");
            hostname = scan.next();

            //Initialize socket and socket I/O streams
            socket = new Socket(hostname, serverPort);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            
            
            // Uses TCPClientMenu class to display and handle menu logic
             /*
                The logic for this menu handler need not be explained for the sake of this assignment, 
                however, in the interest of eliminating ambiguity it does the following:

                * Prompts for user input:
                    * 1 => Send card
                    * 2 => Quit connection
                * Validates selected option
                * Prompts user for card in the specified format
                * Validates card name entered
             */
            action = menuHandler.getClientAction(scan);
            if(action != 2){
                //Prompt for user input
                input = menuHandler.getCardInput(scan);
                while(!input.equalsIgnoreCase("quit")){
                    //Send input to server
                    out.writeUTF(input);

                    //Receive and print response
                    String data = in.readUTF();
                    System.out.println("Received: " + data);
                    input = menuHandler.getCardInput(scan);
                }
            }

            //Shut down connection and send final message => quit
            if(action == 2 || input.equalsIgnoreCase("quit")){
                out.writeUTF(input);
                scan.close();

                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        System.out.println("Close: " + e.getMessage().toString());
                    }
                }
            }
            
        } 
        catch (UnknownHostException e) {
            System.out.println("Socket: " + e.getMessage());
        } 
        catch (EOFException e) {
            System.out.println("EOF: " + e.getMessage().toString());
        } 
        catch (IOException e) {
            System.out.println("Readline: " + e.getMessage().toString());
        }
    }
}