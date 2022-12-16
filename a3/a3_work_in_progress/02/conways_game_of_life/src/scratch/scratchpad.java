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

        String tmp = "";

        int ct = 1;
        for (Cell c : g) {

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
