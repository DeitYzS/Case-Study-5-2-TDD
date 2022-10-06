package se233.chapter5_tdd.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import se233.chapter5_tdd.model.Direction;
import se233.chapter5_tdd.model.Food;
import se233.chapter5_tdd.model.Snake;
import se233.chapter5_tdd.view.Platform;
import se233.chapter5_tdd.view.Score;

import java.util.ArrayList;

public class GameLoop implements Runnable {
    private Platform platform;
    private Snake snake;
    private Food food;
    private float interval = 1000.0f / 10;
    private boolean running;

    public GameLoop(Platform platform, Snake snake, Food food) {
        this.snake = snake;
        this.platform = platform;
        this.food = food;
        running = true;
    }
    private void update() {
        KeyCode cur_key = platform.getKey();
        Direction cur_direction = snake.getCurrentDirection();
        if (cur_key == KeyCode.UP && cur_direction != Direction.DOWN) {
            snake.setCurrentDirection(Direction.UP);
        } else if (cur_key == KeyCode.DOWN && cur_direction != Direction.UP) {
            snake.setCurrentDirection(Direction.DOWN);
        } else if (cur_key == KeyCode.LEFT && cur_direction != Direction.RIGHT) {
            snake.setCurrentDirection(Direction.LEFT);
        } else if (cur_key == KeyCode.RIGHT && cur_direction != Direction.LEFT) {
            snake.setCurrentDirection(Direction.RIGHT);
        }
        snake.update();
    }

    private void checkCollision() {
        if (snake.isCollidingWith(food)) {
            snake.grow();
            food.respawn();
            snake.setScore(snake.getScore() + food.getScore());

        }
        if (snake.isDead()) {
            javafx.application.Platform.runLater(new Runnable() {
                @Override
                public void run() {
                  Alert alert = new Alert(Alert.AlertType.NONE);
                  alert.setTitle("Snake Game");
                  alert.setContentText("GameOver!!!");
                  alert.getButtonTypes().clear();
                  alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

                  alert.showAndWait();
                  System.exit(0);
                }
            });
            running = false;
        }
    }

    private void redraw() {
        platform.render(snake, food);
    }

    private void updateScore(ArrayList<Score> scoreList) {
        javafx.application.Platform.runLater(() -> {
            for (int i=0; i<scoreList.size(); i++) {
                scoreList.get(i).setPoint(this.snake.getScore());
            }
        });
    }

    @Override
    public void run() {
        while (running) {
            update();
            updateScore(platform.getScoreList());
            checkCollision();
            redraw();
            try {
                Thread.sleep((long) interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public Platform getPlatform() {
        return platform;
    }
    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }
}
