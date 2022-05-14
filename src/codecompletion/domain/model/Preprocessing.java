package codecompletion.domain.model;

/**
 * @author filreh
 */
public class Preprocessing {

    /**
     * This method applies all the necessary steps of preprocessing.
     * @param gameDescription
     * @return
     */
    public static String preprocess(String gameDescription) {
        gameDescription = removeMetadata(gameDescription);
        gameDescription = removeComments(gameDescription);
        gameDescription = removeWhitespaces(gameDescription);
        gameDescription = genericValues(gameDescription);
        return gameDescription;
    }

    /**
     * This method removes the metadata from the game description
     *
     * @param gameDescription
     * @return
     */
    public static String removeMetadata(String gameDescription) {
        //TODO
        return null;
    }

    /**
     * This method removes all comments from the game description
     *
     * @param gameDescription
     * @return
     */
    public static String removeComments(String gameDescription) {
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
    public static  String removeWhitespaces(String gameDescription) {
        //TODO
        return null;
    }

    /**
     * This method replaces specific values with generic wildcards
     *
     * @param gameDescription
     * @return
     */
    public static String genericValues(String gameDescription) {
        //TODO
        return null;
    }
}
