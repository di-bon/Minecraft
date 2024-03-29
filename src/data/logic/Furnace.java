package data.logic;

import data.blocks.NullBlock;
import data.blocks.interfaces.Block;
import data.blocks.interfaces.SmeltableBlock;

public class Furnace {
    private SmeltableBlock input;
    private Block output;

    public Furnace() {
        this.reset_furnace();
    }

    public SmeltableBlock view_input_block() {
        return this.input;
    }

    public Block view_output_block() {
        return this.output;
    }

    private void reset_furnace(){
        this.input = new NullBlock();
        this.output = new NullBlock();
    }
    public boolean setInput(SmeltableBlock smeltableBlock) {
        if (this.input.is_null_block()) {
            this.input = smeltableBlock;
            this.output = this.input.smelt();
            return true;
        }
        return false;
    }
    public SmeltableBlock get_input() {
        SmeltableBlock previous_input = this.input;
        this.reset_furnace();
        return previous_input;
    }

    public Block smelt() {
        if (!this.input.is_null_block()) {
            System.out.println("Smelting " + this.input.toString() + " into " + this.output.toString());
            Block smelted_block = this.output;
            this.reset_furnace();
            return smelted_block;
        }
        return this.input;
    }
}
