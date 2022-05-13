package interfaces.utils;

/**
 * @author filreh
 */
public interface iStringComparator {
    /**
     * This method compares two strings for equality. Possibility for use of hashing.
     * @param s1
     * @param s2
     * @return
     */
    boolean equals(String s1, String s2);
}
