package interfaces;

import codecompletion.domain.Instance;

import java.util.List;

/**
 * @author filreh
 */
public interface iNGram {
    /**
     * This method returns a list of all instances with the same key as the provided one.
     * @param key
     * @return
     */
    List<Instance> getMatch(String key);

    /**
     * Get the value of N for the model.
     * @return
     */
    int getN();
}
