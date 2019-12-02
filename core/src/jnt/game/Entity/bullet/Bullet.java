package jnt.game.Entity.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import jnt.game.Entity.Entity;
import jnt.game.Entity.enemy.NormalEnemy;

public class Bullet extends Entity implements Disposable {

    private Sprite bullet;
    private int dame;
    private static final float speed = 10;

    private NormalEnemy target;
    private double distance;

    public Bullet(String type, double x, double y, NormalEnemy target) {

        this.x = x;
        this.y = y;
        this.target = target;

        setBulletType(type);

        // Default
        setActive(true);
    }

    @Override
    public void draw(SpriteBatch batch, float delta) {

        // Draw Bullet Until It Beets the Target
        if(isActive()) {

            bullet.draw(batch);

            update(delta);
        }
    }

    @Override
    public void update(float delta) {

        // Set Bullet's Position
        bullet.setX((float)x);
        bullet.setY((float)y);

        // Bullet Follows the Target Until It Meets the Target and Is Removed After that
        move();

        // Decrease Target's Blood
        if(distance == 0) {
            setActive(false);
            target.setBlood(target.getBlood() - dame);
        }
    }


    public void setBulletType(String type) {

        if(type == "normal") {
            bullet = new Sprite(new Texture(Gdx.files.internal("bullet1.png")));

            // Default
            dame = 3;
        }
        if(type == "smg") {
            bullet = new Sprite(new Texture(Gdx.files.internal("bullet2.png")));

            // Default
            dame = 3;
        }
        if(type == "rifle") {
            bullet = new Sprite(new Texture(Gdx.files.internal("bullet3.png")));

            // Default
            dame = 10;
        }
        if(type == "mortar") {
            bullet = new Sprite(new Texture(Gdx.files.internal("bullet4.png")));

            // Default
            dame = 50;
        }
    }


    public void move() {

        // Calculate the Distance between Bullet and the Target
        double xPos = target.getX() + target.getWidth()/2 - x;
        double yPos = target.getY() + target.getHeight()/2 - y;
        distance = Math.sqrt(Math.pow(xPos, 2) + Math.pow(yPos, 2));

        // Calculate to Make Bullet Keep Following the Target
        double speedX = xPos / distance * speed;
        double speedY = yPos / distance * speed;

        if(Math.abs(xPos) >= Math.abs(speedX) && Math.abs(yPos) >= Math.abs(speedY)) {
            x += speedX;
            y += speedY;
        }
        // Prevent Bullet Passing Over the Target
        else {
            x = target.getX() + target.getWidth()/2;
            y = target.getY() + target.getHeight()/2;
        }
    }


    public int getDame() {
        return dame;
    }

    @Override
    public void dispose() {
        target.dispose();
        bullet.getTexture().dispose();
    }
}