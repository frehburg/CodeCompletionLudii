package validation.controller;

import codecompletion.domain.filehandling.LudiiGameDatabase;

import java.util.List;

public class ValidationController {

    private static final double TEST_PROBABILITY = 0.66;

    public ValidationController() {

    }

    public void validate(int minN, int maxN) {
        for(int N = minN; N <= maxN; N++) {
            validate(N);
        }
    }

    public void validate(int N) {
        String location = "res/out/"+System.currentTimeMillis()+"_"+N+".csv";
        Report r = new Report(N,location);

        // game database
        LudiiGameDatabase db = LudiiGameDatabase.getInstance();
        int amtGames = db.getAmountGames();

        // select games for the split
        CrossValidation crossValidation = new CrossValidation(amtGames,TEST_PROBABILITY);
        List<Integer> testIDs = crossValidation.getTestIDs();
        List<Integer> validationIDs = crossValidation.getValidationIDs();

        //model creation with the testIDs



        // TEST GAMES HERE
        r.addRecord(null);
        //---------------

        r.writeToCSV();
    }

    public void getReport() {

    }
}
