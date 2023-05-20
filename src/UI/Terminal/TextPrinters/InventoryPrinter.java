package UI.Terminal.TextPrinters;

import data.blocks.interfaces.Block;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class InventoryPrinter implements TextPrinter {
    private List<Block> blocks;
    private Comparator<Block> comparator;

    public InventoryPrinter(List<Block> blocks, Comparator<Block> comparator) {
        this.blocks = blocks;
        this.comparator = comparator;
    }

    @Override
    public void display_on_out() {
        Iterator<Block> iterator = this.blocks.iterator();
        while(iterator.hasNext()) {
            iterator.next().display_in_inventory();
            System.out.print(" ");
        }
        System.out.println();
    }

    public void update(List<Block> blocks, Comparator<Block> comparator) {
        this.blocks = blocks;
        this.comparator = comparator;
    }
}
