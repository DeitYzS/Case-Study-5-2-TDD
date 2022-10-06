package se233.chapter5_tdd.model;

import javafx.geometry.Point2D;
import se233.chapter5_tdd.view.Platform;
import java.util.Random;

public class Food {
    private Point2D position;
    private Random rn;
    private int score = 0;
    private boolean isSpecial;

    public Food(Point2D position) {
        this.rn = new Random();
        this.position = position;
    }
    public Food() {
        this.rn = new Random();
        respawn();
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSpecial(boolean special) {
        isSpecial = special;
    }

    public void respawn() {
        Point2D prev_position = this.position;

        do {
            int random = rn.nextInt(5);
            if(random == 3) {
                this.isSpecial = true;
            } else{
                this.isSpecial = false;
            }
            this.position = new Point2D(rn.nextInt(Platform.WIDTH), rn.nextInt(Platform.HEIGHT));
        } while(prev_position == this.position);
    }

    public int getScore() {
        return score;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public Point2D getPosition() { return position;}
}
