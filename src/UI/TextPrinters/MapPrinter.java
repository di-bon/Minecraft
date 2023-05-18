package UI.TextPrinters;

import data.blocks.interfaces.Block;
import utils.MapCoordinates;

public class MapPrinter implements TextPrinter {
    private final int rows = MapCoordinates.DIMENSION_ROWS;
    private final int columns = MapCoordinates.DIMENSION_COLUMNS;
    private Block[][] map;

    public MapPrinter(Block[][] map) {
        this.map = map;
    }

    @Override
    public void display_on_out() {
        System.out.println("\n");

        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                System.out.print(map[row][column].display() + " ");
            }
            System.out.print("\n");
        }
    }

    public void update(Block[][] map) {
        this.map = map;
    }
}
