package codecompletion.domain.model;

import interfaces.codecompletion.domain.model.iNGram;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author filreh
 */
public class NGram implements iNGram {
    private final int N;
    private HashMap<String, List<Instance>> dictionary;

    public NGram(int N) {
        this.N = N;
        this.dictionary = new HashMap<>();
    }

    /**
     * This constructor is to read in a model
     * @param N
     * @param dictionary
     */
    public NGram(int N, HashMap<String, List<Instance>> dictionary) {
        this.N = N;
        this.dictionary = dictionary;
    }

    /**
     * This method adds one instance to the model, be weary of multiplicities
     *
     * @param instance
     */
    @Override
    public void addInstanceToModel(Instance instance) {

    }

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
