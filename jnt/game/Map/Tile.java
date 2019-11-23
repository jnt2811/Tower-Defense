package jnt.game.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Tile implements Disposable {

    private float x, y;
    private Texture texture;
    private TileType tileType;

    public Tile(float x, float y, TileType tileType) {
        this.x = x;
        this.y = y;
        this.tileType = tileType;

        this.texture = new Texture(Gdx.files.internal(tileType.textureName + ".png"));
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }

    public Texture getTexture() {
        return texture;
    }
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public TileType getTileType() {
        return tileType;
    }
    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public int getXPlace() {
        return (int)x / 60;
    }
    public int getYPlace() {
        return (int)y / 60;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
