package jnt.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import java.util.concurrent.atomic.AtomicReference;

public class Map implements Disposable {
    private Texture grass, land, tree, rock, point, silver;
    private Texture tower11, tower12, tower13, tower14, tower15, tower16, tower17, tower18, tower19;
    private Texture tower21, tower22, tower23, tower24, tower25, tower26, tower27, tower28, tower29;
    private Texture tower31, tower32, tower33, tower34, tower35, tower36, tower37, tower38, tower39;
    private Texture tower41, tower42, tower43, tower44, tower45, tower46, tower47, tower48, tower49;
    private Texture heart51, heart52, heart53, heart54;
    private Texture gold61, gold62, gold63, gold64;

    public static final int[][] map = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,51,52,4,4,4},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,53,54,4,4,4},
            {2,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,4,61,62,4,4,4},
            {0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,63,64,4,4,4},
            {0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,11,12,13,21,22,23},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,14,15,16,24,25,26},
            {0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,0,0,17,18,19,27,28,29},
            {0,0,0,0,0,0,1,0,0,1,1,1,1,1,1,0,0,1,0,0,31,32,33,41,42,43},
            {0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,1,0,0,34,35,36,44,45,46},
            {0,0,1,1,1,1,1,0,0,1,0,0,0,0,1,0,0,1,0,0,37,38,39,47,48,49},
            {0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,4,4,4,4,4,4},
            {0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,4,4,4,4,4,4},
            {0,0,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,0,0,4,4,4,4,4,4},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4,4},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4,4},
    };

    public Map() {
        grass = new Texture(Gdx.files.internal("grass.png"));
        land = new Texture(Gdx.files.internal("land.png"));
        tree = new Texture(Gdx.files.internal("tree.png"));
        rock = new Texture(Gdx.files.internal("rock.png"));
        point = new Texture(Gdx.files.internal("point.png"));
        silver = new Texture(Gdx.files.internal("silver.png"));

        tower11 = new Texture(Gdx.files.internal("tower11.png"));
        tower12 = new Texture(Gdx.files.internal("tower12.png"));
        tower13 = new Texture(Gdx.files.internal("tower13.png"));
        tower14 = new Texture(Gdx.files.internal("tower14.png"));
        tower15 = new Texture(Gdx.files.internal("tower15.png"));
        tower16 = new Texture(Gdx.files.internal("tower16.png"));
        tower17 = new Texture(Gdx.files.internal("tower17.png"));
        tower18 = new Texture(Gdx.files.internal("tower18.png"));
        tower19 = new Texture(Gdx.files.internal("tower19.png"));

        tower21 = new Texture(Gdx.files.internal("tower21.png"));
        tower22 = new Texture(Gdx.files.internal("tower22.png"));
        tower23 = new Texture(Gdx.files.internal("tower23.png"));
        tower24 = new Texture(Gdx.files.internal("tower24.png"));
        tower25 = new Texture(Gdx.files.internal("tower25.png"));
        tower26 = new Texture(Gdx.files.internal("tower26.png"));
        tower27 = new Texture(Gdx.files.internal("tower27.png"));
        tower28 = new Texture(Gdx.files.internal("tower28.png"));
        tower29 = new Texture(Gdx.files.internal("tower29.png"));

        tower31 = new Texture(Gdx.files.internal("tower31.png"));
        tower32 = new Texture(Gdx.files.internal("tower32.png"));
        tower33 = new Texture(Gdx.files.internal("tower33.png"));
        tower34 = new Texture(Gdx.files.internal("tower34.png"));
        tower35 = new Texture(Gdx.files.internal("tower35.png"));
        tower36 = new Texture(Gdx.files.internal("tower36.png"));
        tower37 = new Texture(Gdx.files.internal("tower37.png"));
        tower38 = new Texture(Gdx.files.internal("tower38.png"));
        tower39 = new Texture(Gdx.files.internal("tower39.png"));

        tower41 = new Texture(Gdx.files.internal("tower41.png"));
        tower42 = new Texture(Gdx.files.internal("tower42.png"));
        tower43 = new Texture(Gdx.files.internal("tower43.png"));
        tower44 = new Texture(Gdx.files.internal("tower44.png"));
        tower45 = new Texture(Gdx.files.internal("tower45.png"));
        tower46 = new Texture(Gdx.files.internal("tower46.png"));
        tower47 = new Texture(Gdx.files.internal("tower47.png"));
        tower48 = new Texture(Gdx.files.internal("tower48.png"));
        tower49 = new Texture(Gdx.files.internal("tower49.png"));

        heart51 = new Texture(Gdx.files.internal("heart51.png"));
        heart52 = new Texture(Gdx.files.internal("heart52.png"));
        heart53 = new Texture(Gdx.files.internal("heart53.png"));
        heart54 = new Texture(Gdx.files.internal("heart54.png"));

        gold61 = new Texture(Gdx.files.internal("gold61.png"));
        gold62 = new Texture(Gdx.files.internal("gold62.png"));
        gold63 = new Texture(Gdx.files.internal("gold63.png"));
        gold64 = new Texture(Gdx.files.internal("gold64.png"));

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

                if(map[y][x] == 11) batch.draw(tower11, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 12) batch.draw(tower12, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 13) batch.draw(tower13, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 14) batch.draw(tower14, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 15) batch.draw(tower15, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 16) batch.draw(tower16, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 17) batch.draw(tower17, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 18) batch.draw(tower18, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 19) batch.draw(tower19, x*silver.getWidth(), y*silver.getWidth());

                if(map[y][x] == 21) batch.draw(tower21, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 22) batch.draw(tower22, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 23) batch.draw(tower23, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 24) batch.draw(tower24, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 25) batch.draw(tower25, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 26) batch.draw(tower26, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 27) batch.draw(tower27, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 28) batch.draw(tower28, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 29) batch.draw(tower29, x*silver.getWidth(), y*silver.getWidth());

                if(map[y][x] == 31) batch.draw(tower31, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 32) batch.draw(tower32, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 33) batch.draw(tower33, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 34) batch.draw(tower34, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 35) batch.draw(tower35, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 36) batch.draw(tower36, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 37) batch.draw(tower37, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 38) batch.draw(tower38, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 39) batch.draw(tower39, x*silver.getWidth(), y*silver.getWidth());

                if(map[y][x] == 41) batch.draw(tower41, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 42) batch.draw(tower42, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 43) batch.draw(tower43, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 44) batch.draw(tower44, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 45) batch.draw(tower45, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 46) batch.draw(tower46, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 47) batch.draw(tower47, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 48) batch.draw(tower48, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 49) batch.draw(tower49, x*silver.getWidth(), y*silver.getWidth());

                if(map[y][x] == 51) batch.draw(heart51, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 52) batch.draw(heart52, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 53) batch.draw(heart53, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 54) batch.draw(heart54, x*silver.getWidth(), y*silver.getWidth());

                if(map[y][x] == 61) batch.draw(gold61, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 62) batch.draw(gold62, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 63) batch.draw(gold63, x*silver.getWidth(), y*silver.getWidth());
                if(map[y][x] == 64) batch.draw(gold64, x*silver.getWidth(), y*silver.getWidth());
            }
        }
    }

    @Override
    public void dispose() {
        grass.dispose();
        land.dispose();
        tree.dispose();
        rock.dispose();
        point.dispose();
        silver.dispose();

        tower11.dispose();
        tower12.dispose();
        tower13.dispose();
        tower14.dispose();
        tower15.dispose();
        tower16.dispose();
        tower17.dispose();
        tower18.dispose();
        tower19.dispose();

        tower21.dispose();
        tower22.dispose();
        tower23.dispose();
        tower24.dispose();
        tower25.dispose();
        tower26.dispose();
        tower27.dispose();
        tower28.dispose();
        tower29.dispose();

        tower31.dispose();
        tower32.dispose();
        tower33.dispose();
        tower34.dispose();
        tower35.dispose();
        tower36.dispose();
        tower37.dispose();
        tower38.dispose();
        tower39.dispose();

        tower41.dispose();
        tower42.dispose();
        tower43.dispose();
        tower44.dispose();
        tower45.dispose();
        tower46.dispose();
        tower47.dispose();
        tower48.dispose();
        tower49.dispose();

        heart51.dispose();
        heart52.dispose();
        heart53.dispose();
        heart54.dispose();

        gold61.dispose();
        gold62.dispose();
        gold63.dispose();
        gold64.dispose();
    }
}
