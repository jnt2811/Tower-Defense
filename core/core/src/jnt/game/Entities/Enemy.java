package jnt.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Main.Map;

public class Enemy extends Entity {

    private Texture enemy;
    private int X,Y,m,n,speed = 2;

    public Enemy(SpriteBatch batch) {
        super(batch);

        enemy = new Texture(Gdx.files.internal("skeleton.png"));

        X = 0; Y = 0;
        m = 0; n = 0;

        start();
    }

    public void start() {
        for(int y = 0; y< Map.map.length; y++) {
            if(Map.map[y][0] == 1) {
                Y = y*enemy.getHeight();
                m=y;
                break;
            }
        }
    }

    public void move() {
        if(Map.map[m][n+1] == 1) {
            if(X != enemy.getWidth()*(n+1)) X += speed;
            if(X == enemy.getWidth()*(n+1)) {
                Map.map[m][n+1] = -1;
                n++;
            }
        }
        if(Map.map[m+1][n] == 1) {
            if(Y != enemy.getHeight()*(m+1)) Y += speed;
            if(Y == enemy.getHeight()*(m+1)) {
                Map.map[m+1][n] = -1;
                m++;
            }
        }
        if(Map.map[m-1][n] == 1) {
            if(Y != enemy.getHeight()*(m-1)) Y -= speed;
            if(Y == enemy.getHeight()*(m-1)) {
                Map.map[m-1][n] = -1;
                m--;
            }
        }
    }

    @Override
    public void draw() {
        batch.draw(enemy, X , Y);
        move();
    }

    @Override
    public void update() {

    }
}
