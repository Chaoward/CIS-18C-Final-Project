package src;

import java.nio.Buffer;
import java.util.HashMap;
import java.io.*;

public class ContactsManager {
        private static final String FILE_PATH = "./data/contacts.txt";
        private HashMap<String, Integer> contactList;

        ContactsManager() {
            this.contactList = new HashMap<>();

            String[] data;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));

                while (reader.ready()) {
                    data = reader.readLine().split("~");
                    this.contactList.put(data[0], new Integer(data[1]));
                }
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }



        //===== Methods ====================
        public void displayContacts() {
            for (String name : this.contactList.keySet()) {
                System.out.println(name + "  :  " + this.contactList.get(name));
            }
        }
}
