package UI;

import data.blocks.solids.BlockFactory;
import data.blocks.AirBlock;
import data.blocks.Furnace;
import data.blocks.NullBlock;
import data.blocks.solids.IronSwordBlock;
import data.exceptions.BlockErrorException;
import data.blocks.interfaces.Block;
import data.blocks.interfaces.SmeltableBlock;
import data.exceptions.WrongCoordinatesException;
import utils.MapCoordinates;

import java.util.Scanner;

public class MainView {
    private final Map map;
    private final Furnace furnace;
    private final Inventory inventory;

    public MainView() {
        map = new Map();
        furnace = new Furnace();
        inventory = new Inventory();
    }

    public void display_on_out() {
        map.display_with_numbers();
        furnace.display_on_out();
        inventory.display_inventory();
    }

//    Deprecated
    public void move_into_furnace(MapCoordinates mapCoordinates) throws WrongCoordinatesException {
        try {
            if (this.map.isSmeltableBlock(mapCoordinates)) {
                this.furnace.setInput((SmeltableBlock) this.map.getBlockAt(mapCoordinates));
                this.map.insert_at_cords(mapCoordinates, new AirBlock());
            }
        } catch (BlockErrorException bee) {
            bee.printStackTrace();
        }
    }

    public void move_into_furnace_from_inventory(int index) {
        Block block = this.inventory.get_block_from_inventory(index);
        if (block.is_smeltable() && !(block instanceof NullBlock)) {
//            if (this.furnace.setInput((SmeltableBlock) block)) {
//                this.inventory.blocks.remove(index);
//            }
            this.furnace.setInput((SmeltableBlock) block);
            this.inventory.remove_block_from_inventory(index);
        }
    }

    public void move_into_inventory_from_furnace() {
        Block block = this.furnace.get_input();
        if (block instanceof NullBlock) {
            return;
        }
        this.inventory.add_block(block);
        this.furnace.empty_input();
    }

    public void pick_up_block(MapCoordinates mapCoordinates) throws BlockErrorException, WrongCoordinatesException {
//        Block block = this.map.getBlockAt(mapCoordinates);
//        if (block.is_pickable()) {
//            this.inventory.add_block(block);
//            this.map.insert_at_cords(mapCoordinates, new AirBlock());
//        }
        this.inventory.add_block(this.map.gimme_pickable(mapCoordinates));
        this.map.insert_at_cords(mapCoordinates, new AirBlock());
        this.map.process_gravity(mapCoordinates);
    }

    public void toggle_inventory_comparator() {
        this.inventory.change_comparator();
    }

    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.map.randomize_map(10);
        mainView.display_on_out();

//        BlockFactory bf = new BlockFactory();
//        bf.ironSwordBlock();
//        IronSwordBlock ironSwordBlock = new IronSwordBlock();

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
                try {
                    mainView.pick_up_block(c);
                }
                catch (BlockErrorException bee) {
                    bee.printStackTrace();
                } catch (WrongCoordinatesException wce) {
                    wce.printStackTrace();
                }
            }
            mainView.display_on_out();
        }
    }
}
