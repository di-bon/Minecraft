package UI;

import data.blocks.interfaces.Block;
import data.blocks.interfaces.SmeltableBlock;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.BlockErrorException;
import utils.MapCoordinates;
import utils.WrongCoordinatesException;

public class ButtonListPane extends VBox {

    public ButtonListPane(MapPane mapPane, InventoryPane inventoryPane, FurnacePane furnacePane) {
        super();
        this.initialise(mapPane, inventoryPane, furnacePane);
    }

    private void initialise(MapPane mapPane, InventoryPane inventoryPane, FurnacePane furnacePane) {
        this.setAlignment(Pos.CENTER);

        TextField coords_row = new TextField();
        coords_row.setPrefWidth(50);
        TextField coords_column = new TextField();
        coords_column.setPrefWidth(50);
        Button pick = new Button("Pick");
        pick.setPrefWidth(50);
        pick.addEventHandler(ActionEvent.ANY, (ActionEvent event) -> {
//            try {
//                int row = Integer.parseInt(coords_row.getText());
//                int column = Integer.parseInt(coords_column.getText());
//
//                MapCoordinates mapCoordinates = new MapCoordinates(row, column);
//                Block picked_block = mapPane.pick_block_at(mapCoordinates);
//                inventoryPane.add_block(picked_block);
//
//                coords_row.setText("");
//                coords_column.setText("");
//            } catch (NumberFormatException nfe) {
//                nfe.printStackTrace();
//            } catch (WrongCoordinatesException wce) {
//                wce.printStackTrace();
//            } catch (BlockErrorException bee) {
//                System.out.println("Block is not pickable");
//                bee.printStackTrace();
//            }
        });
        HBox pick_hbox = new HBox(3);
        pick_hbox.getChildren().addAll(coords_row, coords_column, pick);

        TextField inventory_index = new TextField();
        inventory_index.setPrefWidth(50);
        Button move_to_furnace = new Button("Move to furnace");
        move_to_furnace.setPrefWidth(100);
        move_to_furnace.addEventHandler(ActionEvent.ANY, (ActionEvent event) -> {
            // move item to furnace
//            try {
//                int index = Integer.parseInt(inventory_index.getText());
//
//                Block block = inventoryPane.get_block_at(index);
//                if (block.is_smeltable()) {
//                    furnacePane.set_input_block((SmeltableBlock) block);
//                    inventoryPane.remove_block_from_inventory(index);
//                }
//            } catch (NumberFormatException nfe) {
//                nfe.printStackTrace();
//            }
        });
        HBox move_to_furnace_hbox = new HBox(2);
        move_to_furnace_hbox.getChildren().addAll(inventory_index, move_to_furnace);

        Button smelt = new Button("Smelt");
        smelt.setPrefWidth(150);
        smelt.addEventHandler(ActionEvent.ANY, (ActionEvent event) -> {
            // smelt item inside furnace
        });
        HBox smelt_hbox = new HBox(1);
        smelt_hbox.getChildren().add(smelt);

        Button move_back = new Button("Move back");
        move_back.setPrefWidth(150);
        move_back.addEventHandler(ActionEvent.ANY, (ActionEvent event) -> {
            // move furnace input item into inventory
        });
        HBox move_back_hbox = new HBox(1);
        move_back_hbox.getChildren().add(move_back);

        Button toggle_inventory_sorting = new Button("Toggle inventory sorting");
        toggle_inventory_sorting.setPrefWidth(150);
        toggle_inventory_sorting.addEventHandler(ActionEvent.ANY, (ActionEvent event) -> {
            // toggle inventory sorting
        });
        HBox toggle_inventory_sorting_hbox = new HBox(1);
        toggle_inventory_sorting_hbox.getChildren().add(toggle_inventory_sorting);

        this.getChildren().addAll(pick_hbox, move_to_furnace_hbox, smelt_hbox, move_back_hbox, toggle_inventory_sorting_hbox);
    }
}
