package jnt.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tower extends GameEntity implements InputProcessor {


    private Texture tower;
    public Sprite sprite;



    public Tower(int id, float x, float y) {
        super(id, x, y);
        if(id == 1) tower = new Texture("Tower1.png");
        if(id == 2) tower = new Texture("Tower2.png");
        if(id == 3) tower = new Texture("Tower3.png");
        sprite.setPosition(Gdx.graphics.getWidth()/2 - sprite.getX()/2, Gdx.graphics.getHeight()/2 -  sprite.getY()/2);
        Gdx.input.setInputProcessor(this);

    }



    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(tower, x, y);
        batch.end();

    }

    @Override
    public void update() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;

    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;


    }

    @Override
    public boolean scrolled(int amount) {
        if(amount > 0) {
            sprite.translateY(1f);
        }
        if(amount < 0)
            sprite.translateY(-1f);

        return false;
    }

    //function : shoot, ...
}
