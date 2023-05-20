package UI.JavaFX.Graphical;

import UI.JavaFX.Controllers.MainSimpleController;
import javafx.scene.layout.*;
import utils.MapCoordinates;

public class MainGUI extends BorderPane {
    private MapPane mapPane;
    private ButtonListPane buttonListPane;
    private FurnacePane furnacePane;
    private InventoryPane inventoryPane;
    private MainSimpleController mainSimpleController;

    public MainGUI(MainSimpleController mainSimpleController) {
        super();
        this.initialise(mainSimpleController);
    }

    public MapPane get_map_pane() {
        return mapPane;
    }

    public FurnacePane get_furnace_pane() {
        return furnacePane;
    }

    public InventoryPane get_inventory_pane() {
        return inventoryPane;
    }

    private void initialise(MainSimpleController mainSimpleController) {
        this.mainSimpleController = mainSimpleController;
        this.mapPane = new MapPane();
        this.furnacePane = new FurnacePane();
        this.inventoryPane = new InventoryPane();
        this.buttonListPane = new ButtonListPane(this.mainSimpleController);

        this.buttonListPane.setPrefWidth(150);
        this.setLeft(this.buttonListPane);

        this.mapPane.setPrefWidth(MapCoordinates.DIMENSION_COLUMNS * BlockPane.DIM_SQUARE);
        this.mapPane.setPrefHeight(MapCoordinates.DIMENSION_ROWS * BlockPane.DIM_SQUARE);
        this.setCenter(this.mapPane);

        this.furnacePane.setPrefWidth(150);
        this.setRight(this.furnacePane);

        this.inventoryPane.setPrefHeight(100);
        this.setBottom(this.inventoryPane);
    }
}
