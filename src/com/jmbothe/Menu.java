package com.jmbothe;

import processing.core.PApplet;

public class Menu {
    String description;
    PApplet p;

    Menu(PApplet p) {
        description = "each player has 30 seconds to use the slingshot to pop as many balloons as possible. The player with the highest score wins.";
        this.p = p;
    }

    public void draw() {
        p.fill(0);

        p.textAlign(p.CENTER);

        p.textSize(60);
        p.text(description, 0, (float) (p.height * 0.4), p.width, (float) (p.height * 0.5));

        p.textSize(300);
        p.text("slngr", (float) (p.width * 0.5), (float) (p.height * 0.25));

        if (p.mouseX > p.width * 0.3 && p.mouseX < p.width * 0.6 && p.mouseY > p.height * 0.7) {
            p.fill(255, 100, 100);
        }
        p.textSize(148);
        p.text("play!",  (float) (p.width * 0.5), (float) (p.height * 0.8));
    }

}
