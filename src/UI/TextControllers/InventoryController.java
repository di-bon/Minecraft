package UI.TextControllers;

import UI.Inventory;
import UI.TextPrinters.InventoryPrinter;
import UI.TextPrinters.TextPrinter;

public class InventoryController extends AbstractTextController {
    private Inventory inventory;

    public InventoryController(Inventory inventory) {
        this.inventory = inventory;
        this.textPrinter = new InventoryPrinter(
                this.inventory.get_blocks(),
                this.inventory.get_comparator()
        );
    }

    @Override
    public void updatePrinter() {
        InventoryPrinter inventoryPrinter = (InventoryPrinter) this.textPrinter;
        inventoryPrinter.update(
                this.inventory.get_blocks(),
                this.inventory.get_comparator()
        );
    }
}
