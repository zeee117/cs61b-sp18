import org.junit.Test;
import static org.junit.Assert.*;

public class RadixSortTester {
    private static String[] input = new String[] {"a", "sum", "code", "run", "running"};
    private static String[] expected = new String[] {"a", "code", "run", "running", "sum"};

    @Test
    public void testSort(){
        String[] output = RadixSort.sort(input);
        assertEquals(output, expected);
    }
}
