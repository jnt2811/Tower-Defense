package jnt.game.Entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TankerEnemy extends  NormalEnemy {

    public TankerEnemy() {
        enemy = new Sprite(new Texture(Gdx.files.internal("enemy3.png")));

        // Default
        blood = 500;
        speed = 2;
        reward = 10;

        // Calculate One Pixel compared to Blood Bar's Width
        onePixel = greenBlood.getWidth() / blood;

        // This Variable is the History of Enemy's Blood
        oldBlood = blood;
    }
}
