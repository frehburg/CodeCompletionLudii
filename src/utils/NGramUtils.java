package utils;

import codecompletion.domain.model.Context;
import codecompletion.domain.model.Instance;
import codecompletion.domain.model.Preprocessing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author filreh
 */
public class NGramUtils {
    public final static boolean GENERIC_STRINGS = true;
    public final static boolean GENERIC_OPTIONS = true;
    public final static String STRING_WILDCARD = Preprocessing.STRING_WILDCARD;
    public final static String OPTION_WILDCARD = Preprocessing.OPTION_WILDCARD;
    /**
     * This method returns a list of all substrings of length N
     *
     * @param N
     * @return
     */
    public static List<List<String>> allSubstrings(String gameDescription, int N) {
        String[] words = gameDescription.split(" ");
        //merge strings in code back together
        List<String> wordsList = new ArrayList<>();
        boolean foundStringInCode = false;
        String stringInCode = "";
        for(int i = 0; i < words.length; i++) {
            String curWord = words[i];
            if(foundStringInCode) {
                if(curWord.endsWith("\"")) {
                    //end of the string in code
                    if(GENERIC_STRINGS) {
                        wordsList.add(STRING_WILDCARD);
                    } else {
                        stringInCode += curWord;
                        wordsList.add(stringInCode);
                    }
                    //resetting helper variables
                    foundStringInCode = false;
                    stringInCode = "";
                } else {
                    //middle of the string in code
                    stringInCode += curWord + " ";
                }
            } else if(curWord.startsWith("\"") && !curWord.endsWith("\"")) {
                //beginning of the string in code
                foundStringInCode = true;
                stringInCode += curWord + " ";
            } else {
                if(GENERIC_STRINGS && curWord.startsWith("\"") && curWord.endsWith("\"")) {
                    //string in code
                    wordsList.add(STRING_WILDCARD);
                } else if(GENERIC_OPTIONS && (curWord.startsWith("<") || curWord.endsWith(">"))){
                    wordsList.add(OPTION_WILDCARD);
                } else if(StringUtils.equals(curWord,"*")) {
                    //do nothing
                } else {
                    //just a normal word
                    wordsList.add(curWord);
                }
            }
        }
        words = wordsList.toArray(new String[0]);
        //end merging strings
        List<List<String>> substringsLengthN = new ArrayList<>();
        for(int i = 0; i <= words.length; i++) {
            int substringEnd = i + N;

            if(substringEnd <= words.length) {
                List<String> curSubstring = new ArrayList<>();
                for(int j = i; j < substringEnd; j++) {
                    curSubstring.add(words[j]);
                }
                substringsLengthN.add(curSubstring);
            }
        }
        return substringsLengthN;
    }

    /**
     * This method takes a list of words and turns it into an N Gram instance
     *
     * @param words
     * @return
     */
    public static Instance createInstance(List<String> words) {
        if(words.size() < 2) {
            return null;
        }
        Instance instance = new Instance(words);
        return instance;
    }

    /**
     * This method takes the context string and turns it into a Context object
     *
     * @param context
     * @return
     */
    public static Context createContext(String context) {
        List<String> words = Arrays.asList(context.split(" "));
        Context contextObject = new Context(words);
        return contextObject;
    }

    public static List<Pair<Instance,Integer>> calculateMatchingWords(List<Instance> unorderedPicklist, Context context) {
        List<Pair<Instance,Integer>> unorderedPicklistMatchingWords = new ArrayList<>();
        for(Instance instance : unorderedPicklist) {
            int matchingWords = instance.matchingWords(context);
            Pair<Instance,Integer> cur = new Pair<>(instance, matchingWords);
            unorderedPicklistMatchingWords.add(cur);
        }
        return  unorderedPicklistMatchingWords;
    }

    /**
     * This method extracts the name of a game out of its description as lud code
     * @param gameDescription
     * @return
     */
    public static String getGameName(String gameDescription) {
        String gameLudeme = "(game";
        int gameLocation = gameDescription.lastIndexOf(gameLudeme);
        char[] gameDescrChars = gameDescription.toCharArray();
        String gameName = "";
        boolean start = false;
        // iterate over game description to find the name of the game
        loop:for(int j = gameLocation; j < gameDescription.length(); j++) {
            char cur = gameDescrChars[j];
            if(cur == '"' && !start) {
                start = true;
            }  else if(cur == '"' && start) {
                // found end of string
                break loop;
            } else if(start) {
                gameName += cur;
            }
        }
        return gameName;
    }
}
