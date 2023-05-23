package UI.JavaFX.Graphical;

import UI.JavaFX.Controllers.MainSimpleController;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import utils.BlockErrorException;
import utils.MapCoordinates;
import utils.WrongCoordinatesException;

public class BlockPaneExternalListener implements EventHandler<MouseEvent> {
    public static final int DEFAULT_HARDNESS_TO_REMOVE = 50;
    private final MapCoordinates coordinates;
    private final MainSimpleController mainSimpleController;

    public BlockPaneExternalListener(MapCoordinates coordinates, MainSimpleController mainSimpleController) {
        this.coordinates = coordinates;
        this.mainSimpleController = mainSimpleController;
    }

    @Override
    public void handle(MouseEvent event) {
        try {
            this.mainSimpleController.mine_block(this.coordinates, BlockPaneExternalListener.DEFAULT_HARDNESS_TO_REMOVE);
        } catch (BlockErrorException bee) {
            new ErrorAlert(bee.getClass().getSimpleName(), "This block can't be picked up");
        }
        catch (WrongCoordinatesException wce) {
            new ErrorAlert(wce.getClass().getSimpleName(), "Invalid coordinates");
        }
    }
}
