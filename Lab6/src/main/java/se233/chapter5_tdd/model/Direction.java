package se233.chapter5_tdd.model;

import javafx.geometry.Point2D;
//import java.awt.geom.Point2D;

public enum Direction {
    RIGHT(new Point2D(1,0)),
    LEFT(new Point2D(-1, 0)),
    DOWN(new Point2D(0,-1)),
    UP(new Point2D(0, 1));
    public final Point2D current;
    Direction(Point2D current) {this.current = current;}
}
