package jnt.game.Map;

public class Map {

    public static final int[][] map = {
            {5,7,8,6,7,7,8,5,7,6,8,8,6,7,7,7,8,5,7,6,4,4,4,4,4,4},
            {8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,4,4,4,4,4,4},
            {2,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,3,4,4,4,4,4,4},
            {8,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,8,4,4,4,4,4,4},
            {7,0,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,0,7,4,4,4,4,4,4},
            {5,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,7,4,4,4,4,4,4},
            {7,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,0,8,4,4,4,4,4,4},
            {7,0,0,0,0,0,1,0,0,1,1,1,1,1,1,0,0,1,0,5,4,4,4,4,4,4},
            {7,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,1,0,6,4,4,4,4,4,4},
            {8,0,1,1,1,1,1,0,0,1,0,0,0,0,1,0,0,1,0,7,4,4,4,4,4,4},
            {5,0,1,0,0,0,0,0,0,1,0,4,4,0,1,0,0,1,0,7,4,4,4,4,4,4},
            {7,0,1,0,0,0,0,0,0,1,0,4,4,0,1,0,0,1,0,6,4,4,4,4,4,4},
            {7,0,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,0,8,4,4,4,4,4,4},
            {8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,4,4,4,4,4,4},
            {6,7,7,5,7,8,7,6,5,7,7,7,8,7,6,7,8,7,7,5,4,4,4,4,4,4},
    };

    public Map() {

        // Drag Down the Map
        for (int y=0; y<map.length/2; y++) {
            for (int x=0; x<map[y].length; x++) {
                int a = map[y][x];
                map[y][x] = map[map.length - 1 - y][x];
                map[map.length - 1 - y][x] = a;
            }
        }
    }
}
