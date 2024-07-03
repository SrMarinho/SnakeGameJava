package com.snake.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import com.snake.game.SnakeGame;
import com.snake.game.entities.Renderizable;

import java.util.*;

public class Snake implements Renderizable  {
    SnakeGame game;
    List<List<Float>> body;
    String direction;
    Map<String, List<Integer>> directionMap;

    public Snake(SnakeGame game) {
        this.game = game;
        direction = "right";
        directionMap = new HashMap<String, List<Integer>>();

        directionMap.put("up", Arrays.asList(0, 1));
        directionMap.put("down", Arrays.asList(0, -1));
        directionMap.put("left", Arrays.asList(-1, 0));
        directionMap.put("right", Arrays.asList(1, 0));

        body = new ArrayList<>();
        body.add(Arrays.asList(
                this.game.cellWidth * (this.game.grid_col / 2),
                this.game.cellHeight * (this.game.grid_col / 2)
                )
        );
        body.add(Arrays.asList(
                this.game.cellWidth * (this.game.grid_col / 2) - this.game.cellWidth,
                this.game.cellHeight * (this.game.grid_col / 2)
                )
        );
        body.add(Arrays.asList(
                this.game.cellWidth * (this.game.grid_col / 2) - this.game.cellWidth * 2,
                this.game.cellHeight * (this.game.grid_col / 2)
                )
        );
    }

    @Override
    public void event() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.UP:
                        if (!Objects.equals(direction, "down")) {
                            direction = "up";
                        }
                        break;
                    case Input.Keys.DOWN:
                        if (!Objects.equals(direction, "up")) {
                            direction = "down";
                        }
                        break;
                    case Input.Keys.LEFT:
                        if (!Objects.equals(direction, "right")) {
                            direction = "left";
                        }
                        break;
                    case Input.Keys.RIGHT:
                        if (!Objects.equals(direction, "left")) {
                            direction = "right";
                        }
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void update() {
        boolean colisionSnakeAndApple =  this.game.collision(
                (float)this.body.get(0).get(0),
                (float)this.body.get(0).get(1),
                this.game.cellWidth,
                this.game.cellHeight,
                this.game.scene.apple.x,
                this.game.scene.apple.y,
                this.game.cellWidth,
                this.game.cellHeight
        );

        if (colisionSnakeAndApple) {
            this.game.score += 1;
            this.game.scene.apple.x = new Random().nextInt(0, this.game.grid_col) * this.game.cellWidth;
            this.game.scene.apple.y = new Random().nextInt(0, this.game.grid_row) * this.game.cellHeight;
            System.out.println(this.game.score);
            List<Float> lastPart = body.getLast();
            body.add(Arrays.asList(lastPart.get(0), lastPart.get(1)));
        }

        boolean colisionSnakeAndWall = this.game.collision(
                (float)this.body.get(0).get(0),
                (float)this.body.get(0).get(1),
                this.game.cellWidth,
                this.game.cellHeight,
                0F,
                0F,
                (float)this.game.width,
                (float)this.game.height
        );

        if (!colisionSnakeAndWall) {
            this.game.running = false;
        }

        for (int i = body.size() - 1; i > 0; i--) {
            List<Float> current = body.get(i);
            List<Float> previous = body.get(i - 1);
            current.set(0, previous.get(0));
            current.set(1, previous.get(1));
        }
        List<Float> head = body.get(0);
        head.set(0, head.get(0) + directionMap.get(direction).get(0) * this.game.cellWidth);
        head.set(1, head.get(1) + directionMap.get(direction).get(1) * this.game.cellHeight);
    }

    @Override
    public void render(ShapeRenderer shape) {
        for (List<Float> path : body) {
            shape.begin(ShapeType.Filled);
            shape.setColor(0, 1, 0, 1);
            shape.rect(path.get(0), path.get(1), this.game.cellWidth, this.game.cellHeight);
            shape.end();

            shape.begin(ShapeType.Line);
            shape.setColor((float) 20 / 255, (float) 20 / 255, (float) 20 / 255, 1);
            shape.rect(path.get(0), path.get(1), this.game.cellWidth, this.game.cellHeight);
            shape.end();
        }
    }

}
