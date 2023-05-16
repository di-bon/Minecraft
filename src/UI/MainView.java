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

    public MainView(boolean random) {
        map = new Map(random);
        furnace = new Furnace();
        inventory = new Inventory();
    }

    public Map get_map() {
        return this.map;
    }

    public Furnace get_furnace() {
        return furnace;
    }

    public Inventory get_inventory() {
        return inventory;
    }

    public void display_on_out() {
        map.display_with_numbers();
        furnace.display_on_out();
        inventory.display_inventory();
    }
    public void move_into_furnace_from_inventory(int index) throws BlockErrorException {
        SmeltableBlock smeltableBlock = this.inventory.get_smeltable_item(index);
        this.furnace.setInput(smeltableBlock);
        this.inventory.remove_block_from_inventory(index);
    }

    public void smelt() {
        Block smelted_block = this.furnace.smelt();
        if (!smelted_block.is_null_block()) {
            this.inventory.add_block(smelted_block);
        }
    }
    public void move_into_inventory_from_furnace() {
        Block block = this.furnace.get_input();
        if (block instanceof NullBlock) {
            return;
        }
        this.inventory.add_block(block);
    }

    public void pick_up_block(MapCoordinates mapCoordinates) throws BlockErrorException, WrongCoordinatesException {
        Block block = this.map.gimme_pickable(mapCoordinates);
        this.inventory.add_block(block);
    }

    public void toggle_inventory_comparator() {
        this.inventory.toggle_comparator();
    }

    public static void main(String[] args) {
        MainView mainView = new MainView(true);
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
                try {
                    mainView.move_into_furnace_from_inventory(col);
                } catch (BlockErrorException bee) {
                    bee.printStackTrace();
                }
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
                try {
                    mainView.pick_up_block(c);
                } catch (BlockErrorException bee) {
                    bee.printStackTrace();
                } catch (WrongCoordinatesException wce) {
                    wce.printStackTrace();
                }
            }
            mainView.display_on_out();
        }
    }
}
