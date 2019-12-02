package jnt.game.Entity.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import jnt.game.Entity.enemy.NormalEnemy;
import jnt.game.Map.Tile;

import java.util.ArrayList;

public class SmgTower extends NormalTower {
    public SmgTower(ArrayList<NormalEnemy> enemies, Tile place) {

        super(enemies, place);

        towerBase = new Sprite(new Texture(Gdx.files.internal("towerBase2.png")));
        towerGun = new Sprite(new Texture(Gdx.files.internal("towerGun2.png")));

        // Default
        type = "smg";
        range = 150;
        price = 30;
        coolDown = 0.1f;
        timer = coolDown;
    }
}
