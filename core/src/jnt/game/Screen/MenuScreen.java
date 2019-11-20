package jnt.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import jnt.game.Button.ButtonManagement;
import jnt.game.Game.TowerDefense;

public class MenuScreen implements Screen {

    private TowerDefense game;
    private ButtonManagement buttons;
    private Sprite background;
    Sound mp3sound;
//    private Music music;

    public MenuScreen(TowerDefense game) {
        this.game = game;

        buttons = new ButtonManagement("menu screen");

        background = new Sprite(new Texture(Gdx.files.internal("menuScreen.png")));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Sound mp3Sound = Gdx.audio.newSound(Gdx.files.internal("spaceship.mp3"));




//        music = Gdx.audio.newMusic(Gdx.files.internal("defeat.mp3"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        music.play();

        game.batch.begin();
        mp3sound.play();
        mp3sound.play(0.5f);


        background.draw(game.batch);

        buttons.draw(game.batch);
        buttons.update();

        game.batch.end();

        if(buttons.getNewGame()) game.setScreen(new GameScreen(game));
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

    @Override
    public void dispose() {
        mp3sound.dispose();

    }
}
