package com.jmbothe;

import processing.core.PApplet;

import java.util.*;

public class Game {
    private static Game game;

    protected Set<Target> targets;
    protected List<Player> players;
    protected List<Player> playersByRank;

    protected Player currentPlayer;
    protected Player winner;
    protected boolean gameOver;
    protected int winnerScore;

    protected PApplet p;

    private Game(PApplet p) {
        this.p = p;
        targets = new HashSet<>();
        players = new ArrayList<>();
        gameOver = false;
    }

    public static Game get(PApplet p) {
        if (game == null) game = new Game(p);
        return game;
    }

    public static Game get() {
        return game;
    }

    public void initGame(int numPlayers) {
        while (numPlayers > 0) {
            numPlayers--;
            players.add(new Player(p));
        }
        currentPlayer = players.get(0);
    }

    public Set<Target> getTargets() {
     return targets;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void switchPlayers() {
        if (players.indexOf(currentPlayer) == players.size() - 1) {
            handleGameOver();
        } else {
            currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
        }
    }

    public void handleGameOver() {
        gameOver = true;
        playersByRank = new ArrayList<>();
        winner = players.get(0);
        winnerScore = winner.score;
        for (Player player : players) {
            player.setHighScore();
            if (player.score > winner.score) {
                winner = player;
                winnerScore = winner.score;
            }
            playersByRank.add(player);
            player.resetScore();
        }
        Collections.sort(playersByRank);
    }

    public void addTarget() {
        targets.add(new Target(p));
    }


    public void manageTargets(Projectile projectile) {
        Set<Target> toRemove = new HashSet<>();
        for (Target target : targets) {
            target.updatePosition();
            if (target.isGone()) {
                toRemove.add(target);
            } if (target.isShot(projectile)) {
                toRemove.add(target);
                Game.get().currentPlayer.addPoints((int) target.points);
            }
            target.draw();
        }
        for (Target target : toRemove) {
            targets.remove(target);
        }
    }
}
