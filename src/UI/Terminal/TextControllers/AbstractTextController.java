package UI.Terminal.TextControllers;

import UI.Terminal.TextPrinters.TextPrinter;

public abstract class AbstractTextController {
    protected TextPrinter textPrinter;

    public AbstractTextController() {

    }

    public abstract void updatePrinter();

    public void update_and_display() {
        this.updatePrinter();
        this.textPrinter.display_on_out();
    }
}
