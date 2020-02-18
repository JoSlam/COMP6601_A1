import java.util.*;

public class TCPClientMenu {
    private String delimiter;
    private Iterable<String> cardValues;
    private Iterable<String> cardSuites;

    public TCPClientMenu(String delimiter, Iterable<String> cardValues, Iterable<String> cardSuites) {
        this.delimiter = delimiter;
        this.cardValues = cardValues;
        this.cardSuites = cardSuites;
    }

    public String getCardInput(Scanner in) {
        String cardName = "";

        System.out.println("\nFormat => suite followed by value EG. 4" + this.delimiter + "H => 4 of hearts");
        System.out.println("Submit cards one at a time.");
        System.out.println("Enter \"QUIT\" to stop.");
        System.out.println("\nEnter card name: ");
        cardName = in.next();

        while (!cardName.equalsIgnoreCase("quit") && !validateCardName(cardName)) {
            System.out.println("Invalid card name, please re-enter.");
            cardName = in.next();
        }
        
        if(!cardName.equalsIgnoreCase("quit")){
            String[] nameSplit = cardName.split(delimiter);
            cardName = nameSplit[0] + nameSplit[1];
        }
        return cardName;
    }

    public Integer getClientAction(Scanner in) {
        Integer input = null;
        System.out.println("\nPlease select an option (1 or 2)");
        System.out.println("1. Send card");
        System.out.println("2. Quit");

        input = in.nextInt();
        while (input < 0 || input > 2) {
            System.out.println("\nPlease select a listed option (1 or 2)");
            input = in.nextInt();
        }

        return input;
    }

    public boolean validateCardName(String cardName) {
        boolean isValid = false;
        String[] splString = cardName.split(this.delimiter);

        if (splString.length > 1) {
            isValid = validateCardValue(splString[0]) || validateCardSuite(splString[1]);
        }
        return isValid;
    }

    public boolean validateCardValue(String value){
        ArrayList<String> list = (ArrayList<String>)this.cardValues;
        return list.contains(value);
    }

    public boolean validateCardSuite(String suite) {
        ArrayList<String> list = (ArrayList<String>)this.cardSuites;
        return list.contains(suite);
    }

}