import com.sun.security.jgss.GSSUtil;

import java.sql.SQLOutput;

public class PrintGrid {
    //Ocean Grid shows all the Boats that have been set (Assumes Bord has size 10x10)
    //
    static void printOceanMap(Grid playersGrid){
        System.out.println("===== OCEAN GRID =====");
        System.out.println("  A B C D E F G H I J  ");
        System.out.println(" +-+-+-+-+-+-+-+-+-+-+ ");
        for(int row = 0; row < 10; row++){
            System.out.print(Integer.toString(row));
            System.out.print("|");
            for(int column = 0; column < 10; column++){
                if(playersGrid.isShot(row,column)){
                    if(playersGrid.hasBoat(row,column)){
                        System.out.print("X|");
                        //TODO Unterscheiden zwischen ganzes Schiff versunken oder nur teilweise
                    }
                    else {
                        System.out.print("o|");
                    }
                }
                else {
                    if(playersGrid.hasBoat(row,column)){
                        System.out.print(playersGrid.getBoatType(row,column));
                        System.out.print("|");

                    }
                    else {
                        System.out.print(" |");
                    }
                }
            }
            System.out.println(Integer.toString(row));


        }
        System.out.println(" +-+-+-+-+-+-+-+-+-+-+ ");
        System.out.println("  A B C D E F G H I J  ");
        System.out.println("=======================");

    }


    static void printTargetGrid(Grid targetGrid){
        System.out.println("===== OCEAN GRID =====");
        System.out.println("  A B C D E F G H I J  ");
        System.out.println(" +-+-+-+-+-+-+-+-+-+-+ ");
        for(int row = 0; row < 10; row++){
            System.out.print(Integer.toString(row));
            System.out.print("|");
            for(int column = 0; column < 10; column++){
                if(targetGrid.isShot(row,column)){
                    if(targetGrid.hasBoat(row,column)){
                        System.out.print("X|");
                    }
                    else {
                        System.out.print("o|");
                    }
                }
                else {
                    if(targetGrid.hasBoat(row,column)){
                        System.out.print(targetGrid.getBoatType(row,column));
                        System.out.print("|");

                    }
                    else {
                        System.out.print(" |");
                    }
                }
            }
            System.out.println(Integer.toString(row));


        }
        System.out.println(" +-+-+-+-+-+-+-+-+-+-+ ");
        System.out.println("  A B C D E F G H I J  ");
        System.out.println("=======================");

    }
}
