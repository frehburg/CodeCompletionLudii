package codecompletion.domain.filehandling;

import utils.FileUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Class is a singleton
 */
public class DocHandler {
    public static final String DOC_LOCATION = "res/documents.txt";
    public static final String GRAMMAR_LOC = "grammar_location";
    public static final String GAMES_LOC = "games_location";
    public static final String MODEL_LOC = "location_model_";
    public static final String SEPARATOR = ":";
    public static final String MODEL_DOES_NOT_EXIST = "MODELDOESNOTEXIST";

    private String grammarLocation;
    private String gamesLocation;
    private Map<Integer,String> modelLocations;

    //singleton
    private static DocHandler docHandler;

    public static DocHandler getInstance() {
        // create object if it's not already created
        if(docHandler == null) {
            docHandler = new DocHandler();
        }

        // returns the singleton object
        return docHandler;
    }

    /**
     * The constructor reads in alla available information from the file on startup
     */
    private DocHandler() {
        modelLocations = new HashMap<>();
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
            int N = Integer.parseInt(split[0].charAt(MODEL_LOC.length())+"");
            modelLocations.put(N,split[1]);
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
        for(Map.Entry<Integer, String> entry : modelLocations.entrySet()) {
            int N = entry.getKey();
            String modelLocation = entry.getValue();
            fw.write(MODEL_LOC+N+SEPARATOR+modelLocation);
        }
        fw.close();
    }

    public void addModelLocation(int N, String location) {
        modelLocations.put(N,location);
    }

    public String getGrammarLocation() {
        return grammarLocation;
    }

    public String getGamesLocation() {
        return gamesLocation;
    }

    public String getModelLocation(int N) {
        return modelLocations.getOrDefault(N,MODEL_DOES_NOT_EXIST);
    }
}
