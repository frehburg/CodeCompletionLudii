package codecompletion.domain.model;

import interfaces.codecompletion.domain.model.iPreprocessing;

/**
 * @author filreh
 */
public class Preprocessing implements iPreprocessing {
    /**
     * This method removes the metadata from the game description
     *
     * @param gameDescription
     * @return
     */
    @Override
    public String removeMetadata(String gameDescription) {
        //TODO
        return null;
    }

    /**
     * This method removes all comments from the game description
     *
     * @param gameDescription
     * @return
     */
    @Override
    public String removeComments(String gameDescription) {
        //TODO
        return null;
    }

    /**
     * This method removes all tabs and unnecessary whitespaces from the game description while adding
     * needed ones.
     *
     * @param gameDescription
     * @return
     */
    @Override
    public String removeWhitespaves(String gameDescription) {
        //TODO
        return null;
    }

    /**
     * This method replaces specific values with generic wildcards
     *
     * @param gameDescription
     * @return
     */
    @Override
    public String genericValues(String gameDescription) {
        //TODO
        return null;
    }
}
