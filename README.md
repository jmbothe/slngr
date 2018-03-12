# slngr

This is a simple, fun non-violent shooter game where 2 to 5 friends can compete to see who can pop the most balloons with a slingshoot in 30 seconds.

This game was built with [Oracle's Java 8.0 SE Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and the [Processing 3.0](https://processing.org/) sketching library.

## Install Instructions

To install, edit, and/or play, [clone this repo](https://help.github.com/articles/cloning-a-repository/) to your machine in a directory of your choice. (more instructions to come once I actually figure out how to do this outside of IntelliJ, i.e., from the command line)

## Game Tour

### Basic gameplay.

![Round View](/img/round_view.gif)

### Demo of projectile velocity as a function of slingshot control point.

![Velocity View](/img/velocity_view.gif)

### Menu and Transition Screens

![Menu View](/img/menu_view.jpg)

![Win View](/img/win_view.jpg)

![Tie View](/img/tie_view.jpg)

## Mock Ups

![Mock 1](/img/mocks1.jpg)

![Mock 2](/img/mocks2.jpg)

## Code Highlights

### Using Processing's Framerate as a Clock

```
// In Main class

public void setup() {
    frameRate(30);
    background(102);
}

//... elsewhere, when a round of play is rendered

if (p.frameCount % 30 == 0) {
    game.addTarget();
    timer.countDown();
}
```

### Projectile Velocity and Slingshot Curve

```
// Drawing a quadratic bezier curve in Slingshot class

p.vertex(x, startY);
p.quadraticVertex(controlX, controlY, x, endY);

//elsewhere, when drawing main game play, setting projectile velocity on mouse release

if (mouseReleased) {

    // Projectile X and Y velocity is inverse of Slingshot control points' distance from rest points

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
```
## Reflection, Challenges, and Unfinished Work

This is the first Java project I have ever worked on, and also the first non-Javascript project I have ever worked on. It was at different times both a joy and a nightmare, and I am ultimately quite pleased with the underlying logic of the game. It goes without saying that the UX and UI need A LOT of work. I'd love to learn more about Processing and take a crack at making this game not only fun, but also beautiful.

As far as challenges go, I'd say getting the projectile/target collision detection down was pretty difficult. I understood conceptually that becuase of the low framerate of the paint cycle and the high velocity of the projectile, that I would need to implement a circle/line-segment type of collision detection, but I had no idea from the outset how challenging that would be. Luckily for me, the wheel had already been invented, and I was able to borrow from [a simple and wonderful collision detection repo](https://github.com/jeffThompson/CollisionDetectionFunctionsForProcessing/).

Aside from that, reasoning about the relationship between Java classes, and figuring out best practices about making references to objects within and external to those classes, is all still a part of my learning process. I definitely learned a lot about modularizing my Java code, but I feel I also ran into some unresolvable conflicts that are probably the result of making some bad decisions at the outset. For example, the game state is kept in a singleton Game.get(). Passing that singleton around my app felt awkward and wrong, but I couldn't really reason myself out of it. That is a choice I will continue to think about and perhaps re-write.

As far as unfinished work is concerned, I feel like there are certainly some optimizations to be made. For one, the collision detection is unwieldy and processor-intensive. Also, I'd like to have more options for the users like length of round, number of rounds, etc. And as mentioned ealier, the view needs a lot of work. I'm looking forward to continuing to work on this fun little game.