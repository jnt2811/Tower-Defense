package jnt.game.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class TileGrid implements Disposable {

    private Tile[][] tileGrid;

    public TileGrid(int[][] map) {

        tileGrid = new Tile[Gdx.graphics.getHeight()/60][Gdx.graphics.getWidth()/60];

        for (int y=0; y < map.length; y++) {
            for (int x=0; x < map[y].length; x++) {

                if (map[y][x] == 0) setTile(x, y, TileType.Grass);
                if (map[y][x] == 1) setTile(x, y, TileType.Land);
                if (map[y][x] == 2 || map[y][x] == 3) setTile(x, y, TileType.Point);
                if (map[y][x] == 4) setTile(x, y, TileType.Silver);
                if (map[y][x] == 5) setTile(x, y, TileType.Rock);
                if (map[y][x] == 6) setTile(x, y, TileType.Rock1);
                if (map[y][x] == 7) setTile(x, y, TileType.Tree);
                if (map[y][x] == 8) setTile(x, y, TileType.Tree1);
                if (map[y][x] == 9) setTile(x, y, TileType.Water);
            }
        }
    }

    public void draw(SpriteBatch batch) {
        for (int y=0; y < tileGrid.length; y++) {
            for (int x=0; x < tileGrid[y].length; x++) {
                tileGrid[y][x].draw(batch);
            }
        }
    }

    public Tile getTile(int yPlace, int xPLace) {
        return tileGrid[yPlace][xPLace];
    }

    public void setTile(int xCoordinate, int yCoordinate, TileType tileType) {
        tileGrid[yCoordinate][xCoordinate] = new Tile(xCoordinate * 60, yCoordinate * 60, tileType);
    }

    @Override
    public void dispose() {
        for (int y = 0; y < tileGrid.length; y++) {
            for (int x=0; x < tileGrid[y].length; x++) {
                tileGrid[y][x].dispose();
            }
        }
    }
}
