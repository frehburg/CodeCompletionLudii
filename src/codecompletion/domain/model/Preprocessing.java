package codecompletion.domain.model;

import utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author filreh
 */
public class Preprocessing {
    private static final boolean DEBUG = true;
    private static final String NUMBER_WILDCARD = "NUMBER";
    private static final String BOOLEAN_WILDCARD = "BOOLEAN";

    /**
     * This method applies all the necessary steps of preprocessing.
     * @param gameDescription
     * @return
     */
    public static String preprocess(String gameDescription) {
//        if(DEBUG)System.out.println("Raw:"+gameDescription);
        gameDescription = removeMetadata(gameDescription);
//        if(DEBUG)System.out.println("Removed Metadata:"+gameDescription);
        gameDescription = removeComments(gameDescription);
//        if(DEBUG)System.out.println("Removed Comments:"+gameDescription);
        gameDescription = removeWhitespaces(gameDescription);
//        if(DEBUG)System.out.println("Removed Withespaces:"+gameDescription);
        gameDescription = genericValues(gameDescription);
        if(DEBUG)System.out.println("Replaced specific values with generic placeholders:"+gameDescription);
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
        if(gameDescription.contains(metadataLudeme)) {
            int startMetadata = gameDescription.lastIndexOf(metadataLudeme);
            gameDescription = gameDescription.substring(0,startMetadata);
        }
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
        char prevChar = '1';

        for(int i = 0; i < chars.length; i++) {
            char curChar = chars[i];
            // so it does not go out of bounds
            char nextChar = i < (chars.length - 1) ? chars[(i + 1)] : chars[i];
            if (prevChar == ' ' && curChar == ' ') {
                //do not add the char to the game description
            } else if(nextChar == ')' || nextChar == '}') {
                //add a space before a closing )
                gameDescription += curChar+" ";
            } else if(curChar == ')' && (nextChar != ')' && nextChar != ' ')) {
                //add a space before a closing )
                gameDescription += curChar+" ";
            } else if(curChar == '{' && nextChar != ' ') {
                //add a space before a closing )
                gameDescription += curChar+" ";
            } else {
                gameDescription += curChar;
            }
            prevChar = curChar;
        }

        chars = gameDescription.toCharArray();
        gameDescription = "";
        prevChar = '1';
        // again remove any double spaces
        for(int i = 0; i < chars.length; i++) {
            char curChar = chars[i];
            // so it does not go out of bounds
            char nextChar = i < (chars.length - 1) ? chars[(i + 1)] : chars[i];
            if ((prevChar == ' ' && curChar == ' ') || curChar == '\t' || curChar == '\n') {
                //do not add the char to the game description
            } else {
                gameDescription += curChar;
            }
            prevChar = curChar;
        }

        return gameDescription;
    }

    /**
     * This method replaces specific values with generic wildcards
     * Strings will stay in as they are of importance to the game
     * @param gameDescription
     * @return
     */
    public static String genericValues(String gameDescription) {

        // REPLACE NUMBERS
        char[] chars = gameDescription.toCharArray();
        gameDescription = "";
        char prevChar = '?';

        boolean foundNumber = false;
        for(int i = 0; i < chars.length; i++) {
            char curChar = chars[i];
            char nextChar = i < (chars.length - 1) ? chars[(i + 1)] : chars[i];
            switch (curChar) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '.':
                    if(!foundNumber) {
                        if(prevChar == ' ' || prevChar == ':') {
                            //found a number
                            foundNumber = true;
                            // do not add
                        } else {
                            // this digit was part of a word
                            gameDescription += curChar;
                        }
                    }
                    if(foundNumber) {
                        if(nextChar == ' ') {
                            gameDescription += NUMBER_WILDCARD;
                            foundNumber = false;
                        }
                    }
                    break;
                default:
                    gameDescription += curChar;
                    break;
            }

            prevChar = curChar;
        }

        gameDescription = gameDescription.replaceAll("True",BOOLEAN_WILDCARD);
        gameDescription = gameDescription.replaceAll("False",BOOLEAN_WILDCARD);

        return gameDescription;
    }
}
