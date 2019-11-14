package jnt.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map{
    private Texture grass, land, tree, rock, point, silver;
    private Texture tower1, tower2, tower3, tower4;
    private Texture heart, gold;

    public static final int[][] map = {
            {2,1,1,1,1,1,1,1,1,10,5},
            {0,0,0,0,0,0,0,0,1,11,5},
            {1,1,1,1,1,0,3,0,1,6,7},
            {1,0,0,0,1,0,0,0,1,8,9},
            {1,0,4,0,1,1,1,1,1,5,5},
            {1,0,0,0,0,0,0,0,0,5,5},
            {1,1,1,1,1,1,1,1,2,5,5},
    };

    public Map() {
        grass = new Texture(Gdx.files.internal("grass.png"));
        land = new Texture(Gdx.files.internal("land.png"));
        tree = new Texture(Gdx.files.internal("tree.png"));
        rock = new Texture(Gdx.files.internal("rock.png"));
        point = new Texture(Gdx.files.internal("point.png"));
        silver = new Texture(Gdx.files.internal("silver.png"));

        tower1 = new Texture(Gdx.files.internal("tower1.png"));
        tower2 = new Texture(Gdx.files.internal("tower2.png"));
        tower3 = new Texture(Gdx.files.internal("tower3.png"));
        tower4 = new Texture(Gdx.files.internal("tower4.png"));

        heart = new Texture(Gdx.files.internal("heart.png"));
        gold = new Texture(Gdx.files.internal("gold.png"));

        for (int y=0; y<map.length/2; y++) {
            for (int x=0; x<map[y].length; x++) {
                int a = map[y][x];
                map[y][x] = map[6 - y][x];
                map[6 - y][x] = a;
            }
        }
    }

    public void draw(SpriteBatch batch) {

        for(int y=0; y<map.length; y++) {
            for(int x = 0; x<map[y].length; x++) {
                if(map[y][x] == 0) batch.draw(grass, x*grass.getWidth(),y*grass.getWidth());
                if(map[y][x] == 1) batch.draw(land, x*land.getWidth(), y*land.getWidth());
                if(map[y][x] == 2) batch.draw(point, x*point.getWidth(), y*point.getWidth());
                if(map[y][x] == 3) batch.draw(tree, x*tree.getWidth(), y*tree.getWidth());
                if(map[y][x] == 4) batch.draw(rock, x*rock.getWidth(), y*rock.getWidth());
                if(map[y][x] == 5) batch.draw(silver, x*silver.getWidth(), y*silver.getWidth());

                if(map[y][x] == 6) batch.draw(tower1, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 7) batch.draw(tower2, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 8) batch.draw(tower3, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 9) batch.draw(tower4, x*silver.getWidth(), y*silver.getWidth());

                if(map[y][x] == 10) batch.draw(heart, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 11) batch.draw(gold, x*silver.getWidth(), y*silver.getWidth());
            }
        }
    }
}
