package display;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.multi.MultiLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppearanceDialog {
    private final TextEditor textEditor;
    private JDialog dialog;
    private JLabel label;
    private JSpinner spinner;
    private SpinnerNumberModel spinnerNumberModel;
    private JButton button;
    private JPanel panel;
    private Listener listener;
    JToggleButton toggleMode;

    public AppearanceDialog(TextEditor textEditor) {
        this.textEditor = textEditor;
        darkMode();
        //init();
    }


    private void init() {
        listener = new Listener();
        dialog = new JDialog(textEditor.getFrame(),"Change the appearance of the Editor");

        panel = new JPanel(new GridLayout(4,1));

        toggleMode = new JToggleButton("Toggle Dark/Light Mode");
        toggleMode.setActionCommand("toggleMode");
        toggleMode.addActionListener(listener);
        panel.add(toggleMode);

        spinnerNumberModel = new SpinnerNumberModel(22,5,50,1);
        spinner = new JSpinner(spinnerNumberModel);
        spinner.addChangeListener(listener);
        panel.add(spinner);

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
            //this triggers the dialog to dispose

            if(toggleMode.isSelected()) {
                darkMode();
            } else {
                lightMode();
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
