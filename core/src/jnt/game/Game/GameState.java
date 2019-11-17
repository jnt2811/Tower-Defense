package jnt.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import jnt.game.Entity.tower.NormalTower;
import jnt.game.Map.Map;
import jnt.game.Map.Tile;
import jnt.game.Map.TileGrid;
import jnt.game.Map.TileType;

public class GameState implements Disposable {

    private Map map;
    private TileGrid tileGrid;
    private Level level;
    private PlayerInfo player;
    private BuildTower buildTower;
    private Tile bugFixed;

    public GameState() {

        map = new Map();
        tileGrid = new TileGrid(map.map);

        player = new PlayerInfo();

        level = new Level(player);
        level.setLevel(1);

        buildTower = new BuildTower(level.getEnemies(), tileGrid, player);

        bugFixed = new Tile(0,0, TileType.Rock1);
    }

    public void render(SpriteBatch batch, float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

        // Create All Objects
        try {
            createMap(batch);
            createTowers(batch, delta);
            createEnemies(batch, delta);
            createInfo(batch);
            createBugFixed(batch);

//            update();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


//    public void update() {
//
//        if(level.isDecreaseHealth()) {
//            level.setDecreaseHealth(false);
//            player.decreaseHealth();
//        }
//    }


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

        player.draw(batch);

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
        level.dispose();
        buildTower.dispose();
        tileGrid.dispose();
        player.dispose();
    }

    public int getHealth() {
        return player.getHealth();
    }
}
