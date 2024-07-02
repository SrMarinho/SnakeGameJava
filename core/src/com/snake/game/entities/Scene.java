package com.snake.game.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.snake.game.entities.Renderizable;
import com.snake.game.entities.Snake;
import com.snake.game.entities.Background;
import com.snake.game.SnakeGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Scene {
    List<Renderizable> objs;
    Snake snake;
    SnakeGame game;
    Background bg;

    public Scene(SnakeGame game) {
        this.game = game;

        objs = new ArrayList<>();

        objs.add(new Background(this.game, 0, 0));

        objs.add(new Snake(game));

        objs.add(new Apple(this.game,
                    new Random().nextInt(0, this.game.grid_col) * this.game.cellWidth,
                    new Random().nextInt(0, this.game.grid_row) * this.game.cellHeight
                )
        );

    }

    public void event() {
        for (Renderizable obj : objs) {
            obj.event();
        }
    }

    public void update() {
        for (Renderizable obj : objs) {
            obj.update();
        }
    }

    public void render(ShapeRenderer shape) {
        for (Renderizable obj : objs) {
            obj.render(shape);
        }
    }
}
