import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        init();

    }

    public static void init() throws IOException {
        BoardControl.init(30, 30);
        BoardControl.spawnFences();
        BoardControl.spawnMhos();
        BoardControl.spawnPlayer();
        BoardControl.print();
        SwingController.initAll();
    }
}
