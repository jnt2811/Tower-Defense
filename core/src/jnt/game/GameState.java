package jnt.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Entity.enemy.Enemy;
import jnt.game.Entity.tower.Tower;

import java.util.ArrayList;

public class GameState {

    private Map map;
    private ArrayList<Tower> towers;
    private Stage stage;
    private int healthNum = 10, goldNum = 0;

    public GameState() {

        map = new Map();

        stage = new Stage();
        stage.setStage(1);

        towers = new ArrayList<>();

        //add 2 towers
        towers.add(new Tower(1, 500, 500, 250, stage.getEnemies()));
        towers.add(new Tower(2, 100, 300, 250, stage.getEnemies()));
    }

    public void render(SpriteBatch batch, float delta) {
        createMap(batch);
        createTowers(batch, delta);
        createEnemies(batch, delta);

        update(delta);
    }

    public void createMap(SpriteBatch batch) {
        batch.begin();

        map.draw(batch);

        batch.end();
    }

    public void createTowers(SpriteBatch batch, float delta) {
        batch.begin();

        for(Tower tower : towers)
            tower.draw(batch, delta);

        batch.end();
    }

    public void createEnemies(SpriteBatch batch, float delta) {
        batch.begin();

        stage.draw(batch,delta);

        batch.end();
    }

    public void update(float delta) {

        for(Enemy enemy : stage.getEnemies()) {
            if(enemy.isDecreaseHealth()) {
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
