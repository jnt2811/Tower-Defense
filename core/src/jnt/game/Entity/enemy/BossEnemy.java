package jnt.game.Entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BossEnemy extends NormalEnemy {

    public BossEnemy() {
        enemy = new Sprite(new Texture(Gdx.files.internal("enemy4.png")));

        // Default
        blood = 1000;
        speed = 1.5f;
        reward = 20;

        // Calculate One Pixel compared to Blood Bar's Width
        onePixel = greenBlood.getWidth() / blood;

        // This Variable is the History of Enemy's Blood
        oldBlood = blood;
    }
}
