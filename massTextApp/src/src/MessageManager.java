package src;

public class MessageManager {
    private int id;
    private String message;

    MessageManager(){ }

    public void setId (int id){
        this.id =  id;
    }

    public void setMessage (String message){
        this.message =  message;
    }

    public void display ( ){
        System.out.println(id +". " + message);
    }

}
