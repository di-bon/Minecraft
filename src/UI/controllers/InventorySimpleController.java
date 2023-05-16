package UI.controllers;

import UI.Inventory;
import UI.InventoryPane;

public class InventorySimpleController implements SimpleController {
    private Inventory inventory;
    private InventoryPane inventoryPane;

    public InventorySimpleController(Inventory inventory, InventoryPane inventoryPane) {
        this.inventory = inventory;
        this.inventoryPane = inventoryPane;
        this.redraw();
    }

    @Override
    public void redraw() {
        this.inventoryPane.set_blocks(inventory.get_blocks_iterator());
    }
}