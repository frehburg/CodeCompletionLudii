package utils;

import interfaces.utils.iStringComparator;

/**
 * @author filreh
 */
public class StringComparator implements iStringComparator {
    /**
     * This method compares two strings for equality. Possibility for use of hashing.
     *
     * @param s1
     * @param s2
     * @return
     */
    @Override
    public boolean equals(String s1, String s2) {
        return s1.equals(s2);
    }
}
