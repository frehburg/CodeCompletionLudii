package codecompletion.domain.filehandling;

import codecompletion.domain.model.ModelCreator;
import codecompletion.domain.model.NGram;
import interfaces.codecompletion.domain.filehandling.iModelLibrary;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author filreh
 */
public class ModelLibrary implements iModelLibrary {

    private final DocHandler docHandler;

    private List<String> modelLocations;
    private Map<Integer,NGram> allModels;

    public ModelLibrary() {
        this.docHandler = DocHandler.getInstance();
        allModels = new HashMap<>();
        this.modelLocations = allModelLocations();
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
        //1. check if it is in the already loaded in models
        NGram model = allModels.getOrDefault(N,null);
        if(model == null) {
            addModel(N);
        }
        return model;
    }

    /**
     * Returns all model locations, is updated everytime it is called
     *
     * @return
     */
    @Override
    public List<String> allModelLocations() {
        modelLocations = new ArrayList<>();
        for(int N = 2; N <= 20; N++) {
            String location = docHandler.getModelLocation(N);
            //if the model exists
            if(!StringUtils.equals(location,docHandler.MODEL_DOES_NOT_EXIST)) {
                modelLocations.add(location);
            }
        }
        return modelLocations;
    }

    /**
     * Returns the amount of models stored currently
     *
     * @return
     */
    @Override
    public int getAmountModels() {
        //update the list before returning
        allModelLocations();
        return modelLocations.size();
    }

    /**
     * If the model is not already included in the list of models, then it is created and added to the list.
     * If this method is called, then the model is not in allModels
     * @param N
     * @return
     */
    @Override
    public NGram addModel(int N) {
        NGram model;
        //1. check if it exists
        if(docHandler.getModelLocation(N).equals(DocHandler.MODEL_DOES_NOT_EXIST)) {
            //1.a does not exist: create a new one
            model = ModelCreator.createModel(N);

        } else {
            //1.b model does exist: read it in from file
            model = ModelFilehandler.readModel(N);
        }
        //either way add it to the loaded in models
        allModels.put(N,model);
        return model;
    }
}
