package data.blocks.solids;

import data.blocks.interfaces.IronSwordInterface;

public class IronSwordBlock extends AbstractSolidBlock implements IronSwordInterface {
    IronSwordBlock() {
        super();
        this.blockname = "IronSwordBlock";
        this.content = 'I';
    }
}
