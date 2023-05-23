package UI.JavaFX.Controllers;

import UI.JavaFX.Graphical.ButtonListPane;
import UI.JavaFX.Graphical.MainGUI;
import data.logic.MainView;
import data.blocks.interfaces.Block;
import utils.BlockErrorException;
import utils.MapCoordinates;
import utils.WrongCoordinatesException;

import java.util.ArrayList;
import java.util.List;

public class MainSimpleController implements SimpleController {
    private final MainView mainView;
    private final MainGUI mainGUI;
    private final FurnaceSimpleController furnaceSimpleController;
    private final InventorySimpleController inventorySimpleController;
    private final MapSimpleController mapSimpleController;
    private final List<SimpleController> controllers_list;
    private final ButtonListPane buttonListPane;

    public MainSimpleController(MainView mainView) {
        this.mainView = mainView;
        this.mainGUI = new MainGUI(this);
        this.buttonListPane = mainGUI.get_button_list_pane();
        this.controllers_list = new ArrayList<>();

        this.mapSimpleController = new MapSimpleController(
                this.mainView.get_map(),
                this.mainGUI.get_map_pane(),
                this
        );

        this.furnaceSimpleController = new FurnaceSimpleController(
                this.mainView.get_furnace(),
                this.mainGUI.get_furnace_pane(),
                this
        );

        this.inventorySimpleController = new InventorySimpleController(
                this.mainView.get_inventory(),
                this.mainGUI.get_inventory_pane()
        );

        this.controllers_list.addAll(
                List.of(
                    this.mapSimpleController,
                    this.furnaceSimpleController,
                    this.inventorySimpleController
                )
        );

        this.redraw();
    }

    @Override
    public void redraw() {
        for (SimpleController controller : this.controllers_list) {
            controller.redraw();
        }
    }

    public void smelt() {
        this.mainView.smelt();
        this.furnaceSimpleController.redraw();
        this.inventorySimpleController.redraw();
    }

    public void move_into_inventory_from_furnace() {
        this.mainView.move_into_inventory_from_furnace();
        this.furnaceSimpleController.redraw();
        this.inventorySimpleController.redraw();
    }

    public void move_into_furnace_from_inventory(int index) throws BlockErrorException {
        this.mainView.move_into_furnace_from_inventory(index);
        this.inventorySimpleController.redraw();
        this.furnaceSimpleController.redraw();
    }

    private void pick_up_block(MapCoordinates mapCoordinates) throws BlockErrorException, WrongCoordinatesException {
        if (this.mainView.get_block_current_hardness(mapCoordinates) <= 0) {
            this.mainView.pick_up_block(mapCoordinates);
            this.mapSimpleController.redraw();
            this.inventorySimpleController.redraw();
        }
    }

    public void mine_block(MapCoordinates mapCoordinates, int hardness_to_remove) throws BlockErrorException, WrongCoordinatesException {
        if (this.mainView.is_pickable(mapCoordinates)) {
            boolean is_using_pickaxe = this.buttonListPane.get_pickaxe_checkbox_value();
            this.mainView.mine_block(mapCoordinates, hardness_to_remove, is_using_pickaxe);
            if (this.mainView.get_block_current_hardness(mapCoordinates) <= 0) {
                this.mainView.pick_up_block(mapCoordinates);
                this.inventorySimpleController.redraw();
            }
            this.mapSimpleController.redraw();
        } else {
            throw new BlockErrorException();
        }
    }

    public int get_block_current_hardness(MapCoordinates mapCoordinates) {
        return this.mainView.get_block_current_hardness(mapCoordinates);
    }

    public void toggle_inventory_comparator() {
        this.mainView.toggle_inventory_comparator();
        this.inventorySimpleController.redraw();
    }

    public void insert_block_at(Block block, MapCoordinates mapCoordinates) throws WrongCoordinatesException {
        this.mainView.insert_block_at(block, mapCoordinates);
        this.mapSimpleController.redraw();
    }

    public void insert_block_at_no_redraw(Block block, MapCoordinates mapCoordinates) throws WrongCoordinatesException {
        this.mainView.insert_block_at(block, mapCoordinates);
    }

    public MainGUI get_main_gui() {
        return this.mainGUI;
    }
}
