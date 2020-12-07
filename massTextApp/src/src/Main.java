package src;
//import com.twilio.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ContactsManager contactsManager =  new ContactsManager();
        Scanner input = new Scanner(System.in);
        ArrayList<User> users = new ArrayList();
        // Maps Username to User
        HashMap<String, Integer> userIndex = new HashMap();
        HashMap<String, Integer> passwordIndex = new HashMap();
        users.add(new User("user", "password"));

        // Create index at the end

        for (int i = 0; i < users.size(); i++) {
            userIndex.put(users.get(i).getUsername(), i);
            passwordIndex.put(users.get(i).getPassword(), i);
        }

        System.out.println("Please log in.\nEnter your username:");
        String username = input.nextLine();
        // if (foundUser == null) {
        if (!(userIndex.containsKey(username))) {
            System.out.println("Username not found");
            return;
        } else {
            // Check the password
            User foundUser = users.get(userIndex.get(username));

            //System.out.println(foundUser);

            System.out.println("Please enter your password:");

            String password = input.nextLine();
            if (foundUser.getPassword().equals(password)) {
                System.out.println("Access Granted");
                int userChoice = 0;

                System.out.println("Hello " + username +
                        " What would you like to do? \n" +
                        "1. Add/Remove/Display Contacts\n" +
                        "2. Add/Remove/Display Messages\n" +
                        "3. Send Message");
                if (userChoice == 1) {
                    System.out.println("1. Add contact\n2. Remove contact\n3. Display all contacts\n");
                    if (userChoice == 1) {
                        Scanner myObj = new Scanner(System.in);
                        System.out.println("Enter name:\n");
                        String name = myObj.next();
                        System.out.println("Enter phone number:\n");
                        Integer number = myObj.nextInt();
                        contactsManager.add(name, number);

                    } else if (userChoice == 2) {
                        System.out.println("Enter name of contact to be removed");
                        Scanner myObj = new Scanner(System.in);
                        String name = myObj.next();
                        contactsManager.remove(name);

                    } else if (userChoice == 3) {
                        contactsManager.displayContacts();

                    } else {
                        System.out.println("Invalid option");
                        return;
                    }

                } else if (userChoice == 2) {
                    if (userChoice == 1) {
                        System.out.println("1. Add message\n2. Remove message\n3. Display all message\n");
                        if (userChoice == 1) {

                        } else if (userChoice == 2) {

                        } else if (userChoice == 3) {

                        } else {
                            System.out.println("Invalid option");
                            return;
                        }


                    } else if (userChoice == 3) {

                    }

                } else {
                    System.out.println("Access Denied");
                }
            }
        }
    }
}

