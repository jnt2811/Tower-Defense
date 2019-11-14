package jnt.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Entity.enemy.BossEnemy;
import jnt.game.Entity.enemy.NormalEnemy;
import jnt.game.Entity.enemy.NinjaEnemy;
import jnt.game.Entity.enemy.TankerEnemy;

import java.util.ArrayList;
import java.util.Stack;

public class Level {

    private Map map;
    private float time;
    private ArrayList<NormalEnemy> enemies;
    private Stack<NormalEnemy> enemieS;
    private boolean isSet = false;

    public Level() {
        enemies = new ArrayList<>();
        enemieS = new Stack<>();
    }

    public void setLevel(int level) {
        if(!isSet) {
            isSet = true;
            if(level == 1) createLevel1();
        }
    }


    public void createLevel1() {

        // Add 4 enemies
//        enemieS.push(new BossEnemy());
//        enemieS.push(new BossEnemy());
//        enemieS.push(new BossEnemy());
//        enemieS.push(new BossEnemy());
//
//        enemieS.push(new TankerEnemy());
//        enemieS.push(new TankerEnemy());
//        enemieS.push(new TankerEnemy());
//        enemieS.push(new TankerEnemy());
//
//        enemieS.push(new NinjaEnemy());
//        enemieS.push(new NinjaEnemy());
//        enemieS.push(new NinjaEnemy());
//        enemieS.push(new NinjaEnemy());


        enemieS.push(new NormalEnemy());
        enemieS.push(new NormalEnemy());
        enemieS.push(new NormalEnemy());
        enemieS.push(new NormalEnemy());

        //
        enemies.add(enemieS.pop());


        if(enemies.size() == 0) isSet = false;
    }


    public void draw(SpriteBatch batch, float delta) {

        time += delta;

        if(time > 1.5) {
            if(!enemieS.isEmpty())
                enemies.add(enemieS.pop());
            time = 0;
        }

        for (NormalEnemy enemy : enemies) enemy.draw(batch);

        update();
    }

    public void update() {
        for (int i=0; i<enemies.size(); i++) {
            if(!enemies.get(i).isActive()) enemies.remove(i);
        }
    }


    public ArrayList<NormalEnemy> getEnemies() {
        return enemies;
    }


    public boolean isSet() {
        return isSet;
    }
}
