package jnt.game.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {

    private SpriteBatch batch;
    private Texture grass, land;

    public Map(SpriteBatch batch) {
        this.batch = batch;
        grass = new Texture(Gdx.files.internal("grass.png"));
        land = new Texture(Gdx.files.internal("land.png"));
    }

    public static final int[][] map = {
            {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
            {1,1,0,0,0,1,1,1,1,1,0,0,0,1,0},
            {0,1,0,0,0,1,0,0,0,1,0,0,0,1,0},
            {0,1,1,1,1,1,0,0,0,1,1,1,1,1,0},
    };

    public void draw() {
        for(int y = 0; y<map.length; y++) {
            for(int x = 0; x<map[y].length; x++) {
                if(map[y][x] == 0) batch.draw(grass, x*grass.getWidth(),600 - y*grass.getWidth());
                if(map[y][x] == 1) batch.draw(land, x*land.getWidth(), 600 - y*grass.getWidth());
            }
        }
    }
}