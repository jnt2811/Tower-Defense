package jnt.game.Entity.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import jnt.game.Entity.enemy.NormalEnemy;
import jnt.game.Map.Tile;

import java.util.ArrayList;

public class RifleTower extends NormalTower {
    public RifleTower(ArrayList<NormalEnemy> enemies, Tile place) {

        super(enemies, place);

        towerBase = new Sprite(new Texture(Gdx.files.internal("towerBase3.png")));
        towerGun = new Sprite(new Texture(Gdx.files.internal("towerGun3.png")));

        // Default
        type = "rifle";
        range = 200;
        price = 20;
        coolDown = 0.2f;
        timer = coolDown;
    }
}
