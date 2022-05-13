package codecompletion.domain.filehandling;

import codecompletion.domain.model.NGram;
import interfaces.codecompletion.domain.filehandling.iModelLibrary;

import java.util.List;

/**
 * @author filreh
 */
public class ModelLibrary implements iModelLibrary {
    /**
     * This method returns a model with the specified N.
     * If it didn't exist before it is created.
     * Adds it to the model locations. Also in the documents.txt
     *
     * @param N
     * @return
     */
    @Override
    public NGram getModel(int N) {
        return null;
    }

    /**
     * Returns all model locations
     *
     * @return
     */
    @Override
    public List<String> allLocations() {
        return null;
    }

    /**
     * Returns the amount of models stored currently
     *
     * @return
     */
    @Override
    public int getAmountModels() {
        return 0;
    }

    /**
     * If the model is not already included in the list of models, then it is created and added to the list.
     *
     * @param N
     * @return
     */
    @Override
    public NGram addModel(int N) {
        return null;
    }
}
