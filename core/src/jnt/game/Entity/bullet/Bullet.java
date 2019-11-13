package jnt.game.Entity.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Entity.Entity;
import jnt.game.Entity.enemy.Enemy;

public class Bullet extends Entity {

    private Sprite bullet;
    private int dame;
    private Enemy target;
    private static final int speed = 5;

    public Bullet(int id, int x, int y, int dame, Enemy target) {

        this.id = id;
        this.x = x;
        this.y = y;
        this.dame = dame;
        this.target = target;

        if(id == 1) bullet = new Sprite(new Texture(Gdx.files.internal("bullet1.png")));
        if(id == 2) bullet = new Sprite(new Texture(Gdx.files.internal("bullet2.png")));

        //default
        setActive(true);
    }

    @Override
    public void draw(SpriteBatch batch, float delta) {

        //draw bullet until bullet meets target
        if(isActive()) {
            bullet.draw(batch);
            update();
        }
    }

    @Override
    public void update() {

        //set bullet position
        bullet.setX(x);
        bullet.setY(y);

        move();

        if(distance() == 0) {
            setActive(false);
            target.setEnemyBlood(target.getEnemyBlood() - dame);
        }
    }

    public void move() {

        //bullet follows the target until it meet the target and is removed
        if(target.getX() + 50 > x ){
            x += speed;
        }
        else if (target.getX() + 50 < x ){
            x -= speed;
        }
        if(target.getY() + 50 > y ){
            y += speed;
        }
        else if (target.getY() + 50 < y ){
            y -= speed;
        }
    }

    //caculate the distance between bullet and target
    public double distance() {

        double a = target.getX() + 50 - x;
        double b = target.getY() + 50 - y;
        double c = Math.pow(a, 2) + Math.pow(b, 2);
        return Math.sqrt(c);
    }

    public int getDame() {
        return dame;
    }
}