package data.blocks;

import data.blocks.interfaces.Block;
import data.blocks.interfaces.SmeltableBlock;
import data.blocks.solids.GlassBlock;

public class SandBlock extends AbstractBlock implements SmeltableBlock {
    public SandBlock() {
        super(50);
        this.blockname = "SandBlock";
        this.content = 'S';
        this.falls_with_gravity = true;
        this.fall_through = false;
        this.pickable = true;
    }

    @Override
    public Block smelt() {
        return new GlassBlock();
    }
}
