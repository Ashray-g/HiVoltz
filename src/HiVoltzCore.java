import java.awt.*;
import java.util.*;
import java.lang.Math.*;

public class HiVoltzCore{
    public static int grid[][];
    public static void main(String[] args) {
        int hor = 12;
        int ver = 12;
        grid = new int[][]{
                {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 3},
                {3, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
        };
        int mhoX = 2;
        int mhoY = 4;

//      ArrayList<Point> aw = new ArrayLisy<>();
        int playerx = 0;
        int playery = 0;
        for (int i = 0; i < hor; i++) {
            for (int j = 0; j < ver; j++) {
                if (grid[i][j] == 2) {
                    playerx = i;
                    playery = j;
                }
            }
        }
        //checking if its horizontal
        if (mhoY == playery) {
            if (mhoX > playerx) {
                grid[mhoX][mhoY] = 0;
                grid[mhoX - 1][mhoY] = 1;
            } else {
                grid[mhoX + 1][mhoY] = 1;
            }
        }

        if (mhoX == playerx) {
            if (mhoY > playery) {
                grid[mhoX][mhoY] = 0;
                grid[mhoX][mhoY - 1] = 0;
            } else {
                grid[mhoX][mhoY + 1] = 1;
            }
        }
        if (Math.abs(mhoX - playerx) == (Math.abs(mhoY - playery))) {
            if (mhoX > playerx && mhoY > playery) {
                grid[mhoX][mhoY] = 0;
                grid[mhoX - 1][mhoY - 1] = 1;
            } else if (mhoX > playerx && mhoY < playery) {
                grid[mhoX][mhoY] = 0;
                grid[mhoX - 1][mhoY + 1] = 1;
            } else if (mhoX < playerx && mhoY < playery) {
                grid[mhoX][mhoY] = 0;
                grid[mhoX + 1][mhoY + 1] = 1;
            } else if (mhoX < playerx && mhoY > playery) {
                grid[mhoX][mhoY] = 0;
                grid[mhoX + 1][mhoY - 1] = 1;
            }

        }
        printGrid();
    }
    public static void printGrid(){
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

}