package UI.TextControllers;

import UI.Map;
import UI.TextPrinters.MapPrinter;
import UI.TextPrinters.TextPrinter;

public class MapController extends AbstractTextController {
    private Map map;

    public MapController(Map map) {
        super();
        this.map = map;
        this.textPrinter = new MapPrinter(this.map.getMap());
    }

    @Override
    public void updatePrinter() {
        MapPrinter mapPrinter = (MapPrinter) this.textPrinter;
        mapPrinter.update(this.map.getMap());
    }
}
