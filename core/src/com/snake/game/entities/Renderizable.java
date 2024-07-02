package com.snake.game.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Renderizable {
    public void event();
    public void update();
    public void render(ShapeRenderer shape);
}
