package src;

import java.io.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

/***** ContactsManager **********
 *  */

public class ContactsManager {
    private static final String FILE_PATH = "src\\src\\data\\contacts.txt";
    private HashMap<String, Integer> contactList;
    private String curUsername;
    private static BufferedReader reader;
    private static PrintWriter writer;

    ContactsManager() {
        this.contactList = new HashMap<>();
    }



    /////// Methods ////////////////////////////////////////////////

    //===== retrieve ==================================
    /** reads contacts.txt and retrieves the passed in
     * user's contact list as a single string. Splits the string
     * into pairs of name and number and store s them together in contactList */
    //=================================================
    public void retrieve(String username) {
        this.contactList = new HashMap<>();
        String[] data = {};

        try {
            reader = new BufferedReader(new FileReader(FILE_PATH));
            boolean userFound = false;

            while (!userFound && reader.ready()) {
                String[] curLine = reader.readLine().split(";");
                if (curLine[0].equals(username)) {
                    data = curLine[1].split("~");
                    this.curUsername = curLine[0];
                    userFound = true;
                }
            }
            if (!userFound) {
                this.curUsername = username;
                reader.close();
                return;
            }

            String[] splitPair;
            for (String pair : data) {
                splitPair = pair.split("/");
                this.contactList.put(splitPair[0], new Integer(splitPair[1].trim()));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    //===== *END* retrieve *END* ==============================



    //===== displayContacts ===================================
    /** displays each contact name and number form the current
     * contactList in the console. */
    //=========================================================
    public void displayContacts() {
        for (String name : this.contactList.keySet()) {
            System.out.println(name + "  :  " + this.contactList.get(name));
        }
    }
    //===== *END* displayContacts *END* =======================



    //===== add ===============================================
    /** adds a new contact with passed in name and numebr to
     * contactList */
    //=========================================================
    public boolean add(String username, Integer number) {
        if (this.contactList.containsKey(username)) return false;
        this.contactList.put(username, number);
        return this.contactList.containsKey(username);
    }
    //===== *END* add *END* ====================================



    //===== remove ===========================================
    /** removes the passed in username from contactList */
    //========================================================
    public boolean remove(String username) {
        if (this.contactList.containsKey(username)) {
            return this.contactList.remove(username, this.contactList.get(username));
        }
        return !this.contactList.containsKey(username);
    }
    //===== *END* remove *END* ===============================



    public void save() {
        File contactsFile = new File((FILE_PATH));
        boolean saved = false;
        String fileData = "";

        try {
            reader = new BufferedReader(new FileReader(contactsFile));
            String[] lineData;

            while (reader.ready()) {
                lineData = reader.readLine().split(";");
                if (lineData[0].equals(this.curUsername)) {
                    fileData += listToString() + "\n";
                    saved = true;
                }
                else {
                    fileData += reader.readLine() + "~";
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println(e.toString());
            return;
        }

        if (!saved) {
            fileData += listToString();
        }

        try {
            writer = new PrintWriter(contactsFile);
            writer.print(fileData);
            writer.flush();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }



    private String listToString() {
        String updatedLine = this.curUsername + ";";
        for (String name : this.contactList.keySet()) {
            updatedLine += name + "/" + this.contactList.get(name).toString() + "~";
        }
        updatedLine += "n";
        updatedLine = updatedLine.replace("~n", "");

        return updatedLine;
    }
}
