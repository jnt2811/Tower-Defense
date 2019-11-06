package jnt.game.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jnt.game.Entities.Enemy;
import jnt.game.Entities.Tower;

public class Map {

    private SpriteBatch batch;
    private Texture grass, sand;
    private Enemy enemy;
    private Tower tower;

    public Map(SpriteBatch batch) {
        this.batch = batch;
        grass = new Texture(Gdx.files.internal("grass.png"));
        sand = new Texture(Gdx.files.internal("sand.png"));

        enemy = new Enemy(batch);
        tower = new Tower(batch);
    }

    public static final int[][] map = {
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,1,1,1,1,1,0,0,0,0,0,0,0},
        {0,0,0,1,0,0,0,1,0,0,0,1,1,1,1,1},
        {0,0,0,1,0,0,0,1,0,0,0,1,0,0,0},
        {1,1,1,1,0,0,0,1,0,0,0,1,0,0,0},
        {0,0,0,0,0,0,0,1,0,0,0,1,0,0,0},
        {0,0,0,0,0,0,0,1,1,1,1,1,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };

    public void draw() {
        for(int y = 0; y<map.length; y++) {
            for(int x = 0; x<map[y].length; x++) {
                if(map[y][x] == 0) batch.draw(grass, x*grass.getWidth(),y*grass.getWidth());
                if(map[y][x] == 1) batch.draw(sand, x*sand.getWidth(), y*grass.getWidth());
                if(map[y][x] == -1) batch.draw(sand, x*sand.getWidth(), y*grass.getWidth());
            }
        }

        tower.draw();
        enemy.draw();
    }
}
