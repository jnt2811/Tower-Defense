package jnt.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Random;

public class Stage1 {

    private int p;
    private ArrayList<Enemy> enemies;

    public Stage1() {
        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(1,-100,870, p));
        enemies.add(new Enemy(4,-300,360, p));
    }

    public void render(SpriteBatch batch) {

        //draw enemies
        for (int i=0; i < enemies.size(); i++) {
            enemies.get(i).render(batch);
            enemies.get(i).update();
        }

        //create more enemies
        if (enemies.get(enemies.size() - 1).x > 200 && enemies.size() < 5) {
            enemies.add(new Enemy(1,-80,870, p));
        }
    }
}
