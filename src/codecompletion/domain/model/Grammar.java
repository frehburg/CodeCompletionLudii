package codecompletion.domain.model;

import interfaces.codecompletion.domain.model.iGrammar;

import java.util.List;

/**
 * @author filreh
 */
public class Grammar implements iGrammar {
    /**
     * This method takes a list of instances with matching keys to the context and filters out the ones
     * that do not match the context, leaving only valid choices behind.
     *
     * @param context
     * @param match
     * @return
     */
    @Override
    public List<Instance> filterOutInvalid(Context context, List<Instance> match) {
        //TODO
        return null;
    }

    @Override
    public String getLocation() {
        //TODO
        return null;
    }
}
