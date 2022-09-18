package byog.Core;

import byog.TileEngine.TETile;

public class MyTest {
    public static void main(String[] args){
        Game g = new Game();
        TETile[][] world = g.playWithInputString("N1234S");
        System.out.println(TETile.toString(world));
    }
}
