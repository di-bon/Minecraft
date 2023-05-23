package UI.JavaFX.Graphical;

import UI.JavaFX.Controllers.MainSimpleController;
import data.blocks.interfaces.Block;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import utils.BlockErrorException;

public class BlockPaneAnonymous extends BlockPane {
    public BlockPaneAnonymous(Block block, MainSimpleController mainSimpleController, int index) {
        super(block);
        this.setOnMouseClicked((MouseEvent event) -> {
            try {
                mainSimpleController.move_into_furnace_from_inventory(index);
            } catch (BlockErrorException bee) {
                new ErrorAlert(bee.getClass().getSimpleName(), "This block is not smeltable");
            }
        });
    }
}
