package jnt.game.Entity.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import jnt.game.Entity.enemy.NormalEnemy;

public class SmgBullet extends NormalBullet {
    public SmgBullet(double x, double y, NormalEnemy target) {

        super(x, y, target);

        bullet = new Sprite(new Texture(Gdx.files.internal("bullet2.png")));

        // Default
        dame = 1;
        speed = 5;
    }
}
