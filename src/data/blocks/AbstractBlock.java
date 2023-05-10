package data.blocks;

import data.blocks.interfaces.Block;
import data.blocks.interfaces.SmeltableBlock;

public abstract class AbstractBlock implements Block {
    protected String blockname;
    protected char content;
    protected boolean falls_with_gravity;
    protected boolean fall_through;
    protected boolean pickable;
    protected int id;
    private static int ID_COUNTER = 0;

    public AbstractBlock() {
        this.id = AbstractBlock.ID_COUNTER;
        AbstractBlock.ID_COUNTER++;
        this.pickable = false;
    }

    @Override
    public char display() {
        return this.content;
    }
    @Override
    public boolean is_falls_with_gravity() {
        return this.falls_with_gravity;
    }

    @Override
    public boolean is_fall_through() {
        return this.fall_through;
    }

    @Override
    public boolean is_pickable() {
        return this.pickable;
    }

    @Override
    public boolean is_smeltable() {
        return this instanceof SmeltableBlock;
    }

    @Override
    public boolean is_null_block() {
        return this instanceof NullBlock;
    }

    @Override
    public String toString() {
        return "AbstractBlock{" +
                "blockname='" + blockname + '\'' +
                ", content=" + content +
                ", falls_with_gravity=" + falls_with_gravity +
                ", fall_through=" + fall_through +
                ", pickable=" + pickable +
                ", id=" + id +
                '}';
    }

    @Override
    public void display_in_inventory() {
        System.out.print("[" + this.content + "]");
    }

    @Override
    public int get_id() {
        return this.id;
    }
}
