package interfaces;

import codecompletion.domain.NGram;
import java.util.List;

/**
 * @author filreh
 */
public interface iModelLibrary {
    /**
     * This method returns a model with the specified N.
     * If it didn't exist before it is created.
     * Adds it to the model locations. Also in the documents.txt
     * @param N
     * @return
     */
    NGram getModel(int N);

    /**
     * Returns all model locations
     * @return
     */
    List<String> allLocations();

    /**
     * Returns the amount of models stored currently
     * @return
     */
    int getAmountModels();

    /**
     * If the model is not already included in the list of models, then it is created and added to the list.
     * @param N
     * @return
     */
    NGram addModel(int N);
}
