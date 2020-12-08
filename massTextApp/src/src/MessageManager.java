package src;

public class MessageManager {
    private String msgName;
    private String message;

    MessageManager(){ }

    public void setMsgName (String msgName){
        this.msgName =  msgName;
    }

    public void setMessage (String message){
        this.message =  message;
    }

    public void display ( ){
        System.out.println(msgName +"\n" + message);
    }

    public String toString()
    {
        return (msgName +"\n" + message + "\n");
    }

}
