package com.jmbothe;

import processing.core.PApplet;

public class Timer {
    private final int originalCount;
    private int count;
    private float textSize;
    private String prefix;
    private float x;
    private float y;

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

    public int getCount() {
        return count;
    }

    public void draw() {
        if (count >= 0) {
            p.fill(0);
            p.textSize(textSize);
            p.text(prefix + count, p.width * x, p.height * y);
        }
    }

}
