package display;

import codecompletion.domain.filehandling.DocHandler;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppearanceDialog {
    private final TextEditor textEditor;
    private JDialog dialog;
    private JLabel label;
    private JSpinner spinnerFontSize;
    private SpinnerNumberModel spinnerNumberModel;
    private JPanel panel;
    private Listener listener;
    private JToggleButton toggleMode, toggleBold;

    public AppearanceDialog(TextEditor textEditor) {
        this.textEditor = textEditor;
        init();
    }


    private void init() {
        listener = new Listener();
        dialog = new JDialog(textEditor.getFrame(),"Change the appearance of the Editor");

        panel = new JPanel(new GridLayout(4,1));

        label = new JLabel("Appearance Settings");
        panel.add(label);

        toggleBold = new JToggleButton("Toggle Bold");
        toggleBold.setActionCommand("toggleBold");
        toggleBold.addActionListener(listener);
        panel.add(toggleBold);

        spinnerNumberModel = new SpinnerNumberModel(22,5,50,1);
        spinnerFontSize = new JSpinner(spinnerNumberModel);
        spinnerFontSize.addChangeListener(listener);
        panel.add(spinnerFontSize);

        toggleMode = new JToggleButton("Toggle Dark/Light Mode");
        toggleMode.setActionCommand("toggleMode");
        toggleMode.addActionListener(listener);
        panel.add(toggleMode);

        dialog.add(panel);
        dialog.setSize(new Dimension(420,340));
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        Image img = new ImageIcon(DocHandler.getInstance().getLogoLocation()).getImage();
        dialog.setIconImage(img);
        dialog.setVisible(true);
    }

    private void changeFontSize(int size) {
        Font current = textEditor.getTextArea().getFont();
        Font newFont = new Font(Font.MONOSPACED,current.getStyle(),size);
        textEditor.getTextArea().setFont(newFont);
    }

    private void changeFontStyle(int style) {
        Font current = textEditor.getTextArea().getFont();
        Font newFont = new Font(Font.MONOSPACED,style,current.getSize());
        textEditor.getTextArea().setFont(newFont);
    }

    private void lightMode() {
        try {
            UIManager.setLookAndFeel( new FlatLightLaf());
            SwingUtilities.updateComponentTreeUI(textEditor.getFrame());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
    }

    private void darkMode() {
        // Dark LAF
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf());
            SwingUtilities.updateComponentTreeUI(textEditor.getFrame());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
    }

    private class Listener implements ActionListener, ChangeListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals(toggleMode.getActionCommand())) {
                if(toggleMode.isSelected()) {
                    toggleMode.setText("Dark");
                    darkMode();
                } else {
                    toggleMode.setText("Light");
                    lightMode();
                }
            }else if(e.getActionCommand().equals(toggleBold.getActionCommand())) {
                if(toggleBold.isSelected()) {
                    toggleBold.setText("Plain");
                    changeFontStyle(Font.PLAIN);
                } else {
                    toggleBold.setText("Bold");
                    changeFontStyle(Font.BOLD);
                }
            }
        }

        /**
         * Invoked when the target of the listener has changed its state.
         *
         * @param e a ChangeEvent object
         */
        @Override
        public void stateChanged(ChangeEvent e) {
            //change font size
            changeFontSize((int)spinnerNumberModel.getValue());
        }
    }
}
