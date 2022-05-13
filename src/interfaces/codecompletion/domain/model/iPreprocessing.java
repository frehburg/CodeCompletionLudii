package interfaces.codecompletion.domain.model;

/**
 * @author filreh
 */
public interface iPreprocessing {
    /**
     * This method performs all necessary steps of preprocessing for a game description:
     * - remove metadata
     * - remove comments
     * - remove white spaces
     * - make values generic, e.g. "Hello" -> String
     * @param gameDescription
     * @return
     */

    /**
     * This method removes the metadata from the game description
     * @param gameDescription
     * @return
     */
    String removeMetadata(String gameDescription);

    /**
     * This method removes all comments from the game description
     * @param gameDescription
     * @return
     */
    String removeComments(String gameDescription);

    /**
     * This method removes all tabs and unnecessary whitespaces from the game description while adding
     * needed ones.
     * @param gameDescription
     * @return
     */
    String removeWhitespaves(String gameDescription);

    /**
     * This method replaces specific values with generic wildcards
     * @param gameDescription
     * @return
     */
    String genericValues(String gameDescription);
}
