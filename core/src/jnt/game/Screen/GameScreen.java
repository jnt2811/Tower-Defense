package jnt.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Timer;
import jnt.game.Entities.Bullet;
import jnt.game.Entities.Enemy;
import jnt.game.Entities.Tower;
import jnt.game.Main.Map;
import jnt.game.Main.TowerDefense;

import java.util.ArrayList;

public class GameScreen implements Screen {

    private TowerDefense game;
    private Map map;
    private Enemy enemy;
    private Tower tower;
    private double a, b, c;
    private int x, y;
    private boolean active;
    private ArrayList<Enemy> enemies;

    public GameScreen(TowerDefense game) {
        this.game = game;

        map = new Map(game.batch);
        enemy = new Enemy(game.batch);
        tower = new Tower(game.batch);

        x = tower.getX() + 50;
        y = tower.getY() + 50;

        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(game.batch));
    }

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        map.draw();

        tower.draw();

        if(enemy.isAlive()) {
            for (int i=0; i<enemies.size(); i++) {
                enemy.draw();
            }
        }

        collision();

        game.batch.end();
    }

    public void collision() {
        if( distance() <= 250) {
            active = true;
        }

        //va chạm
        for (int i=0; i<tower.getBulletsSize(); i++) {

            //xét điều kiện va chạm
            if(tower.getEachX(i) >= enemy.getX() + 10  && tower.getEachX(i) <= enemy.getX() + 70
                    && tower.getEachY(i) >= enemy.getY() + 30 && tower.getEachY(i) <= enemy.getY() + 70) {

                //xoá viên đạn va chạm vào địch (viên đạn đó biến mất)
                tower.setEachRemove(i, true);

                enemy.decreaseBloodEnemy(tower.getBulletDamage(i));
                System.out.println(enemy.getBloodEnemy());
            }

            if(enemy.getBloodEnemy() == 0) enemy.setAlive(false);
        }

        //nếu đạn thoả mãn điều kiện (active) thì sẽ vẽ đạn ra (trong trường hợp địch còn sống)
        if(active) tower.drawBullets(enemy.isAlive());
    }

    //pytago tính khoảng cách tháp và địch
    public double distance() {
        a = Math.abs(enemy.getX() - tower.getX());
        b = Math.abs(enemy.getY() - tower.getY());
        c = Math.pow(a, 2) + Math.pow(b, 2);
        return Math.sqrt(c);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {

    }
    @Override
    public void resize(int width, int height) {

    }
    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }
    @Override
    public void hide() {

    }
}
