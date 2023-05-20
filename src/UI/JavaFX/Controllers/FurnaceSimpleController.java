package UI.JavaFX.Controllers;

import UI.JavaFX.Graphical.BlockPane;
import UI.logic.Furnace;
import UI.JavaFX.Graphical.FurnacePane;

public class FurnaceSimpleController implements SimpleController {
    private Furnace furnace;
    private FurnacePane furnacePane;

    public FurnaceSimpleController(Furnace furnace, FurnacePane furnacePane) {
        this.furnace = furnace;
        this.furnacePane = furnacePane;
        this.redraw();
    }

    @Override
    public void redraw() {
        this.furnacePane.set_io(
                new BlockPane(this.furnace.view_input_block()),
                new BlockPane(this.furnace.view_output_block())
        );
    }
}
