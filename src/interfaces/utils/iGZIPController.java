package interfaces.utils;

/**
 * @author filreh
 */
public interface iGZIPController {
    /**
     * This method reads in a .csv file, compresses it and writes the contents to a .gz file.
     * @param from location of .csv file
     * @param to location of .gz file
     * @return
     */
    boolean compress(String from, String to);

    /**
     * This method reads in a .gz file, decompresses it and writes the contents to a .csv file.
     * @param from location of .gz file
     * @param to location of .csv file
     * @return
     */
    boolean decompress(String from, String to);
}
