package data.blocks;

import data.blocks.interfaces.Block;
import data.blocks.interfaces.SmeltableBlock;
import data.blocks.solids.AbstractSolidBlock;

public class NullBlock extends AbstractSolidBlock implements SmeltableBlock {
    public NullBlock() {
        super();
        this.blockname = "NullBlock";
        this.content = 'N';
    }

    @Override
    public Block smelt() {
        return new NullBlock();
    }

    public static void main(String[] args) {
        NullBlock nullBlock = new NullBlock();
        System.out.println(nullBlock);
    }
}
