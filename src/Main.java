import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        init();
        SwingController.initAll();
    }

    public static void init() throws IOException {
        BoardControl.init(12, 12);
        BoardControl.spawnFences();
        BoardControl.spawnMhos();
        BoardControl.spawnPlayer();
        BoardControl.print();
    }
}
