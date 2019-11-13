package jnt.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Entity.enemy.Enemy;
import jnt.game.Entity.enemy.NormalEnemy;

import java.util.ArrayList;

public class Stage {

    private Map map;
    private ArrayList<Enemy> enemies;
    private boolean isSet = false;

    public Stage() {
        enemies = new ArrayList<>();
    }

    public void setStage(int stageNum) {
        if(!isSet) {
            isSet = true;
            if(stageNum == 1) createStage1();
        }
    }

    public void createStage1() {

            //add 4 enemies
//            enemies.add(new Enemy(1,10));
//            enemies.add(new Enemy(2,15));
//            enemies.add(new Enemy(3,20));
            enemies.add(new NormalEnemy(4,25));

            if(enemies.size() == 0) isSet = false;
    }

    public void draw(SpriteBatch batch, float delta) {
        for (Enemy enemy : enemies)
            enemy.draw(batch, delta);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public boolean isSet() {
        return isSet;
    }
}
