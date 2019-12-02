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
    private boolean isActive = false, full = false, victory = false;
    private PlayerInfo player;
    private Music warning;
    private ButtonManagement buttons;
    private int level = 1;

    public Level(PlayerInfo player, ButtonManagement buttons) {

        this.player = player;

        enemies = new ArrayList<>();
        enemieS = new Stack<>();

        coolDown = 0.5f;

        warning = Gdx.audio.newMusic(Gdx.files.internal("warning.mp3"));

        this.buttons = buttons;
    }

    private void setLevel() {
        if(level == 1 && !isActive) createLevel1();
        if(level == 2 && !isActive) createLevel2();
        if(level == 3 && !isActive) createLevel3();
    }

    private void createLevel1() {

        // Add 4 enemies for each type
        for (int i=0; i<10; i++) enemieS.push(new NormalEnemy());
        for (int i=0; i<1; i++) enemieS.push(new NinjaEnemy());
        for (int i=0; i<1; i++) enemieS.push(new TankerEnemy());
        for (int i=0; i<1; i++) enemieS.push(new BossEnemy());

        isActive = true;
    }

    private void createLevel2() {

        // Add 4 enemies for each type
        for (int i=0; i<2; i++) enemieS.push(new NormalEnemy());
        for (int i=0; i<1; i++) enemieS.push(new NinjaEnemy());
        for (int i=0; i<1; i++) enemieS.push(new TankerEnemy());
        for (int i=0; i<0; i++) enemieS.push(new BossEnemy());

        isActive = true;
    }

    private void createLevel3() {

        // Add 4 enemies for each type
        for (int i=0; i<2; i++) enemieS.push(new NormalEnemy());
        for (int i=0; i<1; i++) enemieS.push(new NinjaEnemy());
        for (int i=0; i<1; i++) enemieS.push(new TankerEnemy());
        for (int i=0; i<1; i++) enemieS.push(new BossEnemy());

        isActive = true;
    }


    public void draw(SpriteBatch batch, float delta) {

        if (buttons.getStartWave()) {

            // Set Enemies
            setLevel();

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

        //
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

        // Check enemies are pushed from stack to array
        if(enemieS.size() == 0 && enemies.size() > 0) full = true;

        // Check Stage finished yet ?
        if(enemies.size() == 0 && full) {
            buttons.setButton0Play();

            if(level <= 4) level++;
            if(level == 2) buttons.setButton1Level2();
            else if(level == 3) buttons.setButton1Level3();
            else if(level == 4) victory = true;

            full = false;
            isActive = false;
        }
    }

    public ArrayList<NormalEnemy> getEnemies() {
        return enemies;
    }

    public boolean getVictory() {return victory;}

    @Override
    public void dispose() {
        for(NormalEnemy enemy : enemies) enemy.dispose();
        buttons.dispose();
    }
}
