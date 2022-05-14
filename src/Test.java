import codecompletion.domain.filehandling.LudiiGameDatabase;
import codecompletion.domain.filehandling.ModelLibrary;
import codecompletion.domain.model.Context;
import codecompletion.domain.model.NGram;
import codecompletion.domain.model.Preprocessing;
import utils.Model2CSV;
import utils.NGramUtils;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Test {
    private static final String COMMA = ",";

    public static void main(String[] args) {
        testModelCreation();
    }

    public static void testModelCreation() {
        ModelLibrary lib = ModelLibrary.getInstance();
        lib.getModel(2);
    }

    public static void print(Object o) {
        System.out.println(o);
    }

    public static void print(int i) {
        System.out.println(i);
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public static <T> void print(List<T> list) {
        System.out.println(list);
    }
}
