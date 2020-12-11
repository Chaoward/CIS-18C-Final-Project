package src;

import java.util.ArrayList;

public class Stack {
    // Collection to store data
    private ArrayList<String> data;
    private Integer top;
    public Stack(){
        data =  new ArrayList();
        top = 0;

    }
    // Push method (push items onto stack)
    public void push(String itemToPush){
        if (data.size() <= top){
            data.add(itemToPush);
        }else{
            data.set(top, itemToPush);
        }
        top++;
// notes
    }
    // Pop method (pop items off stack and returns them)
    public String pop(){
        top--;
        return data.get(top);
    }

    // Top method (peeks at top item in stack)
    public String top() throws Exception {
        if (top > 0) {
            return data.get(top - 1);
        }else{
            throw new Exception("Stack is empty!");
        }
    }
    public boolean empty() {
        return top == 0;
    }
}