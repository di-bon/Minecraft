package UI.controllers;

import UI.MainGUI;
import UI.MainView;
import data.blocks.interfaces.Block;
import utils.BlockErrorException;
import utils.MapCoordinates;
import utils.WrongCoordinatesException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MainSimpleController implements SimpleController {
    private MainView mainView;
    private MainGUI mainGUI;
    private FurnaceSimpleController furnaceSimpleController;
    private InventorySimpleController inventorySimpleController;
    private MapSimpleController mapSimpleController;
    private List<SimpleController> controllers_list;

    public MainSimpleController(MainView mainView) {
        this.mainView = mainView;
        this.mainGUI = new MainGUI(this);
        this.controllers_list = new ArrayList<>();

        this.mapSimpleController = new MapSimpleController(
                this.mainView.get_map(),
                this.mainGUI.get_map_pane()
        );
        this.controllers_list.add(this.mapSimpleController);

        this.furnaceSimpleController = new FurnaceSimpleController(
                this.mainView.get_furnace(),
                this.mainGUI.get_furnace_pane()
        );
        this.controllers_list.add(this.furnaceSimpleController);

        this.inventorySimpleController = new InventorySimpleController(
                this.mainView.get_inventory(),
                this.mainGUI.get_inventory_pane()
        );
        this.controllers_list.add(this.inventorySimpleController);

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

    public void pick_up_block(MapCoordinates mapCoordinates) throws BlockErrorException, WrongCoordinatesException {
        this.mainView.pick_up_block(mapCoordinates);
        this.mapSimpleController.redraw();
        this.inventorySimpleController.redraw();
    }

    public void toggle_inventory_comparator() {
        this.mainView.toggle_inventory_comparator();
        this.inventorySimpleController.redraw();
    }

    public MainGUI get_main_gui() {
        return this.mainGUI;
    }
}
