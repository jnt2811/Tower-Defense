package jnt.game.Map;

public enum TileType {

    Grass("grass"), Land("land"), Silver("silver"),
    Point("point"),
    Tree("tree"), Tree1("tree1"),
    Rock("rock"), Rock1("rock1"),
    Water("water");

    String textureName;

    TileType(String textureName) {
        this.textureName = textureName;
    }
}
