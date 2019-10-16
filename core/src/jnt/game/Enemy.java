package jnt.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemy extends GameEntity {

    private TextureAtlas enemy;
    private Animation enemyA;
    private Texture greenBlood, redBlood;
    private int bloodEnemy = 24, p;
    private  boolean dead = false, remove = false;
    private float speed = 10, frame = 10.0f, elapsedTime = 0;

    public Enemy(int id, float x, float y, int p) {

        //
        super(id, x, y);

        //enemies
        if(id == 1) {
            enemy = new TextureAtlas(Gdx.files.internal("Enemy1.atlas"));
            enemyA = new Animation(1/frame, enemy.getRegions());
        }
        if(id == 2) {
            enemy = new TextureAtlas(Gdx.files.internal("Enemy2.atlas"));
            enemyA = new Animation(1/frame, enemy.getRegions());
        }
        if(id == 3) {
            enemy = new TextureAtlas(Gdx.files.internal("Enemy3.atlas"));
            enemyA = new Animation(1/frame, enemy.getRegions());
        }
        if(id == 4) {
            enemy = new TextureAtlas(Gdx.files.internal("Enemy4.atlas"));
            enemyA = new Animation(1/frame, enemy.getRegions());
        }

        //blood bar
        greenBlood = new Texture(Gdx.files.internal("greenBlood.png"));
        redBlood = new Texture(Gdx.files.internal("redBlood.png"));

        //position
        if(y > 500) this.p = 0;
        if(y < 500) this.p = 1;
    }

    @Override
    public void render(SpriteBatch batch) {

        //draw enemy
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw((TextureRegion) enemyA.getKeyFrame(elapsedTime, true), x, y);

        //draw enemy's blood bar on it's head
        for(int i=0; i<24; i++) {
            float X= x + i*3, Y = y +80;

            if(id == 4) {
                X = x + i*5;
                Y = y + 150;
            }

            if(i < bloodEnemy) batch.draw(greenBlood, X, Y);
            else batch.draw(redBlood, X, Y);
        }
    }

    @Override
    public void update() {

        //move 1
        if (p == 0){
            if (x < 425) x = x + speed;
            if (x >= 425 && x <= 435) y = y - speed;
            if (x < 1000 && y >= 555 && y <= 565) x = x + speed;
            if (x >= 940 && x <= 950) y = y - speed;
            if (y >= 190 && y <= 200) x = x + speed;
            if (x >= 1450 && x <= 1460) y = y + speed;
            if (x >= 1450 && y >= 710 && y <= 720) x = x + speed;
        }
        //move 2
        if (p == 1){
            if (x < 425) x = x + speed;
            if (x >= 425 && x <= 435) y = y + speed;
            if (x < 1000 && y >= 555 && y <= 565) x = x + speed;
            if (x >= 940 && x <= 950) y = y - speed;
            if (y >= 190 && y <= 200) x = x + speed;
            if (x >= 1450 && x <= 1460) y = y + speed;
            if (x >= 1450 && y >= 710 && y <= 720) x = x + speed;
        }

        //update score & health
        if (x >= Gdx.graphics.getWidth() && x <= Gdx.graphics.getWidth()+10 && remove == false) {
            StageScreen.healthNum--;
            remove = true;
        }
        if(bloodEnemy == 0) {
            dead = true;
            StageScreen.goldNum += 2;
        }

        //function : remove the enemy if it's dead
    }
}
