package utils;

import interfaces.utils.iGZIPController;

/**
 * @author filreh
 */
public class GZIPController implements iGZIPController {
    /**
     * This method reads in a .csv file, compresses it and writes the contents to a .gz file.
     *
     * @param from location of .csv file
     * @param to   location of .gz file
     * @return
     */
    @Override
    public boolean compress(String from, String to) {
        //TODO
        return false;
    }

    /**
     * This method reads in a .gz file, decompresses it and writes the contents to a .csv file.
     *
     * @param from location of .gz file
     * @param to   location of .csv file
     * @return
     */
    @Override
    public boolean decompress(String from, String to) {
        //TODO
        return false;
    }
}
