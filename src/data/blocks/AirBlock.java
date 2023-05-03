package data.blocks;

public class AirBlock extends AbstractBlock {
    private char content;
    private final boolean falls_with_gravity;
    private final boolean fall_through;

    public AirBlock() {
        this.blockname = "AirBlock";
        this.content = '.';
        this.falls_with_gravity = false;
        this.fall_through = true;
    }

    public char display() {
        return this.content;
    }

    public boolean is_falls_with_gravity() {
        return this.falls_with_gravity;
    }

    public boolean is_fall_through() {
        return this.fall_through;
    }
}