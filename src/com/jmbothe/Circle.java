package com.jmbothe;

import processing.core.PApplet;

public class Circle {
    protected float x;
    protected float y;
    protected final float xVel;
    protected final float yVel;

    PApplet p;

    Circle(float x, float y, float xVel, float yVel, PApplet p) {
        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;
        this.p = p;
    }

    public void updatePosition() {
        x += xVel;
        y += yVel;
    }
}
