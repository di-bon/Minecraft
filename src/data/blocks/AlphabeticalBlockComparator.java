package data.blocks;

import data.blocks.interfaces.Block;

import java.util.Comparator;

public class AlphabeticalBlockComparator implements Comparator<Block> {
    @Override
    public int compare(Block b1, Block b2) {
        if (b1.toString().equals(b2.toString())) {
            return 0;
        }
        if (b1.toString().compareTo(b2.toString()) > 0) {
            return 1;
        }
        return -1;
    }
}
