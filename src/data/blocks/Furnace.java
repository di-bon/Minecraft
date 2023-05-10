package data.blocks;

import UI.Inventory;
import data.blocks.interfaces.Block;
import data.blocks.interfaces.SmeltableBlock;

public class Furnace {
    SmeltableBlock input;
    Block output;

    public Furnace() {
        this.input = new NullBlock();
        this.output = new NullBlock();
    }

    public void display_on_out() {
        System.out.println("||" + this.input.display() + " --> " + this.output.display() + "||");
    }

    public boolean is_input_empty() {
        return this.input instanceof NullBlock;
    }

    public void setInput(SmeltableBlock smeltableBlock) {
        this.input = smeltableBlock;
        this.output = this.input.smelt();
    }

    public void empty_input() {
        this.setInput(new NullBlock());
    }

    public SmeltableBlock get_input() {
        return this.input;
    }

    public Block smelt() {
        Block smelted_block = this.input.smelt();
        this.setInput(new NullBlock());
        return smelted_block;
    }
}
