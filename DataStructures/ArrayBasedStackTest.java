import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayBasedStackTest {

    @Test
    public void isEmptyTest() {
        ArrayBasedStack<Integer>  s = new ArrayBasedStack<>();

        assertTrue("Stack is not empty", s.isEmpty());
    }

    @Test
    public void pushPopTest() {
        ArrayBasedStack<String> s = new ArrayBasedStack<>();

        String one = "one";
        String two = "two";
        String three = "three";

        s.push(one);
        s.push(two);
        s.push(three);

        assertEquals("Stacked popped wrong item", s.pop(), three);
        assertEquals("Stacked popped wrong item", s.pop(), two);
        assertEquals("Stacked popped wrong item", s.pop(), one);
    }
}
