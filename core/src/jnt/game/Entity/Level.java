package jnt.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import jnt.game.Button.ButtonManagement;
import jnt.game.Entity.enemy.BossEnemy;
import jnt.game.Entity.enemy.NinjaEnemy;
import jnt.game.Entity.enemy.NormalEnemy;
import jnt.game.Entity.enemy.TankerEnemy;
import jnt.game.Game.PlayerInfo;
import jnt.game.Map.Map;

import java.util.ArrayList;
import java.util.Stack;

public class Level extends Entity implements Disposable {

    private Map map;
    private float coolDown;
    private ArrayList<NormalEnemy> enemies;
    private Stack<NormalEnemy> enemieS;
    private boolean done1 = false, done2 = false, done3 = false;
    private PlayerInfo player;
    private Music warning;
    private ButtonManagement buttons;

    public Level(PlayerInfo player, ButtonManagement buttons) {

        this.player = player;

        enemies = new ArrayList<>();
        enemieS = new Stack<>();

        coolDown = 0.5f;

        warning = Gdx.audio.newMusic(Gdx.files.internal("warning.mp3"));

        this.buttons = buttons;
    }

    public void setLevel(int level) {
        if(level == 1) createLevel1();
        if(level == 2) createLevel2();
        if(level == 3) createLevel3();
    }


    public void createLevel1() {

        // Add 4 enemies for each type
        for (int i=0; i<10; i++) enemieS.push(new NormalEnemy());
        for (int i=0; i<10; i++) enemieS.push(new NinjaEnemy());
        for (int i=0; i<5; i++) enemieS.push(new TankerEnemy());
        for (int i=0; i<5; i++) enemieS.push(new BossEnemy());

        //
        if(enemies.size() == 0) done1 = true;
    }
    public void createLevel2() {

        // Add 4 enemies for each type
        for (int i=0; i<0; i++) enemieS.push(new NormalEnemy());
        for (int i=0; i<1; i++) enemieS.push(new NinjaEnemy());
        for (int i=0; i<0; i++) enemieS.push(new TankerEnemy());
        for (int i=0; i<0; i++) enemieS.push(new BossEnemy());

        //
        if(enemies.size() == 0) done2 = true;
    }
    public void createLevel3() {

        // Add 4 enemies for each type
        for (int i=0; i<0; i++) enemieS.push(new NormalEnemy());
        for (int i=0; i<1; i++) enemieS.push(new NinjaEnemy());
        for (int i=0; i<0; i++) enemieS.push(new TankerEnemy());
        for (int i=0; i<0; i++) enemieS.push(new BossEnemy());

        //
        if(enemies.size() == 0) done1 = true;
    }


    public void draw(SpriteBatch batch, float delta) {
//        System.out.println(buttonManagement.getStartWave());

        if (buttons.getStartWave()) {

            // Add Enemies to the Array
            if (!buttons.getPaused()) {
                coolDown -= delta;
                if(coolDown <= 0) {
                    if(!enemieS.isEmpty())
                        enemies.add(enemieS.pop());
                    coolDown = 0.4f;
                }
            }

            // Draw Enemies
            for (NormalEnemy enemy : enemies) enemy.draw(batch, delta);
        }
    }

    public void update(float delta) {

        // Enemies update
        for (NormalEnemy enemy : enemies) enemy.update(delta);

        for (int i=0; i<enemies.size(); i++) {

            // Decrease Player's Health if enemy reaches the final point
            if(enemies.get(i).isFinished()) {

                warning.play();

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
