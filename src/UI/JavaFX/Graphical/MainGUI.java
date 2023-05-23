package UI.JavaFX.Graphical;

import UI.JavaFX.Controllers.MainSimpleController;
import data.BlockFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import utils.MapCoordinates;
import utils.WrongCoordinatesException;

import java.util.Random;

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

    public ButtonListPane get_button_list_pane() {
        return buttonListPane;
    }

    private void initialise(MainSimpleController mainSimpleController) {
        this.set_layout(mainSimpleController);
        this.add_event_listeners();
    }

    private void set_layout(MainSimpleController mainSimpleController) {
        this.mainSimpleController = mainSimpleController;
        this.mapPane = new MapPane(this.mainSimpleController);
        this.furnacePane = new FurnacePane(this.mainSimpleController);
        this.inventoryPane = new InventoryPane(this.mainSimpleController);
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

    private void add_event_listeners() {
        this.setOnMouseClicked((MouseEvent mouseEvent) -> {
            this.requestFocus();
        });
        this.setOnKeyPressed((KeyEvent keyEvent) -> {
            System.out.println(keyEvent);
            switch (keyEvent.getText()) {
                case "S" -> this.mainSimpleController.toggle_inventory_comparator();
                case "N" -> this.insert_random_blocks(1);
                case "R" -> this.insert_random_blocks(10);
            }
        });
    }

    private void insert_random_blocks(int num_of_blocks) {
        BlockFactory blockFactory = new BlockFactory();
        for (int i = 0; i < num_of_blocks; i++) {
            Random random = new Random();
            MapCoordinates mapCoordinates = new MapCoordinates(
                    random.nextInt(MapCoordinates.DIMENSION_ROWS),
                    random.nextInt(MapCoordinates.DIMENSION_COLUMNS)
            );
            try {
                this.mainSimpleController.insert_block_at_no_redraw(blockFactory.random_block(), mapCoordinates);
            } catch (WrongCoordinatesException wce) {
                wce.printStackTrace();
            }
        }
        try {
            this.mainSimpleController.process_map_gravity();
        } catch (WrongCoordinatesException wce) {
            wce.printStackTrace();
        }
        this.mainSimpleController.redraw();
    }
}
