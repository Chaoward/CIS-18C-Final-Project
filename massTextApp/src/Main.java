package src;
//import com.twilio.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<User> users = new ArrayList();
        // Maps Username to User
        HashMap<String, Integer> userIndex = new HashMap();
        HashMap<String, Integer> passwordIndex = new HashMap();
        users.add(new User("user","password"));

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

            System.out.println(foundUser);

            System.out.println("Hello, " + foundUser.getUsername() + ".  Please enter your password:");

            String password = input.nextLine();
            if (foundUser.getPassword().equals(password)) {
                System.out.println("Access Granted");
            int choice = 3;
            String displayChoice;

            switch (choice){
                case 1: displayChoice = "Contacts";
                case 2: displayChoice = "Messages";
                case 3: displayChoice = "End program";
            }

            } else {
                System.out.println("Access Denied");
            }
        }
    }
}

