package com.jmbothe;

import processing.core.PApplet;

public class Slingshot {
    private float controlX;
    private float controlY;
    protected final float x;
    protected final float startY;
    protected final float endY;
    protected final float restY;

    PApplet p;

    Slingshot(PApplet p) {
        x = p.width * 0.15f;
        startY = p.height * 0.4f;
        endY = p.height * 0.6f;
        restY = startY + ( (endY - startY) / 2 );
        controlX = x;
        controlY = restY;

        this.p = p;
    }

    public void updateControlPoints(float controlX, float controlY) {
        this.controlX = controlX;
        this.controlY = controlY;
    }

    public void rebound() {
        if (controlX != x) {
            controlX += (x - controlX) / 1.5;
        }
        if (controlY != restY) {
            controlY += (restY - controlY) / 1.5;
        }
    }

    public float getControlX() {
        return controlX;
    }

    public float getControlY() {
        return controlY;
    }

    public void draw() {
        if (p.mousePressed) updateControlPoints(p.mouseX, p.mouseY);

        p.noFill();
        p.strokeWeight(4);
        p.beginShape();
        p.vertex(x, startY);
        p.quadraticVertex(controlX, controlY, x, endY);
        p.endShape();
    }
}
