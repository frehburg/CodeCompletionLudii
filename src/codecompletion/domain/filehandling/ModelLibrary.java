package codecompletion.domain.filehandling;

import codecompletion.domain.model.ModelCreator;
import codecompletion.domain.model.NGram;
import interfaces.codecompletion.domain.filehandling.iModelLibrary;
import utils.GZIPController;
import utils.Model2CSV;

import java.util.List;

/**
 * @author filreh
 */
public class ModelLibrary implements iModelLibrary {

    private final DocHandler docHandler;

    public ModelLibrary(DocHandler docHandler) {
        this.docHandler = docHandler;
    }

    /**
     * This method returns a model with the specified N.
     * If it didn't exist before it is created. ANd written to a file.
     * Adds it to the model locations. Also in the documents.txt
     *
     * @param N
     * @return
     */
    @Override
    public NGram getModel(int N) {
        NGram model;
        //model does not exist
        if(docHandler.getModelLocation(N).equals(DocHandler.MODEL_DOES_NOT_EXIST)) {
            //create model
            model = ModelCreator.createModel(N);

        } else {
            //model does exist
            model = ModelFilehandler.readModel(N);
        }
        return model;
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
