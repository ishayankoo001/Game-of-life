package com.example.gameoflife;
import com.SGS.dependency.IResponseFunction;
import com.SGS.dependency.Message;

public class GolResponseFunction implements IResponseFunction{
    @Override
    public Message getResponse(Message message) {
        int sum = 0;
        //itereate through message
        for (int m : message.getNumbers()) {
            sum += m;
        }
        Message aliveMessage = new Message(new int[message.getNumbers().length]);
        Message deadMessage = new Message(new int[message.getNumbers().length]);
        for (int i = 0; i < message.getNumbers().length; i++) {
            aliveMessage.getNumbers()[i] = 1;
            deadMessage.getNumbers()[i] = 0;
        }
        if (sum == 3) {
            return aliveMessage;
        } else if (sum == 2) {
            return message;
        } else {
            return deadMessage;
        }
    }

    @Override
    public String mapToString() {
        return null;
    }
}
