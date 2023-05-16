package UI.controllers;

import UI.BlockPane;
import UI.Furnace;
import UI.FurnacePane;

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
