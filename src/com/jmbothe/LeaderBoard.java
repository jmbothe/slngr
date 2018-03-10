package com.jmbothe;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LeaderBoard {
    protected List<Player> rankedPlayers;

    protected PApplet p;

    LeaderBoard(PApplet p) {
        rankedPlayers = new ArrayList<>();
        this.p = p;
    }

    public void updateRanks() {
//        Set<Player> players = Game.get(p).getPlayers();
        // get winner from prev game
        //
    }
}
