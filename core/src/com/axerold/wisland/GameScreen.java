package com.axerold.wisland;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

public class GameScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private final Island wolfIsland;
    private Constants constants;
    Texture wolf, hare, doubleWolf, doubleHare, group, hollow, male, female;

    private void texInit(){
        wolf = new Texture("40px/wolf.png");
        hare = new Texture("40px/hare.png");
        doubleWolf = new Texture("40px/wolftwo.png");
        doubleHare = new Texture("40px/haretwo.png");
        group = new Texture("40px/group.png");
        hollow = new Texture("40px/hollow.png");
        male = new Texture("40px/male.png");
        female = new Texture("40px/female.png");
    }

    public GameScreen(OrthographicCamera camera){
        constants = new Constants();
        wolfIsland = new Island(6, constants);
        wolfIsland.randomFill(3, 6);
        this.camera = camera;
        this.batch = new SpriteBatch();
        texInit();
    }

    private void update(){

    }

    private Sprite choose(int i1, int j1)
    {
        HashMap<String, Integer> stats = wolfIsland.getRegion(i1,j1);
        Texture tex;
        int hares = stats.get("hares"), wolves = stats.get("wolves");
        if (hares >= 1 && wolves >= 1) {
            tex = group;
        }
        else if (hares >= 2){
            tex = doubleHare;
        }
        else if (wolves >= 2)
        {
            tex = doubleWolf;
        }
        else if (hares == 1)
        {
            tex = hare;
        }
        else if (wolves == 1)
        {
            tex = wolf;
        }
        else{
            tex = hollow;
        }
        return new Sprite(tex);
    }

    @Override
    public void render(float delta){
        this.update();
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for (int i = 0; i < wolfIsland.getN(); i++) {
            for (int j = 0; j < wolfIsland.getN(); j++)
            {
                batch.draw(choose(i,j),j*constants.getPixels(), i*constants.getPixels());
            }
        }
        batch.end();
        wolfIsland.doCycle();
        try {
            Thread.sleep(/*5**/5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dispose(){
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
