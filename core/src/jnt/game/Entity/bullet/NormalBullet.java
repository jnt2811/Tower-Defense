package jnt.game.Entity.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Entity.Entity;
import jnt.game.Entity.enemy.NormalEnemy;

public class NormalBullet extends Entity {

    protected Sprite bullet;
    protected int dame;
    protected float speed;

    private NormalEnemy target;
    private double distance;

    public NormalBullet(double x, double y, NormalEnemy target) {

        this.x = x;
        this.y = y;
        this.target = target;

        bullet = new Sprite(new Texture(Gdx.files.internal("bullet1.png")));

        // Default
        dame = 1;
        speed = 3;

        setActive(true);
    }

    @Override
    public void draw(SpriteBatch batch) {

        // Draw Bullet Until It Beets the Target
        if(isActive()) {

            bullet.draw(batch);

            update();
        }
    }

    @Override
    public void update() {

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
}