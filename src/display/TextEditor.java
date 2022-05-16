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

    // Singleton
    private static TextEditor textEditor;

    public static TextEditor getInstance() {
        return textEditor;
    }

    public static void createInstance(int N) {
        if(textEditor == null) {
            textEditor = new TextEditor(N);
        }
    }

    private TextEditor(int N) {
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
        textArea.setFont(new Font(Font.MONOSPACED,Font.BOLD, 22));
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
        JMenuItem openGameFromFile = new JMenuItem("Open Game from File");
        openGameFromFile.addActionListener(listener);
        file.add(openGameFromFile);
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
        JMenuItem appearance = new JMenuItem("Change appearance");
        appearance.addActionListener(listener);
        options.add(appearance);
        JMenuItem codeCompletion = new JMenuItem("Change Code Completion Model");
        codeCompletion.addActionListener(listener);
        options.add(codeCompletion);

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
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            if (s.equals(button.getActionCommand())) {
                // set the text of the label to the text of the field
                //TODO make this method show the picklist

            } else if (s.equals("Open New Game")) {
                askToSave(false);
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
                askToSave(true);
                System.out.println("Recevied button press");
            } else if (s.equals("Change appearance")) {
                AppearanceDialog appearanceDialog = new AppearanceDialog(TextEditor.this);
            } else if (s.equals("Change Code Completion Model")) {
                CodeCompletionDialog ccDialog = new CodeCompletionDialog(frame,controller);
            } else if (s.equals("Open Game from File")) {
                OpenGameFromFileDialog gameFromFileDialog = new OpenGameFromFileDialog(TextEditor.this);
            }
        }
        private void load() {
            System.out.println("In method");
            fileChooser = new JFileChooser("res/");
            fileChooser.setDialogTitle("Choose a game to load in");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(ludFilter);
            fileChooser.setAcceptAllFileFilterUsed(false);
            if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File directory = fileChooser.getSelectedFile();
                fileLocation = directory.getPath();
            }
            if(fileLocation == null) {
                return;
            }
            String gameDescription = GameFileHandler.readGame(fileLocation);
            textArea.setText(gameDescription.substring(1,gameDescription.length()));
        }

        private void save() {
            if(fileLocation == null) {
                saveAs();
            } else {
                String gameDescription = textArea.getText();
                gameName = NGramUtils.getGameName(gameDescription);
                gameName = gameName == null ? "New Game" : gameName;
                if(fileLocation.endsWith(".lud")) {
                    GameFileHandler.writeGame(gameDescription,fileLocation);
                } else {
                    GameFileHandler.writeGame(gameDescription,fileLocation+"\\"+gameName+".lud");
                }
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

        public void askToSave(boolean load) {
            System.out.println("In ask to save");
            if(textArea.getText().equals("")) {
                if(load) {
                    load();
                }
                return;
            }
            Dialog d = new Dialog(frame, "Do you want to save your game?");
            JPanel dPanel = new JPanel(new GridLayout(1,2));
            JButton yes = new JButton("Yes");
            yes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    save();
                    d.dispose();
                    textArea.setText("");
                    if(load) {
                        load();
                    }
                }
            });
            dPanel.add(yes);
            JButton no = new JButton("No");
            no.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    d.dispose();
                    textArea.setText("");
                    if(load) {
                        load();
                    }
                }
            });
            dPanel.add(no);
            d.add(dPanel);
            d.setSize(new Dimension(500,150));
            d.setResizable(false);
            d.setLocationRelativeTo(null);
            d.setVisible(true);
        }
    }

    public void openGameFromFile(String location) {
        String gameDescription = GameFileHandler.readGame(location);
        textArea.setText(gameDescription.substring(1,gameDescription.length()));
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
