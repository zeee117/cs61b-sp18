import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testOffByN(){
        CharacterComparator cc = new OffByN(5);

        assertTrue(cc.equalChars('a', 'f'));
        assertTrue(cc.equalChars('f', 'a'));
        assertFalse(cc.equalChars('a', 'b'));
        assertFalse(cc.equalChars('a', 'F'));
    }
}
