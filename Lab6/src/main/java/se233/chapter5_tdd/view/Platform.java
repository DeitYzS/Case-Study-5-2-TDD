package se233.chapter5_tdd.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import se233.chapter5_tdd.model.Food;
import se233.chapter5_tdd.model.Snake;

import java.awt.*;

public class Platform extends Pane {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public static final int TITLE_SIZE = 10;
    private Canvas canvas;
    private KeyCode key;

    public Platform() {
        this.setHeight(TITLE_SIZE * HEIGHT);
        this.setWidth(TITLE_SIZE * WIDTH);
        canvas = new Canvas(TITLE_SIZE * WIDTH, TITLE_SIZE * HEIGHT);
        this.getChildren().add(canvas);
    }

    public void render(Snake snake, Food food) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,WIDTH*TITLE_SIZE,HEIGHT*TITLE_SIZE);
        gc.setFill(Color.BLUE);
        snake.getBody().forEach(p ->{
            gc.fillRect(p.getX() * TITLE_SIZE, food.getPosition().getY()*TITLE_SIZE, TITLE_SIZE, TITLE_SIZE);
        });
    }

    public KeyCode getKey() {return key;}
    public void setKey(KeyCode key) {this.key = key;}
}
