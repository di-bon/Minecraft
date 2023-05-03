package data.blocks;

public class WaterBlock extends AbstractBlock {
    public WaterBlock() {
        this.blockname = "WaterBlock";
        this.content = 'W';
        this.falls_with_gravity = true;
        this.fall_through = true;
    }
}