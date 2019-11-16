package jnt.game.Entity.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.utils.Disposable;
import jnt.game.Entity.enemy.NormalEnemy;
import jnt.game.Map.Tile;
import jnt.game.Map.TileGrid;
import jnt.game.Map.TileType;

import java.util.ArrayList;

public class BuildTower extends InputAdapter implements Disposable {

    private Sprite tower1Button, tower2Button,tower3Button,tower4Button;
    private ArrayList<NormalTower> towers;
    private ArrayList<NormalEnemy> enemies;
    private TileGrid tileGrid;
    private NormalTower tempTower;
    private boolean holdingTower;
    private int mouseX, mouseY;
    private MouseJointDef jointDef;

    public BuildTower(ArrayList<NormalEnemy> enemies, TileGrid tileGrid) {

        this.enemies = enemies;
        this.tileGrid = tileGrid;

        tower1Button = new Sprite(new Texture(Gdx.files.internal("tower1Button.png")));
        tower2Button = new Sprite(new Texture(Gdx.files.internal("tower2Button.png")));
        tower3Button = new Sprite(new Texture(Gdx.files.internal("tower3Button.png")));
        tower4Button = new Sprite(new Texture(Gdx.files.internal("tower4Button.png")));

        // Default
        tower1Button.setPosition(1230, 510);
        tower2Button.setPosition(1410, 510);
        tower3Button.setPosition(1230, 330);
        tower4Button.setPosition(1410, 330);

        // Arrays
        towers = new ArrayList<>();
        enemies = new ArrayList<>();

        //
        this.tempTower = null;
        this.holdingTower = false;

        //
        jointDef = new MouseJointDef();
//        jointDef.bodyA = ground;
        jointDef.collideConnected = true;
        jointDef.maxForce = 500;
    }

    public void build(SpriteBatch batch, float delta) {

        tower1Button.draw(batch);
        tower2Button.draw(batch);
        tower3Button.draw(batch);
        tower4Button.draw(batch);

        // Get mouse's Coordinates
        mouseX = Gdx.input.getX();
        mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        if( mouseX >= tower1Button.getX() && mouseX <= (tower1Button.getX() + tower1Button.getWidth())
                && mouseY >= tower1Button.getY() &&  mouseY <= (tower1Button.getY() + tower1Button.getHeight()) ) {

            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && tempTower == null) {

                pickTower(new NormalTower(enemies, tileGrid.getTile(mouseY/60, mouseX/60)));

                System.out.println("in");
            }
        }

//        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
//
//            if(checkValid(mouseX, mouseY))
//                towers.add(new NormalTower(enemies, tileGrid.getTile(mouseY/60, mouseX/60)));
//        }

        for (NormalTower tower : towers) tower.draw(batch,delta);

        if(holdingTower && checkValid(mouseX, mouseY)) {
            tempTower.setX((int)getMouseTile().getX());
            tempTower.setY((int)getMouseTile().getY());
            tempTower.draw(batch, delta);
            tempTower.setPlaced(false);
        }
    }


    public void pickTower(NormalTower tower) {
        tempTower = tower;
        holdingTower = true;
    }


    public boolean checkValid(int mouseX, int mouseY) {
        if(tileGrid.getTile(mouseY/60, mouseX/60).getTileType() != TileType.Grass) return false;
        return true;
    }


    public Tile getMouseTile() {
        return tileGrid.getTile(mouseY/60, mouseX/60);
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public void dispose() {
        for(NormalTower tower : towers) tower.dispose();
    }
}
