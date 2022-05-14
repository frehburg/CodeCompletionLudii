import codecompletion.domain.model.Context;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<String>(
                Arrays.asList("Buenos Aires", "Córdoba", "La Plata"));
        int length = words.size();

        String prediction = words.get(length - 1);
        String key = words.get(length - 2);

        System.out.println("La Plata "+prediction + " Córdoba " + key);
    }

}
