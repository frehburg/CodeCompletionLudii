package interfaces.utils;

import codecompletion.domain.model.NGram;

/**
 * @author filreh
 */
public interface iModel2CSV {
    /**
     * This method writes a model to a csv file. The location is according to the internal
     * storing mechanism that is based on the N parameter.
     * @param model
     * @return
     */
    String model2csv(NGram model);

    /**
     * This method reads in a model from a .csv file.
     * @param location
     * @return
     */
    NGram csv2model(String location);
}
