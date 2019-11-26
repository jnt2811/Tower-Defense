package jnt.game.Button;

public enum ButtonType {

    Play("play"), Pause("pause"), Resume("resume"),
    Save("save"), Quit("quit"),
    Level1("1"), Level2("2"), Level3("3"),
    NewGame("newGame"), TryAgain("tryAgain"), QuitGame("quitGame"),
    Start("start"), Load("load"), Exit("exit");

    String spriteName;

    ButtonType(String spriteName) {
        this.spriteName = spriteName;
    }
}
