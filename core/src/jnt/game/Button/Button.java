package jnt.game.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Button implements Disposable {

    private float x, y;
    private Texture texture;
    private ButtonType buttonType;

    public Button(float x, float y, ButtonType buttonType) {

        this.x = x;
        this.y = y;
        this.buttonType = buttonType;
        this.texture = new Texture(Gdx.files.internal(buttonType.textureName + ".png"));
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public int getHeight() {return texture.getHeight();}
    public int getWidth() {return texture.getWidth();}

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

    public Texture getTexture() {return texture;}

    public void setTexture(ButtonType buttonType) {
        setButtonType(buttonType);
        this.texture = new Texture(Gdx.files.internal(buttonType.textureName + ".png"));
    }

    public ButtonType getButtonType() {
        return buttonType;
    }
    public void setButtonType(ButtonType buttonType) {
        this.buttonType = buttonType;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
