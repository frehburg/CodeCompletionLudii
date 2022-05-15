package display;
import javax.swing.*;

/**
 * Source: https://www.geeksforgeeks.org/java-swing-jprogressbar/
 */
public class GeeksForGeeks extends JFrame {

    public static void main(String[] args)
    {
        int billion = 1000000000;
        ProgressBar pb = new ProgressBar("Count to 1 billion", "Count to 1 billion",100);
        for(int j = 0; j < 100; j++) {
            for(int i = 0; i < billion; i++) {

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pb.updateProgress(j);
        }
    }

}
