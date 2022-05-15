package codecompletion.domain.filehandling;

import utils.FileUtils;
import utils.StringUtils;

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
    public static final String LOGO_LOC = "logo_location";
    public static final String SEPARATOR = ":";
    public static final String MODEL_DOES_NOT_EXIST = "MODELDOESNOTEXIST";

    private static final boolean DEBUG = false;

    private String grammarLocation;
    private String gamesLocation;
    private String logoLocation;
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
            if(DEBUG)System.out.println(nextLine);
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
        if(StringUtils.equals(split[0],GRAMMAR_LOC)) {
            grammarLocation = split[1];
            if(DEBUG)System.out.println(grammarLocation);
        }
        if(StringUtils.equals(split[0],GAMES_LOC)) {
            gamesLocation = split[1];
            if(DEBUG)System.out.println(gamesLocation);
        }
        if(StringUtils.equals(split[0], LOGO_LOC)) {
            logoLocation = split[1];
            if(DEBUG)System.out.println(logoLocation);
        }
        if(split[0].startsWith(MODEL_LOC)) {
            int N = Integer.parseInt(split[0].charAt(MODEL_LOC.length())+"");
            modelLocations.put(N,split[1]);
            if(DEBUG)System.out.println(modelLocations.get(N));
        }
    }

    /**
     * This method writes the stored data about documents to the documents.txt
     * @throws IOException
     */
    public void writeDocumentsFile() throws IOException {
        FileWriter fw = FileUtils.writeFile(DOC_LOCATION);
        if(grammarLocation != null) {
            fw.write(GRAMMAR_LOC+SEPARATOR+grammarLocation+"\n");
        }
        if(gamesLocation != null) {
            fw.write(GAMES_LOC+SEPARATOR+gamesLocation+"\n");
        }
        if(logoLocation != null) {
            fw.write(LOGO_LOC+SEPARATOR+logoLocation+"\n");
        }
        for(Map.Entry<Integer, String> entry : modelLocations.entrySet()) {
            int N = entry.getKey();
            String modelLocation = entry.getValue();
            fw.write(MODEL_LOC+N+SEPARATOR+modelLocation+"\n");
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

    public String getLogoLocation() {
        return logoLocation;
    }

    public String getModelLocation(int N) {
        return modelLocations.getOrDefault(N,MODEL_DOES_NOT_EXIST);
    }

    /**
     * Write the documents file on closing
     */
    public void close() {
        try {
            writeDocumentsFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
