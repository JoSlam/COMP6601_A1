import java.util.*;

public class TCPClientMenu {
    public String prompt;
    private String delimiter;
    private Iterable<String> cardValues;
    private Iterable<String> cardSuites;

    public TCPClientMenu(String delimiter, Iterable<String> cardValues, Iterable<String> cardSuites) {
        this.delimiter = delimiter;
        this.cardValues = cardValues;
        this.cardSuites = cardSuites;
    }

    public void showMenu() {
        Scanner scan = new Scanner(System.in);
        String input = "";
        Integer action = null;

        System.out.printf(this.prompt);
        input = scan.next();

        action = getClientAction(scan);

        switch (action) {
        case 1:

            break;
        case 2:

        default:
            break;
        }
    }

    public String getCardInput(Scanner in) {
        String cardName = "";

        System.out.println(
                "Enter a card name: (Format - suite followed by value EG. 4" + this.delimiter + "H => 4 of hearts)");
        cardName = in.next();

        while (!validateCardName(cardName)) {

        }

        return cardName;
    }

    public boolean validateCardName(String cardName) {
        boolean isValid = false;

        String[] splString = cardName.split(this.delimiter);
        String cardValue = "";
        String cardSuite = "";

        if (splString.length > 1) {
            cardValue = splString[0];
            cardSuite = splString[1];
        }
        return isValid;
    }

    public boolean validateCardValue(String value){
        return (ArrayList<String>)this.cardValues)
    }

    public boolean validateCardSuite(String suite) {

    }

    public Integer getClientAction(Scanner in) {
        Integer input = null;
        System.out.println("Please select an option (1 or 2)");
        System.out.println("1. Send card");
        System.out.println("2. Quit");

        input = in.nextInt();
        while (input < 0 || input > 2) {
            System.out.println("\nPlease select a listed option (1 or 2)");
            input = in.nextInt();
        }

        return input;
    }

}