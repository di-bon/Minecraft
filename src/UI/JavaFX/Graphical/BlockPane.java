package UI.JavaFX.Graphical;

import data.blocks.AirBlock;
import data.blocks.NullBlock;
import data.blocks.SandBlock;
import data.blocks.WaterBlock;
import data.blocks.interfaces.Block;
import data.blocks.solids.*;

// JavaFX
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
//    private Text text;

    public BlockPane(Block block) {
        super();
        this.block = block;
        this.initialise();
    }

    private void initialise() {
        this.getChildren().clear();

        ImageView image = new ImageView(
                new Image(
                    BlockPane.get_block_texture_name(this.block),
                    BlockPane.DIM_SQUARE,
                    BlockPane.DIM_SQUARE,
                    false,
                    false
                )
        );

//      debug
//        this.text = new Text(block.get_blockname());
//        this.text.setFont(new Font(8));
//        this.getChildren().add(this.text);

        this.getChildren().add(image);
    }

    private static String get_block_texture_name(Block block) {
        String texture = "UI/JavaFX/Graphical/block_textures/";
        if (block instanceof DirtBlock) {
            texture += "dirt_block";
        }
        else if (block instanceof GlassBlock) {
            texture += "glass_block";
        }
        else if (block instanceof IronSwordBlock) {
            texture += "iron_ingot";
        }
        else if (block instanceof RawIronBlock) {
            texture += "raw_iron_block";
        }
        else if (block instanceof TorchBlock) {
            texture += "torch";
        }
        else if (block instanceof AirBlock) {
            texture += "air_block";
        }
        else if (block instanceof SandBlock) {
            texture += "sand_block";
        }
        else if (block instanceof WaterBlock) {
            texture += "water";
        }
        else if (block instanceof NullBlock) {
            texture += "null_block";
        }
        else {
            texture += "missing_block";
        }
        texture += "_texture.png";

        return texture;
    }

    public void change_block(Block block) {
        this.block = block;
        this.initialise();
    }

    public Block get_block() {
        return this.block;
    }
}
