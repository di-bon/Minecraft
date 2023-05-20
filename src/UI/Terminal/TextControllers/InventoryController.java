package UI.Terminal.TextControllers;

import UI.logic.Inventory;
import UI.Terminal.TextPrinters.InventoryPrinter;

public class InventoryController extends AbstractTextController {
    private Inventory inventory;

    public InventoryController(Inventory inventory, InventoryPrinter inventoryPrinter) {
        this.inventory = inventory;
        this.textPrinter = inventoryPrinter;
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
