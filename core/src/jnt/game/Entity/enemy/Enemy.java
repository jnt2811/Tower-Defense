package jnt.game.Entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Entity.Entity;
import jnt.game.Map;

public abstract class Enemy extends Entity {

    private Sprite enemy;
    protected Texture greenBlood, redBlood;
    protected int id, m, n, xBlood, defaultBlood;
    private int enemyBlood, direction1, direction2;
    protected int[][] Valid;
    private static final int speed = 2;
    private boolean decreaseHealth = false;

    public Enemy(int id, int enemyBlood) {

        this.id = id;
        this.enemyBlood = enemyBlood;

        if(this.id == 1) enemy = new Sprite(new Texture(Gdx.files.internal("enemy1.png")));
        if(this.id == 2) enemy = new Sprite(new Texture(Gdx.files.internal("enemy2.png")));
        if(this.id == 3) enemy = new Sprite(new Texture(Gdx.files.internal("enemy3.png")));
        if(this.id == 4) enemy = new Sprite(new Texture(Gdx.files.internal("enemy4.png")));

        greenBlood = new Texture(Gdx.files.internal("greenBlood.png"));
        redBlood = new Texture(Gdx.files.internal("redBlood.png"));

        defaultBlood = enemyBlood;

        int count = 0;

        for(int y = 0; y<Map.map.length; y++)
            for(int x = 0; x<Map.map[y].length; x++) count++;

        Valid = new int[Map.map.length][count];
        m = 0; n = 0;

        //history of steps
        direction1 = 0; direction2 = 0;

        start();

        //default
        setActive(true);
    }

    @Override
    public void draw(SpriteBatch batch, float delta) {

        if(isActive()) {
            enemy.draw(batch);
            drawEnemyBlood(batch);

            update();
        }
    }

    @Override
    public void update() {
        enemy.setX(x);
        enemy.setY(y);

        move();
        enemyRotation();

        if (getEnemyBlood() == 0) setActive(false);
    }

    //draw blood bar
    public void drawEnemyBlood(SpriteBatch batch) {
        if(id == 1) xBlood = x + 15;
        if(id == 2) xBlood = x + 15;
        if(id == 3) xBlood = x + 15;
        if(id == 4) xBlood = x + 10;

        for (int i=0; i< defaultBlood; i++) {
            int X= xBlood + i*3, Y = y + 80;
            batch.draw(redBlood, X , Y);
        }
        for(int i=0; i < enemyBlood; i++) {
            int X= xBlood + i*3, Y = y + 80;
            if(i < enemyBlood) batch.draw(greenBlood, X, Y);
        }
    }

    //rotate when it turns left or right
    public void enemyRotation() {

        if(direction1 != direction2) {
            if(direction2 == 1) enemy.setRotation(0);
            if(direction2 == 2) enemy.setRotation(180);
            if(direction2 == 3) enemy.setRotation(90);
            if(direction2 == 4) enemy.setRotation(-90);
        }
        direction1 = direction2;
    }

    //look for the start point
    public void start() {
        for(int Y = 0; Y< Map.map.length; Y++) {
            if(Map.map[Y][0] == 2) {

                //passed square is '-1' in Valid array
                Valid[Y][0] = -1;

                y += Y*enemy.getHeight();
                x += 0*enemy.getWidth();

                m=Y;

                direction1 = 1;

                break;
            }
        }
    }

    //prevent "screen error"
    static private boolean valid(int i, int j){
        return (0 <= i &&  i < 7 && 0 <= j && j < 9);
    }

    //move based on each map's square
    public void move() {
        //right
        if(valid(m, n + 1) && Map.map[m][n+1] == 1 && (Valid[m][n+1] != -1)) {
            if(x != enemy.getWidth()*(n+1)) x+=speed;
            direction2 = 1;
            if(x == enemy.getWidth()*(n+1)) {
                Valid[m][n+1] = -1;
                n++;
            }
        }

        //left
        if(valid(m, n - 1) && Map.map[m][n-1] == 1 && (Valid[m][n-1] != -1)) {
            if(x != enemy.getWidth()*(n-1)) x-=speed;
            direction2 = 2;
            if(x == enemy.getWidth()*(n-1)) {
                Valid[m][n-1] = -1;
                n--;
            }
        }

        //up
        if(valid(m + 1, n) && Map.map[m+1][n] == 1 && Valid[m+1][n] != -1) {
            if(y != enemy.getHeight()*(m+1)) y+=speed;
            direction2 = 3;
            if(y == enemy.getHeight()*(m+1)) {
                Valid[m+1][n] = -1;
                m++;
            }
        }

        //down
        if(valid(m - 1, n ) && Map.map[m-1][n] == 1 && (Valid[m-1][n] != -1)) {
            if(y != enemy.getHeight()*(m-1)) y-=speed;
            direction2 = 4;
            if(y == enemy.getHeight()*(m-1)) {
                Valid[m-1][n] = -1;
                m--;
            }
        }

        //get disappeared when reaching the final point
        if(valid(m, n + 1) && Map.map[m][n+1] == 2 && (Valid[m][n+1] != -1)) {
            if(x != enemy.getWidth()*(n+1)) x+=speed;
            if(x == enemy.getWidth()*(n+1)) {
                if(isActive()) {
                    setActive(false);
                    if(!decreaseHealth) decreaseHealth = true;
                }
            }
        }
    }

    public int getEnemyBlood() {
        return enemyBlood;
    }
    public void setEnemyBlood(int enemyBlood) {
        this.enemyBlood = enemyBlood;
    }

    public boolean isDecreaseHealth() {
        return decreaseHealth;
    }
    public void setDecreaseHealth(boolean decreaseHealth) {
        this.decreaseHealth = decreaseHealth;
    }
}
