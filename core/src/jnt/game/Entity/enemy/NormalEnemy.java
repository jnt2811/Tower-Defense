package jnt.game.Entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Entity.Entity;
import jnt.game.Map;

public class NormalEnemy extends Entity {

    protected Sprite enemy;
    protected int blood;
    protected float speed;

    private int m, n, oldBlood;
    private Sprite greenBlood, redBlood;
    private int direction1, direction2;
    private int[][] Valid;
    private boolean decreaseHealth = false;
    private float onePixel;

    public NormalEnemy() {

        enemy = new Sprite(new Texture(Gdx.files.internal("enemy1.png")));

        // Default
        blood = 20;
        speed = 2;


        greenBlood = new Sprite(new Texture(Gdx.files.internal("greenBlood.png")));
        redBlood = new Sprite(new Texture(Gdx.files.internal("redBlood.png")));

        // Set Blood Bars' Scale Better
        greenBlood.setScale(0.7f,1);
        redBlood.setScale(0.7f,1);

        // Calculate One Pixel compared to Blood Bar's Width
        onePixel = greenBlood.getWidth() / blood;

        // This Variable is the History of Enemy's Blood
        oldBlood = blood;

        int count = 0;
        for(int y = 0; y<Map.map.length; y++)
            for(int x = 0; x<Map.map[y].length; x++) count++;
        // History of Steps
        Valid = new int[Map.map.length][count];

        // Look for the Start Point
        start();

        setActive(true);
    }

    @Override
    public void draw(SpriteBatch batch) {

        if(isActive()) {

            // Draw Enemy
            enemy.draw(batch);

            // Draw Enemy's Blood Bar
            drawEnemyBlood(batch);

            update();
        }
    }

    @Override
    public void update() {

        enemy.setPosition((float)x, (float)y);

        // Automatically Look for the Way to the Final Point
        move();

        // Rotate When Enemy Turns LEFT or RIGHT
        enemyRotation();

        // Die if Blood = 0
        if (getBlood() <= 0) setActive(false);
    }


    public void drawEnemyBlood(SpriteBatch batch) {

        redBlood.draw(batch);
        greenBlood.draw(batch);

        redBlood.setPosition((float)x, (float)y + 85);
        greenBlood.setPosition((float)x, (float)y + 85);

        if (blood < oldBlood) {
            float newBlood = blood*onePixel;
            greenBlood.setSize(newBlood, greenBlood.getHeight());
            oldBlood = blood;
        }
    }


    public void enemyRotation() {

        if(direction1 != direction2) {
            if(direction2 == 1) enemy.setRotation(0);
            if(direction2 == 2) enemy.setRotation(180);
            if(direction2 == 3) enemy.setRotation(90);
            if(direction2 == 4) enemy.setRotation(-90);
        }
        direction1 = direction2;
    }


    public void start() {
        for(int Y = 0; Y< Map.map.length; Y++) {
            if(Map.map[Y][0] == 2) {

                // Passed square is '-1' in Valid array
                Valid[Y][0] = -1;

                y += Y*enemy.getHeight();
                x += 0*enemy.getWidth();

                m=Y;

                direction1 = 1;

                break;
            }
        }
    }


    // Move based on Each Map's Square
    public void move() {

        // RIGHT
        if(valid(m, n + 1) && Map.map[m][n+1] == 1 && (Valid[m][n+1] != -1)) {
            if(x != enemy.getWidth()*(n+1)) x+=speed;
            direction2 = 1;
            if(x == enemy.getWidth()*(n+1)) {
                Valid[m][n+1] = -1;
                n++;
            }
        }

        // LEFT
        if(valid(m, n - 1) && Map.map[m][n-1] == 1 && (Valid[m][n-1] != -1)) {
            if(x != enemy.getWidth()*(n-1)) x-=speed;
            direction2 = 2;
            if(x == enemy.getWidth()*(n-1)) {
                Valid[m][n-1] = -1;
                n--;
            }
        }

        // UP
        if(valid(m + 1, n) && Map.map[m+1][n] == 1 && Valid[m+1][n] != -1) {
            if(y != enemy.getHeight()*(m+1)) y+=speed;
            direction2 = 3;
            if(y == enemy.getHeight()*(m+1)) {
                Valid[m+1][n] = -1;
                m++;
            }
        }

        // DOWN
        if(valid(m - 1, n ) && Map.map[m-1][n] == 1 && (Valid[m-1][n] != -1)) {
            if(y != enemy.getHeight()*(m-1)) y-=speed;
            direction2 = 4;
            if(y == enemy.getHeight()*(m-1)) {
                Valid[m-1][n] = -1;
                m--;
            }
        }

        // Get DISAPPEARED When Enemy Reaches the Final Point
        if(valid(m, n + 1) && Map.map[m][n+1] == 2 && (Valid[m][n+1] != -1)) {
            if(x != enemy.getWidth()*(n+1)) x+=speed;
            if(x == enemy.getWidth()*(n+1)) {
                if(isActive()) {
                    setActive(false);

                    // ALso Decrease Player's Health
                    if(!decreaseHealth) decreaseHealth = true;
                }
            }
        }
    }


    // Prevent "Screen ERROR"
    static private boolean valid(int i, int j){
        return (0 <= i &&  i < 7 && 0 <= j && j < 9);
    }


    //
    public double getWidth() {
        return enemy.getWidth();
    }
    public double getHeight() {
        return enemy.getHeight();
    }

    public int getBlood() {
        return blood;
    }
    public void setBlood(int blood) {
        this.blood = blood;
    }

    public boolean isDecreaseHealth() {
        return decreaseHealth;
    }
    public void setDecreaseHealth(boolean decreaseHealth) {
        this.decreaseHealth = decreaseHealth;
    }
}
