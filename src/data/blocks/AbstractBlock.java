package data.blocks;

import data.blocks.interfaces.Block;

public abstract class AbstractBlock implements Block {
    protected String blockname;
    protected char content;
    protected boolean falls_with_gravity;
    protected boolean fall_through;

    @Override
    public char display() {
        return this.content;
    }
    @Override
    public char getContent() { return this.content; }
    @Override
    public boolean is_falls_with_gravity() {
        return this.falls_with_gravity;
    }

    @Override
    public boolean is_fall_through() {
        return this.fall_through;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "\n\tblockname='" + blockname + '\'' +
                ",\n\tcontent=" + content +
                ",\n\tfalls_with_gravity=" + falls_with_gravity +
                ",\n\tfall_through=" + fall_through +
                "\n}";
    }
}
