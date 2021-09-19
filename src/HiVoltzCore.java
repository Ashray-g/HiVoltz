import java.awt.*;
import java.util.ArrayList;

public class HiVoltzCore {
    public static ArrayList<Point> updateMhos(int[][] grid, Point player, ArrayList<Point> mhos){
        ArrayList<Point> newMhos = new ArrayList<>();

        for(Point mho : mhos){ //mho loop
            int mhoX = mho.x;
            int mhoY = mho.y;
            int newMhoY = mhoY;
            int newMhoX = mhoX;

            int playerX = player.x;
            int playerY = player.y;

            //checking if its Vertical
            if(mhoX == playerX && mhoY == playerY){
                //no change
            }else if (mhoX == playerX) {
                if (mhoY > playerY) {
                    newMhoY = mhoY-1;
                } else {
                    newMhoY = mhoY+1;
                }
            }
            //Checking if it's horizontal
            else if (mhoY == playerY) {
                if (mhoX > playerX) {
                    newMhoX = mhoX - 1;
                } else {
                    newMhoX = mhoX - 1;
                }
            }
            //Checking for direct diagonals
            else if (Math.abs(mhoY - playerY) == (Math.abs(mhoX - playerX))) {
                if (mhoY > playerY && mhoX > playerX) {
                    newMhoY = mhoY - 1;
                    newMhoX = mhoX - 1;
                } else if (mhoY > playerY) {
                    newMhoY = mhoY - 1;
                    newMhoX = mhoX + 1;
                } else if (mhoX < playerX) {
                    newMhoY = mhoY + 1;
                    newMhoX = mhoX + 1;
                } else{
                    newMhoY = mhoY + 1;
                    newMhoX = mhoX - 1;
                }
            }else if(Math.abs(playerX-mhoX)>Math.abs(playerY-mhoY)) {
                if (playerX>mhoX){
                    newMhoX = mhoX + 1;
                }
                else {
                    newMhoX = mhoX - 1;
                }
            }
            else if(Math.abs(playerX-mhoX)<Math.abs(playerY-mhoY)) {
                if (playerY>mhoY){
                    newMhoY = mhoY + 1;
                }
                else {
                    newMhoY = mhoY - 1;
                }
            }


            grid[mhoY][mhoX] = 0;
            newMhos.add(new Point(newMhoX, newMhoY));
        } //end mho loop
        ArrayList<Point> mos = new ArrayList<>();
        for(Point mho : newMhos){
            if(grid[mho.y][mho.x] == 0){
                grid[mho.y][mho.x] = 2;
                mos.add(mho);
            }
            if(grid[mho.y][mho.x] == 3){
                grid[mho.y][mho.x] = 2;
                BoardControl.gameOver();
            }
        }

        return mos;

    }
}
