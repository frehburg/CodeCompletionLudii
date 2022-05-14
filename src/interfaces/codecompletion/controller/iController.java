package interfaces.codecompletion.controller;

import codecompletion.Ludeme;
import codecompletion.domain.model.NGram;

import java.util.List;

/**
 * @author filreh
 */
public interface iController {

    /**
     * 1. Convert the context string into a Context object
     * 2. Get the matching Instance objects from the model
     * 3. Filter out the invalid Instances using the Grammar
     * 4. Use the BucketSort for the correct ordering
     * @param context
     * @return list of candidate predictions sort after matching words with context, multiplicity
     */
    List<Ludeme> getPicklist(String context);
    /**
     * 1. Convert the context string into a Context object
     * 2. Get the matching Instance objects from the model
     * 3. Filter out the invalid Instances using the Grammar
     * 4. Use the BucketSort for the correct ordering
     * 5. Optional: Shorten list to maxLength
     * @param context
     * @param maxLength
     * @return list of candidate predictions sort after matching words with context, multiplicity
     */
    List<Ludeme> getPicklist(String context, int maxLength);

    /**
     * This method switches out the current model, remember to update the N parameter
     * @param model
     */
    void changeModel(NGram model);

    /**
     * End all necessary connections and open links to storage. Discard the model
     */
    void close();

    /**
     * Get the value of N for the current model
     * @return
     */
    int getN();


}
