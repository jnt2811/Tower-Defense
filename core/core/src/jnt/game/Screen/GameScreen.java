package jnt.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import jnt.game.Main.Map;
import jnt.game.Main.TowerDefense;

public class GameScreen implements Screen {

    private TowerDefense game;
    private Map map;

    public GameScreen(TowerDefense game) {

        this.game = game;

        map = new Map(game.batch);
    }

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        map.draw();

        game.batch.end();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {

    }
    @Override
    public void resize(int width, int height) {

    }
    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }
    @Override
    public void hide() {

    }
}
