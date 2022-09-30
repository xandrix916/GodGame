package com.axerold.wisland;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture wolf, hare, doubleWolf, doubleHare, group, hollow;
	Island wolfIsland;
	private static final int px = 40;

	@Override
	public void create () {
		wolfIsland = new Island(20,0.2,1.0,0.1,0.2);
		wolfIsland.randomFill(20, 50);
		wolf = new Texture("40px/wolf.png");
		hare = new Texture("40px/hare.png");
		doubleWolf = new Texture("40px/wolftwo.png");
		doubleHare = new Texture("40px/haretwo.png");
		group = new Texture("40px/group.png");
		hollow = new Texture("40px/hollow.png");
		batch = new SpriteBatch();
	}

	private Texture choose(Region r)
	{
		if (r.getAmHares() >= 1 && r.getAmWolves() >= 1) {
			return group;
		}
		else if (r.getAmHares() >= 2){
			return doubleHare;
		}
		else if (r.getAmWolves() >= 2)
		{
			return doubleWolf;
		}
		else if (r.getAmHares() == 1)
		{
			return hare;
		}
		else if (r.getAmWolves() == 1)
		{
			return wolf;
		}
		return hollow;
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();
		for (int i = 0; i < wolfIsland.getN(); i++) {
			for (int j = 0; j < wolfIsland.getN(); j++)
			{
				batch.draw(choose(wolfIsland.getRegion(i,j)),j*px, i*px);
			}
		}
		batch.end();
		wolfIsland.doCycle();
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		wolf.dispose();
		hare.dispose();
		doubleHare.dispose();
		doubleWolf.dispose();
		group.dispose();
		hollow.dispose();
	}
}
