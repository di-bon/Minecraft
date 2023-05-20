package UI.Terminal.TextPrinters;

import data.blocks.interfaces.Block;
import data.blocks.interfaces.SmeltableBlock;

public class FurnacePrinter implements TextPrinter {
    private SmeltableBlock input;
    private Block output;

    public FurnacePrinter(SmeltableBlock input, Block output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void display_on_out() {
        System.out.println("||" + this.input.display() + " --> " + this.output.display() + "||");
    }

    public void update(SmeltableBlock input, Block output) {
        this.input = input;
        this.output = output;
    }
}
