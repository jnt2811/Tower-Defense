package jnt.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import jnt.game.Entity.tower.BuildTower;
import jnt.game.Map.Map;
import jnt.game.Map.Tile;
import jnt.game.Map.TileGrid;
import jnt.game.Map.TileType;

public class GameState implements Disposable {

    private Map map;
    private TileGrid tileGrid;
    private Level level;
    private BuildTower buildTower;
    private BitmapFont healthNum, goldNum;
    private Tile bugFixed;

    private int health = 10, gold = 0;

    public GameState() {

        map = new Map();
        tileGrid = new TileGrid(map.map);

        level = new Level();
        level.setLevel(1);

        buildTower = new BuildTower(level.getEnemies(), tileGrid);

        bugFixed = new Tile(0,0, TileType.Rock1);

        healthNum = new BitmapFont(Gdx.files.internal("health.fnt"));
        goldNum = new BitmapFont(Gdx.files.internal("health.fnt"));
        
    }

    public void render(SpriteBatch batch, float delta) {
        try {
            createMap(batch);
            createTowers(batch, delta);
            createEnemies(batch, delta);
            createInfo(batch);
            createBugFixed(batch);

            update();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void update() {
        if(level.isDecreaseHealth()) {
            level.setDecreaseHealth(false);
            health--;
        }
    }


    public void createMap(SpriteBatch batch) {
        batch.begin();

        tileGrid.draw(batch);

        batch.end();
    }


    public void createTowers(SpriteBatch batch, float delta) {
        batch.begin();

        buildTower.build(batch, delta);

        batch.end();
    }


    public void createEnemies(SpriteBatch batch, float delta) {
        batch.begin();

        level.draw(batch,delta);

        batch.end();
    }


    public void createInfo(SpriteBatch batch) {
        batch.begin();

        healthNum.draw(batch, "" + health, 1410, 860);
        goldNum.draw(batch, "" + gold, 1410, 740);

        batch.end();
    }

    public void createBugFixed(SpriteBatch batch) {
        batch.begin();

        bugFixed.draw(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        bugFixed.dispose();
        healthNum.dispose();
        goldNum.dispose();
        level.dispose();
        buildTower.dispose();
        tileGrid.dispose();
    }

    public int getHealth() {
        return health;
    }
}
