package com.snake.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.snake.game.SnakeGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		int width = 500;
		int height = 500;
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(15);
		config.setTitle("SnakeGame");
		config.setWindowedMode(width, height);
		new Lwjgl3Application(new SnakeGame(width, height), config);
	}
}
