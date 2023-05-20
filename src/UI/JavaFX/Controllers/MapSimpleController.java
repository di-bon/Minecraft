package UI.JavaFX.Controllers;

import UI.JavaFX.Graphical.BlockPane;
import UI.JavaFX.Graphical.MapPane;
import UI.logic.Map;
import data.blocks.interfaces.Block;
import utils.MapCoordinates;
import utils.WrongCoordinatesException;

public class MapSimpleController implements SimpleController {
    private Map map;
    private MapPane mapPane;

    public MapSimpleController(Map map, MapPane mapPane) {
        this.map = map;
        this.mapPane = mapPane;
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
                    this.mapPane.set_cell(mapCoordinates, new BlockPane(block_to_set));
                } catch (WrongCoordinatesException wce) {
                    wce.printStackTrace();
                }
            }
        }
    }
}
