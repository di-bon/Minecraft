package UI.JavaFX.Controllers;

import UI.JavaFX.Graphical.BlockPaneExternal;
import UI.JavaFX.Graphical.MapPane;
import data.logic.Map;
import data.blocks.interfaces.Block;
import utils.MapCoordinates;
import utils.WrongCoordinatesException;

public class MapSimpleController implements SimpleController {
    private final Map map;
    private final MapPane mapPane;
    private final MainSimpleController mainSimpleController;

    public MapSimpleController(Map map, MapPane mapPane, MainSimpleController mainSimpleController) {
        this.map = map;
        this.mapPane = mapPane;
        this.mainSimpleController = mainSimpleController;
        this.redraw();
    }

    @Override
    public void redraw() {
        this.mapPane.getChildren().clear();
        for (int row = 0; row < this.map.getRows(); row++) {
            for (int column = 0; column < this.map.getColumns(); column++) {
                try {
                    MapCoordinates mapCoordinates = new MapCoordinates(row, column);
                    Block block_to_set = this.map.getBlockAt(mapCoordinates);
                    this.mapPane.set_cell(mapCoordinates, new BlockPaneExternal(block_to_set, mapCoordinates, this.mainSimpleController));
                } catch (WrongCoordinatesException wce) {
                    wce.printStackTrace();
                }
            }
        }
    }
}
