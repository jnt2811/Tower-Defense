package jnt.game.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

public class ButtonManagement implements Disposable {

    private String screenName;
    private ArrayList<Button> buttons;
    private float mouseX, mouseY;
    private Button button0, button1;
    private Music click;
    private boolean startWave = false, paused = false, newGame = false;

    public ButtonManagement(String screenName) {
        this.screenName = screenName;

        buttons = new ArrayList<>();
        button0 = new Button(1230, 220, ButtonType.Play);
        button1 = new Button(660, 180, ButtonType.Level1);

        click = Gdx.audio.newMusic(Gdx.files.internal("click.wav"));

        getButtons();
    }

    public void draw(SpriteBatch batch) {
        for(Button button : buttons) button.draw(batch);
    }

    public void update() {

        // Get mouse's Coordinates
        mouseX = Gdx.input.getX();
        mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        getClick();
    }

    private void getButtons() {

        if(screenName == "menu screen") {

            buttons.add(new Button(630, 325, ButtonType.Start)); //0 - start
            buttons.add(new Button(475, 200, ButtonType.Load)); //1 - load
            buttons.add(new Button(785, 200, ButtonType.Exit)); //2 - exit

            for(Button button : buttons) button.setButtonSize(300, 110);
        }

        if(screenName == "game screen") {
            buttons.add(button0); //0 - play, pause, resume
            buttons.add(new Button(1230, 120, ButtonType.Save)); //1 - save
            buttons.add(new Button(1230, 20, ButtonType.Quit)); //2 - resume
            buttons.add(button1); //3 - level
        }

        if(screenName == "victory screen") {

            buttons.add(new Button(650, 200, ButtonType.QuitGame)); //0 - quit game
            for(Button button : buttons) button.setButtonSize(250, 65);
        }

        if(screenName == "defeat screen") {

            buttons.add(new Button(650, 200, ButtonType.QuitGame)); //0 - quit game

            for(Button button : buttons) button.setButtonSize(250, 65);

        }
    }

    private void getClick() {

        if(screenName == "menu screen") {
            if(checkClick(0)) {
                click.play();
                newGame = true;
            }
            if(checkClick(1)) {
                click.play();
                //load game ...
            }
            if(checkClick(2)) Gdx.app.exit();
        }

        if(screenName == "game screen") {
            // Play, Pause, Resume Game
            if(checkClick(0)) {
                click.play();
                if(button0.getButtonType() == ButtonType.Play) {
                    button0.setSprite(ButtonType.Pause);
                    startWave = true;
                }
                else if(button0.getButtonType() == ButtonType.Pause) {
                    button0.setSprite(ButtonType.Resume);
                    paused = true;
                }
                else if(button0.getButtonType() == ButtonType.Resume) {
                    button0.setSprite(ButtonType.Pause);
                    paused = false;
                }
            }
            // Save Game
            if (checkClick(1)) {
                click.play();
                //save game ...
            }
            // Quit Game
            if (checkClick(2)) Gdx.app.exit();
        }

        if(screenName == "victory screen" || screenName == "defeat screen") {
            if(checkClick(0)) Gdx.app.exit();
        }
    }

    private boolean checkClick(int i) {
        if(mouseX >= buttons.get(i).getX() && mouseX <= buttons.get(i).getX() + buttons.get(i).getWidth()
                && mouseY >= buttons.get(i).getY() && mouseY <= buttons.get(i).getY() + buttons.get(i).getHeight()
                && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) return true;
        return false;
    }

    public boolean getStartWave() {return startWave;}
    public boolean getPaused() {return paused;}
    public boolean getNewGame() {return newGame;}

    public void setButton0Play() {
        button0.setSprite(ButtonType.Play);
        startWave = false;
    }

    public void setButton1Level2() {
        button1.setSprite(ButtonType.Level2);
    }
    public void setButton1Level3() {
        button1.setSprite(ButtonType.Level3);
    }

    @Override
    public void dispose() {
        button0.dispose();
        button1.dispose();
        for(Button button : buttons) button.dispose();
    }
}
