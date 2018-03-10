package com.jmbothe;

import processing.core.PApplet;

public class Collision {
    PApplet p;

    Collision(PApplet p) {
        this.p = p;
    }


    // Code lovingly lifted from Jeff Thompson's Collision Detection Library
    // https://github.com/jeffThompson/CollisionDetectionFunctionsForProcessing/
    public boolean linePoint(float x1, float y1, float x2, float y2, float px, float py) {

        // get distance from the point to the two ends of the line
        float d1 = p.dist(px, py, x1, y1);
        float d2 = p.dist(px, py, x2, y2);

        // get the length of the line
        float lineLen = p.dist(x1, y1, x2, y2);

        // since floats are so minutely accurate, add
        // a little buffer zone that will give collision
        float buffer = 0.05f;    // higher # = less accurate

        // if the two distances are equal to the line's
        // length, the point is on the line!
        // note we use the buffer here to give a range,
        // rather than one #
        if (d1 + d2 >= lineLen - buffer && d1 + d2 <= lineLen + buffer) {
            return true;
        }
        return false;
    }

    // POINT/CIRCLE
    public boolean pointCircle(float px, float py, float cx, float cy, float r) {

        // get distance between the point and circle's center
        // using the Pythagorean Theorem
        float distX = px - cx;
        float distY = py - cy;
        float distance = (float) Math.sqrt( (distX * distX) + (distY * distY) );

        // if the distance is less than the circle's
        // radius the point is inside!
        if (distance <= r) {
            return true;
        }
        return false;
    }

    public boolean lineCircle(float cx, float cy, int d, float lx1, float ly1, float lx2, float ly2) {

        // is either end INSIDE the circle?
        // if so, return true immediately
        boolean inside1 = pointCircle(lx1, ly1, cx, cy, d / 2);
        boolean inside2 = pointCircle(lx2, ly2, cx, cy, d / 2);
        if (inside1 || inside2) return true;

        // first get the length of the line using the Pythagorean theorem
        float distX = lx1 - lx2;
        float distY = ly1 - ly2;
        float lineLength = (float) Math.sqrt((distX * distX) + (distY * distY));

        // then solve for r (dot product of line and circle)
        float r = (float) ((((cx - lx1) * (lx2 - lx1)) + ((cy - ly1) * (ly2 - ly1))) / Math.pow(lineLength, 2));

        // get x,y points of the closest point
        float closestX = lx1 + r * (lx2 - lx1);
        float closestY = ly1 + r * (ly2 - ly1);

        boolean onSegment = linePoint(lx1, ly1, lx2, ly2, closestX, closestY);
        if (!onSegment) return false;

        // to get the length of the line, use the Pythagorean theorem again
        float distToPointX = closestX - cx;
        float distToPointY = closestY - cy;
        float distToPoint = (float) Math.sqrt(Math.pow(distToPointX, 2) + Math.pow(distToPointY, 2));

        // if that distance is less than the radius of the ball: collision
        if (distToPoint <= d / 2) return true;
        return false;
    }

}
