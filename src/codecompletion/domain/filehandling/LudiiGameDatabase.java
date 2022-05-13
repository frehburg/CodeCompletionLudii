package codecompletion.domain.filehandling;

import interfaces.codecompletion.domain.filehandling.iLudiiGameDatabase;

import java.util.List;

/**
 * @author filreh
 */
public class LudiiGameDatabase implements iLudiiGameDatabase {
    /**
     * Returns a list of all the locations of game descriptions in the database.
     *
     * @return
     */
    @Override
    public List<String> getLocations() {
        return null;
    }

    /**
     * Returns the amount of games in the database
     *
     * @return
     */
    @Override
    public int getAmountGames() {
        return 0;
    }

    /**
     * Returns the description of the game with the id in the locations list
     *
     * @param id
     * @return
     */
    @Override
    public String getDescription(int id) {
        return null;
    }

    /**
     * Returns the description of the game with the name specified. Create a map that links names to
     * ids and use the other method.
     *
     * @param name
     * @return
     */
    @Override
    public String getDescription(String name) {
        return null;
    }
}
