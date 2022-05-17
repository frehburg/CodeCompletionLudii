import codecompletion.Ludeme;
import codecompletion.controller.Controller;
import codecompletion.domain.filehandling.DocHandler;
import codecompletion.domain.filehandling.LudiiGameDatabase;
import codecompletion.domain.filehandling.ModelLibrary;
import codecompletion.domain.model.Instance;
import codecompletion.domain.model.NGram;
import codecompletion.domain.model.Preprocessing;
import display.ProgressBar;
import utils.BucketSort;
import utils.GZIPController;
import utils.Instance2Ludeme;
import utils.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Test {
    private static final String COMMA = ",";
    public static final String TRAINING = "res/crossvalidation/trainingIDs.txt";
    public static final String VALIDATION = "res/crossvalidation/validationIDs.txt";

    public static void main(String[] args) {
        Controller controller = new Controller(2);
        List<Ludeme> picklist = controller.getPicklist("(game \"Tic-Tac-Toe\"");
        picklist.forEach(l -> print(l));
        controller.close();
        //GZIPController.decompress("res/models/ngram_model_2.gz","res/models/ngram_model_2.csv");
    }

    public static void getMultipleModels(int maxN) {
        for(int N = 2; N <= maxN; N++) {
            ModelLibrary.getInstance().getModel(N);
            DocHandler.getInstance().writeDocumentsFile();
        }
    }

    public static void testProgressBar() {
        int billion = 1000000000;
        ProgressBar pb = new ProgressBar("Count to 1 billion", "Count to 1 billion",100);
        for(int j = 0; j < 100; j++) {
            for(int i = 0; i < billion; i++) {

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pb.updateProgress(j);
        }
    }

    public void getCrossvalidationIDS() {
        //DONT EXECUTE, will OVERWRITE
//        LudiiGameDatabase db = LudiiGameDatabase.getInstance();
//        List<Integer> gameIDs = new ArrayList<>();
//        int amtGames = db.getAmountGames();
//        for(int i = 0; i < amtGames; i++) {
//            gameIDs.add(i);
//        }
//
//        int training = (int) (amtGames*0.66);
//        List<Integer> trainingIDs = new ArrayList<>();
//        Random r = new Random(System.nanoTime());
//        for(int i = 0; i < training; i++) {
//            int randomID = r.nextInt(amtGames);
//            if(!trainingIDs.contains(randomID)) {
//                trainingIDs.add(randomID);
//            } else {
//                i--;
//            }
//        }
//        trainingIDs.sort(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
//            }
//        });
//        FileWriter fw = FileUtils.writeFile(TRAINING);
//        for(int id : trainingIDs) {
//            try {
//                fw.write(id+"\n");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int validation = amtGames - training;
//        List<Integer> validationIDs = new ArrayList<>();
//        for(int i = 0; i < amtGames; i++) {
//            if(!trainingIDs.contains(i)) {
//                validationIDs.add(i);
//            }
//        }
//        fw = FileUtils.writeFile(VALIDATION);
//        for(int id : validationIDs) {
//            try {
//                fw.write(id+"\n");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
