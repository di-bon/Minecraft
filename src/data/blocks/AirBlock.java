package data.blocks;

public class AirBlock extends AbstractBlock {
    public AirBlock() {
        super();
        this.blockname = "AirBlock";
        this.content = '.';
        this.falls_with_gravity = false;
        this.fall_through = true;
    }
}