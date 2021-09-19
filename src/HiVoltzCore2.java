import java.awt.*;
import java.util.*;
import java.lang.Math.*;

public class HiVoltzCore2 {
    public static int grid[][];
    public static void main(String[] args) {
        int hor = 12;
        int ver = 12;
        grid = new int[][]{
                {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
        };
        int mhoY = 3;
        int mhoX = 2;
//      ArrayList<Point> aw = new ArrayList<>();
        int playerx = 0;
        int playery = 0;
        for (int i = 0; i < hor; i++) {
            for (int j = 0; j < ver; j++) {
                if (grid[i][j] == 2) {
                    playerx = j;
                    playery = i;
                }
            }
        }
        //checking if its horizontal
        if (mhoX == playerx) {
            System.out.println("Horizontal");
            System.out.println(grid[mhoX][mhoY]);
            grid[mhoY][mhoX] = 0;
            if (mhoY > playery) {
                grid[mhoY - 1][mhoX] = 1;
            }
            else {
                grid[mhoY + 1][mhoX] = 1;
                System.out.println("Deleted original");
            }
        } else if (mhoY == playery) {
            grid[mhoY][mhoX] = 0;
            if (mhoX > playerx) {
                grid[mhoY][mhoX - 1] = 0;
            } else {
                grid[mhoY][mhoX + 1] = 1;
            }
        } else if (Math.abs(mhoY - playery) == (Math.abs(mhoX - playerx))) {
            if (mhoY > playery && mhoX > playerx) {
                grid[mhoY][mhoX] = 0;
                grid[mhoY - 1][mhoX - 1] = 1;
            } else if (mhoY > playery && mhoX < playerx) {
                grid[mhoY][mhoX] = 0;
                grid[mhoY - 1][mhoX + 1] = 1;
            } else if (mhoY < playery && mhoX < playerx) {
                grid[mhoY][mhoX] = 0;
                grid[mhoY + 1][mhoX + 1] = 1;
            } else if (mhoY < playery && mhoX > playerx) {
                grid[mhoY][mhoX] = 0;
                grid[mhoY + 1][mhoX - 1] = 1;
            }

        }
    }
}