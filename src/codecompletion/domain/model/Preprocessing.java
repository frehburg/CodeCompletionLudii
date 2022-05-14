package codecompletion.domain.model;

/**
 * @author filreh
 */
public class Preprocessing {
    private static final boolean DEBUG = false;

    /**
     * This method applies all the necessary steps of preprocessing.
     * @param gameDescription
     * @return
     */
    public static String preprocess(String gameDescription) {
//        if(DEBUG)System.out.println("Raw:"+gameDescription);
        gameDescription = removeMetadata(gameDescription);
        if(DEBUG)System.out.println("Removed Metadata:"+gameDescription);
        if(DEBUG)System.out.println("Removed Comments:");
        gameDescription = removeComments(gameDescription);
        gameDescription = removeWhitespaces(gameDescription);
        if(DEBUG)System.out.println("Removed Withespaces:"+gameDescription);
//        gameDescription = genericValues(gameDescription);
//        if(DEBUG)System.out.println("Replaced specific values with generic placeholders:"+gameDescription);
        return gameDescription;
    }

    /**
     * This method removes the metadata from the game description
     *
     * @param gameDescription
     * @return
     */
    public static String removeMetadata(String gameDescription) {
        String metadataLudeme = "(metadata";
        int startMetadata = gameDescription.lastIndexOf(metadataLudeme);
        gameDescription = gameDescription.substring(0,startMetadata);
        return gameDescription;
    }

    /**
     * This method removes all comments from the game description and ravels the description into one line.
     *
     * @param gameDescription
     * @return
     */
    public static String removeComments(String gameDescription) {
        String commentLudeme = "//";
        String[] lines = gameDescription.split("\n");
        String noComments = "";
        for(int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if(line.contains(commentLudeme)) {
                int commentLocation = line.lastIndexOf(commentLudeme);
                line = line.substring(0,commentLocation);
            }
            noComments += line;
            if(DEBUG)System.out.println("Line "+i+": " + line);
        }
        gameDescription = noComments;
        return gameDescription;
    }

    /**
     * This method removes all tabs and unnecessary whitespaces from the game description while adding
     * needed ones.
     *
     * @param gameDescription
     * @return
     */
    public static  String removeWhitespaces(String gameDescription) {
        char[] chars = gameDescription.toCharArray();
        gameDescription = "";
        char lastChar = '1';
        for(char c : chars) {
            if (lastChar == ' ') {
                //do not add the char to the game description
            } else if(lastChar == ')') {
                //add a space after a closing )
                gameDescription += " "+c;
            } else {
                gameDescription += c;
            }
        }
        return gameDescription;
    }

    /**
     * This method replaces specific values with generic wildcards
     *
     * @param gameDescription
     * @return
     */
    public static String genericValues(String gameDescription) {
        //TODO
        return gameDescription;
    }
}
