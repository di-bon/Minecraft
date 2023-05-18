package UI.TextControllers;

import UI.Furnace;
import UI.TextPrinters.FurnacePrinter;

public class FurnaceController extends AbstractTextController {
    private Furnace furnace;

    public FurnaceController(Furnace furnace) {
        this.furnace = furnace;
        this.textPrinter = new FurnacePrinter(
                this.furnace.view_input_block(),
                this.furnace.view_output_block()
        );
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
