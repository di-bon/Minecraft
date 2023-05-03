package data.blocks;

import data.blocks.interfaces.Block;
import data.blocks.interfaces.SmeltableBlock;

public class Furnace {
    SmeltableBlock input;
    Block output;

    public Furnace() {
        input = new NullBlock();
        output = new NullBlock();
    }

    public void display_on_out() {
        System.out.println("||" + this.input.display() + " --> " + this.output.display() + "||");
    }

    public void smelt() {
        this.output = this.input.smelt();
        this.input = new NullBlock();
    }

    public void setInput(SmeltableBlock smeltableBlock) {
        this.input = smeltableBlock;
        this.output = this.input.smelt();
    }
}
