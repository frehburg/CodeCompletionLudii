package codecompletion.domain.filehandling;

import utils.FileUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DocHandler {
    private static final String DOC_LOCATION = "res/documents.txt";
    private static final String GRAMMAR_LOC = "grammar_location";
    private static final String GAMES_LOC = "games_location";
    private static final String MODEL_LOC = "location_model_";
    private static final String SEPARATOR = ":";

    private String grammarLocation;
    private String gamesLocation;
    private List<String> modelLocations;

    /**
     * The constructor reads in alla available information from the file on startup
     */
    public DocHandler() {
        modelLocations = new ArrayList<>();
        Scanner sc = FileUtils.readFile(DOC_LOCATION);
        while (sc.hasNext()) {
            String nextLine = sc.nextLine();
            parseDocumentsLine(nextLine);
        }
        sc.close();
    }

    /**
     * This method parses the different lines of the documents.txt:
     * -grammar location
     * -games location
     * -model location
     * @param line
     * @return
     */
    private void parseDocumentsLine(String line) {
        String[] split = line.split(SEPARATOR);
        if(split[0].equals(GRAMMAR_LOC)) {
            grammarLocation = split[1];
        }
        if(split[0].equals(GAMES_LOC)) {
            gamesLocation = split[1];
        }
        if(split[0].startsWith(MODEL_LOC)) {
            grammarLocation = split[1];
        }
    }

    /**
     * This method writes the stored data about documents to the documents.txt
     * @throws IOException
     */
    public void writeDocumentsFile() throws IOException {
        FileWriter fw = FileUtils.writeFile(DOC_LOCATION);
        if(grammarLocation != null) {
            fw.write(GRAMMAR_LOC+SEPARATOR+grammarLocation);
        }
        if(gamesLocation != null) {
            fw.write(GAMES_LOC+SEPARATOR+gamesLocation);
        }
        for(int i = 0; i < modelLocations.size(); i++) {
            int N = i + 2;
            //TODO: make N detection from filename better
            fw.write(MODEL_LOC+N+SEPARATOR+modelLocations.get(i));
        }
        fw.close();
    }

    public String getGrammarLocation() {
        return grammarLocation;
    }

    public String getGamesLocation() {
        return gamesLocation;
    }

    public List<String> getModelLocations() {
        return modelLocations;
    }
}
