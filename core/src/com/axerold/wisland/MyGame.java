package com.axerold.wisland;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

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
				Region r = wolfIsland.getRegion(i,j);
				batch.draw(choose(r),j*px, i*px);
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
