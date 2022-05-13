package codecompletion.domain.model;

import interfaces.codecompletion.domain.model.iNGram;

import java.util.List;

/**
 * @author filreh
 */
public class NGram implements iNGram {
    /**
     * This method returns a list of all instances with the same key as the provided one.
     *
     * @param key
     * @return
     */
    @Override
    public List<Instance> getMatch(String key) {
        //TODO
        return null;
    }

    /**
     * Get the value of N for the model.
     *
     * @return
     */
    @Override
    public int getN() {
        //TODO
        return 0;
    }
}
