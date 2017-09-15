import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListStackTest {

    @Test
    public void isEmptyTest() {
        LinkedListStack stack = new LinkedListStack();

        assertTrue("Stack is not empty", stack.isEmpty());
    }

    @Test
    public void pushAndPopTest() {
        LinkedListStack stack = new LinkedListStack();

        String one = "one";
        String two = "two";
        String three = "three";

        stack.push(one);
        stack.push(two);
        stack.push(three);

        assertEquals("Items don't match", stack.pop(), three);
        assertEquals("Items don't match", stack.pop(), two);
        assertEquals("Items don't match", stack.pop(), one);
    }
}
