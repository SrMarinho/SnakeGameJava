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
	int width;
	int height;
	float cellWidth;
	float cellHeight;

	public SnakeGame(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid_col = 30;
		this.grid_row = 30;
		this.cellWidth = (float) this.width / this.grid_col;
		this.cellHeight = (float) this.height / this.grid_col;
	}

	@Override
	public void create () {
		shape = new ShapeRenderer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		for (int i = 0; i <= grid_col; i++) {
			shape.begin(ShapeType.Line);
			shape.setColor(0.1F, 0.1F, 0.1F, 1);
			shape.line(this.cellWidth * i, 0,  this.cellWidth * i, this.height);
			shape.end();
		}
		for (int i = 0; i <= grid_row; i++) {
			shape.begin(ShapeType.Line);
			shape.setColor(0.1F, 0.1F, 0.1F, 1);
			shape.line(0, this.cellHeight * i, this.width, this.cellHeight * i);
			shape.end();
		}
	}
	
	@Override
	public void dispose () {
	}
}
