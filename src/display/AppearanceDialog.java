package display;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
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
        init();
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

    }

    private void lightMode() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void darkMode() {
        // Dark LAF
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            UIManager.put("control", new Color(128, 128, 128));
            UIManager.put("info", new Color(128, 128, 128));
            UIManager.put("nimbusBase", new Color(18, 30, 49));
            UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
            UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
            UIManager.put("nimbusFocus", new Color(115, 164, 209));
            UIManager.put("nimbusGreen", new Color(176, 179, 50));
            UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
            UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
            UIManager.put("nimbusOrange", new Color(191, 98, 4));
            UIManager.put("nimbusRed", new Color(169, 46, 34));
            UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
            UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
            UIManager.put("text", new Color(230, 230, 230));
            SwingUtilities.updateComponentTreeUI(textEditor.getFrame());
        } catch (UnsupportedLookAndFeelException exc) {
            System.err.println("Nimbus: Unsupported Look and feel!");
        }
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //this triggers the dialog to dispose

            if(toggleMode.isSelected()) {
                darkMode();
            } else {
                lightMode();
            }
            dialog.dispose();
        }
    }
}
