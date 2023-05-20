package UI.Terminal.TextPrinters;

import java.util.ArrayList;
import java.util.List;

public class MainPrinter implements TextPrinter {
    private MapPrinter mapPrinter;
    private FurnacePrinter furnacePrinter;
    private InventoryPrinter inventoryPrinter;
    private List<TextPrinter> printerList;

    public MainPrinter(MapPrinter mapPrinter, FurnacePrinter furnacePrinter, InventoryPrinter inventoryPrinter) {
        this.printerList = new ArrayList<>();

        this.mapPrinter = mapPrinter;
        this.printerList.add(this.mapPrinter);

        this.furnacePrinter = furnacePrinter;
        this.printerList.add(this.furnacePrinter);

        this.inventoryPrinter = inventoryPrinter;
        this.printerList.add(this.inventoryPrinter);
    }

    @Override
    public void display_on_out() {
        for (TextPrinter printer : printerList) {
            printer.display_on_out();
        }
    }

    public void update(MapPrinter mapPrinter, FurnacePrinter furnacePrinter, InventoryPrinter inventoryPrinter) {
        this.printerList.clear();

        this.mapPrinter = mapPrinter;
        this.printerList.add(this.mapPrinter);

        this.furnacePrinter = furnacePrinter;
        this.printerList.add(this.furnacePrinter);

        this.inventoryPrinter = inventoryPrinter;
        this.printerList.add(this.inventoryPrinter);
    }
}
