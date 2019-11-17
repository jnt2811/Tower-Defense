package jnt.game.Game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import jnt.game.Entity.enemy.BossEnemy;
import jnt.game.Entity.enemy.NinjaEnemy;
import jnt.game.Entity.enemy.NormalEnemy;
import jnt.game.Entity.enemy.TankerEnemy;
import jnt.game.Map.Map;

import java.util.ArrayList;
import java.util.Stack;

public class Level implements Disposable {

    private Map map;
    private float coolDown;
    private ArrayList<NormalEnemy> enemies;
    private Stack<NormalEnemy> enemieS;
    private boolean done1 = false, done2 = false, done3 = false;
    private PlayerInfo player;

    public Level(PlayerInfo player) {

        this.player = player;

        enemies = new ArrayList<>();
        enemieS = new Stack<>();

        coolDown = 0.5f;
    }

    public void setLevel(int level) {
        if(level == 1) createLevel1();
        if(level == 2) createLevel1();
        if(level == 3) createLevel1();
    }


    public void createLevel1() {

        // Add 4 enemies for each type
        for (int i=0; i<0; i++) enemieS.push(new NormalEnemy());
        for (int i=0; i<1; i++) enemieS.push(new NinjaEnemy());
        for (int i=0; i<0; i++) enemieS.push(new TankerEnemy());
        for (int i=0; i<0; i++) enemieS.push(new BossEnemy());

        //
        if(enemies.size() == 0) done1 = false;
    }


    public void draw(SpriteBatch batch, float delta) {

        // Add Enemies to the Array
        coolDown -= delta;
        if(coolDown <= 0) {
            if(!enemieS.isEmpty())
                enemies.add(enemieS.pop());
            coolDown = 0.4f;
        }

        // Draw Enemies
        for (NormalEnemy enemy : enemies) enemy.draw(batch, delta);

        update();
    }

    public void update() {

        for (int i=0; i<enemies.size(); i++) {

            // Decrease Player's Health if enemy reaches the final point
            if(enemies.get(i).isFinished()) {

                player.decreaseHealth();
                enemies.get(i).setFinished(false);
                enemies.get(i).setActive(false);
                enemies.remove(i);
            }

            // Earn Rewards if tower kills enemy
            else if(!enemies.get(i).isActive()) {

                player.increaseGold(enemies.get(i).getReward());

                enemies.remove(i);
            }
        }
    }

    //
    public ArrayList<NormalEnemy> getEnemies() {
        return enemies;
    }

    @Override
    public void dispose() {
        for(NormalEnemy enemy : enemies) enemy.dispose();
    }
}
