package com.snake.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.snake.game.entities.Scene;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SnakeGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ShapeRenderer shape;
	public int grid_col;
	public int grid_row;
	public int width;
	public int height;
	public float cellWidth;
	public float cellHeight;
	Scene scene;
	Boolean running;

	public SnakeGame(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid_col = 30;
		this.grid_row = 30;
		this.cellWidth = (float) this.width / this.grid_col;
		this.cellHeight = (float) this.height / this.grid_row;
		this.scene = new Scene(this);
		this.running = true;
	}

	@Override
	public void create () {
		shape = new ShapeRenderer();
	}

	@Override
	public void render () {
		ScreenUtils.clear((float) 20 /255, (float) 20 /255, (float) 20 /255, 1);

		scene.event();
		if (this.running) {
			scene.update();
		}
        scene.render(shape);
	}
	
	@Override
	public void dispose () {
	}
}
