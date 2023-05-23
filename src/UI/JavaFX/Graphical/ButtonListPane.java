package UI.JavaFX.Graphical;

import UI.JavaFX.Controllers.MainSimpleController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utils.BlockErrorException;
import utils.MapCoordinates;
import utils.WrongCoordinatesException;

import java.util.List;

public class ButtonListPane extends VBox {
    private MainSimpleController mainSimpleController;
    private CheckBox pickaxe_checkbox;
    public ButtonListPane(MainSimpleController mainSimpleController) {
        super();
        this.initialise(mainSimpleController);
    }

    private void initialise(MainSimpleController mainSimpleController) {
        this.mainSimpleController = mainSimpleController;
        this.setAlignment(Pos.CENTER);

        HBox pick_hbox = this.create_mining_buttons();
        HBox move_to_furnace_hbox = this.create_furnace_move_buttons();
        HBox smelt_hbox = this.create_smelt_button();
        HBox move_back_hbox = this.create_move_back_button();
        HBox toggle_inventory_sorting_hbox = this.create_toggle_inventory_sorting_button();
        HBox pickaxe_hbox = this.create_pickaxe_checkbox();

        this.getChildren().addAll(
                pick_hbox,
                move_to_furnace_hbox,
                smelt_hbox,
                move_back_hbox,
                toggle_inventory_sorting_hbox,
                pickaxe_hbox
        );
    }

    public boolean get_pickaxe_checkbox_value() {
        return this.pickaxe_checkbox.isSelected();
    }

    private HBox create_pickaxe_checkbox() {
        pickaxe_checkbox = new CheckBox();
        Text text = new Text("Use pickaxe");

        HBox pickaxe_checkbox_hbox = new HBox(2);
        pickaxe_checkbox_hbox.getChildren().addAll(List.of(pickaxe_checkbox, text));
        return pickaxe_checkbox_hbox;
    }

    private HBox create_toggle_inventory_sorting_button() {
        Button toggle_inventory_sorting = new Button("Toggle inventory sorting");
        toggle_inventory_sorting.setPrefWidth(150);
        toggle_inventory_sorting.addEventHandler(ActionEvent.ANY, (ActionEvent event) -> {
            // toggle inventory sorting
            this.mainSimpleController.toggle_inventory_comparator();
        });
        HBox toggle_inventory_sorting_hbox = new HBox(1);
        toggle_inventory_sorting_hbox.getChildren().add(toggle_inventory_sorting);
        return toggle_inventory_sorting_hbox;
    }

    private HBox create_move_back_button() {
        Button move_back = new Button("Move back");
        move_back.setPrefWidth(150);
        move_back.addEventHandler(ActionEvent.ANY, (ActionEvent event) -> {
            // move furnace input item into inventory
            this.mainSimpleController.move_into_inventory_from_furnace();
        });
        HBox move_back_hbox = new HBox(1);
        move_back_hbox.getChildren().add(move_back);
        return move_back_hbox;
    }

    private HBox create_smelt_button() {
        Button smelt = new Button("Smelt");
        smelt.setPrefWidth(150);
        smelt.addEventHandler(ActionEvent.ANY, (ActionEvent event) -> {
            // smelt item inside furnace
            this.mainSimpleController.smelt();
        });
        HBox smelt_hbox = new HBox(1);
        smelt_hbox.getChildren().add(smelt);
        return smelt_hbox;
    }

    private HBox create_furnace_move_buttons() {
        TextField inventory_index = new TextField();
        inventory_index.setPrefWidth(50);
        Button move_to_furnace = new Button("Move to furnace");
        move_to_furnace.setPrefWidth(100);
        move_to_furnace.addEventHandler(ActionEvent.ANY, (ActionEvent event) -> {
            // move item to furnace
            try {
                int index = Integer.parseInt(inventory_index.getText());
                this.mainSimpleController.move_into_furnace_from_inventory(index);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            } catch (BlockErrorException bee) {
                bee.printStackTrace();
            }
        });
        HBox move_to_furnace_hbox = new HBox(2);
        move_to_furnace_hbox.getChildren().addAll(inventory_index, move_to_furnace);
        return move_to_furnace_hbox;
    }

    private HBox create_mining_buttons() {
        TextField coords_row = new TextField();
        coords_row.setPrefWidth(50);
        TextField coords_column = new TextField();
        coords_column.setPrefWidth(50);
        Button pick = new Button("Pick");
        pick.setPrefWidth(50);
        pick.addEventHandler(ActionEvent.ANY, (ActionEvent event) -> {
            // pick up block
            try {
                int row = Integer.parseInt(coords_row.getText());
                int column = Integer.parseInt((coords_column.getText()));
                MapCoordinates mapCoordinates = new MapCoordinates(row, column);
                System.out.println("Picking up block at " + mapCoordinates);
                this.mainSimpleController.mine_block(mapCoordinates, BlockPaneExternalListener.DEFAULT_HARDNESS_TO_REMOVE);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            } catch (BlockErrorException bee) {
                bee.printStackTrace();
            } catch (WrongCoordinatesException wce) {
                wce.printStackTrace();
            }
        });
        HBox pick_hbox = new HBox(3);
        pick_hbox.getChildren().addAll(coords_row, coords_column, pick);
        return pick_hbox;
    }
}
