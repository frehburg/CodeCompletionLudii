package codecompletion.domain.model;

import interfaces.codecompletion.domain.model.iInstance;

import java.util.List;

/**
 * @author filreh
 */
public class Instance implements iInstance {
    /**
     * This method increases the multiplicity of the instance by one.
     */
    @Override
    public void increaseMultiplicity() {
        //TODO
    }

    /**
     * This method simply counts up the number of words this instance has in common with the context,
     * starting at the back.
     *
     * @param context
     * @return
     */
    @Override
    public int matchingWords(Context context) {
        //TODO
        return 0;
    }

    @Override
    public String getPrediction() {
        //TODO
        return null;
    }

    @Override
    public String getKey() {
        //TODO
        return null;
    }

    @Override
    public List<String> getWords() {
        //TODO
        return null;
    }

    @Override
    public int getMultiplicity() {
        //TODO
        return 0;
    }
}
