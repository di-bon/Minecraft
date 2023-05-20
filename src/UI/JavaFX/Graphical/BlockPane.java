package UI.JavaFX.Graphical;

import data.blocks.AirBlock;
import data.blocks.SandBlock;
import data.blocks.WaterBlock;
import data.blocks.interfaces.Block;
import data.blocks.solids.*;

// JavaFX
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

// Every BlockPane is a 50px x 50px square
public class BlockPane extends StackPane {
    public static final int DIM_SQUARE = 50;
    private static final String FONT_NAME = "Verdana";
    private static final int FONT_SIZE = 6;
    private static final FontWeight FONT_WEIGHT = FontWeight.BOLD;
    private static final Color BLOCK_BORDER = Color.BLACK;
    private static final Color FONT_FILL = Color.BLACK;
    private static final Color FONT_BORDER = Color.BLACK;
    private Block block;
    private Rectangle rectangle;
    private Text text;

    public BlockPane(Block block) {
        super();
        this.block = block;
        this.initialise();
    }

    private void initialise() {
        this.getChildren().clear();
        rectangle = new Rectangle(BlockPane.DIM_SQUARE, BlockPane.DIM_SQUARE);
        rectangle.setFill(this.get_block_color(this.block));
        text = new Text(block.get_blockname());
        text.setFont(new Font(BlockPane.FONT_SIZE));
        this.text.maxWidth(BlockPane.DIM_SQUARE);
        this.getChildren().addAll(rectangle, text);
    }

    private static Color get_block_color(Block block) {
        if (block instanceof DirtBlock) {
            return Color.BROWN;
        }
        if (block instanceof GlassBlock) {
            return new Color(0.8,0.8,0.8, 0.25);
        }
        if (block instanceof IronSwordBlock) {
            return Color.GREY;
        }
        if (block instanceof RawIronBlock) {
            return Color.DARKGRAY;
        }
        if (block instanceof TorchBlock) {
            return Color.PURPLE;
        }
        if (block instanceof AirBlock) {
            return Color.LIGHTBLUE;
        }
        if (block instanceof SandBlock) {
            return Color.SANDYBROWN;
        }
        if (block instanceof WaterBlock) {
            return Color.CYAN;
        }
        return Color.WHITE;
    }

    public void change_block(Block block) {
        this.block = block;
        this.initialise();
    }

    public Block get_block() {
        return this.block;
    }
}
