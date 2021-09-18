import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingController extends JFrame implements KeyListener {
    private static SwingController swingController = new SwingController();

    private static JPanel panel;

    /**
     * Sets up the JFrame size and visibility
     */
    private static void initializeJFrame(){
        swingController.setSize(40 * 13, 40 * 13);
        swingController.setVisible(true);

        swingController.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Sets up the JPanel and adds it to the JFrame
     */
    private static void initializeJPanel(){
        panel = new MyPanel();
        swingController.add(panel);
        swingController.pack();

        swingController.setSize(40 * 13, 40 * 13);

    }

    /**
     * Calls initializeJPanel and initializeJFrame
     */
    public static void initAll(){
        initializeJFrame();
        initializeJPanel();
    }

    public static SwingController getSwingController() {
        return swingController;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        // Board.keyInputted(e.getKeyChar());
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static class MyPanel extends JPanel implements ActionListener {

        Timer timer=new Timer(100, this);
        public void paint(Graphics g) {

            for(int i = 0;i< 12;i++){
                for(int j = 0;j<12;j++){
                    g.drawRect(i * 40, j * 40, 40, 40);
                }
            }
            timer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==timer){
                repaint();// this will call at every 0.1 second
            }
        }
    }

}


