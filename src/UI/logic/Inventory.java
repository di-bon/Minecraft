package UI.logic;

import data.blocks.utils.AlphabeticalBlockComparator;
import data.blocks.utils.BlockComparator;
import utils.BlockErrorException;
import data.blocks.interfaces.Block;
import data.blocks.interfaces.SmeltableBlock;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Inventory {
    private List<Block> blocks;
    private final int inventory_size;
    private Comparator<Block> comparator;

    public Inventory(int capacity) {
        this.inventory_size = capacity;
        try {
            this.blocks = new ArrayList<>(inventory_size);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
        this.comparator = new AlphabeticalBlockComparator();
    }
    public Inventory() {
        // size is 10 by default
        this.inventory_size = 10;
        this.blocks = new ArrayList<>();
        this.comparator = new AlphabeticalBlockComparator();
    }

    public List<Block> get_blocks() {
        return this.blocks;
    }

    public Iterator<Block> get_blocks_iterator() {
        return this.blocks.iterator();
    }

    public Comparator<Block> get_comparator() {
        return this.comparator;
    }

    public boolean add_block(Block block) {
        if (block.is_null_block()) {
            return false;
        }
        if (this.blocks.size() < this.inventory_size) {
            this.blocks.add(block);
            this.sort_inventory();
            return true;
        }
        return false;
    }

    public void remove_block_from_inventory(int index) throws IndexOutOfBoundsException {
        this.blocks.remove(index);
    }

    private boolean is_smeltable(int index) throws IndexOutOfBoundsException {
        return this.blocks.get(index).is_smeltable();
    }

    public SmeltableBlock get_smeltable_item(int index) throws BlockErrorException {
        if (this.is_smeltable(index)) {
            return (SmeltableBlock) this.blocks.get(index);
        }
        throw new BlockErrorException();
    }

    private void sort_inventory() {
        this.blocks.sort(comparator);
    }

    public void toggle_comparator() {
        if (this.comparator instanceof AlphabeticalBlockComparator) {
            this.comparator = new BlockComparator();
        }
        else {
            this.comparator = new AlphabeticalBlockComparator();
        }
    }
}
