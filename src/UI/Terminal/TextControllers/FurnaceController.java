package UI.Terminal.TextControllers;

import data.logic.Furnace;
import UI.Terminal.TextPrinters.FurnacePrinter;

public class FurnaceController extends AbstractTextController {
    private Furnace furnace;

    public FurnaceController(Furnace furnace, FurnacePrinter furnacePrinter) {
        this.furnace = furnace;
        this.textPrinter = furnacePrinter;
    }

    @Override
    public void updatePrinter() {
        FurnacePrinter furnacePrinter = (FurnacePrinter) this.textPrinter;
        furnacePrinter.update(
                this.furnace.view_input_block(),
                this.furnace.view_output_block()
        );
    }
}
