package com.axerold.wisland;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.HashMap;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture wolf, hare, doubleWolf, doubleHare, group, hollow, male, female;
	Island wolfIsland;
	private static final int px = 40;

	@Override
	public void create () {
		wolfIsland = new Island(20,0.1,0.4,0.67,0.92,1.0,0.1,0.2);
		wolfIsland.randomFill(20, 50);
		wolf = new Texture("40px/wolf.png");
		hare = new Texture("40px/hare.png");
		doubleWolf = new Texture("40px/wolftwo.png");
		doubleHare = new Texture("40px/haretwo.png");
		group = new Texture("40px/group.png");
		hollow = new Texture("40px/hollow.png");
		male = new Texture("40px/male.png");
		female = new Texture("40px/female.png");
		batch = new SpriteBatch();
	}

	private Texture choose(int i1, int j1)
	{
		HashMap<String, Integer> stats = wolfIsland.getRegion(i1,j1);
		int hares = stats.get("hares"), wolves = stats.get("wolves");
		if (hares >= 1 && wolves >= 1) {
			return group;
		}
		else if (hares >= 2){
			return doubleHare;
		}
		else if (wolves >= 2)
		{
			return doubleWolf;
		}
		else if (hares == 1)
		{
			return hare;
		}
		else if (wolves == 1)
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
				batch.draw(choose(i,j),j*px, i*px);
			}
		}
		/*for (int i = 0; i < wolfIsland.getN(); i++) {
			for (int j = 0; j < wolfIsland.getN(); j++) {
				Region r = wolfIsland.getRegion(i,j);
				if (r.getAmMales() >= 1)
					batch.draw(male,j*px, i*px);
				if (r.getAmFemales() >= 1)
					batch.draw(female,j*px, i*px);
			}
		}*/
		batch.end();
		wolfIsland.doCycle();
		try {
			Thread.sleep(/*5**/5000);
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
		male.dispose();
		female.dispose();
		hollow.dispose();
	}
}
