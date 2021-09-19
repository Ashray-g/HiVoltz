import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Settings.initAll();
        init();
        SwingController.initAll();
        SwingController.getSwingController().setSize(Integer.parseInt(Settings.getSide().getText())*41, Integer.parseInt(Settings.getSide().getText()) * 41);
        SwingController.getSwingController().setVisible(true);
    }

    public static void init() throws IOException {
        BoardControl.init(Integer.parseInt(Settings.getSide().getText()), Integer.parseInt(Settings.getSide().getText()));
        BoardControl.spawnFences();
        BoardControl.spawnMhos();
        BoardControl.spawnPlayer();
        BoardControl.print();


    }
}
