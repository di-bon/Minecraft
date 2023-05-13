package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import utils.MapCoordinates;

public class MainGUI extends BorderPane {
    private MapPane mapPane;
    private ButtonListPane buttonListPane;
    private FurnacePane furnacePane;

    private InventoryPane inventoryPane;

    public MainGUI() {
        super();
        this.initialise();
    }

    private void initialise() {
        this.mapPane = new MapPane();
        this.mapPane.randomise_map();
        this.furnacePane = new FurnacePane();
        this.inventoryPane = new InventoryPane();
        this.buttonListPane = new ButtonListPane(this.mapPane, this.inventoryPane, this.furnacePane);


        this.buttonListPane.setPrefWidth(150);
        this.setLeft(this.buttonListPane);

        // debug
//        ((Pane)this.getLeft()).setBackground(new Background(new BackgroundFill(new Color(1, 0, 0, 1), new CornerRadii(0), new Insets(0))));

        this.mapPane.setPrefWidth(MapCoordinates.DIMENSION_COLUMNS * BlockPane.DIM_SQUARE);
        this.mapPane.setPrefHeight(MapCoordinates.DIMENSION_ROWS * BlockPane.DIM_SQUARE);
        this.setCenter(this.mapPane);

        // debug
//        ((Pane)this.getCenter()).setBackground(new Background(new BackgroundFill(new Color(0, 1, 0, 1), new CornerRadii(0), new Insets(0))));

        this.furnacePane.setPrefWidth(150);
        this.setRight(this.furnacePane);

        // debug
//        ((Pane)this.getRight()).setBackground(new Background(new BackgroundFill(new Color(1, 0, 0, 1), new CornerRadii(0), new Insets(0))));

        this.inventoryPane.setPrefHeight(100);
        this.setBottom(this.inventoryPane);
    }
}
