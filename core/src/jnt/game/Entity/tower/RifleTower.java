package jnt.game.Entity.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import jnt.game.Entity.enemy.NormalEnemy;

import java.util.ArrayList;

public class RifleTower extends NormalTower {
    public RifleTower(int x, int y, ArrayList<NormalEnemy> enemies) {

        super(x, y, enemies);

        towerBase = new Sprite(new Texture(Gdx.files.internal("towerBase3.png")));
        towerGun = new Sprite(new Texture(Gdx.files.internal("towerGun3.png")));

        // Default
        type = "rifle";
        range = 250;
    }
}
