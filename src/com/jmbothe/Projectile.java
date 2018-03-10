package com.jmbothe;

import processing.core.PApplet;

public class Projectile extends Circle {
    protected float diameter;

    Projectile(float x, float y, float xVel, float yVel, PApplet p) {
        super(x, y, xVel, yVel, p);
        diameter = 16;
    }

    public void draw() {
        updatePosition();
        p.stroke(0);
        p.strokeWeight(3);
        p.fill(255, 0, 0);
        p.ellipse(x, y, diameter, diameter);
    }
}
