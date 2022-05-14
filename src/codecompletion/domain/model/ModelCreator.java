package codecompletion.domain.model;

import codecompletion.domain.filehandling.DocHandler;
import codecompletion.domain.filehandling.LudiiGameDatabase;
import codecompletion.domain.filehandling.ModelFilehandler;
import utils.NGramUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author filreh
 */
public class ModelCreator {
    /**
     * This method should only be called as part of the validation process or by the ModelLibrary
     * This method creates a new model and writes a model to a .gz file.
     * The location is according to the internal storing mechanism that is based on the N parameter.
     * This method only adds the game descriptions with the specified ids to the model
     *
     * By listing only a limited number of games, this method can construct a model for validation
     * @param N
     * @param gameIDs List of game descriptions to be included in the model
     * @param validation If true, the model will not be written to a file
     * @return
     */
    public static NGram createModel(int N, List<Integer> gameIDs, boolean validation) {
        NGram model = new NGram(N);

        LudiiGameDatabase db = LudiiGameDatabase.getInstance();

        // this method only adds the game descriptions with the specified ids to the model
        // not all games! therefore not db.getAmountGames()
        int amountGames = gameIDs.size();

        for(int gameID : gameIDs) {
            String curGameDescription = db.getDescription(gameID);

            //apply preprocessing
            String cleanGameDescription = Preprocessing.preprocess(curGameDescription);

            //add all instances of length in {2,...,N}
            for(int i = 2; i <= N; i++) {
                List<String> substrings = NGramUtils.allSubstrings(cleanGameDescription, i);
                for(String substring : substrings) {
                    Instance curInstance = NGramUtils.createInstance(substring);
                    model.addInstanceToModel(curInstance);
                }
            }
        }
        //if the model is only created for validation purposes, it is not written to a file
        if(!validation) {
            ModelFilehandler.writeModel(model);
        }
        return model;
    }

    /**
     * This method should only be called as part of the validation process or by the ModelLibrary
     * This method creates a new model and writes a model to a .gz file.
     * The location is according to the internal storing mechanism that is based on the N parameter.
     * This method adds all game descriptions to the model and is therefore not for validation purposes.
     * @param N
     * @return
     */
    public static NGram createModel(int N) {
        List<Integer> gameIDs = new ArrayList<>();
        LudiiGameDatabase db = LudiiGameDatabase.getInstance();

        int amountGames = db.getAmountGames();

        for (int i = 0; i < amountGames; i++) {
            gameIDs.add(i);
        }

        return createModel(N, gameIDs, false);
    }
}
