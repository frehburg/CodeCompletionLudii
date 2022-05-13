package interfaces;

import codecompletion.domain.Context;

import java.util.List;

/**
 * @author filreh
 */
public interface iInstance {
    /**
     * This method increases the multiplicity of the instance by one.
     */
    void increaseMultiplicity();

    /**
     * This method checks whether the instance is equal to any object.
     * If it is another instance object, only the words need to be compared.
     * This must be done with the String comparator
     * @param o
     * @return
     */
    boolean equals(Object o);

    /**
     * This method simply counts up the number of words this instance has in common with the context,
     * starting at the back.
     * @param context
     * @return
     */
    int matchingWords(Context context);

    /**
     * This method produces a string that represents the instance with all its fields.
     * @return
     */
    String toString();

    String getPrediction();

    String getKey();

    List<String> getWords();

    int getMultiplicity();
}
