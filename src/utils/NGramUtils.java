package utils;

import codecompletion.domain.model.Context;
import codecompletion.domain.model.Instance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author filreh
 */
public class NGramUtils {
    /**
     * This method returns a list of all substrings of length N
     *
     * @param N
     * @return
     */
    public static List<String> allSubstrings(String gameDescription, int N) {
        String[] words = gameDescription.split(" ");
        List<String> substringsLengthN = new ArrayList<>();
        for(int i = 0; i <= words.length; i++) {
            int substringEnd = i + N;

            if(substringEnd <= words.length) {
                String curSubstring = "";
                for(int j = i; j < substringEnd; j++) {
                    curSubstring += words[j];
                    if(j != substringEnd - 1) {
                        curSubstring += " ";
                    }
                }
                substringsLengthN.add(curSubstring);
            }
        }
        return substringsLengthN;
    }

    /**
     * This method takes a substring and turns it into an N Gram instance
     *
     * @param substring
     * @return
     */
    public static Instance createInstance(String substring) {
        List<String> words = Arrays.asList(substring.split(" "));
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
}
