package data.blocks.solids;

import data.blocks.interfaces.Block;
import data.blocks.interfaces.SmeltableBlock;

public class RawIronBlock extends AbstractSolidBlock implements SmeltableBlock {
    public RawIronBlock() {
        this.blockname = "RawIronBlock";
        this.content = 'R';
    }

    @Override
    public Block smelt() {
        return new IronSwordBlock();
    }
}
