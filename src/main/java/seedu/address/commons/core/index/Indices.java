package seedu.address.commons.core.index;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a zero-based or one-based list of indices.
 *
 */
public class Indices {
    private int[] zeroBasedIndices;

    /**
     * Indices can only be created by calling {@link Indices#fromZeroBased(int[])} or
     * {@link Indices#fromOneBased(int[])}.
     */
    private Indices(int[] zeroBasedIndices) {
        for (int i = 0; i < zeroBasedIndices.length; i++) {
            if (zeroBasedIndices[i] < 0) {
                throw new IndexOutOfBoundsException();
            }
        }
        this.zeroBasedIndices = zeroBasedIndices;
    }

    public int[] getZeroBased() {
        return zeroBasedIndices;
    }

    public int[] getOneBased() {
        int[] oneBasedIndices = new int[zeroBasedIndices.length];
        for (int i = 0; i < zeroBasedIndices.length; i++) {
            oneBasedIndices[i] = zeroBasedIndices[i] + 1;
        }
        return oneBasedIndices;
    }

    /**
     * Creates a new {@code Indices} using a zero-based index.
     */
    public static Indices fromZeroBased(int[] zeroBasedIndex) {
        return new Indices(zeroBasedIndex);
    }

    /**
     * Creates a new {@code Indices} using a one-based index.
     */
    public static Indices fromOneBased(int[] oneBasedIndices) {
        int[] zeroBasedIndices = new int[oneBasedIndices.length];
        for (int i = 0; i < zeroBasedIndices.length; i++) {
            zeroBasedIndices[i] = oneBasedIndices[i] - 1;
        }
        return new Indices(zeroBasedIndices);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Indices)) {
            return false;
        }

        Indices otherIndex = (Indices) other;
        return zeroBasedIndices == otherIndex.zeroBasedIndices;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("zeroBasedIndices", zeroBasedIndices).toString();
    }
}
