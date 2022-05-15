package display;

import javax.swing.*;
import java.awt.*;

public class ProgressBar {
    private final String operationDescription;
    private JFrame frame;
    private JProgressBar progressBar;
    private final int operationsMax;
    private final String operationName;
    private double progress;
    private JPanel panel;
    private JLabel label;


    public ProgressBar(String operationName, String operationDescription, int operationsMax) {
        this.operationsMax = operationsMax;
        this.operationName = operationName;
        this.operationDescription = operationDescription;
        init();
    }

    public ProgressBar(String operationName, int operationsMax) {
        this.operationsMax = operationsMax;
        this.operationName = operationName;
        this.operationDescription = "";
        init();
    }

    private void init(){
        this.progress = 0.0;
        this.frame = new JFrame(operationName);
        this.panel = new JPanel();
        this.progressBar = new JProgressBar((int) progress);
        this.label = new JLabel(operationDescription);
        progressBar.setPreferredSize(new Dimension(650,60));
        progressBar.setStringPainted(true);
        label.setFont(new Font("Dialog",Font.BOLD, 20));
        label.setLabelFor(progressBar);
        panel.add(label);
        panel.add(progressBar);
        frame.add(panel);
        // set the size of the frame
        frame.setSize(700, 190);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
    }

    /**
     * Sets the progress of the bar to the specified percent level
     * @param percent
     */
    public void updateProgress(double percent) {
        this.progress = percent*100;
        int progressInt = (int) progress;
        progressBar.setValue(progressInt);

        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    /**
     * Calculates the progress of the bar based on maxOperations and operations finished
     * @param operationsFinished
     */
    public void updateProgress(int operationsFinished) {
        double percent = (((double) operationsFinished) / ((double) operationsMax));
        updateProgress(percent);
    }

    public void close() {
        progressBar.setString("Finished");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        frame.dispose();
    }
}
