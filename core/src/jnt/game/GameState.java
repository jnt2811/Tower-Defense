package jnt.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Entity.enemy.NormalEnemy;
import jnt.game.Entity.tower.MortarTower;
import jnt.game.Entity.tower.NormalTower;
import jnt.game.Entity.tower.RifleTower;
import jnt.game.Entity.tower.SmgTower;

import java.util.ArrayList;

public class GameState {

    private Map map;
    private ArrayList<NormalTower> towers;
    private Level level;
    private int healthNum = 10, goldNum = 0;
    private BitmapFont health, gold;

    public GameState() {

        map = new Map();

        level = new Level();
        level.setLevel(1);

        towers = new ArrayList<>();
        health = new BitmapFont(Gdx.files.internal("health.fnt"));
        gold = new BitmapFont(Gdx.files.internal("health.fnt"));

        //add 4 towers
        towers.add(new NormalTower(500, 500, level.getEnemies()));
        towers.add(new SmgTower(100, 300, level.getEnemies()));
        towers.add(new RifleTower(700, 300, level.getEnemies()));
        towers.add(new MortarTower(300, 100, level.getEnemies()));
        
    }

    public void render(SpriteBatch batch, float delta) {
        createMap(batch);
        createTowers(batch, delta);
        createEnemies(batch, delta);
        createHealth(batch);
        createGold(batch);

        update(delta);
    }

    public void createMap(SpriteBatch batch) {
        batch.begin();

        map.draw(batch);

        batch.end();
    }


    public void createTowers(SpriteBatch batch, float delta) {
        batch.begin();

        for(NormalTower tower : towers)
            tower.draw(batch);

        batch.end();
    }


    public void createEnemies(SpriteBatch batch, float delta) {
        batch.begin();

        level.draw(batch,delta);

        batch.end();
    }


    public void createHealth(SpriteBatch batch) {
        batch.begin();

        health.draw(batch, "" + healthNum, 1010, 660);

        batch.end();
    }


    public void createGold(SpriteBatch batch) {
        batch.begin();

        gold.draw(batch, "" + goldNum, 1010, 560);

        batch.end();
    }


    public void update(float delta) {

        for(NormalEnemy enemy : level.getEnemies()) {
            System.out.println(enemy.isDecreaseHealth());
            if(enemy.isDecreaseHealth()) {
                System.out.println("ok");
                healthNum--;
                enemy.setDecreaseHealth(false);
            }
        }
    }


    public int getHealthNum() {
        return healthNum;
    }
    public void setHealthNum(int healthNum) {
        this.healthNum = healthNum;
    }

    public int getGoldNum() {
        return goldNum;
    }
    public void setGoldNum(int goldNum) {
        this.goldNum = goldNum;
    }
}
