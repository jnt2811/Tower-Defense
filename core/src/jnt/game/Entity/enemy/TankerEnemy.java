package jnt.game.Entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TankerEnemy extends  NormalEnemy {

    public TankerEnemy() {
        enemy = new Sprite(new Texture(Gdx.files.internal("enemy3.png")));

        // Default
        blood = 30;
        speed = 2;
    }
}
