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
    private Text arrow;
    private HBox hBox;
    public FurnacePane() {
        super();
        this.initialise();
    }

    private void initialise() {
        this.furnace = new Furnace();
//        this.set_input_block(new NullBlock());

        this.setAlignment(Pos.CENTER);
        this.title = new Text("Furnace");

        this.hBox = new HBox(3);
        this.hBox.setAlignment(Pos.CENTER);

//        this.input = new BlockPane(this.furnace.view_input_block());
        this.input = new BlockPane(new SandBlock());
        this.arrow = new Text("-->");
        this.output = new BlockPane(new GlassBlock());
//        this.output = new BlockPane(this.furnace.view_output_block());

        this.getChildren().add(this.title);
        this.hBox.getChildren().addAll(this.input, this.arrow, this.output);
        this.getChildren().add(this.hBox);
    }

//    public void set_input_block(SmeltableBlock smeltableBlock) {
//        this.furnace.setInput(smeltableBlock);
//    }
}
