package display;

import javax.swing.*;
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
        changeFontSize(30);
        changeFontStyle(Font.PLAIN);
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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Color control = UIManager.getColor("control");
            Color info = UIManager.getColor("info");
            Color text = UIManager.getColor("text");
            UIManager.put("control", control);
            UIManager.put("info", info);
            UIManager.put("text", text);
            SwingUtilities.updateComponentTreeUI(textEditor.getFrame());
        } catch (ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void darkMode() {
        // Dark LAF
        try {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Color control = new Color(128, 128, 128);
            Color info = new Color(128, 128, 128);
            Color text = new Color(230, 230, 230);
            UIManager.getLookAndFeelDefaults().put("text",text);
            UIManager.getLookAndFeelDefaults().put("info",info);
            UIManager.getLookAndFeelDefaults().put("control",control);
            SwingUtilities.updateComponentTreeUI(textEditor.getFrame());
//            UIManager.put("control", new Color(128, 128, 128));
//            UIManager.put("info", new Color(128, 128, 128));
//            UIManager.put("nimbusBase", new Color(18, 30, 49));
//            UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
//            UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
//            UIManager.put("nimbusFocus", new Color(115, 164, 209));
//            UIManager.put("nimbusGreen", new Color(176, 179, 50));
//            UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
//            UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
//            UIManager.put("nimbusOrange", new Color(191, 98, 4));
//            UIManager.put("nimbusRed", new Color(169, 46, 34));
//            UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
//            UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
//            UIManager.put("text", new Color(230, 230, 230));
//            SwingUtilities.updateComponentTreeUI(textEditor.getFrame());
        } catch (ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException e) {
            e.printStackTrace();
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
