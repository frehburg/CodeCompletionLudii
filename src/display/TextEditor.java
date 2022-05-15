package display;

import codecompletion.controller.Controller;
import codecompletion.domain.filehandling.DocHandler;
import codecompletion.domain.filehandling.GameFileHandler;
import utils.NGramUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class TextEditor {
    private static int N;
    private final String gameDescription;
    private JFrame frame;
    private  JButton button;
    private JTextArea textArea;
    private Controller controller;
    private Listener listener;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JMenuBar menuBar;
    private JFileChooser fileChooser;

    private String gameName;
    private String fileLocation;

    //
    private static FileFilter ludFilter = new FileFilter() {
        public String getDescription() {
            return "Ludii Game Files (*.lud)";
        }

        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            } else {
                return f.getName().toLowerCase().endsWith(".lud");
            }
        }
    };

    public TextEditor(String gameDescription, int N) {
        gameName = NGramUtils.getGameName(gameDescription);
        this.gameDescription = gameDescription;
        this.N = N;
        init();
    }

    public TextEditor(int N) {
        gameName = "New Game";
        this.gameDescription = "";
        this.N = N;
        init();
    }

    private void init() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException e) {
            e.printStackTrace();
        }
        controller = new Controller(N);
        fileChooser = new JFileChooser("res/");
        frame = new JFrame("Editing: "+gameName);
        listener = new Listener();
        button = new JButton("Get Picklist");
        button.addActionListener(listener);
        textArea = new JTextArea(30,30);
        textArea.setText(gameDescription);
        scrollPane = new JScrollPane(textArea);
        panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        panel.add(button,BorderLayout.SOUTH);

        //menu
        menuBar = new JMenuBar();
        menuBar.setMinimumSize(new Dimension(1280,30));
        JMenu file = new JMenu("File");
        JMenuItem openNewGame = new JMenuItem("Open New Game");
        openNewGame.addActionListener(listener);
        file.add(openNewGame);
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(listener);
        file.add(save);
        JMenuItem saveAs = new JMenuItem("Save as");
        saveAs.addActionListener(listener);
        file.add(saveAs);
        JMenuItem load = new JMenuItem("Load Game");
        load.addActionListener(listener);
        file.add(load);

        JMenu options = new JMenu("Options");

        menuBar.add(file);
        menuBar.add(options);


        frame.setJMenuBar(menuBar);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        Image img = new ImageIcon(DocHandler.getInstance().getLogoLocation()).getImage();
        frame.setIconImage(img);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            /**
             * Will be executed when the frame is closed
             * @param e
             */
            public void windowClosing(WindowEvent e) {
                controller.close();
            }
        });
        frame.setSize(new Dimension(1280,720));
        frame.setVisible(true);

    }

    private class Listener implements ActionListener {
        // if the button is pressed
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            if (s.equals(button.getActionCommand())) {
                // set the text of the label to the text of the field
                //TODO make this method show the picklist

            } else if (s.equals("Open New Game")) {
                // open dialog to select which game
            } else if (s.equals("Save")) {
                // if saved before, save there else perform save as
                if(fileLocation == null) {
                    saveAs();
                } else {
                    save();
                }
            } else if (s.equals("Save as")) {
                // open dialog to select storage location
                saveAs();
            } else if (s.equals("Load Game")) {
                // open dialog to select storage location
                load();
            }
        }
        private void load() {
            fileChooser = new JFileChooser("res/");
            fileChooser.setDialogTitle("Choose a game to load in");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(ludFilter);
            fileChooser.setAcceptAllFileFilterUsed(false);
            if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File directory = fileChooser.getSelectedFile();
                fileLocation = directory.getPath();
            }
            System.out.println(fileLocation);
            String gameDescription = GameFileHandler.readGame(fileLocation);
            textArea.setText(gameDescription.substring(1,gameDescription.length()));
        }

        private void save() {
            String gameDescription = textArea.getText();
            gameName = NGramUtils.getGameName(gameDescription);
            gameName = gameName == null ? "New Game" : gameName;
            if(fileLocation.endsWith(".lud")) {
                GameFileHandler.writeGame(gameDescription,fileLocation);
            } else {
                GameFileHandler.writeGame(gameDescription,fileLocation+"\\"+gameName+".lud");
            }
        }

        private void saveAs() {
            fileChooser = new JFileChooser("res/");
            fileChooser.setDialogTitle("Choose a directory to save your game.");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File directory = fileChooser.getSelectedFile();
                fileLocation = directory.getPath();
            }
            save();
        }
    }
}
