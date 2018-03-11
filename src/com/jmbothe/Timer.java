package com.jmbothe;

import processing.core.PApplet;

public class Timer {
    protected int originalCount;
    protected int count;
    protected float textSize;
    protected String prefix;
    protected float x;
    protected float y;

    private PApplet p;

    Timer(int count, float textSize, String prefix, float x, float y, PApplet p) {
        originalCount = count;
        this. count = count;
        this.textSize = textSize;
        this.prefix = prefix;
        this.x = x;
        this.y = y;
        this.p = p;
    }

    public void countDown() {
        count--;
    }

    public void reset() {
        this.count = originalCount;
    }

    public void draw() {
        p.fill(0);
        p.textSize(textSize);
        p.text(prefix + count, p.width * x, p.height * y);
    }

}
