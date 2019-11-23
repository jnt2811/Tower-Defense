package jnt.game.Entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import jnt.game.Entity.Entity;
import jnt.game.Map.Map;

public class NormalEnemy extends Entity implements Disposable {

    protected Sprite enemy;
    protected int blood, reward;
    protected float speed;
    protected float onePixel;
    protected Sprite greenBlood, redBlood;
    protected int oldBlood;

    private int m, n;
    private int direction1, direction2, turn;
    private int[][] Valid;
    private boolean finished = false, random = false;

    public NormalEnemy() {

        enemy = new Sprite(new Texture(Gdx.files.internal("enemy1.png")));
        enemy.setSize(60,60);

        // Default
        blood = 20;
        speed = 3;
        reward = 2;
        setActive(true);

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
    }

    @Override
    public void draw(SpriteBatch batch, float delta) {

        if(isActive()) {

            // Draw Enemy
            enemy.draw(batch);

            // Draw Enemy's Blood Bar
            drawEnemyBlood(batch);
        }
    }

    @Override
    public void update(float delta) {

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

        redBlood.setPosition((float)x, (float)y + 50);
        greenBlood.setPosition((float)x, (float)y + 50);

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
        for (int Y = 0; Y < Map.map.length; Y++) {
            for (int X = 0; X < Map.map[Y].length; X++) {

                if (Map.map[Y][X] == 2) {

                    // Passed square is '-1' in Valid array
                    Valid[Y][X] = -1;

                    y += Y * enemy.getHeight();
                    x += X * enemy.getWidth();

                    m = Y;
                    n = X;

                    direction1 = 1;

                    break;
                }
            }
        }
    }

    // Move based on Each Map's Square
    public void move() {

        if(valid(m,n + 1) && (Map.map[m][n+1] == 1 || Map.map[m][n+1] == 3) && Valid[m][n+1] != -1) turn = 1; //RIGHT
        else if(valid(m,n - 1) && (Map.map[m][n-1] == 1 || Map.map[m][n-1] == 3) && Valid[m][n-1] != -1) turn = 2; //LEFT
        else if(valid(m + 1,n) && (Map.map[m+1][n] == 1 || Map.map[m+1][n] == 3) && Valid[m+1][n] != -1) turn = 3; //UP
        else if(valid(m - 1,n) && (Map.map[m-1][n] == 1 || Map.map[m-1][n] == 3) && Valid[m-1][n] != -1) turn = 4; //DOWN

        if(turn == 1) {
            if(x != enemy.getWidth()*(n+1)) x+=speed;
            if(x >= enemy.getWidth()*(n+1)) {
                x = enemy.getWidth()*(n+1);
                if(Map.map[m][n+1] == 1) {
                    Valid[m][n+1] = -1;
                    n++;
                }
                else if(Map.map[m][n+1] == 3) {
                    if(isActive()) setActive(false);
                    if(!finished) finished = true;
                }
            }
            direction2 = 1;
            turn = 0;
        }

        if(turn == 2) {
            if(x != enemy.getWidth()*(n-1)) x-=speed;
            if(x <= enemy.getWidth()*(n-1)) {
                x = enemy.getWidth()*(n-1);
                if(Map.map[m][n-1] == 1) {
                    Valid[m][n-1] = -1;
                    n--;
                }
                else if(Map.map[m][n-1] == 3) {
//                    if(isActive()) setActive(false);
                    if(!finished) finished = true;
                }
            }
            direction2 = 2;
            turn = 0;
        }

        if(turn == 3) {
            if(y != enemy.getHeight()*(m+1)) y+=speed;
            if(y >= enemy.getHeight()*(m+1)) {
                y = enemy.getHeight()*(m+1);
                if(Map.map[m+1][n] == 1) {
                    Valid[m+1][n] = -1;
                    m++;
                }
                else if(Map.map[m+1][n] == 3) {
//                    if(isActive()) setActive(false);
                    if(!finished) finished = true;
                }
            }
            direction2 = 3;
            turn = 0;
        }

        if(turn == 4) {
            if(y != enemy.getHeight()*(m-1)) y-=speed;
            if(y <= enemy.getHeight()*(m-1)) {
                y = enemy.getHeight()*(m-1);
                if(Map.map[m-1][n] == 1) {
                    Valid[m-1][n] = -1;
                    m--;
                }
                else if(Map.map[m-1][n] == 3) {
//                    if(isActive()) setActive(false);
                    if(!finished) finished = true;
                }
            }
            direction2 = 4;
            turn = 0;
        }
    }

    // Prevent "Screen ERROR"
    static private boolean valid(int i, int j){
        // i = height, j = width
        return (0 <= i &&  i < Gdx.graphics.getHeight()/60 && 0 <= j && j < Gdx.graphics.getWidth()/60 - 6);
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

    public boolean isFinished() {
        return finished;
    }
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getReward() {
        return reward;
    }
    public void setReward(int reward) {
        this.reward = reward;
    }

    @Override
    public void dispose() {
        enemy.getTexture().dispose();
        greenBlood.getTexture().dispose();
        redBlood.getTexture().dispose();
    }
}