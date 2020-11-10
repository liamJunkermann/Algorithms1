package assignment1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
// import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 * Test class for Collinear.java
 *
 * @author Liam Junkermann
 * @version 18/09/18 12:21:26
 */
@RunWith(JUnit4.class)
public class CollinearTest {
    // ~ Constructor ........................................................
    @Test
    public void testConstructor() {
        new Collinear();
    }

    // ~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty() {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays", expectedResult,
                Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult,
                Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse() {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})", expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleTrue() {
        int[] a3 = { 15, 5 };
        int[] a2 = { 5 };
        int[] a1 = { 10, 15, 5 };

        int expectedResult = 1;

        assertEquals(
                "countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",
                expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3)
                + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }

    @Test
    public void testSort() {
        int[] a = { 11, 28, 86, 71, 4, 41, 8, 80 };
        int[] expected = { 4, 8, 11, 28, 41, 71, 80, 86 };
        int[] original = Arrays.copyOf(a, a.length);
        Collinear.sort(a);
        assertArrayEquals("sort(" + Arrays.toString(original) + ")", expected, a);
    }

    @Test
    public void testBinarySearch(){
        int[] a = { 4, 8, 11, 28, 41, 71, 80, 86 };
        int exists = 11;
        int noExist = 10;
        assertEquals("binarySearch("+Arrays.toString(a)+"," + exists + ")", true, Collinear.binarySearch(a, exists));
        assertEquals("binarySearch("+Arrays.toString(a)+"," + noExist + ")", false, Collinear.binarySearch(a, noExist));
    }
}
