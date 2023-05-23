package UI.JavaFX.Graphical;

import UI.JavaFX.Controllers.MainSimpleController;
import data.blocks.interfaces.Block;
import utils.MapCoordinates;

public class BlockPaneExternal extends BlockPane {
    public BlockPaneExternal(Block block, MapCoordinates coordinates, MainSimpleController mainSimpleController) {
        super(block);
        this.setOnMouseClicked(
                new BlockPaneExternalListener(
                        coordinates,
                        mainSimpleController
                )
        );
    }
}

