package codecompletion.controller;

import codecompletion.Ludeme;
import codecompletion.domain.filehandling.ModelLibrary;
import codecompletion.domain.model.Context;
import codecompletion.domain.model.Grammar;
import codecompletion.domain.model.Instance;
import codecompletion.domain.model.NGram;
import interfaces.codecompletion.controller.iController;
import utils.BucketSort;
import utils.Instance2Ludeme;
import utils.NGramUtils;

import java.util.List;

/**
 * @author filreh
 */
public class Controller implements iController {

    private int N;
    private ModelLibrary ml;
    private Grammar grammar;
    private NGram model;

    public Controller(int N) {
        this.N = N;
        initModel();
    }

    /**
     * This method should
     * - request a model with the specified N from the ModelLibrary
     * - load in the grammar
     */
    @Override
    public void initModel() {
        ml = new ModelLibrary();
        model = ml.getModel(N);
        grammar = new Grammar();
    }

    /**
     * 1. Convert the context string into a Context object
     * 2. Get the matching Instance objects from the model
     * 3. Filter out the invalid Instances using the Grammar
     * 4. Use the BucketSort for the correct ordering
     *
     * @param contextString
     * @return list of candidate predictions sort after matching words with context, multiplicity
     */
    @Override
    public List<Ludeme> getPicklist(String contextString) {
        // 1.
        Context context = NGramUtils.createContext(contextString);
        // 2. context sensitivity
        List<Instance> match = model.getMatch(context.getKey());
        // 3. type-meatching
        List<Instance> unorderedPicklist = grammar.filterOutInvalid(context,match);
        // 4. Sorting after matching words and multiplicity
        List<Instance> picklist = BucketSort.sort(unorderedPicklist);
        // convert to ludemes
        List<Ludeme> picklistLudemes = Instance2Ludeme.foreachInstance2ludeme(picklist);
        return picklistLudemes;
    }

    /**
     * 1. Convert the context string into a Context object
     * 2. Get the matching Instance objects from the model
     * 3. Filter out the invalid Instances using the Grammar
     * 4. Use the BucketSort for the correct ordering
     * 5. Optional: Shorten list to maxLength
     *
     * @param context
     * @param maxLength
     * @return list of candidate predictions sort after matching words with context, multiplicity
     */
    @Override
    public List<Ludeme> getPicklist(String context, int maxLength) {
        List<Ludeme> picklist = getPicklist(context);
        if(picklist.size() >= maxLength) {
            picklist = picklist.subList(0,maxLength);
        }
        return picklist;
    }

    /**
     * This method switches out the current model, remember to update the N parameter
     *
     * @param model
     */
    @Override
    public void changeModel(NGram model) {
        this.model = model;
        this.N = model.getN();
    }

    /**
     * End all necessary connections and open links to storage. Discard the model
     */
    @Override
    public void close() {
        //TODO
    }

    /**
     * Get the value of N for the current model
     *
     * @return
     */
    @Override
    public int getN() {
        //TODO
        return N;
    }
}
