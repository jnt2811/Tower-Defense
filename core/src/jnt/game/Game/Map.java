package jnt.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map{
    private Texture grass, land, tree, rock, point, silver;

    public static final int[][] map = {
            {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,4,51,52,4,4,4},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4,53,54,4,4,4},
            {1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,4,61,62,4,4,4},
            {0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,4,63,64,4,4,4},
            {0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,11,12,13,21,22,23},
            {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,14,15,16,24,25,26},
            {0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,17,18,19,27,28,29},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,31,32,33,41,42,43},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,34,35,36,44,45,46},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,37,38,39,47,48,49},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4,4},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4,4},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4,4},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4,4},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4,4},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4,4},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4,4},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4,4},
    };

    public Map() {
        grass = new Texture(Gdx.files.internal("grass.png"));
        land = new Texture(Gdx.files.internal("land.png"));
        tree = new Texture(Gdx.files.internal("tree.png"));
        rock = new Texture(Gdx.files.internal("rock.png"));
        point = new Texture(Gdx.files.internal("point.png"));
        silver = new Texture(Gdx.files.internal("silver.png"));

        // Drag Down the Map
        for (int y=0; y<map.length/2; y++) {
            for (int x=0; x<map[y].length; x++) {
                int a = map[y][x];
                map[y][x] = map[map.length - 1 - y][x];
                map[map.length - 1 - y][x] = a;
            }
        }
    }

    public void draw(SpriteBatch batch) {

        for(int y=0; y<map.length; y++) {
            for(int x = 0; x<map[y].length; x++) {
                if(map[y][x] == 0) batch.draw(grass, x*grass.getWidth(),y*grass.getWidth());
                if(map[y][x] == 1) batch.draw(land, x*land.getWidth(), y*land.getWidth());
                if(map[y][x] == 2) batch.draw(point, x*point.getWidth(), y*point.getWidth());
                if(map[y][x] == 3) batch.draw(point, x*tree.getWidth(), y*tree.getWidth());
                if(map[y][x] == 4) batch.draw(silver, x*rock.getWidth(), y*rock.getWidth());
                if(map[y][x] == 5) batch.draw(rock, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 6) batch.draw(tree, x*silver.getWidth(), y*silver.getWidth());

                for(int i=11; i<= 19; i++) {
                    if(map[y][x] == i)
                        batch.draw(new Texture(Gdx.files.internal("tower"+i+".png")), x*silver.getWidth(), y*silver.getWidth());
                }

                for(int i=21; i<= 29; i++) {
                    if(map[y][x] == i)
                        batch.draw(new Texture(Gdx.files.internal("tower"+i+".png")), x*silver.getWidth(), y*silver.getWidth());
                }

                for(int i=31; i<= 39; i++) {
                    if(map[y][x] == i)
                        batch.draw(new Texture(Gdx.files.internal("tower"+i+".png")), x*silver.getWidth(), y*silver.getWidth());
                }

                for(int i=41; i<= 49; i++) {
                    if(map[y][x] == i)
                        batch.draw(new Texture(Gdx.files.internal("tower"+i+".png")), x*silver.getWidth(), y*silver.getWidth());
                }

                for(int i=51; i<= 54; i++) {
                    if(map[y][x] == i)
                        batch.draw(new Texture(Gdx.files.internal("heart"+i+".png")), x*silver.getWidth(), y*silver.getWidth());
                }

                for(int i=61; i<= 64; i++) {
                    if(map[y][x] == i)
                        batch.draw(new Texture(Gdx.files.internal("gold"+i+".png")), x*silver.getWidth(), y*silver.getWidth());
                }
            }
        }
    }
}
