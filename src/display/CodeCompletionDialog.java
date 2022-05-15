package display;

import codecompletion.controller.Controller;
import codecompletion.domain.filehandling.DocHandler;
import codecompletion.domain.filehandling.ModelLibrary;
import codecompletion.domain.model.NGram;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CodeCompletionDialog {
    private JDialog dialog;
    private JLabel label;
    private JSpinner spinner;
    private SpinnerNumberModel spinnerNumberModel;
    private JButton button;
    private JPanel panel;
    private Listener listener;

    private int selectedN;
    private Controller controller;

    public CodeCompletionDialog(JFrame frame, Controller controller) {
        listener = new Listener();
        this.controller = controller;
        selectedN = 7;
        label = new JLabel("Select the value of the Code Completion model parameter N:");
        button = new JButton("Change N");
        button.addActionListener(listener);
        spinnerNumberModel = new SpinnerNumberModel(7,2,20,1);
        spinner = new JSpinner(spinnerNumberModel);
        spinner.addChangeListener(listener);
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);

        panel = new JPanel(new BorderLayout());

        panel.add(label,BorderLayout.NORTH);
        panel.add(spinner,BorderLayout.CENTER);
        panel.add(button,BorderLayout.SOUTH);

        this.dialog = new JDialog(frame,"Change Code Completion Model");
        this.dialog.setLayout(new BorderLayout());
        this.dialog.add(panel, BorderLayout.CENTER);
        this.dialog.setSize(new Dimension(420,340));
        this.dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.dialog.setLocationRelativeTo(null);
        Image img = new ImageIcon(DocHandler.getInstance().getLogoLocation()).getImage();
        this.dialog.setIconImage(img);
        this.dialog.setVisible(true);

    }

    private class Listener implements ActionListener, ChangeListener {
        public void actionPerformed(ActionEvent e) {
            dialog.dispose();
            ModelLibrary lib = ModelLibrary.getInstance();
            NGram model = lib.getModel(selectedN);
            controller.changeModel(model);
        }

        /**
         * Invoked when the target of the listener has changed its state.
         *
         * @param e a ChangeEvent object
         */
        @Override
        public void stateChanged(ChangeEvent e) {
            selectedN = spinnerNumberModel.getNumber().intValue();

            if(selectedN < 2 || selectedN > 20) {
                spinnerNumberModel.setValue(7);
            }
        }
    }
}
