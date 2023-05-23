package UI.JavaFX.Graphical;

import UI.JavaFX.Controllers.MainSimpleController;
import data.blocks.interfaces.Block;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class BlockPaneInternal extends BlockPane {
    public BlockPaneInternal(Block block, FurnaceBlockType furnaceBlockType, MainSimpleController mainSimpleController) {
        super(block);
        this.setOnMouseClicked(new BlockPaneInternalListener(furnaceBlockType, mainSimpleController));
    }

    private record BlockPaneInternalListener(
            FurnaceBlockType furnaceBlockType,
            MainSimpleController mainSimpleController
    ) implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            switch (furnaceBlockType) {
                case INPUT -> this.mainSimpleController.move_into_inventory_from_furnace();
                case OUTPUT -> this.mainSimpleController.smelt();
            }
        }
    }
}
