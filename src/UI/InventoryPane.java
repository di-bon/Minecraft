package UI;

import data.blocks.NullBlock;
import data.blocks.interfaces.Block;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class InventoryPane extends VBox {
    private Text text;
    private HBox hBox;
    private Inventory inventory;

    public InventoryPane() {
        super();
        this.initialise();
    }

    private void initialise() {
        this.setAlignment(Pos.CENTER);
        this.text = new Text("Inventory");
        this.hBox = new HBox();
        this.hBox.setAlignment(Pos.CENTER);
        this.inventory = new Inventory();
//        this.copy_first_items_from_inventory();
        this.getChildren().addAll(this.text, this.hBox);
    }

//    private void copy_first_items_from_inventory() {
//        this.hBox.getChildren().clear();
//        List<Block> blocks_to_show = this.inventory.get_up_to_first_9_block();
//        if (blocks_to_show.size() < 9) {
//            while (blocks_to_show.size() != 9) {
//                blocks_to_show.add(new NullBlock());
//            }
//        }
//        for (int i = 0; i < blocks_to_show.size(); i++) {
//            this.hBox.getChildren().add(new BlockPane(blocks_to_show.get(i)));
//        }
//    }

    public void add_block(Block block) {
        this.inventory.add_block(block);
        this.add_block_pane(new BlockPane((block)));
    }

//    public Block get_block_at(int index) throws IndexOutOfBoundsException {
//        return this.inventory.get_block_from_inventory(index);
//    }

//    public void remove_block_from_inventory(int index) throws IndexOutOfBoundsException {
//        this.inventory.remove_block_from_inventory(index);
//    }

    private void add_block_pane(BlockPane blockPane) {
        this.hBox.getChildren().add(blockPane);
    }
//    private void add_block_panes(List<BlockPane> blockPaneList) {
//         this.hBox.getChildren().addAll(blockPaneList);
//    }
}
