package UI;

import data.blocks.AlphabeticalBlockComparator;
import data.blocks.BlockComparator;
import data.blocks.NullBlock;
import data.blocks.interfaces.InventoryBlock;
import data.exceptions.BlockErrorException;
import data.blocks.interfaces.Block;
import data.blocks.interfaces.SmeltableBlock;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Inventory {
    private List<Block> blocks;
    private final int inventory_size;
    private Comparator<Block> comparator = new AlphabeticalBlockComparator();

    public Inventory(int capacity) {
        this.inventory_size = capacity;
        try {
            this.blocks = new ArrayList<>(inventory_size);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
    }
    public Inventory() {
        // size is 10 by default
        this.inventory_size = 10;
        this.blocks = new ArrayList<>();
    }

    public void display_inventory() {
        Iterator<Block> iterator = this.blocks.iterator();
        while(iterator.hasNext()) {
            iterator.next().display_in_inventory();
            System.out.print(" ");
        }
        System.out.println("");

//        "Usate un Iterator per ciclare sulla collection"
//        for(Block block : this.blocks) {
//            block.display_in_inventory();
//        }
    }

    // TODO: change this void method to boolean method
    public boolean add_block(Block block) {
        if (this.blocks.size() < this.inventory_size) {
            this.blocks.add(block);
            this.order_inventory();
            return true;
        }
        return false;
    }
    public Block get_block_from_inventory(int index) {
        try {
            return this.blocks.get(index);
        } catch (IndexOutOfBoundsException iooe) {
            iooe.printStackTrace();
            return new NullBlock();
        }
    }

    public boolean remove_block_from_inventory(int index) {
        try {
            this.blocks.remove(index);
            return true;
        } catch (IndexOutOfBoundsException iooe) {
            iooe.printStackTrace();
            return false;
        }
    }
    private boolean is_smeltable(int index) throws BlockErrorException {
        return this.blocks.get(index).is_smeltable();
    }

    public SmeltableBlock get_smeltable_item(int index) throws BlockErrorException {
        return this.is_smeltable(index) ? (SmeltableBlock) this.blocks.get(index) : new NullBlock();
    }

    private void order_inventory() {
        this.blocks.sort(comparator);
    }

    public void change_comparator() {
        if (this.comparator instanceof AlphabeticalBlockComparator) {
            this.comparator = new BlockComparator();
        }
        else {
            this.comparator = new AlphabeticalBlockComparator();
        }
    }
}
