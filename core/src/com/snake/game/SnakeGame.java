package com.snake.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.snake.game.entities.Scene;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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
	public Scene scene;
	public Boolean running;
	public int score;
	float move_time;
	Instant last_move_time;

	public SnakeGame(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid_col = 30;
		this.grid_row = 30;
		this.cellWidth = (float) this.width / this.grid_col;
		this.cellHeight = (float) this.height / this.grid_row;
		this.scene = new Scene(this);
		this.running = true;
		this.score = 0;
		this.move_time = 50F;
		this.last_move_time = Instant.now() ;
	}

	@Override
	public void create () {
		shape = new ShapeRenderer();
		Instant last_move_time = Instant.now() ;
	}

	@Override
	public void render () {
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			if (!this.running) {
				this.scene = new Scene(this);
				this.score = 0;
				this.running = true;
			}
		}

		ScreenUtils.clear((float) 20 /255, (float) 20 /255, (float) 20 /255, 1);

		scene.event();
		if (this.running) {
			Instant current_time = Instant.now();
			Duration duration = Duration.between(this.last_move_time, current_time);
			if (duration.toMillis() > this.move_time) {
				scene.update();
				this.last_move_time = current_time;
			}
		}

        scene.render(shape);
	}
	
	@Override
	public void dispose () {
	}

	public boolean collision(int x1, int y1, int x2, int y2) {
		return x1 == x2 && y1 == y2;
	}
}
