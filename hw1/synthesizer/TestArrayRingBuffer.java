package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        for(int i=9; i>=0; i--){
            arb.enqueue(i);
        }
        assertEquals(9, arb.dequeue());
        assertEquals(8, arb.dequeue());
        assertEquals(7, arb.dequeue());
        assertEquals(6, arb.peek());
        assertEquals(6, arb.dequeue());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
