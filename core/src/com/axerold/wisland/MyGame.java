package com.axerold.wisland;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends Game {
	SpriteBatch batch;
	OrthographicCamera camera;
	private static MyGame INSTANCE;
	public MyGame(){
		INSTANCE = this;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		int widthScreen = Gdx.graphics.getWidth();
		int heightScreen = Gdx.graphics.getHeight();
		camera.setToOrtho(false, widthScreen, heightScreen);
		setScreen(new GameScreen(camera));
	}

}
