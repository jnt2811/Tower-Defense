package jnt.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import jnt.game.Game.GameState;
import jnt.game.Game.TowerDefense;

public class GameScreen implements Screen {
    private TowerDefense game;
    private GameState gameState;

    public GameScreen(TowerDefense game) {
        this.game = game;
        gameState = new GameState();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameState.render(game.batch, delta);
    }

    @Override
    public void dispose() {
        game.batch.dispose();
    }

    //Useless
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
