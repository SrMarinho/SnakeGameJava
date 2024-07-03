package com.snake.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import com.snake.game.SnakeGame;
import java.util.*;

public class Snake implements Renderizable {
    SnakeGame game;
    List<List<Integer>> body;
    String direction;
    String nextDirection;
    Map<String, List<Integer>> directionMap;

    public Snake(SnakeGame game) {
        this.game = game;
        direction = "right";
        nextDirection = direction;
        directionMap = new HashMap<>();

        directionMap.put("up", Arrays.asList(0, 1));
        directionMap.put("down", Arrays.asList(0, -1));
        directionMap.put("left", Arrays.asList(-1, 0));
        directionMap.put("right", Arrays.asList(1, 0));

        body = new ArrayList<>();
        List<Integer> head = Arrays.asList(
                Math.round((float) (this.game.grid_col / 2)),
                Math.round((float) (this.game.grid_row / 2))
        );

        body.add(head);
        body.add(Arrays.asList(head.get(0) - 1, head.get(1)));
        body.add(Arrays.asList(head.get(0) - 2, head.get(1)));
    }

    @Override
    public void event() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.UP:
                        if (!Objects.equals(direction, "down")) {
                            nextDirection = "up";
                        }
                        break;
                    case Input.Keys.DOWN:
                        if (!Objects.equals(direction, "up")) {
                            nextDirection = "down";
                        }
                        break;
                    case Input.Keys.LEFT:
                        if (!Objects.equals(direction, "right")) {
                            nextDirection = "left";
                        }
                        break;
                    case Input.Keys.RIGHT:
                        if (!Objects.equals(direction, "left")) {
                            nextDirection = "right";
                        }
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void update() {
        boolean colisionSnakeAndApple = this.game.collision(
                this.body.get(0).get(0),
                this.body.get(0).get(1),
                this.game.scene.apple.x,
                this.game.scene.apple.y
        );

        if (colisionSnakeAndApple) {
            this.game.score += 1;
            this.game.scene.apple.randPos(this);
            System.out.println(this.game.score);
            List<Integer> lastPart = body.get(body.size() - 1);
            body.add(Arrays.asList(lastPart.get(0), lastPart.get(1)));
        }

        for (int i = body.size() - 1; i > 0; i--) {
            List<Integer> current = body.get(i);
            List<Integer> previous = body.get(i - 1);
            current.set(0, previous.get(0));
            current.set(1, previous.get(1));
        }

        // Atualiza a direção apenas quando a cobra se move
        direction = nextDirection;

        List<Integer> head = body.get(0);
        head.set(0, head.get(0) + directionMap.get(direction).get(0));
        head.set(1, head.get(1) + directionMap.get(direction).get(1));

        boolean colisionSnakeAndWall = (new Object() {
            public boolean wallColision() {
                List<Integer> head = body.get(0);
                return head.get(0) < 0 ||
                        head.get(0) > game.grid_col ||
                        head.get(1) < 0 ||
                        head.get(1) > game.grid_row;
            }
        }).wallColision();

        if (colisionSnakeAndWall) {
            this.game.running = false;
        }

        boolean colisionHeadAndBody = (new Object() {
            public boolean bodyColision() {
                List<Integer> head = body.get(0);
                for (int i = body.size() - 1; i > 0; i--) {
                    List<Integer> current = body.get(i);
                    if (Objects.equals(head.get(0), current.get(0)) &&
                            Objects.equals(head.get(1), current.get(1))) {
                        return true;
                    }
                }
                return false;
            }
        }).bodyColision();

        if (colisionHeadAndBody) {
            this.game.running = false;
        }
    }

    @Override
    public void render(ShapeRenderer shape) {
        for (List<Integer> path : body) {
            shape.begin(ShapeType.Filled);
            shape.setColor(0, 1, 0, 1);
            shape.rect(path.get(0) * this.game.cellWidth, path.get(1) * this.game.cellHeight, this.game.cellWidth, this.game.cellHeight);
            shape.end();
        }
    }
}
