package jnt.game.Entity.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import jnt.game.Entity.enemy.NormalEnemy;

public class MortarBullet extends NormalBullet {
    public MortarBullet(double x, double y, NormalEnemy target) {

        super(x, y, target);

        bullet = new Sprite(new Texture(Gdx.files.internal("bullet4.png")));

        // Default
        dame = 5;
        speed = 2;
    }
}
