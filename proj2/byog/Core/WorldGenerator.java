package byog.Core;

import java.util.Random;

public class WorldGenerator {
    private int width;
    private int height;
    private long seed;
    private Random random;

    public WorldGenerator(int w, int h){
        width = w;
        height = h;
    }

    public void worldDraw(long s){
        seed = s;
        random = new Random(seed);

    }
}
