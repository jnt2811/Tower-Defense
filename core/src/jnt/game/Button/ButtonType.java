package jnt.game.Button;

public enum ButtonType {

    Play("play"), Pause("pause"), Resume("resume"),
    Save("save"), Quit("quit");

    String textureName;

    ButtonType(String textureName) {
        this.textureName = textureName;
    }
}
