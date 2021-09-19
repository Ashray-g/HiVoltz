import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class BoardControl {
    // 0 is empty
    // 1 is fence
    // 2 is mho
    // 3 is player
    private static int[][] board;
    private static HashSet<Point> randomPositionsFences = new HashSet<>();
    private static HashSet<Point> randomPositionsMhos = new HashSet<>();
    private static Point randomPositionPlayer = new Point();

    private static State currentState = State.TITLE;

    private static int height;
    private static int width;

    public enum State{
        TITLE, IN_GAME, GAME_OVER, YOU_WIN
    }

    public static void start(){
        currentState = State.IN_GAME;
    }
    public static void lose(){
        currentState = State.GAME_OVER;
    }
    public static void win(){
        currentState = State.YOU_WIN;
    }
    public static void restart(){
        currentState = State.TITLE;
        Main.init();
    }

    public static State getCurrentState() {
        return currentState;
    }

    public static void init(int height2, int width2) {
        height = height2;
        width = width2;
        board = new int[height][width];
        randomPositionsFences = new HashSet<>();
        randomPositionsMhos = new HashSet<>();
    }

    public static int[][] getBoard() {
        return board;
    }

    public static HashSet<Point> getRandomPositionsFences() {
        return randomPositionsFences;
    }

    public static HashSet<Point> getRandomPositionsMhos() {
        return randomPositionsMhos;
    }

    public static Point getRandomPositionPlayer() {
        return randomPositionPlayer;
    }

    public static int length() {
        return getBoard().length;
    }

    public static void spawnFences() {
        Random ran = new Random();

        int fencesToSpawn = (int)((width - 2) * (height - 2) * 0.2 + 0.5);

        for (int i = 0; i < fencesToSpawn; i++) {
            Point pos = new Point(ran.nextInt(width - 2) + 1, ran.nextInt(height - 2) + 1);
            if (!randomPositionsFences.contains(pos)) {
                randomPositionsFences.add(pos);
            } else {
                i--;
            }
        }

        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < length(); x++) {
                if (y == 0) {
                    board[y][x] = 1;
                } else {
                    board[length() - 1][x] = 1;
                }
            }
        }

        for (int y = 1; y < length() - 1; y++) {
            for (int x = 0; x < 2; x++) {
                if (x == 0) {
                    board[y][x] = 1;
                } else {
                    board[y][length() - 1] = 1;
                }
            }
        }

        for (Point k : randomPositionsFences) {
            int x = (int) k.getX();
            int y = (int) k.getY();
            board[y][x] = 1;
        }
        System.out.println(randomPositionsFences);
    }

    public static void spawnMhos() {
        Random ran = new Random();

        int mhosToSpawn = (int)((width - 2) * (height - 2) * 0.12 + 0.5);

        for (int i = 0; i < mhosToSpawn; i++) {
            Point pos = new Point(ran.nextInt(width - 2) + 1, ran.nextInt(height - 2) + 1);
            if (!randomPositionsMhos.contains(pos) && !randomPositionsFences.contains(pos)) {
                randomPositionsMhos.add(pos);
            } else {
                i--;
            }
        }

        for (Point k : randomPositionsMhos) {
            int x = (int) k.getX();
            int y = (int) k.getY();
            board[y][x] = 2;
        }
        System.out.println(randomPositionsMhos);
    }

    public static void spawnPlayer() {
        Random ran = new Random();

        while (true) {
            Point pos = new Point(ran.nextInt(width - 2) + 1, ran.nextInt(height - 2) + 1);
            if (!randomPositionsMhos.contains(pos) && !randomPositionsFences.contains(pos)) {
                randomPositionPlayer = pos;
                break;
            }
        }

        int x = (int) randomPositionPlayer.getX();
        int y = (int) randomPositionPlayer.getY();
        board[y][x] = 3;
    }

    public static void print() {
        for (int y = 0; y < length(); y++) {
            for (int x = 0; x < length(); x++) {
                System.out.print(board[y][x]);
            }
            System.out.println();
        }
    }

    public static void resetPlayerPosition(Point p) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        board[y][x] = 0;
        int x1 = (int) randomPositionPlayer.getX();
        int y1 = (int) randomPositionPlayer.getY();
        if(board[y1][x1] != 0) gameOver();
        else board[y1][x1] = 3;
    }

    public static void updatePlayerPosition(char in) throws InterruptedException {
        Random ran = new Random();

        Point temp = new Point((int) randomPositionPlayer.getX(), (int) randomPositionPlayer.getY());

        switch(in) {
            case 'q':
                randomPositionPlayer.setLocation(randomPositionPlayer.getX() - 1, randomPositionPlayer.getY() - 1); break;
            case 'w':
                randomPositionPlayer.setLocation(randomPositionPlayer.getX(), randomPositionPlayer.getY() - 1); break;
            case 'e':
                randomPositionPlayer.setLocation(randomPositionPlayer.getX() + 1, randomPositionPlayer.getY() - 1); break;
            case 'a':
                randomPositionPlayer.setLocation(randomPositionPlayer.getX() - 1, randomPositionPlayer.getY()); break;
            case 's':
                break;
            case 'd':
                randomPositionPlayer.setLocation(randomPositionPlayer.getX() + 1, randomPositionPlayer.getY()); break;
            case 'z':
                randomPositionPlayer.setLocation(randomPositionPlayer.getX() - 1, randomPositionPlayer.getY() + 1); break;
            case 'x':
                randomPositionPlayer.setLocation(randomPositionPlayer.getX(), randomPositionPlayer.getY() + 1); break;
            case 'c':
                randomPositionPlayer.setLocation(randomPositionPlayer.getX() + 1, randomPositionPlayer.getY() + 1); break;
            case 'j':
                randomPositionPlayer.setLocation(ran.nextInt(width - 2) + 1, ran.nextInt(height - 2) + 1); break;

        }

        resetPlayerPosition(temp);

        ArrayList<Point> mhos = new ArrayList<>(getRandomPositionsMhos());

        ArrayList<Point> mhos2 = HiVoltzCore.updateMhos(BoardControl.getBoard(), BoardControl.getRandomPositionPlayer(), mhos);

        randomPositionsMhos.clear();
        randomPositionsMhos.addAll(mhos2);
        if(randomPositionsMhos.isEmpty()){
            currentState = State.YOU_WIN;
        }
    }

    public static void gameOver(){
        randomPositionPlayer.x = 200;
        randomPositionPlayer.y = 200;
        currentState = State.GAME_OVER;
    }
}