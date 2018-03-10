package com.jmbothe;

import processing.core.PApplet;

public class Timer {
    protected int count;
    private PApplet p;

    Timer(PApplet p) {
        count = 30;
        this.p = p;
    }

    public void countDown() {
        count--;
    }

    public void draw() {
        if (count < 6) {
            p.fill(255, 100, 100);
        } else {
            p.fill(0);
        }
        p.textSize(48);
        p.text("Time: " + count, (float) (p.width * 0.45), (float) (p.height * 0.05));
    }

}
