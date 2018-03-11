package com.jmbothe;

import processing.core.PApplet;

public class Round {
    private Timer timer;
    private final ScoreView scoreView;
    private final Slingshot slingshot;
    private Projectile projectile;
    private boolean clicked;
    private PApplet p;

    Round(PApplet p) {
        timer = new Timer(5, 36, "Time Left ", 0.1f, p);
        slingshot = new Slingshot(p);
        scoreView = new ScoreView(p);
        this.p = p;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setClicked() {
        clicked = true;
    }

    public void draw() {
        // Add new Target every 60 frames (i.e., every second)
        if (p.frameCount % 60 == 0) {
            Game.get(p).addTarget();
            timer.countDown();
        }

        //Draw slingshot and projectile
        if (!p.mousePressed) slingshot.rebound();
        slingshot.draw();
        if (projectile != null) projectile.draw();

        // Update position, draw, and remove Targets as necessary
        Game.get(p).manageTargets(projectile);

        timer.draw();
        scoreView.draw();

        if (clicked) {
            clicked = false;
            float xVel = (slingshot.x - slingshot.getControlX()) / 3;
            float yVel = (slingshot.restY - slingshot.getControlY()) / 3;

            projectile = new Projectile(
                    slingshot.getControlX() + (slingshot.x - slingshot.getControlX()),
                    slingshot.getControlY() + (slingshot.restY - slingshot.getControlY()),
                    xVel,
                    yVel,
                    p
            );
        }

    }

}
