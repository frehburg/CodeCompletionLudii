package codecompletion.domain.model;

import interfaces.codecompletion.domain.model.iNGram;

import java.util.ArrayList;
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
        // TODO: Duplicate every NUMBER into
        String integerWildcard = Preprocessing.INTEGER_WILDCARD;
        String floatWildcard = Preprocessing.FLOAT_WILDCARD;

        List<Instance> recs = dictionary.getOrDefault(instance.getKey(),new ArrayList<>());
        boolean foundEqual = false;
        for(Instance i : recs) {
            if(i.equals(instance)) {
                // found an instance with the same words --> increase its multiplicity
                i.increaseMultiplicity();
                foundEqual = true;
                break;
            }
        }

        if(!foundEqual) {
            recs.add(instance);
        }

        dictionary.put(instance.getKey(),recs);
    }

    /**
     * This method returns a list of all instances with the same key as the provided one.
     *
     * @param key
     * @return
     */
    @Override
    public List<Instance> getMatch(String key) {
        List<Instance> match = dictionary.getOrDefault(key, new ArrayList<>());
        return match;
    }

    /**
     * Get the value of N for the model.
     *
     * @return
     */
    @Override
    public int getN() {
        return N;
    }
}
