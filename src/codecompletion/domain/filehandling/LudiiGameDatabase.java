package codecompletion.domain.filehandling;

import interfaces.codecompletion.domain.filehandling.iLudiiGameDatabase;
import utils.FileUtils;
import utils.StringUtils;

import java.io.File;
import java.util.*;

/**
 * @author filreh
 */
public class LudiiGameDatabase implements iLudiiGameDatabase {

    private static final boolean DEBUG = false;
    private final DocHandler docHandler;

    private ArrayList<String> locations;
    private Map<Integer, String> descriptions;
    private Map<String, Integer> names;
    //Singleton
    private static LudiiGameDatabase db;

    public static LudiiGameDatabase getInstance() {
        // create object if it's not already created
        if(db == null) {
            db = new LudiiGameDatabase();
        }

        // returns the singleton object
        return db;
    }

    private LudiiGameDatabase() {
        this.docHandler = DocHandler.getInstance();
        init();
    }

    private void init() {
        fetchGameLocations();
        names = new HashMap<>();
        descriptions = new HashMap<>();
        fetchGameNames();
    }

    private void fetchGameLocations() {
        File folder = new File(docHandler.getGamesLocation());
        ArrayList<File> files = FileUtils.listFilesForFolder(folder);
        locations = new ArrayList<>();
        for(File f : files) {
            String location = f.getAbsolutePath();
            locations.add(location);
            if(DEBUG)System.out.println(location);
        }
    }

    private void fetchGameNames() {
        for(int i = 0; i < getAmountGames(); i++) {
            String gameDescription = getDescription(i);
            String gameLudeme = "(game";
            int gameLocation = gameDescription.lastIndexOf(gameLudeme);
            char[] gameDescrChars = gameDescription.toCharArray();
            String gameName = "";
            boolean start = false;
            // iterate over game description to find the name of the game
            loop:for(int j = 0; j < gameDescription.length(); j++) {
                char cur = gameDescrChars[j];
                if(cur == '"' && !start) {
                    start = true;
                }  else if(cur == '"' && start) {
                    // found end of string
                    break loop;
                } else if(start) {
                    gameName += cur;
                }
            }
            System.out.println(gameName);
            names.put(gameName, i);
        }
    }

    /**
     * Returns a list of all the locations of game descriptions in the database.
     *
     * @return
     */
    @Override
    public List<String> getLocations() {
        return locations;
    }

    /**
     * Returns the amount of games in the database
     *
     * @return
     */
    @Override
    public int getAmountGames() {
        return locations.size();
    }

    /**
     * Returns the description of the game with the id in the locations list
     *
     * @param id
     * @return
     */
    @Override
    public String getDescription(int id) {
        //fetches description if it was already read in
        String description = descriptions.getOrDefault(id,"null");

        // else, reads it in
        if(StringUtils.equals(description,"null")) {
            description = "";
            String location = locations.get(id);
            //reads line by line
            Scanner sc = FileUtils.readFile(location);
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                description += "\n"+nextLine;
            }
            sc.close();

        }
        return description;
    }

    /**
     * Returns the description of the game with the name specified. Create a map that links names to
     * ids and use the other method.
     *
     * @param name
     * @return
     */
    @Override
    public String getDescription(String name) {
        int id = names.get(name);
        return getDescription(id);
    }
}
