import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SwingController.initAll();
        BoardControl.init(12, 12);
        BoardControl.spawnFences();
        BoardControl.spawnMhos();
        BoardControl.spawnPlayer();
        BoardControl.print();

//        for(Point tp : BoardControl.getRandomPositionsMhos() ){
//            if(BoardControl.getRandomPositionsFences().contains(tp)){
//                System.out.println(tp);
//            }
//        }

    }
}
