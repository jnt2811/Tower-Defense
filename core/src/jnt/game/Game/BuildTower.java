package jnt.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.utils.Disposable;
import jnt.game.Entity.enemy.NormalEnemy;
import jnt.game.Entity.tower.MortarTower;
import jnt.game.Entity.tower.NormalTower;
import jnt.game.Entity.tower.RifleTower;
import jnt.game.Entity.tower.SmgTower;
import jnt.game.Map.Map;
import jnt.game.Map.Tile;
import jnt.game.Map.TileGrid;
import jnt.game.Map.TileType;

import java.util.ArrayList;

public class BuildTower implements Disposable {

    private Sprite tower1Button, tower2Button, tower3Button,tower4Button;
    private ArrayList<NormalTower> towers;
    private ArrayList<NormalEnemy> enemies;
    private TileGrid tileGrid;
    private NormalTower tempTower;
    private boolean holdingTower;
    private int mouseX, mouseY;
    private PlayerInfo player;

    public BuildTower(ArrayList<NormalEnemy> enemies, TileGrid tileGrid, PlayerInfo player) {

        this.enemies = enemies;
        this.tileGrid = tileGrid;
        this.player = player;

        tower1Button = new Sprite(new Texture(Gdx.files.internal("tower1Button.png")));
        tower2Button = new Sprite(new Texture(Gdx.files.internal("tower2Button.png")));
        tower3Button = new Sprite(new Texture(Gdx.files.internal("tower3Button.png")));
        tower4Button = new Sprite(new Texture(Gdx.files.internal("tower4Button.png")));

        // Default
        tower1Button.setPosition(1230, 510);
        tower2Button.setPosition(1410, 510);
        tower3Button.setPosition(1230, 330);
        tower4Button.setPosition(1410, 330);

        // Array
        towers = new ArrayList<>();

        //
        this.tempTower = null;
        this.holdingTower = false;
    }

    public void build(SpriteBatch batch, float delta) {

        tower1Button.draw(batch);
        tower2Button.draw(batch);
        tower3Button.draw(batch);
        tower4Button.draw(batch);

        for (NormalTower tower : towers) tower.draw(batch,delta);

        // Get mouse's Coordinates
        mouseX = Gdx.input.getX();
        mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        // Pick up the Tower
        if(checkIn(tower1Button)) pickTower(new NormalTower(enemies, tileGrid.getTile(mouseY/60, mouseX/60)));
        if(checkIn(tower2Button)) pickTower(new SmgTower(enemies, tileGrid.getTile(mouseY/60, mouseX/60)));
        if(checkIn(tower3Button)) pickTower(new RifleTower(enemies, tileGrid.getTile(mouseY/60, mouseX/60)));
        if(checkIn(tower4Button)) pickTower(new MortarTower(enemies, tileGrid.getTile(mouseY/60, mouseX/60)));

        // Tower picked up follows the mouse
        if(holdingTower && checkValid()) {

            tempTower.setX((int)getMouseTile().getX());
            tempTower.setY((int)getMouseTile().getY());
            tempTower.draw(batch, delta);
            tempTower.setPlaced(false);
        }

        // Place the Tower
        if(placeTower()) {

            towers.add(tempTower);

            //
            tempTower.setPlaced(true);
            tileGrid.setTile(mouseX/60, mouseY/60, TileType.Grass1);
            player.decreaseGold(tempTower.getPrice());

            //
            holdingTower = false;
            tempTower = null;
        }

        // Abort An Order!
        if(abortOrder()) {
            holdingTower = false;
            tempTower = null;
        }
    }

    public void pickTower(NormalTower tower) {

        tempTower = tower;

        if(checkGold(tempTower)) holdingTower = true;
        else tempTower = null;
    }

    public boolean placeTower() {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && tempTower != null && checkValid()) return true;
        return false;
    }

    // Check tile, tower must be placed in Grass tile, this function is used in placeTower
    public boolean checkValid() {
        if(tileGrid.getTile(mouseY/60, mouseX/60).getTileType() == TileType.Grass) return true;
        return false;
    }

    // Check the mouse if it is clicked in towerButton's area
    public boolean checkIn(Sprite sprite) {
        if( mouseX >= sprite.getX() && mouseX <= (sprite.getX() + sprite.getWidth())
                && mouseY >= sprite.getY() &&  mouseY <= (sprite.getY() + sprite.getHeight())
                && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && tempTower == null) return true;
        return false;
    }

    public boolean abortOrder() {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) && tempTower != null) return true;
        return false;
    }

    // PLayer can buy more towers if they have enough gold, this function is used in pickTower
    public boolean checkGold(NormalTower tempTower) {
        if(player.getGold() - tempTower.getPrice() >= 0) return true;
        return false;
    }

    public Tile getMouseTile() {
        return tileGrid.getTile(mouseY/60, mouseX/60);
    }

    @Override
    public void dispose() {
        for(NormalTower tower : towers) tower.dispose();
        tempTower.dispose();
        tileGrid.dispose();
        tower1Button.getTexture().dispose();
        tower2Button.getTexture().dispose();
        tower3Button.getTexture().dispose();
        tower4Button.getTexture().dispose();
    }
}
