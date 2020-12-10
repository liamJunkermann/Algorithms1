package assignment3;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 * Test class for Doubly Linked List
 *
 * @version 3.1 09/11/15 11:32:15
 *
 * @author Liam Junkermann
 */

@RunWith(JUnit4.class)
public class BSTTest {

    @Test
    public void testIsEmpty() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertTrue("Test BST is empty", bst.isEmpty());
    }

    @Test
    public void testSize() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Empty bst", bst.size(), 0);
        bst.put(2, 2);
        assertEquals("Single bst", bst.size(), 1);
        bst.put(1, 1);
        bst.put(3, 3);
        assertEquals("3 BST", bst.size(), 3);
    }

    @Test
    public void testContains() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertFalse("Empty bst", bst.contains(0));
        bst.put(1, 1);
        assertTrue("Filled and contains value", bst.contains(1));
        assertFalse("Filled and does not contain value", bst.contains(3));
    }

    @Test
    public void testGet() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertNull("Empty bst", bst.get(1));
        bst.put(1, 1);
        assertNull("Key is not there", bst.get(0));
        assertEquals("Key exists", (int) bst.get(1), 1);
        bst.put(2, 2);
        assertEquals("Another key exists", (int) bst.get(2), 2);
    }

    @Test
    public void testHeight() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Empty BST", bst.height(), -1);
        bst.put(3, 3);
        assertEquals("Single elem", bst.height(), 0);
        bst.put(1, 1);
        assertEquals("Two elements", bst.height(), 1);
        bst.put(0, 0);
        assertEquals("3 elements", bst.height(), 2);

    }

    @Test
    public void testMedian() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertNull(bst.median());
        bst.put(6, 6);
        assertTrue(bst.median().equals(6));
        bst.put(8, 8);
        bst.put(5, 5);
        bst.put(16, 16);
        bst.put(12, 12);
        assertTrue(bst.median().equals(8));
        bst = new BST<Integer, Integer>();
        bst.put(15, 15);
        bst.put(25, 25);
        bst.put(16, 16);
        bst.put(9, 9);
        bst.put(8, 8);
        bst.put(12, 12);
        assertTrue(bst.median().equals(12));
    }

    @Test
    public void testPrintKeysInOrder() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("()", bst.printKeysInOrder());
        bst.put(10, 10);
        assertEquals("(()10())", bst.printKeysInOrder());
        bst.put(1, 1);
        bst.put(0, 0);
        bst.put(5, 5);
        assertEquals("(((()0())1(()5()))10())", bst.printKeysInOrder());
    }

    @Test
    public void testPut() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.put(1, 1);
        bst.put(1, null);
        bst.put(2, 3);
        bst.put(4, 3);
        bst.put(2, 2);
    }

    /**
     * <p>
     * Test {@link BST#prettyPrintKeys()}.
     * </p>
     */

    @Test
    public void testPrettyPrint() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Checking pretty printing of empty tree", "-null\n", bst.prettyPrintKeys());

        // -7
        // |-3
        // | |-1
        // | | |-null
        bst.put(7, 7); // | | -2
        bst.put(8, 8); // | | |-null
        bst.put(3, 3); // | | -null
        bst.put(1, 1); // | -6
        bst.put(2, 2); // | |-4
        bst.put(6, 6); // | | |-null
        bst.put(4, 4); // | | -5
        bst.put(5, 5); // | | |-null
                       // | | -null
                       // | -null
                       // -8
                       // |-null
                       // -null

        String result = "-7\n" + " |-3\n" + " | |-1\n" + " | | |-null\n" + " | |  -2\n" + " | |   |-null\n"
                + " | |    -null\n" + " |  -6\n" + " |   |-4\n" + " |   | |-null\n" + " |   |  -5\n"
                + " |   |   |-null\n" + " |   |    -null\n" + " |    -null\n" + "  -8\n" + "   |-null\n"
                + "    -null\n";
        assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
    }

    /**
     * <p>
     * Test {@link BST#delete(Comparable)}.
     * </p>
     */
    @Test
    public void testDelete() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.delete(1);
        assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());

        bst.put(7, 7); // _7_
        bst.put(8, 8); // / \
        bst.put(3, 3); // _3_ 8
        bst.put(1, 1); // / \
        bst.put(2, 2); // 1 6
        bst.put(6, 6); // \ /
        bst.put(4, 4); // 2 4
        bst.put(5, 5); // \
                       // 5

        assertEquals("Checking order of constructed tree", "(((()1(()2()))3((()4(()5()))6()))7(()8()))",
                bst.printKeysInOrder());

        bst.delete(9);
        assertEquals("Deleting non-existent key", "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

        bst.delete(8);
        assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

        bst.delete(6);
        assertEquals("Deleting node with single child", "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

        bst.delete(3);
        assertEquals("Deleting node with two children", "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
    }

}
