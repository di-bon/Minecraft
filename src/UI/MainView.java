package UI;

import data.blocks.NullBlock;
import utils.BlockErrorException;
import data.blocks.interfaces.Block;
import data.blocks.interfaces.SmeltableBlock;
import utils.MapCoordinates;
import utils.WrongCoordinatesException;

import java.util.Scanner;

public class MainView {
    private final Map map;
    private final Furnace furnace;
    private final Inventory inventory;

    public MainView() {
        map = new Map(true);
        furnace = new Furnace();
        inventory = new Inventory();
    }

    public void display_on_out() {
        map.display_with_numbers();
        furnace.display_on_out();
        inventory.display_inventory();
    }
    public void move_into_furnace_from_inventory(int index) {
        try {
            SmeltableBlock smeltableBlock = this.inventory.get_smeltable_item(index);
            this.furnace.setInput(smeltableBlock);
        } catch (BlockErrorException bee) {
            bee.printStackTrace();
        }
    }
    public void move_into_inventory_from_furnace() {
        Block block = this.furnace.get_input();
        if (block instanceof NullBlock) {
            return;
        }
        this.inventory.add_block(block);
    }

    public void pick_up_block(MapCoordinates mapCoordinates) {
        try {
            Block block = this.map.gimme_pickable(mapCoordinates);
            this.inventory.add_block(block);
        } catch (BlockErrorException bee) {
            bee.printStackTrace();
        } catch (WrongCoordinatesException wce) {
            wce.printStackTrace();
        }
    }

    public void toggle_inventory_comparator() {
        this.inventory.toggle_comparator();
    }

    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.map.randomise_map();
        mainView.display_on_out();

        while (true) {
            System.out.println("Enter row and then column to pick that block");
            System.out.println("Enter '9' and the item number to move the item to the furnace");
            System.out.println("Enter '99' and then '9' to smelt");
            System.out.println("Enter '99' and then '0' to toggle the inventory sorting");
            System.out.println("Enter '99' and then any number to take from the furnace into the inventory");
            Scanner myObj = new Scanner(System.in);
            int row = myObj.nextInt();
            int col = myObj.nextInt();
            if (row == 9){
                mainView.move_into_furnace_from_inventory(col);
            }else if( row == 99 ){
                if (col == 9) {
                    Block smelted_block = mainView.furnace.smelt();
                    if (!smelted_block.is_null_block()) {
                        mainView.inventory.add_block(smelted_block);
                    }
//                    this.inventory.add_block(mainView.furnace.smelt(););
//                    mainView.furnace.smelt(mainView.inventory);
                }else if (col == 0){
                    mainView.toggle_inventory_comparator();
                }else {
                    mainView.move_into_inventory_from_furnace();
                }
            } else{
                MapCoordinates c = new MapCoordinates(row,col);
                mainView.pick_up_block(c);
            }
            mainView.display_on_out();
        }
    }
}
