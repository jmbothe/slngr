package com.jmbothe;

import processing.core.PApplet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Game {
    private static Game game;

    protected Set<Target> targets;
    protected Map<PLAYER, Player> players;

    protected enum PLAYER {PLAYER_1, PLAYER_2};
    protected PLAYER currentPlayer;
    protected Player winner;
    protected boolean gameOver;

    PApplet p;

    private Game(PApplet p) {
        this.p = p;
        targets = new HashSet<>();
        players = new HashMap<>();

        players.put(PLAYER.PLAYER_1, new Player(p));
        players.put(PLAYER.PLAYER_2, new Player(p));

        currentPlayer = PLAYER.PLAYER_1;
        gameOver = false;
    }

    public static Game get(PApplet p) {
        if (game == null) game = new Game(p);
        return game;
    }

    public Set<Target> getTargets() {
     return targets;
    }

    public Map<PLAYER, Player> getPlayers() {
        return players;
    }

    public void switchPlayers() {
        if (currentPlayer.equals(PLAYER.PLAYER_1)) {
            currentPlayer = PLAYER.PLAYER_2;
        } else {
            currentPlayer = null;
        }
    }
    public boolean handleGameOver() {
        if (currentPlayer == null) {
            gameOver = true;
            winner = players.get(PLAYER.PLAYER_1).score > players.get(PLAYER.PLAYER_2).score
                    ? players.get(PLAYER.PLAYER_1)
                    : players.get(PLAYER.PLAYER_2);
            return true;
        } else {
            return false;
        }
    }

    public void addTarget() {
        targets.add(new Target(p));
    }


    public void manageTargets(Projectile projectile) {
        Set<Target> toRemove = new HashSet<>();
        for (Target target : targets) {
            target.updatePosition();
            if (target.isGone() || target.isShot(projectile)) {
                toRemove.add(target);
            }
            target.draw();
        }
        for (Target target : toRemove) {
            targets.remove(target);
        }
    }
}
