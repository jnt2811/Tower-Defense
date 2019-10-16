package jnt.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Timer;

public class StageScreen implements Screen {

    private Texture inGameScreen;
    private Texture health;
    private Texture startButton, pauseButton, resumeButton, saveButton, quitButton;
    private BitmapFont font1, font2;
    public static int healthNum = 5, goldNum = 10, scoreNum = 0, stageNum = 1, saveData = 0;
    private boolean stageStared = false, paused = false;

    private TowerDefense game;
    private Tower tower1, tower2, tower3;
    private Stage1 stage1;

    public StageScreen(TowerDefense game) {

        //
        this.game = game;

        //game screen
        inGameScreen = new Texture("InGame.png");

        //words & symbol
        health = new Texture("Heart.png");
        font1 = new BitmapFont(Gdx.files.internal("health.fnt"));
        font2 = new BitmapFont(Gdx.files.internal("gold.fnt"));

        //buttons
        startButton = new Texture("StartButton.png");
        pauseButton = new Texture("PauseButton.png");
        resumeButton = new Texture("ResumeButton.png");
        saveButton = new Texture("SaveButton.png");
        quitButton = new Texture("QuitButton.png");

        //towers
        tower1 = new Tower(1, 85, 70);
        tower2 = new Tower(2, 350, 70);
        tower3 = new Tower(3, 600, 79);

        //stage
        stage1 = new Stage1();
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

        //draw in game screen
        game.batch.draw(inGameScreen, 0, 0);
            //continue...

        //draw green heart
        game.batch.draw(health, 1700, 20);
        font1.getData().setScale(1f,1.3f);
        font1.draw(game.batch, healthNum + "",1767,110);

        //draw gold's number
        font2.getData().setScale(1f,1.5f);
        font2.draw(game.batch, goldNum + "", 1090, 100);

        //draw score's number
        font2.draw(game.batch, scoreNum + "", 1440, 100);

        //draw start stage , pause, resume button(s)
        if(!stageStared) {
            game.batch.draw(startButton, 1650, 510);
            if(Gdx.input.getX() >= 1650 && Gdx.input.getX() <= 1895 && Gdx.input.getY() >= 480 && Gdx.input.getY() <= 570) {
                if(Gdx.input.isTouched()) {
                    game.buttonSound.play();
                    float delay = 0.2f;
                    Timer.schedule(new Timer.Task(){
                        public void run() {
                            stageStared = true;
                        }
                    }, delay);
                }
            }
        }
        if(stageStared) {
            if(!paused) {

                //draw pause button
                game.batch.draw(pauseButton, 1650, 510);

                //switch to resume
                if(Gdx.input.getX() >= 1650 && Gdx.input.getX() <= 1895 && Gdx.input.getY() >= 480 && Gdx.input.getY() <= 570) {
                    if(Gdx.input.isTouched()) {

                        //button sound
                        game.buttonSound.play();

                        //pause
                        float delay = 0.2f;
                        Timer.schedule(new Timer.Task(){
                            public void run() {
                                paused = true;
                            }
                        }, delay);
                    }
                }
            }
            if(paused) {

                //draw resume button
                game.batch.draw(resumeButton, 1650, 510);

                //switch to pause
                if(Gdx.input.getX() >= 1650 && Gdx.input.getX() <= 1895 && Gdx.input.getY() >= 480 && Gdx.input.getY() <= 570) {
                    if(Gdx.input.isTouched()) {

                        //button sound
                        game.buttonSound.play();

                        //resume
                        float delay = 0.2f;
                        Timer.schedule(new Timer.Task(){
                            public void run() {
                                paused = false;
                            }
                        }, delay);
                    }
                }
            }
        }

        //draw save button
        game.batch.draw(saveButton, 1650, 380);
        if(Gdx.input.getX() >= 1650 && Gdx.input.getX() <= 1895 && Gdx.input.getY() >= 610 && Gdx.input.getY() <= 700) {
            if(Gdx.input.isTouched()) {

                //button sound
                game.buttonSound.play();

                //save data
                saveData = stageNum;
            }
        }

        //draw quit button
        game.batch.draw(quitButton, 1650, 250);
        if(Gdx.input.getX() >= 1650 && Gdx.input.getX() <= 1895 && Gdx.input.getY() >= 740 && Gdx.input.getY() <= 830) {
            if(Gdx.input.isTouched()) {

                //button sound
                game.buttonSound.play();

                //exit
                float delay = 0.5f;
                Timer.schedule(new Timer.Task(){
                    public void run() {
                        Gdx.app.exit();
                    }
                }, delay);
            }
        }

        //draw tower1
        tower1.render(game.batch);
            //continue...

        //draw tower2
        tower2.render(game.batch);
            //continue...

        //draw tower3
        tower3.render(game.batch);
            //continue...

        //draw stages
        if(stageNum == 1) {

            //draw STAGE number
            font1.draw(game.batch, "STAGE " + stageNum, 50, 1047);

            //draw stage
            if(stageStared) stage1.render(game.batch);
                //continue...
        }

        //draw game over screen
        if(healthNum == 0) {
            float delay = 0.1f;
            Timer.schedule(new Timer.Task(){
                public void run() {
                    game.setScreen(new GameOverScreen(game));
                }
            }, delay);
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
        game.dispose();
        inGameScreen.dispose();
        health.dispose();
        font1.dispose();
        font2.dispose();
        startButton.dispose();
        pauseButton.dispose();
        resumeButton.dispose();
        saveButton.dispose();
        quitButton.dispose();
    }

    public static void reset() {
        healthNum = 5;
        goldNum = 10;
        scoreNum = 0;
        stageNum = 1;
    }
}
