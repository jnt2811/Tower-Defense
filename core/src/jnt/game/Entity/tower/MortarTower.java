package jnt.game.Entity.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import jnt.game.Entity.enemy.NormalEnemy;
import jnt.game.Map.Tile;

import java.util.ArrayList;

public class MortarTower extends NormalTower {
    public MortarTower(ArrayList<NormalEnemy> enemies, Tile place) {

        super(enemies, place);

        towerBase = new Sprite(new Texture(Gdx.files.internal("towerBase4.png")));
        towerGun = new Sprite(new Texture(Gdx.files.internal("towerGun4.png")));

        // Default
        type = "mortar";
        range = 350;
    }
}
