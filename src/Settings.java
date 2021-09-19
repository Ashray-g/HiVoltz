import javax.swing.*;
import java.io.IOException;

public class Settings extends JFrame {
    private static Settings settings = new Settings();

    private static JPanel panel;
    private static JTextField side;

    public static Settings getSettings() {
        return settings;
    }

    public static JPanel getPanel() {
        return panel;
    }

    public static JTextField getSide() {
        return side;
    }

    /**
     * Sets up the JFrame size and visibility
     */
    private static void initializeJFrame(){
        settings.setSize(100, 100);
        settings.setVisible(true);

        settings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Sets up the JPanel and adds it to the JFrame
     */
    private static void initializeJPanel(){
        panel = new JPanel();
        settings.pack();

        settings.setSize(100, 100);
        settings.add(panel);

        side = new JTextField("12", 1);

        panel.add(side);

        settings.setVisible(true);

    }

    public static void initAll() throws IOException {
        initializeJFrame();
        initializeJPanel();
    }
}
