package com.jmbothe;

import processing.core.PApplet;

import java.lang.management.PlatformLoggingMXBean;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private static Game game;

    protected final Set<Target> targets;
    protected final List<Player> players;
    protected final List<Player> playersByRank;
    protected final List<Player> tiedPlayers;

    private Player currentPlayer;
    private Player winner;
    private boolean gameOver;
    private int winnerScore;
    private boolean tie;

    protected PApplet p;

    private Game(PApplet p) {
        targets = new HashSet<>();
        players = new ArrayList<>();
        playersByRank = new ArrayList<>();
        tiedPlayers = new ArrayList<>();
        gameOver = false;

        this.p = p;
    }

    public static Game get(PApplet p) {
        if (game == null) game = new Game(p);
        return game;
    }

    public void initGame(int numPlayers) {
        while (numPlayers > 0) {
            numPlayers--;
            Player player = new Player(p);
            players.add(player);
            playersByRank.add(player);
        }
        currentPlayer = players.get(0);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public int getWinnerScore() {
        return winnerScore;
    }

    public boolean getTie() {
        return tie;
    }

    public void switchPlayers() {
        if (players.indexOf(currentPlayer) == players.size() - 1) {
            handleGameOver();
        } else {
            currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
        }
    }

    public void handleGameOver() {
        playersByRank.clear();
        tiedPlayers.clear();
        gameOver = true;

        winner = players.get(0);
        winnerScore = winner.getScore();

        for (Player player : players) {
            player.setHighScore();
            if (player.getScore() > winnerScore) {
                winner = player;
                winnerScore = winner.getScore();
            }
            playersByRank.add(player);
        }
        for (Player player : players) {
            if ((player != winner) && player.getScore() == winnerScore) {
                tie = true;
                tiedPlayers.add(player);
            }
        }
        if (tie) {
            tiedPlayers.add(winner);
        } else {
            winner.addWin();
        }
        Collections.sort(playersByRank);
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void resetGame() {
        for (Player player : players) {
            player.resetScore();
        }
        gameOver = false;
        tie = false;
        currentPlayer = players.get(0);
    }

    public void addTarget() {
        targets.add(new Target(p));
    }

    public void clearTargets() {
        targets.clear();
    }

    public void manageTargets(Projectile projectile) {
        Set<Target> toRemove = new HashSet<>();
        for (Target target : targets) {
            target.updatePosition();
            if (target.isGone()) {
                toRemove.add(target);
            } if (target.isShot(projectile)) {
                toRemove.add(target);
                Game.get(p).currentPlayer.addPoints((int) target.getPoints());
            }
            target.draw();
        }
        for (Target target : toRemove) {
            targets.remove(target);
        }
    }
}
