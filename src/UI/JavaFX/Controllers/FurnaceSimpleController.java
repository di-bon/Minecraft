package UI.JavaFX.Controllers;

import UI.JavaFX.Graphical.BlockPaneInternal;
import UI.JavaFX.Graphical.FurnaceBlockType;
import data.logic.Furnace;
import UI.JavaFX.Graphical.FurnacePane;

public class FurnaceSimpleController implements SimpleController {
    private final Furnace furnace;
    private final FurnacePane furnacePane;
    private final MainSimpleController mainSimpleController;

    public FurnaceSimpleController(Furnace furnace, FurnacePane furnacePane, MainSimpleController mainSimpleController) {
        this.furnace = furnace;
        this.furnacePane = furnacePane;
        this.mainSimpleController = mainSimpleController;
        this.redraw();
    }

    @Override
    public void redraw() {
        this.furnacePane.set_io(
                new BlockPaneInternal(this.furnace.view_input_block(), FurnaceBlockType.INPUT, this.mainSimpleController),
                new BlockPaneInternal(this.furnace.view_output_block(), FurnaceBlockType.OUTPUT, this.mainSimpleController)
        );
    }
}
