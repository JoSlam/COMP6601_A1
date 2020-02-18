import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

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

            System.out.printf("Enter hostname: ");
            hostname = scan.next();

            socket = new Socket(hostname, serverPort);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            
        
            action = menuHandler.getClientAction(scan);
            if(action != 2){
                input = menuHandler.getCardInput(scan);
                while(!input.equalsIgnoreCase("quit")){
                    out.writeUTF(input);
                    String data = in.readUTF();
                    System.out.println("Received: " + data);
                    input = menuHandler.getCardInput(scan);
                }
            }

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