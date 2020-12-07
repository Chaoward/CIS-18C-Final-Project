import String;

public class Messages {
    private String message;
    private String title;
    private String name;

    //Constructor, sets title, message, and name to be used in one step
    Messages (String m, String t, String n) {
        this.message = m;
        this.title = t;
        this.name = n;
        //Change the place holder into the name
        this.nameInput();
    }

    //Only requires the message to be input
    Messages (String m){
        this.message = m;
        this.title = "";
        this.name = "";
    }
    //Empty to allow instantiation without needing to initialized
    Messages (){
        this.message = "";
        this.title = "";
        this.name = "";
    }

    //set message that will be used
    setMessage(String m) {
        this.message = m;
        //Check if the name also exists and set the name in the message
        if (itemExists(this.name)) {
            this.nameInput();
        }
    }

    //set name to be used in the message
    setName(String n){
        this.name = n;
        //Check if the message also exists and set the name in the message
        if(itemExists(this.message)){
            this.nameInput();
        }
    }

    //Set title for the message
    setTitle(String t){
        this.title = t;
    }

    //Check if the name, title, or message has been input
    private boolean itemExists(String item){
        //Set boolean variable, initialized
        boolean exists = false;
        //Validate existence
        if(item == ""){
            exists = false;
        }
        return exists;
    }

    //Change the dollar sign place holder for the name inputted
    private void nameInput() {
        if(itemExists(this.message) && itemExists(this.name)) {
            this.message.replace('$', this.name);
        }
    }

    //Erases all data from the object, sets to NULL
    void erase () {
        this.message = "";
        this.title = "";
        this.name = "";
    }
}