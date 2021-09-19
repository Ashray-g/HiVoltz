import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class SwingController extends JFrame implements KeyListener {
    private static SwingController swingController = new SwingController();

    private static JPanel panel;

    private static boolean titlePainted = false;

    /**
     * Sets up the JFrame size and visibility
     */
    private static void initializeJFrame(){
        swingController.setSize(40 * 13, 40 * 13);
        swingController.setVisible(true);

        swingController.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        swingController.addKeyListener(swingController);

    }

    /**
     * Sets up the JPanel and adds it to the JFrame
     */
    private static void initializeJPanel(){
        panel = new MyPanel();
        swingController.add(panel);
        swingController.pack();

        swingController.setSize(40 * 13, 40 * 13);

        JLabel lblLName = new JLabel("Last Name:");
        JTextField tfLName = new JTextField(20);
        lblLName.setLabelFor(tfLName);

        swingController.add(lblLName);
        panel.add(lblLName);

    }

    private static Image fence;
    private static Image player;
    private static Image mho;
    private static Image title;
    private static Image gameOver;
    private static Image youWin;

    static {
        try {
            title = ImageIO.read(new File("src/assets/title.png"));
            gameOver = ImageIO.read(new File("src/assets/gameOver.png"));
            youWin = ImageIO.read(new File("src/assets/youWin2.png"));
        } catch (IOException e) {
        }
    }

    private static void initializeImages() throws IOException {
        fence = ImageIO.read(new File("src/assets/fence.png"));
        mho = ImageIO.read(new File("src/assets/mho.png"));
        player = ImageIO.read(new File("src/assets/player.png"));
    }

    /**
     * Calls initializeJPanel and initializeJFrame
     */
    public static void initAll() throws IOException{
        initializeJFrame();
        initializeJPanel();
        initializeImages();
    }

    public static SwingController getSwingController() {
        return swingController;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(BoardControl.getCurrentState() == BoardControl.State.IN_GAME && "qweasdzxcjftr".contains(e.getKeyChar() + "")){
            try {
                BoardControl.updatePlayerPosition(e.getKeyChar());
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        } else if(BoardControl.getCurrentState() == BoardControl.State.TITLE && e.getKeyChar() == 't'){
            BoardControl.start();
        } else if(BoardControl.getCurrentState() == BoardControl.State.GAME_OVER && e.getKeyChar() == 'r'){
            BoardControl.restart();
        } else if(BoardControl.getCurrentState() == BoardControl.State.YOU_WIN && e.getKeyChar() == 'r'){
            BoardControl.restart();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static class MyPanel extends JPanel implements ActionListener {

        Timer timer=new Timer(10, this);

        public void paint(Graphics g) {
            drawPlease(g);
            timer.start();
        }

        public void drawPlease(Graphics g){
            if(BoardControl.getCurrentState() == BoardControl.State.IN_GAME){
                game(g);
            }else if(BoardControl.getCurrentState() == BoardControl.State.TITLE){
                titleDraw(g);
            }else if(BoardControl.getCurrentState() == BoardControl.State.GAME_OVER){
                g.fillRect(0, 0, 800, 800);
                g.drawImage(gameOver, 0, 20, (int)(gameOver.getWidth(null)/3), (int)(gameOver.getHeight(null)/3),null);
            }else if(BoardControl.getCurrentState() == BoardControl.State.YOU_WIN){
                g.fillRect(0, 0, 800, 800);
                g.drawImage(youWin, 0, 50, (int)(youWin.getWidth(null)/2.6), (int)(youWin.getHeight(null)/2.6),null);
            }
        }

        public void titleDraw(Graphics g){
            g.fillRect(0, 0, 800, 800);
            g.drawImage(title, 0, 70, (int)(title.getWidth(null)/2.8), (int)(title.getHeight(null)/2.8),null);
        }

        public void game(Graphics g){
            g.fillRect(0, 0, 800, 800);
            for(int i = 0;i< 12;i++){
                for(int j = 0;j<12;j++){
                    g.fillRect(i * 40, j * 40, 40, 40);
                    if(BoardControl.getBoard()[j][i] == 1){
                        g.drawImage(fence, i * 40 + 5, j * 40 + 5, 25, 25, null);
                    }else if(BoardControl.getBoard()[j][i] == 2){
                        g.drawImage(mho, i * 40 + 5, j * 40 + 5, 25, 25, null);
                    }
                }
            }
            g.drawImage(player, BoardControl.getRandomPositionPlayer().x * 40 + 5, BoardControl.getRandomPositionPlayer().y * 40 + 5,  25,25, null);


        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==timer){
                repaint();// this will call at every 0.1 second
            }
        }
    }

}


