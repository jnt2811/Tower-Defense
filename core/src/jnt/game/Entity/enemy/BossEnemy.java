package jnt.game.Entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BossEnemy extends NormalEnemy {

    public BossEnemy() {
        enemy = new Sprite(new Texture(Gdx.files.internal("enemy4.png")));

        // Default
        blood = 35;
        speed = 2;
    }
}
