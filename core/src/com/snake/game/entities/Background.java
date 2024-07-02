package com.snake.game.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.snake.game.SnakeGame;

import java.util.List;

public class Background implements Renderizable{
    int x;
    int y;
    SnakeGame game;

    public Background(SnakeGame game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;
    }

    public void event(){};
    public void update(){};
    public void render(ShapeRenderer shape){
        for (int i = 0; i <= this.game.grid_col; i++) {
            shape.begin(ShapeType.Line);
            shape.setColor(0.15F, 0.15F, 0.15F, 1);
            shape.line(this.game.cellWidth * i, 0,  this.game.cellWidth * i, this.game.height);
            shape.end();
        }
        for (int i = 0; i <= this.game.grid_row; i++) {
            shape.begin(ShapeType.Line);
            shape.setColor(0.15F, 0.15F, 0.15F, 1);
            shape.line(0, this.game.cellWidth * i, this.game.width, this.game.cellHeight * i);
            shape.end();
        }
    };
}
