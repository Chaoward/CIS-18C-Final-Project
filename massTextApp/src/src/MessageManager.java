package src;

import java.io.*;
import java.util.ArrayList;

public class MessageManager {
    private static final String FOLDER_PATH = "src\\src\\data\\messages\\";
    private ArrayList<Messages> messageList;
    private String curUsername;
    private static BufferedReader reader;
    private static PrintWriter writer;

    MessageManager() {
        this.messageList = new ArrayList<>(5);
        this.curUsername = "";
    }


    //===== retrieve ==================================
    /**  */
    //=================================================
    public void retrieve(String username) {
        //logout function here

        try {
            reader = new BufferedReader(new FileReader(FOLDER_PATH + username + ".txt"));
            this.curUsername = username;

            while (reader.ready()) {
                String[] lineData = reader.readLine().split("~");
                this.messageList.add(new Messages(lineData[1] , lineData[0]));
            }

            reader.close();
        } catch (IOException e) { //if the file is not found create a new file
            try {
                writer = new PrintWriter(FOLDER_PATH + username + ".txt");
                writer.print("");
                writer.flush();
            } catch (IOException e2) {
                System.out.println(e2.toString());
            }
        }
    }
    //===== *END* retrieve *END* ==============================



    public void displayMessage() {
        System.out.println("Index\t:\tCategory\t:\tMessage\n");
        for (Messages msg : this.messageList) {
            System.out.println(this.messageList.indexOf(msg) + "\t:\t"
                    + msg.getTitle() + "\t:\t"
                    + msg.getContent());
        }
    }


    //===== add ===============================================
    /** adds a  */
    //=========================================================
    public boolean add(String message, String title) {
        Messages newMsg = new Messages(message, title);
        this.messageList.add(newMsg);
        return this.messageList.contains(newMsg);
    }
    //===== *END* add *END* ====================================



    //===== remove ===========================================
    /** removes the passed in username from contactList */
    //========================================================
    public boolean remove(int index) {
        this.messageList.remove(index);
        return this.messageList.get(index) == null;
    }
    //===== *END* remove *END* ===============================



    //===== save =============================================
    /** updates save file */
    //========================================================
    public void save() {
        File msgFile = new File((FOLDER_PATH + this.curUsername + ".txt"));

        //write updated data to the file
        try {
            writer = new PrintWriter(msgFile);
            //writes each array item as each line in file
            for (Messages msg: this.messageList) {
                writer.println(msg.getTitle() + "~" + msg.getContent());
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }
    //===== *END* save *END* =================================



    //===== logOut ===========================================
    /** saves and empties the current messageList and userName. */
    //========================================================
    public void logOut() {
        if (this.curUsername == "") return;
        save();
        this.messageList = new ArrayList<>(5);
        this.curUsername = "";
    }
    //===== *END* logOut *END* ===============================

}
