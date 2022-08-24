import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void testFlik(){
        Integer a = 1;
        Integer b = 500;
        Integer c = 1;
        System.out.println(Flik.isSameNumber(a, b));
        System.out.println(Flik.isSameNumber(a, c));
        assertTrue(Flik.isSameNumber(a, c));
    }
}
