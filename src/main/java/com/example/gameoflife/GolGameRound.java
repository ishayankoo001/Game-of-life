package com.example.gameoflife;

import com.SGS.dependency.Message;
import com.example.gameoflife.Cell;

public class GolGameRound {
    public Cell[][] players;

    public GolGameRound(Cell[][] players) {
        this.players = players;
    }

    public void calculateDeadPlayers() {
        for (Cell[] playerarr : players) {
            for (Cell player : playerarr) {
                if (player.isActive() && player.checkDeath()) {
                    player.setIsActive(false);
                }
            }
        }
    }

    public void Respond() {
        for (Cell[] playerarr : players) {
            for (Cell player : playerarr) {
                if (player.isActive()) {
                    Message response = player.getResponseFunction().getResponse(player.getMessagesToRespond());
                    player.respondToAllAcquiantances(response);
                }
            }
        }
        //at this point all newMessagesInbox are filled with the responses
    }

}
