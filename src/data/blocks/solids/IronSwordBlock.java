package data.blocks.solids;

import data.blocks.interfaces.IronSwordInterface;

public class IronSwordBlock extends AbstractSolidBlock implements IronSwordInterface {
    public IronSwordBlock() {
        this.blockname = "IronSwordBlock";
        this.content = 'I';
    }
}
