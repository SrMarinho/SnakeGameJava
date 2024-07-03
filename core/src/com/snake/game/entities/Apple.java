package com.snake.game.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.snake.game.SnakeGame;
import com.snake.game.entities.Snake;

import java.util.List;
import java.util.Random;

public class Apple implements Renderizable{
    SnakeGame game;
    int x;
    int y;
    float width;
    float height;

    public Apple(SnakeGame game) {
        this.game = game;
        this.x = 0;
        this.y = 0;
        this.width = this.game.cellWidth;
        this.height = this.game.cellHeight;
    }

    public void randPos(Snake snake) {
        Random random = new Random();
        int possibleX, possibleY;
        boolean validPosition;

        do {
            possibleX = random.nextInt(this.game.grid_col);
            possibleY = random.nextInt(this.game.grid_row);
            validPosition = true;

            for (List<Integer> part : snake.body) {
                if (part.get(0) == possibleX && part.get(1) == possibleY) {
                    validPosition = false;
                    break;
                }
            }
        } while (!validPosition);

        this.x = possibleX;
        this.y = possibleY;
    }

    @Override
    public void event() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(ShapeRenderer shape) {
        shape.begin(ShapeType.Filled);
        shape.setColor(1, 0, 0, 1);
        shape.rect(this.x * this.game.cellWidth, this.y * this.game.cellHeight, this.game.cellWidth, this.game.cellHeight);
        shape.end();
    }
}
