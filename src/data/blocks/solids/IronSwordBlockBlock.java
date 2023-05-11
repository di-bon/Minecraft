package data.blocks.solids;

import data.blocks.interfaces.IronSwordInterfaceBlock;

public class IronSwordBlockBlock extends AbstractSolidBlock implements IronSwordInterfaceBlock {
    IronSwordBlockBlock() {
        super();
        this.blockname = "IronSwordBlock";
        this.content = 'I';
    }
}
