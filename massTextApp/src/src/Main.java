package src;
//import com.twilio.*;

//NOTE: Why HashMaps pf password it's better just to have a collection of Users only

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/***** MASS TEXT MESSAGE SENDER ******************
 * This program will take message template or a user
 * written message then take the users contacts.txt and
 * send a personalized message to each contact using
 * the selected message.
 ************************************************/

public class Main {
    private static final String USER_FILE_PATH = "src\\src\\data\\users.txt";
    private static ArrayList<User> users = new ArrayList();
    //Main Global Scanner
    static Scanner input = new Scanner(System.in);
    //For displaying correct user or password request

    public static void main(String[] args) throws IOException {
//        final String FILE_PATH = "src\\src\\data\\messages.txt";
//        try (FileWriter writer = new FileWriter("src\\src\\data\\messages.txt")) {
//        }
        MessageManager messageManager = new MessageManager();
        ContactsManager contactsManager = new ContactsManager();
        //===== getUserName ==============================
        //  THe login will be handled by an unordered
        // collection (hashmap)
        //================================================
        // Will we implement username creation?

        boolean runLoop = true;
        boolean runLoop2 = true;

        // the first do while run loop starts here for the login
        do {
            readUsers();
            //Get Username and Password from user
            String username = getUserOrPasswordI("username");
            String password = getUserOrPasswordI("password");
            int userChoice;
            // if (foundUser == null || password == null) check the username and password
            //
            User foundUser = linearUserSearch(username, password);

            if (foundUser == null || !foundUser.passwordEquals(password)) {
                System.out.println("Login attempt failed user or password are incorrect");
                //attempt counter here?
                // If the password is correct move onto the User Interface loop.
            } else {
                System.out.println("------Access Granted------\n");
                contactsManager.retrieve(foundUser.getUsername());
                messageManager.retrieve(foundUser.getUsername());

                //If user logged in read all messaged that are saved to file

                do {
                    //Display message menu and get choice
                    userChoice = menuInput(username);
                    //Examine input
                    if (userChoice == 1) {
                        //Display contact menu and get choice
                        //userChoice = menuInput(username, "contact menu");
                        contactsMenu(contactsManager, userChoice);
                    } else if (userChoice == 2) {
                        messageMenu(messageManager, userChoice);
                    } else if (userChoice == 3) {
                        messageSender(contactsManager, userChoice);
                    } else if (userChoice == 4) {
                        runLoop = false;
                        input.nextLine();
                    } else if (userChoice == 5) {
                        System.exit(0);
                    }
                } while (runLoop == true);
                contactsManager.logOut();
                messageManager.logOut();
            }

        } while (runLoop2 == true);


    }


    public static void readUsers() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(USER_FILE_PATH));
            String[] lineData;

            while (reader.ready()) {
                lineData = reader.readLine().split("~");
                users.add(new User(lineData[0], lineData[1]));
            }

            reader.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    //===== getUserOrPassword =============================================

    /**
     * Utilizes input to either display text that asks for a password or
     * username, then stores them, or displays a message explaining t
     * he argument was invalid if it was
     * Returns -> String that contains a password or username,
     * based on the argument, or an empty string
     */
    //========================================================
    public static String getUserOrPasswordI(String q) {
        //Variable to hold the username or password entered
        String temp;
        //Check contents of q
        if (q == "username") {
            //Get Username
            System.out.println("Please log in.\n" +
                    "Enter your username:");
            temp = input.nextLine();
        } else if (q == "password") {
            //Get Password
            System.out.println("Please enter your password:");
            temp = input.nextLine();
        } else {
            System.out.println("Invalid Argument");
            temp = "";
        }
        return temp;
    }
    //===== linearUserSearch =============================================

    /**
     * Uses linear search to find a username that matches that
     * which was inputted.
     * Returns -> User object with information matching the username's
     */
    //========================================================
    public static User linearUserSearch(String u, String p) {
        boolean searching = true;
        User temp = null;
        //searches through all users to match inputted values
        for (int i = 0; searching && i < users.size(); ++i) {
            if (users.get(i).getUsername().equals(u)) {
                temp = users.get(i);
                searching = false;
            }
        }
        return temp;
    }
    //===== messageMenu =============================================

    /**
     * Displays possible actions and the inputs required to access the
     * functions for multiple menus
     * <p>
     * Parameters:
     * u    = username
     * type = type of menu required
     * <p>
     * Returns -> Integer representing menu option
     */
    //========================================================
    public static int menuInput(String u) {
        //Display message action menu
        System.out.println("Hello " + u +
                ", Welcome to the Mass Text Message Sender!\n" +
                "What would you like to do? \n" +
                "1. Add/Remove/Display Contacts\n" +
                "2. Add/Remove/Display Messages\n" +
                "3. Send Message\n" +
                "4. Log out\n" +
                "5. Exit Program");

        //Return input
        return input.nextInt();
    }
    //===== contactMenu =============================================

    /**
     * Displays possible actions and the inputs required to access the
     * functions for contactsMenu
     * Parameters:
     * ContactsManager contactsManager
     * int userChoice
     */
    //================================================================
    public static void contactsMenu(ContactsManager contactsManager, int userChoice) {
        System.out.println("1. Add Contact\n2. Remove Contact\n3. Display all Contacts\n4. Return to Main Menu");
        userChoice = input.nextInt();
        if (userChoice == 1) {
            System.out.println("Enter name:\n");
            input.nextLine();
            String name = input.nextLine();
            System.out.println("Enter phone number:\n");
            String number = new String(input.nextLine());
            contactsManager.add(name, number);
        } else if (userChoice == 2) {
            contactsManager.displayContacts();
            System.out.println("Enter name of contact to be removed");
            input.nextLine();
            String name = input.nextLine();
            contactsManager.remove(name);

        } else if (userChoice == 3) {
            contactsManager.displayContacts();
            System.out.println("Return to main ");

        } else {
            System.out.println("Invalid option");
        }
    }
    //===== messageMenu =============================================

    /**
     * Displays possible actions and the inputs required to access the
     * functions for MessageMenu
     * Parameters:
     * MessageManager messageManager
     * int userChoice
     */
    //================================================================
    private static void messageMenu(MessageManager messageManager, int userChoice) {


        System.out.println("1. Add message\n2. Remove message\n3. Display all message\n 4. Return to Main Menu");
        userChoice = input.nextInt();
        if (userChoice == 1) {
            if (userChoice == 1) {
                String userInput;
                input.nextLine();
                System.out.println("Message Name:\n");
                //get user input
                System.out.println("Enter your message here and place an \"`\" for the name placement.\n");
                //get user msg
            }


        } else if (userChoice == 2) {
            // delete a message from the messageManager
            System.out.println("What message would you like to delete?\n");


        } else if (userChoice == 3) {
            //display all the current messages
            System.out.println("Here is your saved messages.\n");

        } else if (userChoice == 4) {
            return;
        } else {
            System.out.println("Invalid option");
            return;
        }

    }

    private static void messageSender(ContactsManager contactsManager, int userChoice) {
        if (userChoice == 3) {
            //run the message builder
        }
    }
}


