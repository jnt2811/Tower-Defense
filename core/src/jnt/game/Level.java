package jnt.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Entity.enemy.NormalEnemy;
import jnt.game.Entity.enemy.NinjaEnemy;

import java.util.ArrayList;

public class Level {

    private Map map;
    private ArrayList<NormalEnemy> enemies;
    private boolean isSet = false;

    public Level() {
        enemies = new ArrayList<>();
    }

    public void setLevel(int level) {
        if(!isSet) {
            isSet = true;
            if(level == 1) createLevel1();
        }
    }

    public void createLevel1() {

            // Add 1 enemies
            enemies.add(new NormalEnemy());

            if(enemies.size() == 0) isSet = false;
    }

    public void draw(SpriteBatch batch, float delta) {
        for (NormalEnemy normalEnemy : enemies)
            normalEnemy.draw(batch);
    }

    public ArrayList<NormalEnemy> getEnemies() {
        return enemies;
    }

    public boolean isSet() {
        return isSet;
    }
}
