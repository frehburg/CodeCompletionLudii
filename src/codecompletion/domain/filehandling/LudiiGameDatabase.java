package codecompletion.domain.filehandling;

import interfaces.codecompletion.domain.filehandling.iLudiiGameDatabase;
import utils.FileUtils;

import java.io.File;
import java.util.*;

/**
 * @author filreh
 */
public class LudiiGameDatabase implements iLudiiGameDatabase {

    private static final boolean DEBUG = false;
    private final DocHandler docHandler;

    private ArrayList<String> locations;
    private Map<Integer, String> names, descriptions;

    public LudiiGameDatabase(DocHandler docHandler) {
        this.docHandler = docHandler;
    }

    private void init() {
        fetchGameLocations();
        names = new HashMap<>();
        descriptions = new HashMap<>();
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
        if(description.equals("null")) {
            String location = locations.get(id);
            //reads line by line
            Scanner sc = new Scanner(location);
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                description += nextLine;
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
    public String getDescription(String name) throws Exception {
        // TODO
        throw new Exception("This method has not been implemented yet");
    }
}
