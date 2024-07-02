package com.snake.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import java.awt.*;

public class SnakeGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ShapeRenderer shape;
	int grid_col;
	int grid_row;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		shape = new ShapeRenderer();
		grid_col = 30;
		grid_row = 30;
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		for (int i = 0; i <= grid_col; i++) {
			shape.begin(ShapeType.Line);
			shape.setColor(1, 1, 1, 1);
			shape.line(50 * i, 0, 50 * i, 500);
			shape.end();
		}
		for (int i = 0; i <= grid_col; i++) {
			shape.begin(ShapeType.Line);
			shape.setColor(1, 1, 1, 1);
			shape.line(0, 50 * i, 500, 50 * i);
			shape.end();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
