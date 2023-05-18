package UI.TextControllers;

import Main.Main;
import UI.Furnace;
import UI.Inventory;
import UI.MainView;
import UI.Map;
import UI.TextPrinters.FurnacePrinter;
import UI.TextPrinters.InventoryPrinter;
import UI.TextPrinters.MainPrinter;
import UI.TextPrinters.MapPrinter;

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

        this.controllerList = new ArrayList<>();

        this.mapController = new MapController(mainView.get_map());
        this.controllerList.add(this.mapController);

        this.furnaceController = new FurnaceController(mainView.get_furnace());
        this.controllerList.add(this.furnaceController);

        this.inventoryController = new InventoryController(mainView.get_inventory());
        this.controllerList.add(this.inventoryController);

        this.textPrinter = new MainPrinter(
                new MapPrinter(
                        this.mainView.get_map().getMap()
                ),
                new FurnacePrinter(
                        this.mainView.get_furnace().view_input_block(),
                        this.mainView.get_furnace().view_output_block()
                ),
                new InventoryPrinter(
                        this.mainView.get_inventory().get_blocks(),
                        this.mainView.get_inventory().get_comparator()
                )
        );
    }

    @Override
    public void updatePrinter() {
        for (AbstractTextController controller : controllerList) {
            controller.updatePrinter();
        }
    }

    public static void main(String[] args) {
        MainView mainView = new MainView(true);
        MainViewController mainViewController = new MainViewController(mainView);
        mainViewController.update_and_display();
    }
}
