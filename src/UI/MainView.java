package UI;

import data.blocks.AirBlock;
import data.blocks.Furnace;
import data.blocks.interfaces.SmeltableBlock;
import utils.MapCoordinates;

import java.util.Scanner;

public class MainView {
    private final Map map;
    private final Furnace furnace;

    public MainView() {
        map = new Map();
        furnace = new Furnace();
    }

    public void display_on_out() {
        map.display_with_numbers();
        furnace.display_on_out();
    }

    public void move_into_furnace(MapCoordinates mapCoordinates) {
        if (this.map.isSmeltableBlock(mapCoordinates)) {
            this.furnace.setInput((SmeltableBlock) this.map.getBlockAt(mapCoordinates));
            this.map.insert_at_cords(mapCoordinates, new AirBlock());
        }
    }

    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.map.randomize_map(10);
        mainView.display_on_out();

        while (true) {
            System.out.print("Enter row and then column, or enter '9' and then '9' for smelting: ");
            Scanner myObj = new Scanner(System.in);
            int row = myObj.nextInt();
            int col = myObj.nextInt();
            if (row == 9 && col == 9){
                mainView.furnace.smelt();
            } else {
                mainView.move_into_furnace(new MapCoordinates(row, col));
            }
            mainView.display_on_out();
        }
    }
}
