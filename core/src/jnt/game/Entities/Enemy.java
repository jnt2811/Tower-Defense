package jnt.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Main.Map;

public class Enemy extends Entity {

    private Texture enemy, greenBlood, redBlood;
    private int X,Y,m,n,speed = 2,a,b, bloodEnemy;
    private boolean alive = true;

    //Mảng đánh dấu các ô đã đi
    private int[][] Valid;

    public Enemy(SpriteBatch batch) {
        super(batch);

        enemy = new Texture(Gdx.files.internal("skeleton.png"));
        greenBlood = new Texture(Gdx.files.internal("greenBlood.png"));
        redBlood = new Texture(Gdx.files.internal("redBlood.png"));

        for(int y = 0; y<Map.map.length; y++) {
            for(int x = 0; x<Map.map[y].length; x++) {
                b++;
            }
            a++;
        }

        Valid = new int[a][b];

        X = 0; Y = 0;
        m = 0; n = 0;

        bloodEnemy = 24;

        start();
    }

    //tìm điểm bắt đầu
    public void start() {
        for(int y = 0; y< Map.map.length; y++) {
            if(Map.map[y][0] == 1) {

                //ô đã đi sẽ được đánh dấu là -1 trên mảng Valid
                Valid[y][0] = -1;

                Y += y*enemy.getHeight();
                X += 0*enemy.getWidth();

                m=y;

                break;
            }
        }
    }

    //khoanh vùng tránh lỗi out màn hình
    static private boolean valid(int i, int j){
        return (0 <= i &&  i < 7 && 0 <= j && j < 15);
    }

    //di chuyển dựa trên các ô của map
    public void move() {
        if(valid(m, n + 1) && Map.map[m][n+1] == 1 && (Valid[m][n+1] != -1)) {
            if(X != enemy.getWidth()*(n+1)) X+=speed;
            if(X == enemy.getWidth()*(n+1)) {
                Valid[m][n+1] = -1;
                n++;
            }
        }
        if(valid(m, n - 1) && Map.map[m][n-1] == 1 && (Valid[m][n-1] != -1)) {
            if(X != enemy.getWidth()*(n-1)) X-=speed;
            if(X == enemy.getWidth()*(n-1)) {
                Valid[m][n-1] = -1;
                n--;
            }
        }
        if(valid(m + 1, n) && Map.map[m+1][n] == 1 && Valid[m+1][n] != -1) {
            if(Y != enemy.getHeight()*(m+1)) Y+=speed;
            if(Y == enemy.getHeight()*(m+1)) {
                Valid[m+1][n] = -1;
                m++;
            }
        }
        if(valid(m - 1, n ) && Map.map[m-1][n] == 1 && (Valid[m-1][n] != -1)) {
            if(Y != enemy.getHeight()*(m-1)) Y-=speed;
            if(Y == enemy.getHeight()*(m-1)) {
                Valid[m-1][n] = -1;
                m--;
            }
        }
    }

    @Override
    public void draw() {
        batch.draw(enemy, X , 600 - Y);
        move();

        //draw enemy's blood bar on it's head
        for(int i=0; i<bloodEnemy; i++) {
            int E= X + i*3, F = Y +80;

            if(i < bloodEnemy) batch.draw(greenBlood, E, 780 - F);
            else batch.draw(redBlood, E, 780 - F);
        }
    }

    @Override
    public void update() {

    }

    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getX() {
        return X;
    }
    public int getY() {
        return 600-Y;
    }

    public int decreaseBloodEnemy(int damage) {
        return bloodEnemy-=damage;
    }
    public int getBloodEnemy() {
        return bloodEnemy;
    }
}
