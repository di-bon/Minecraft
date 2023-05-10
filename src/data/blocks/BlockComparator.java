package data.blocks;

import data.blocks.interfaces.Block;

import java.util.Comparator;

public class BlockComparator implements Comparator<Block> {
    @Override
    public int compare(Block b1, Block b2) {
        if(b1.get_id() == b2.get_id()) {
            return 0;
        }
        if(b1.get_id() > b2.get_id()) {
            return 1;
        }
        return -1;
    }
}
