package jnt.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;

public class StartScreen implements Screen {

    private Texture startScreen, newGameButton, loadButton, exitButton;

    private TowerDefense game;

    public StartScreen(TowerDefense game) {
        this.game = game;

        startScreen = new Texture("StartGame.png");
        newGameButton = new Texture("NewGameButton.png");
        loadButton = new Texture("LoadButton.png");
        exitButton = new Texture("ExitButton.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //start draw
        game.batch.begin();

        //draw star screen
        game.batch.draw(startScreen, 0, 0);

        //draw new game button
        game.batch.draw(newGameButton, 800, 550);
        if(Gdx.input.getX() >= 800 && Gdx.input.getX() <= 1110 && Gdx.input.getY() >= 410 && Gdx.input.getY() <= 530) {
            if(Gdx.input.isTouched()) {

                //button sound
                game.buttonSound.play();

                //button function
                float delay = 0.35f;
                Timer.schedule(new Timer.Task(){
                    public void run() {
                        game.setScreen(new StageScreen(game));
                    }
                }, delay);
            }
        }

        //draw load button
        game.batch.draw(loadButton, 800, 400);
        if(Gdx.input.getX() >= 800 && Gdx.input.getX() <= 1110 && Gdx.input.getY() >= 560 && Gdx.input.getY() <= 675) {
            if(Gdx.input.isTouched()) {

                //button sound
                game.buttonSound.play();

                if(StageScreen.saveData != 0){
                    //load data
                    StageScreen.stageNum = StageScreen.saveData;

                    //button function
                    float delay = 0.35f;
                    Timer.schedule(new Timer.Task(){
                        public void run() {
                            game.setScreen(new StageScreen(game));
                        }
                    }, delay);
                }
            }
        }

        //draw exit button
        game.batch.draw(exitButton, 800, 250);
        if(Gdx.input.getX() >= 800 && Gdx.input.getX() <= 1110 && Gdx.input.getY() >= 710 && Gdx.input.getY() <= 830) {
            if(Gdx.input.isTouched()) {

                //button sound
                game.buttonSound.play();

                //button function
                float delay = 0.3f;
                Timer.schedule(new Timer.Task(){
                    public void run() {
                        Gdx.app.exit();
                    }
                }, delay);
            }
        }

        //end draw
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
        game.buttonSound.dispose();
        startScreen.dispose();
        newGameButton.dispose();
        loadButton.dispose();
        exitButton.dispose();
    }
}
