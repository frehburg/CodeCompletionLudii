package interfaces.utils;

import codecompletion.domain.model.Context;
import codecompletion.domain.model.Instance;

import java.util.List;

/**
 * @author filreh
 */
public interface iNGramUtils {
    /**
     * This method returns a list of all substrings of length N
     * @param N
     * @return
     */
    List<String> allSubstrings(int N);

    /**
     * This method takes a substring and turns it into an N Gram instance
     * @param substring
     * @return
     */
    Instance createInstance(String substring);

    /**
     * This method takes the context string and turns it into a Context object
     * @param context
     * @return
     */
    Context createContext(String context);
}
