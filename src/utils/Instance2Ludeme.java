package utils;

import codecompletion.Ludeme;
import codecompletion.domain.model.Instance;

import java.util.ArrayList;
import java.util.List;

public class Instance2Ludeme {
    /**
     * This method converts a Ngram instance into a ludeme object
     * @param instance
     * @return
     */
    public static Ludeme instance2ludeme(Instance instance) {
        //TODO
        return new Ludeme();
    }

    public static List<Ludeme> foreachInstance2ludeme(List<Instance> instances) {
        List<Ludeme> ludemes = new ArrayList<>();
        for(Instance instance : instances) {
            ludemes.add(instance2ludeme(instance));
        }
        return ludemes;
    }
}
