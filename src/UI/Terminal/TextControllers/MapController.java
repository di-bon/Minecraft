package UI.Terminal.TextControllers;

import data.logic.Map;
import UI.Terminal.TextPrinters.MapPrinter;

public class MapController extends AbstractTextController {
    private Map map;

    public MapController(Map map, MapPrinter mapPrinter) {
        super();
        this.map = map;
        this.textPrinter = mapPrinter;
    }

    @Override
    public void updatePrinter() {
        MapPrinter mapPrinter = (MapPrinter) this.textPrinter;
        mapPrinter.update(this.map.getMap());
    }
}
