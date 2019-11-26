package jnt.game.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Button implements Disposable {

    private float x, y;
    private Sprite sprite;
    private ButtonType buttonType;

    public Button(float x, float y, ButtonType buttonType) {

        this.x = x;
        this.y = y;
        this.buttonType = buttonType;
        this.sprite = new Sprite(new Texture(Gdx.files.internal(buttonType.spriteName + ".png")));
    }

    public void draw(SpriteBatch batch) {
        sprite.setPosition(x, y);
        sprite.draw(batch);
    }

    public float getHeight() {return sprite.getHeight();}
    public float getWidth() {return sprite.getWidth();}

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

    public Sprite getSprite() {return sprite;}

    public void setSprite(ButtonType buttonType) {
        setButtonType(buttonType);
        this.sprite = new Sprite(new Texture(Gdx.files.internal(buttonType.spriteName + ".png")));
    }

    public ButtonType getButtonType() {
        return buttonType;
    }
    public void setButtonType(ButtonType buttonType) {
        this.buttonType = buttonType;
    }

    public void setButtonSize(int width, int height) {
        sprite.setSize(width, height);
    }

    @Override
    public void dispose() {
        sprite.getTexture().dispose();
    }
}
