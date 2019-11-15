package jnt.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
    private Texture bug;

    private int health = 10, gold = 0;
    private BitmapFont healthNum, goldNum;

    public GameState() {

        bug = new Texture(Gdx.files.internal("grass.png"));

        map = new Map();

        level = new Level();
        level.setLevel(1);

        healthNum = new BitmapFont(Gdx.files.internal("health.fnt"));
        goldNum = new BitmapFont(Gdx.files.internal("health.fnt"));

        towers = new ArrayList<>();

        // Add 4 towers
        towers.add(new NormalTower(500, 500, level.getEnemies()));
        towers.add(new SmgTower(100, 300, level.getEnemies()));
        towers.add(new RifleTower(700, 300, level.getEnemies()));
        towers.add(new MortarTower(300, 100, level.getEnemies()));
        
    }

    public void render(SpriteBatch batch, float delta) {

        createMap(batch);
        createTowers(batch, delta);
        createEnemies(batch, delta);
        createInfo(batch);
        createBug(batch);

        update();
    }


    public void update() {
        if(level.isDecreaseHealth()) {
            level.setDecreaseHealth(false);
            health--;
        }
    }


    public void createMap(SpriteBatch batch) {
        batch.begin();

        map.draw(batch);

        batch.end();
    }


    public void createTowers(SpriteBatch batch, float delta) {
        batch.begin();

        for(NormalTower tower : towers)
            tower.draw(batch,delta);

        batch.end();
    }


    public void createEnemies(SpriteBatch batch, float delta) {
        batch.begin();

        level.draw(batch,delta);

        batch.end();
    }


    public void createInfo(SpriteBatch batch) {
        batch.begin();

        healthNum.draw(batch, "" + health, 1410, 860);
        goldNum.draw(batch, "" + gold, 1410, 740);

        batch.end();
    }

    public void createBug(SpriteBatch batch) {
        batch.begin();

        batch.draw(bug, 0,0);

        batch.end();
    }

    public void dispose() {
        healthNum.dispose();
        goldNum.dispose();

        map.dispose();
    }
}
