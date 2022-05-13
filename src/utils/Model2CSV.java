package utils;

import codecompletion.domain.model.NGram;
import interfaces.utils.iModel2CSV;

/**
 * @author filreh
 */
public class Model2CSV implements iModel2CSV {
    /**
     * This method writes a model to a csv file. The location is according to the internal
     * storing mechanism that is based on the N parameter.
     *
     * @param model
     * @return
     */
    @Override
    public String model2csv(NGram model) {
        //TODO
        return null;
    }

    /**
     * This method reads in a model from a .csv file.
     *
     * @param location
     * @return
     */
    @Override
    public NGram csv2model(String location) {
        //TODO
        return null;
    }
}
