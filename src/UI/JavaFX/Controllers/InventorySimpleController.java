package UI.JavaFX.Controllers;

import data.logic.Inventory;
import UI.JavaFX.Graphical.InventoryPane;

public class InventorySimpleController implements SimpleController {
    private final Inventory inventory;
    private final InventoryPane inventoryPane;

    public InventorySimpleController(Inventory inventory, InventoryPane inventoryPane) {
        this.inventory = inventory;
        this.inventoryPane = inventoryPane;
        this.redraw();
    }

    @Override
    public void redraw() {
        this.inventoryPane.set_blocks(this.inventory.get_blocks_iterator());
    }
}
