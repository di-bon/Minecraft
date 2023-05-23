package data.blocks.solids;

public class TorchBlock extends AbstractSolidBlock {
    public TorchBlock() {
        super(1);
        this.blockname = "TorchBlock";
        this.content = 'T';
        this.fall_through = true;
    }
}
