import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SwingController.initAll();
        init();

//        for(Point tp : BoardControl.getRandomPositionsMhos() ){
//            if(BoardControl.getRandomPositionsFences().contains(tp)){
//                System.out.println(tp);
//            }
//        }

    }

    public static void init(){
        BoardControl.init(14, 14);
        BoardControl.spawnFences();
        BoardControl.spawnMhos();
        BoardControl.spawnPlayer();
        BoardControl.print();
    }
}
