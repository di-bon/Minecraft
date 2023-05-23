package UI.JavaFX.Graphical;

import UI.JavaFX.Controllers.MainSimpleController;
import data.blocks.NullBlock;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FurnacePane extends VBox {
    private BlockPane input;
    private BlockPane output;
    private Text title;
    private final Text arrow = new Text("-->");
    private HBox hBox;
    private final MainSimpleController mainSimpleController;
    public FurnacePane(MainSimpleController mainSimpleController) {
        super();
        this.mainSimpleController = mainSimpleController;
        this.initialise();
    }

    private void initialise() {
        this.setAlignment(Pos.CENTER);
        this.title = new Text("Furnace");

        this.hBox = new HBox(3);
        this.hBox.setAlignment(Pos.CENTER);

        this.input = new BlockPaneInternal(new NullBlock(), FurnaceBlockType.INPUT, this.mainSimpleController);
        this.output = new BlockPaneInternal(new NullBlock(), FurnaceBlockType.OUTPUT, this.mainSimpleController);

        this.getChildren().add(this.title);
        this.populate_hbox();
        this.getChildren().add(this.hBox);
    }

    public void set_io(BlockPaneInternal input, BlockPaneInternal output) {
        this.hBox.getChildren().clear();
        this.input = input;
        this.output = output;
        this.populate_hbox();
    }

    private void populate_hbox() {
        this.hBox.getChildren().addAll(this.input, this.arrow, this.output);
    }
}
