package com.example.gameoflife;

import com.SGS.dependency.*;
import javafx.scene.shape.Rectangle;

public class Cell extends Player implements IPlayer {



    public int x;


    public int y;


    public Cell(String name, Universe universe, int x, int y) {
        super(name, universe);
        this.setIsActive(false);
        Message msg = new Message(new int[universe.getK()]);
        for (int i = 0; i < universe.getK(); i++) {
            msg.getNumbers()[i] = 0;
        }
        setMessagesToRespond(msg);

        this.x = x;
        this.y = y;
    }

    public Rectangle getRectangle(int width, int height, int w, int h) {
        int y1 = height / h;
        int x1 = width / w;

        Rectangle r = new Rectangle(x * x1, y * y1, x1*0.9, y1*0.9);
        if(isActive()){
            r.setFill(javafx.scene.paint.Color.BLACK);
        } else {
            r.setFill(javafx.scene.paint.Color.WHITE);
            //add border
            r.setStroke(javafx.scene.paint.Color.BLACK);
        }
        return r;

    }
    public void click() {
        setIsActive(!isActive());
    }

    @Override
    public void respondToMessages() {
        super.respondToMessages();
    }

    public void sendToAll() {
        for (AcquaintanceElement acq : getAcquaintances().getAcquaintanceElements()) {
            if(acq == null) {
                continue;
            }
            Player player = acq.getPlayer();
            int playerIndex = acq.getNumber();
            int msg = isActive() ? 1 : 0;
            player.receiveMessage(msg, playerIndex);
        }
    }
    public void respondToAllAcquiantances(Message calculatedResponses){
        for (int i = 0; i < getAcquaintances().getAcquaintanceElements().length ; i++) {
            AcquaintanceElement acq = getAcquaintances().getAcquaintanceElements()[i];
            if (acq == null) {
                continue;
            }
            Player player = acq.getPlayer();
            int playerIndex = acq.getNumber();
            player.receiveMessage(calculatedResponses.getNumbers()[i], playerIndex);
        }
    }

    @Override
    public boolean checkDeath() {
        return super.checkDeath();
    }
}
