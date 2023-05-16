package UI;

import data.blocks.NullBlock;
import data.blocks.SandBlock;
import data.blocks.interfaces.SmeltableBlock;
import data.blocks.solids.GlassBlock;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FurnacePane extends VBox {
    private Furnace furnace;
    private BlockPane input;
    private BlockPane output;
    private Text title;
    private Text arrow = new Text("-->");
    private HBox hBox;
    public FurnacePane() {
        super();
        this.initialise();
    }

    private void initialise() {
        this.furnace = new Furnace();

        this.setAlignment(Pos.CENTER);
        this.title = new Text("Furnace");

        this.hBox = new HBox(3);
        this.hBox.setAlignment(Pos.CENTER);

        this.input = new BlockPane(new NullBlock());
        this.output = new BlockPane(new NullBlock());

        this.getChildren().add(this.title);
        this.populate_hbox();
        this.getChildren().add(this.hBox);
    }

    public void set_io(BlockPane input, BlockPane output) {
        this.hBox.getChildren().clear();
        this.input = input;
        this.output = output;
        this.populate_hbox();
    }

    private void populate_hbox() {
        this.hBox.getChildren().addAll(this.input, this.arrow, this.output);
    }
}
