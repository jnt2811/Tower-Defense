package jnt.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Timer;

public class VictoryScreen implements Screen {

    private Texture victoryScreen, quitButton, mainMenuButton;
    private BitmapFont victory;

    TowerDefense game;

    public VictoryScreen(TowerDefense game) {
        victoryScreen = new Texture("StartGame.png");
        victory = new BitmapFont(Gdx.files.internal("health.fnt"));

        mainMenuButton = new Texture("MainMenuButton.png");
        quitButton = new Texture("QuitButton.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
//
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //begin
        game.batch.begin();

        //draw screen
        game.batch.draw(victoryScreen,0,0);

        //draw "GAME OVER"
        victory.getData().setScale(1.5f,1f);
        victory.draw(game.batch, "VICTORY",810,700);

        //draw 'Try Again'
        game.batch.draw(mainMenuButton,830,450);
        if(Gdx.input.getX() >= 830 && Gdx.input.getX() <= 1075 && Gdx.input.getY() >= 535 && Gdx.input.getY() <= 625) {
            if(Gdx.input.isTouched()) {

                //button sound
                game.buttonSound.play();

                //get back to start screen
                float delay = 0.35f;
                Timer.schedule(new Timer.Task(){
                    public void run() {

                        //reset
                        StageScreen.reset();

                        //new game screen
                        game.setScreen(new StageScreen(game));
                    }
                }, delay);
            }
        }

        //draw 'Quit'
        game.batch.draw(quitButton,830,300);
        if(Gdx.input.getX() >= 830 && Gdx.input.getX() <= 1075 && Gdx.input.getY() >= 685 && Gdx.input.getY() <= 780) {
            if(Gdx.input.isTouched()) {

                //button sound
                game.buttonSound.play();

                //exit
                float delay = 0.35f;
                Timer.schedule(new Timer.Task(){
                    public void run() {
                        Gdx.app.exit();
                    }
                }, delay);
            }
        }

        //end
        game.batch.end();
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
        game.batch.dispose();
        victoryScreen.dispose();
        mainMenuButton.dispose();
        quitButton.dispose();
        victory.dispose();
    }
}
