package seedu.address.commons.core.index;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class IndicesTest {

    @Test
    public void createIndicesFromArray() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Indices.fromOneBased(new int[]{0}));

        // check equality using the same array
        assertArrayEquals(new int[]{1, 2, 3}, Indices.fromOneBased(new int[]{1, 2, 3}).getOneBased());
        assertArrayEquals(new int[]{5,6,7}, Indices.fromOneBased(new int[]{5,6,7}).getOneBased());

        assertArrayEquals(new int[]{0,1,2}, Indices.fromOneBased(new int[]{1,2,3}).getZeroBased());
        assertArrayEquals(new int[]{4,5,6}, Indices.fromOneBased(new int[]{5,6,7}).getZeroBased());
    }

    @Test
    public void equals() {
        final Indices indices = new Indices(new int[]{1, 2, 3});

        // same values -> returns true
        assertTrue(indices.equals(new Indices(new int[]{1, 2, 3})));

        // same object -> returns true
        assertTrue(indices.equals(indices));

        // null -> returns false
        assertFalse(indices.equals(null));

        // different types -> returns false
        assertFalse(indices.equals(5.0f));

        // different index -> returns false
        assertFalse(indices.equals(new Indices(new int[]{1, 2})));
    }

    @Test
    public void toStringMethod() {
        Indices indices = new Indices(new int[]{1, 2, 3});
        String expected = Indices.class.getCanonicalName() + "{zeroBasedIndices=" + Arrays.toString(indices.getZeroBased()) + "}";
        assertEquals(expected, indices.toString());
    }
}
