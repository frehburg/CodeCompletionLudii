package codecompletion.domain.model;

import codecompletion.domain.filehandling.DocHandler;
import codecompletion.domain.filehandling.ModelFilehandler;

/**
 * @author filreh
 */
public class ModelCreator {
    /**
     * This method should only be called as part of the validation process or by the ModelLibrary
     * This method creates a new model and writes a model to a .gz file.
     * The location is according to the internal storing mechanism that is based on the N parameter.
     *
     * @param N
     * @return
     */
    public static NGram createModel(int N) {
        ModelFilehandler.writeModel(null);
        //TODO
        return null;
    }
}
