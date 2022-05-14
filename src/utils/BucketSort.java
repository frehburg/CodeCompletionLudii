package utils;

import codecompletion.domain.model.Instance;

import java.util.List;

/**
 * @author filreh
 */
public class BucketSort {
    /**
     * This method takes in an unordered picklist that has already been filtered for invalid choices.
     * Now it first uses a bucket sort to sort it into buckets according to the amount of matching words of each
     * instance.
     * Then it sorts those buckets by multiplicity and ravels everything into one list again.
     *
     * @param unorderedPicklist
     * @return
     */
    public static List<Instance> sort(List<Instance> unorderedPicklist) {
        //TODO
        return unorderedPicklist;
    }
}
