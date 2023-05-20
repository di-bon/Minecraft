package UI.Terminal.TextControllers;

import UI.JavaFX.Controllers.SimpleController;
import UI.logic.MainView;
import UI.Terminal.TextPrinters.FurnacePrinter;
import UI.Terminal.TextPrinters.InventoryPrinter;
import UI.Terminal.TextPrinters.MainPrinter;
import UI.Terminal.TextPrinters.MapPrinter;
import utils.BlockErrorException;
import utils.MapCoordinates;
import utils.WrongCoordinatesException;

import java.util.ArrayList;
import java.util.List;

public class MainViewController extends AbstractTextController {
    private MainView mainView;
    private MapController mapController;
    private FurnaceController furnaceController;
    private InventoryController inventoryController;
    private List<AbstractTextController> controllerList;

    public MainViewController(MainView mainView) {
        this.mainView = mainView;

        MapPrinter mapPrinter = new MapPrinter(
                this.mainView.get_map().getMap()
        );
        FurnacePrinter furnacePrinter = new FurnacePrinter(
                this.mainView.get_furnace().view_input_block(),
                this.mainView.get_furnace().view_output_block()
        );
        InventoryPrinter inventoryPrinter = new InventoryPrinter(
                this.mainView.get_inventory().get_blocks(),
                this.mainView.get_inventory().get_comparator()
        );
        this.textPrinter = new MainPrinter(
                mapPrinter,
                furnacePrinter,
                inventoryPrinter
        );

        this.controllerList = new ArrayList<>();

        this.mapController = new MapController(mainView.get_map(), mapPrinter);
        this.controllerList.add(this.mapController);

        this.furnaceController = new FurnaceController(mainView.get_furnace(), furnacePrinter);
        this.controllerList.add(this.furnaceController);

        this.inventoryController = new InventoryController(mainView.get_inventory(), inventoryPrinter);
        this.controllerList.add(this.inventoryController);

    }

    @Override
    public void updatePrinter() {
        for (AbstractTextController controller : controllerList) {
            controller.updatePrinter();
        }
    }

    public void smelt() {
        this.mainView.smelt();
        this.furnaceController.update_and_display();
        this.inventoryController.update_and_display();
    }

    public void move_into_inventory_from_furnace() {
        this.mainView.move_into_inventory_from_furnace();
        this.furnaceController.update_and_display();
        this.inventoryController.update_and_display();
    }

    public void move_into_furnace_from_inventory(int index) throws BlockErrorException {
        this.mainView.move_into_furnace_from_inventory(index);
        this.inventoryController.update_and_display();
        this.furnaceController.update_and_display();
    }

    public void pick_up_block(MapCoordinates mapCoordinates) throws BlockErrorException, WrongCoordinatesException {
        this.mainView.pick_up_block(mapCoordinates);
        this.mapController.update_and_display();
        this.inventoryController.update_and_display();
    }

    public void toggle_inventory_comparator() {
        this.mainView.toggle_inventory_comparator();
        this.inventoryController.update_and_display();
    }
}
