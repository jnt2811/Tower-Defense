package jnt.game.Entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class NinjaEnemy extends NormalEnemy {

    public NinjaEnemy() {
        enemy = new Sprite(new Texture(Gdx.files.internal("enemy2.png")));

        // Default
        blood = 25;
        speed = 2;
    }
}
