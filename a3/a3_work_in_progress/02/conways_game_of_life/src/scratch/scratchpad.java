package scratch;

import cell.Cell;
import cell.Grid;
import gamemodel.*;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class scratchpad {

    public static void main(String[] args) {

        int w = 1;
        int h = 1;
        Grid g = new Grid(w,h);

        // set first cell to RED
        g.getCell(0,0).instantBirth(player.PlayerColor.RED);

        String tmp = "";

        int ct = 1;
        for (Cell c : g.getIterator()) {

            String cstr = c.getState().getColorName();

            if (ct%w == 0) {
                tmp += String.format("%s\n", cstr);
            } else {
                tmp += String.format("%s ", cstr);
            }
            ct += 1;

        }
        System.out.println(tmp);

        tmp = "";

        ct = 1;
        for (Cell c : g.getIterator()) {

            String cstr = c.getState().getColorName();

            if (ct%w == 0) {
                tmp += String.format("%s\n", cstr);
            } else {
                tmp += String.format("%s ", cstr);
            }
            ct += 1;

        }
        System.out.println(tmp);




        tmp = "";

        ct = 1;
        for (Cell c : g.getIteratorReversed()) {

            String cstr = c.getState().getColorName();

            if (ct%w == 0) {
                tmp += String.format("%s\n", cstr);
            } else {
                tmp += String.format("%s ", cstr);
            }
            ct += 1;

        }
        System.out.println(tmp);

    }

}
