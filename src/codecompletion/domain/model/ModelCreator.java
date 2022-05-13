package codecompletion.domain.model;

import interfaces.codecompletion.domain.model.iModelCreator;

/**
 * @author filreh
 */
public class ModelCreator implements iModelCreator {
    /**
     * This method should only be called as part of the validation process or by the ModelLibrary
     * This method creates a new model and writes a model to a csv file.
     * The location is according to the internal storing mechanism that is based on the N parameter.
     *
     * @param N
     * @return
     */
    @Override
    public String createModel(int N) {
        //TODO
        return null;
    }
}
