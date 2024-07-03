package com.snake.game.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Gdx;
import com.snake.game.SnakeGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scene {
    List<Renderizable> objs;
    Snake snake;
    SnakeGame game;
    Background bg;
    Apple apple;

    public Scene(SnakeGame game) {
        this.game = game;

        objs = new ArrayList<>();

        Snake snake = new Snake(this.game);
        objs.add(snake);

        this.apple = new Apple(this.game);
        this.apple.randPos(snake);
        objs.add(apple);

        objs.add(new Background(this.game, 0, 0));
    }

    public void event() {
        for (Renderizable obj : objs) {
            obj.event();
        }
    }

    public void update() {
        Gdx.graphics.setTitle("Score: " + this.game.score);
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
