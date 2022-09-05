package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    private class Position{
        int x;
        int y;
        public Position(int pX, int pY){
            x = pX;
            y = pY;
        }
    }

    /* return the width of the yth line */
    private static int xWidth(int s, int y){
        if(y < s){
            return s + 2*y;
        }
        return 5*s - 2*y - 2;
    }

    /* return the size of empty which we shouldn't draw in each line */
    private static int xEmpty(int s, int y){
        if(y < s){
            return s - y - 1;
        }
        return y - s;
    }

    /* draw a single hexagon , it's size according to size
        s: size      t: the character in world*/
    public static void addHexagon(TETile[][] world, int s, Position p, TETile t){
        for(int y = 0; y < 2*s; y++){
            int width = xWidth(s, y);
            int empty = xEmpty(s, y);
            for(int x = empty; x < width + empty; x++){
                world[p.x + x][p.y + y] =  t;
            }
        }
    }

    @Test
    public void testXWidth(){
        assertEquals(2, xWidth(2, 0));
        assertEquals(4, xWidth(2, 2));
        assertEquals(8, xWidth(4, 2));
        assertEquals(6, xWidth(4, 6));
    }

    @Test
    public void testXEmpty(){
        assertEquals(1, xEmpty(2, 0));
        assertEquals(1, xEmpty(3, 1));
        assertEquals(2, xEmpty(3, 5));
        assertEquals(2, xEmpty(4, 6));
    }

    /* return the random Tileset */
    private static TETile randomTile(){
        int nextTile = RANDOM.nextInt(3);
        switch(nextTile){
            case 0: return Tileset.FLOWER;
            case 1: return Tileset.WALL;
            case 2: return Tileset.FLOOR;
            case 3: return Tileset.GRASS;
            default: return Tileset.MOUNTAIN;
        }
    }

    /* change the position */
    private static void positionChange(Position p, int s, int x, int y){
        p.x = 2*s*x;
        if(x % 2 == 1){
            p.y = s + 3*s*y;
        }else{
            if(x == 0 || x == 4){
                p.y = 2 * s + 3*s*y;
            }
            else{
                p.y = 3*s*y;
            }
        }
    }

    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for(int i = 0; i<WIDTH; i++){
            for(int j =0; j<HEIGHT; j++){
                world[i][j] = Tileset.NOTHING;
            }
        }

        HexWorld hw = new HexWorld();
        Position p = hw.new Position(0, 0);
        int size = 3;
        for(int i=0; i<3; i++){
            for(int j=0; j<i+3; j++){
                positionChange(p, size, i, j);
                int nextTile = RANDOM.nextInt();
                addHexagon(world, size, p, randomTile());
            }
        }
        for(int i=3; i<5; i++){
            for(int j=0; j<7-i; j++){
                positionChange(p, size, i, j);
                addHexagon(world, size, p, randomTile());
            }
        }


        ter.renderFrame(world);
    }
}
