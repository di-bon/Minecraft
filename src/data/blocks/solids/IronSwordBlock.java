package data.blocks.solids;

import data.blocks.interfaces.IronSwordInterfaceBlock;

public class IronSwordBlock extends AbstractSolidBlock implements IronSwordInterfaceBlock {
    IronSwordBlock() {
        super(150);
        this.blockname = "IronSwordBlock";
        this.content = 'I';
    }
}
