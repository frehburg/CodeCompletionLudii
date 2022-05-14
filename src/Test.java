import codecompletion.controller.Controller;
import codecompletion.domain.filehandling.DocHandler;
import codecompletion.domain.filehandling.ModelLibrary;
import codecompletion.domain.model.NGram;

import java.util.List;

public class Test {
    private static final String COMMA = ",";

    public static void main(String[] args) {
        Controller controller = new Controller(2);
        System.out.println(controller.getPicklist("(equipment { (board"));
        controller.close();
    }

    public static void testModelCreation() {
        ModelLibrary lib = ModelLibrary.getInstance();
        NGram model = lib.getModel(5);
        DocHandler.getInstance().close();
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
