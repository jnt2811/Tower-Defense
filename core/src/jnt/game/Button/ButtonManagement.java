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
    private Button buttonOriginal;
    private Music click;
    private boolean startWave = false;

    public ButtonManagement(String screenName) {
        this.screenName = screenName;

        buttons = new ArrayList<>();
        buttonOriginal = new Button(1230, 220, ButtonType.Play);

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

    public void getButtons() {
        if(screenName == "game screen") {
            buttons.add(buttonOriginal); //0
            buttons.add(new Button(1230, 120, ButtonType.Save)); //1
            buttons.add(new Button(1230, 20, ButtonType.Quit)); //2
        }
    }

    public void getClick() {

        // Play, Pause, Resume Game
        if(checkClick(0)) {

            click.play();

            if(buttonOriginal.getButtonType() == ButtonType.Play) {
                buttonOriginal.setTexture(ButtonType.Pause);
                startWave = true;
            }
            else if(buttonOriginal.getButtonType() == ButtonType.Pause) {
                buttonOriginal.setTexture(ButtonType.Resume);
                //
            }
            else if(buttonOriginal.getButtonType() == ButtonType.Resume) {
                buttonOriginal.setTexture(ButtonType.Pause);
                //
            }
        }

        // Save Game
        if (checkClick(1)) {
            click.play();
        }

        // Quit Game
        if (checkClick(2)) Gdx.app.exit();
    }

    public boolean checkClick(int i) {
        if(mouseX >= buttons.get(i).getX() && mouseX <= buttons.get(i).getX() + buttons.get(i).getWidth()
                && mouseY >= buttons.get(i).getY() && mouseY <= buttons.get(i).getY() + buttons.get(i).getHeight()
                && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) return true;
        return false;
    }

    public boolean getStartWave() {return startWave;}

    @Override
    public void dispose() {
        for(Button button : buttons) button.dispose();
    }
}
