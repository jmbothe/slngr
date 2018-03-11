package com.jmbothe;

import processing.core.PApplet;

public class Round {
    protected Timer timer;
    protected ScoreView scoreView;
    protected Slingshot slingshot;
    protected Projectile projectile;
    protected boolean clicked;
    protected PApplet p;

    Round(PApplet p) {
        timer = new Timer(10, 36, "Time Left ", 0.4f, 0.1f, p);
        slingshot = new Slingshot(p);
        scoreView = new ScoreView(p);
        this.p = p;
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
            float xVel = (slingshot.x - slingshot.controlX) / 3;
            float yVel = (slingshot.restY - slingshot.controlY) / 3;

            projectile = new Projectile(
                    slingshot.controlX + (slingshot.x - slingshot.controlX),
                    slingshot.controlY + (slingshot.restY - slingshot.controlY),
                    xVel,
                    yVel,
                    p
            );
        }

    }

}
