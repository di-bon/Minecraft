package UI.JavaFX.Graphical;

import UI.JavaFX.Controllers.MainSimpleController;
import data.blocks.interfaces.Block;
import data.blocks.solids.DirtBlock;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Iterator;

public class InventoryPane extends VBox {
    private Text text;
    private HBox hBox;
    private final MainSimpleController mainSimpleController;

    public InventoryPane(MainSimpleController mainSimpleController) {
        super();
        this.mainSimpleController = mainSimpleController;
        this.initialise();
    }

    private void initialise() {
        this.setAlignment(Pos.CENTER);
        this.text = new Text("Inventory");
        this.hBox = new HBox();
        this.hBox.prefHeight(BlockPane.DIM_SQUARE);
        this.hBox.setAlignment(Pos.CENTER);
        this.getChildren().addAll(this.text, this.hBox);
    }

    public void set_blocks(Iterator<Block> blocks_iterator) {
        this.hBox.getChildren().clear();
        int index = 0;
        while (blocks_iterator.hasNext()) {
            this.hBox.getChildren().add(
                new BlockPaneAnonymous(blocks_iterator.next(),
                        this.mainSimpleController,
                        index++
                )
            );
        }
    }
}
