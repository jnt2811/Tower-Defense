package jnt.game.Entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class NinjaEnemy extends NormalEnemy {

    public NinjaEnemy() {
        enemy = new Sprite(new Texture(Gdx.files.internal("enemy2.png")));

        // Default
        blood = 20;
        speed = 4;
        reward = 5;

        // Calculate One Pixel compared to Blood Bar's Width
        onePixel = greenBlood.getWidth() / blood;

        // This Variable is the History of Enemy's Blood
        oldBlood = blood;
    }
}
