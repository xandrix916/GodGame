package com.axerold.wisland;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture wolf, hare, doubleWolf, doubleHare, group;
	Island wolfIsland;


	@Override
	public void create () {
		wolfIsland = new Island(20,0.2,1.0,0.1,0.2);
		wolfIsland.randomFill(20, 50);
		wolf = new Texture("wolf.png");
		hare = new Texture("hare.png");
		doubleWolf = new Texture("wolftwo.png");
		doubleHare = new Texture("haretwo.png");
		group = new Texture("group.png");
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		wolf.dispose();
		hare.dispose();
		doubleHare.dispose();
		doubleWolf.dispose();
		group.dispose();
	}
}
