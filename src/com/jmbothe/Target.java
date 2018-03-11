package com.jmbothe;

import processing.core.PApplet;
import java.util.Random;

public class Target extends Circle {
    private static Random random = new Random();
    private final Collision collision;

    private final float points;
    private final float diameter;

    PApplet p;

    Target(PApplet p) {
        super((float) (p.width * 0.5) + random.nextInt((int) (p.width * 0.5)), p.height, 0, -5 + random.nextInt(5), p);
        points = 0 - yVel;
        diameter = 120 - (points * 12);
        this.p = p;
        collision = new Collision(p);
    }

    public boolean isGone() {
        return y < 0 - diameter;
    }

    public boolean isShot(Projectile projectile) {
        return projectile != null && collision.lineCircle(
                x, y,
                (int) diameter,
                projectile.x, projectile.y,
                projectile.x + projectile.xVel,
                projectile.y + projectile.yVel
                );
    }

    public float getPoints() {
        return points;
    }

    public void draw() {
        p.fill(255);
        p.stroke(0);
        p.strokeWeight(3);
        p.ellipse(x, y, diameter, diameter);

        p.fill(0);
        p.textSize(32);
        p.text((int) points, x - 9, y + 9);
    }
}
