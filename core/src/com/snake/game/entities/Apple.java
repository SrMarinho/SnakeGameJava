package com.snake.game.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.snake.game.SnakeGame;

public class Apple implements Renderizable{
    SnakeGame game;
    float x;
    float y;
    float width;
    float height;

    public Apple(SnakeGame game, float x, float y) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = this.game.cellWidth;
        this.height = this.game.cellHeight;
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
        shape.rect(this.x, this.y, this.game.cellWidth, this.game.cellHeight);
        shape.end();

        shape.begin(ShapeType.Line);
        shape.setColor((float) 20 / 255, (float) 20 / 255, (float) 20 / 255, 1);
        shape.rect(this.x, this.y, this.game.cellWidth, this.game.cellHeight);
        shape.end();
    }
}
