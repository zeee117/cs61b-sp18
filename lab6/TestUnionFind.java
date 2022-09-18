import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {
    @Test
    public void testUnionFind(){
        UnionFind uf = new UnionFind(10);
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(1, 2);
        assertEquals(1, uf.parent(0));

        // assertTrue(uf.connected(0, 2));
        uf.union(0, 2);

        assertEquals(4, uf.sizeOf(0));
        assertEquals(4, uf.sizeOf(1));
        assertEquals(4, uf.sizeOf(2));
        assertEquals(4, uf.sizeOf(3));
        assertTrue(uf.connected(1, 3));

        assertEquals(3, uf.find(0));
    }
}
