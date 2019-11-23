package jnt.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import jnt.game.Button.ButtonManagement;
import jnt.game.Game.Frame;
import jnt.game.Game.GameState;
import jnt.game.Game.TowerDefense;

public class GameScreen implements Screen {

    private TowerDefense game;
    private GameState gameState;
    private Frame fps;

    public GameScreen(TowerDefense game) {

        this.game = game;
        gameState = new GameState();
        fps = new Frame();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameState.render(game.batch, delta);

        fps.render();
        fps.update();

        if(gameState.getVictory()) game.setScreen(new VictoryScreen(game));
        if(gameState.getHealth() == 0) game.setScreen(new DefeatScreen(game));
    }

    @Override
    public void dispose() {
        game.dispose();
        game.batch.dispose();
        gameState.dispose();
        fps.dispose();
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
