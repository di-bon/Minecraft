package Main;

import UI.*;
import UI.controllers.FurnaceSimpleController;
import UI.controllers.InventorySimpleController;
import UI.controllers.MainSimpleController;
import UI.controllers.MapSimpleController;
import data.BlockFactory;
import data.blocks.NullBlock;
import data.blocks.SandBlock;
import data.blocks.interfaces.Block;
import data.blocks.solids.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import utils.MapCoordinates;
import utils.WrongCoordinatesException;

import java.util.Random;

public class MainFX extends Application {
    int index = 0;
    @Override
    public void start(Stage primaryStage) {
//        BlockPane blockPane = new BlockPane(new DirtBlock());
//        Button button = new Button();
//        button.setText("Change block");
//        button.addEventHandler(ActionEvent.ANY, (ActionEvent event) -> {
//            this.index++;
//            this.index %= 6;
//            change_block(blockPane, this.index);
//        });
//
//        Pane vertical_container = new VBox();
//        ((VBox)vertical_container).setSpacing(10);
//        vertical_container.getChildren().addAll(blockPane, button);
//        ((VBox) vertical_container).setAlignment(Pos.CENTER);

//        List<Block> blockList = new ArrayList<>();
//        blockList.add(new DirtBlock());
//        blockList.add(new SandBlock());
//        blockList.add(new DirtBlock());
//        blockList.add(new RawIronBlock());

//        List<BlockPane> blockPaneList = new ArrayList<>();
//        for (Block block : blockList) {
//            blockPaneList.add(new BlockPane(block));
//        }

//        InventoryPane inventoryPane = new InventoryPane();
//        inventoryPane.add_block_panes(blockPaneList);

        StackPane root = new StackPane();

//        root.getChildren().add(vertical_container);
//        root.getChildren().add(inventoryPane);


//        MapPane mapPane = new MapPane();
//        mapPane.randomise_map();
//        root.getChildren().add(mapPane);

//        FurnacePane furnacePane = new FurnacePane();
//        root.getChildren().add(furnacePane);

//        ButtonListPane buttonListPane = new ButtonListPane();
//        root.getChildren().add(buttonListPane);

//        MainGUI mainGUI = new MainGUI();
//        root.getChildren().add(mainGUI);

        // FurnaceSimpleController test
//        Furnace furnace = new Furnace();
//        FurnacePane furnacePane = new FurnacePane();
//        FurnaceSimpleController furnaceSimpleController = new FurnaceSimpleController(furnace, furnacePane);
//
//        Button button = new Button("Redraw");
//        button.addEventHandler(ActionEvent.ANY, (ActionEvent event) -> {
//            furnace.setInput(new SandBlock());
//            furnaceSimpleController.redraw();
//        });
//        VBox vBox = new VBox(2);
//        vBox.getChildren().addAll(furnacePane, button);
//        root.getChildren().add(vBox);

        // InventorySimpleController test
//        Inventory inventory = new Inventory();
//        InventoryPane inventoryPane = new InventoryPane();
//        InventorySimpleController inventorySimpleController = new InventorySimpleController(inventory, inventoryPane);
//        Button button = new Button("Redraw");
//        button.addEventHandler(ActionEvent.ANY, (ActionEvent event) -> {
//            inventory.add_block(new DirtBlock());
//            inventorySimpleController.redraw();
//        });
//        VBox vBox = new VBox(2);
//        vBox.getChildren().addAll(inventoryPane, button);
//        root.getChildren().add(vBox);

        // MapSimpleController test
//        Map map = new Map(false);
//        MapPane mapPane = new MapPane();
//        MapSimpleController mapSimpleController = new MapSimpleController(map, mapPane);
//        Button button = new Button("Redraw");
//        button.addEventHandler(ActionEvent.ANY, (ActionEvent event) -> {
//            try {
//                Random random = new Random();
//                int row = random.nextInt(MapCoordinates.DIMENSION_ROWS);
//                int column = random.nextInt(MapCoordinates.DIMENSION_COLUMNS);
//                BlockFactory blockFactory = new BlockFactory();
//                Block random_block = blockFactory.random_block();
//                MapCoordinates mapCoordinates = new MapCoordinates(row, column);
//                System.out.println("Inserting " + random_block.get_blockname() + " at " + mapCoordinates);
//                map.insert_at_cords(mapCoordinates, random_block);
//                mapSimpleController.redraw();
//            } catch (WrongCoordinatesException wce) {
//                wce.printStackTrace();
//            }
//        });
//        VBox vBox = new VBox(2);
//        vBox.getChildren().addAll(mapPane, button);
//        root.getChildren().add(vBox);

//        BlockPane blockPane = mapPane.get_block_at_coords(new MapCoordinates(8, 2));
//        blockPane.change_block(new DirtBlock());

        MainSimpleController mainSimpleController = new MainSimpleController(new MainView(true));
        MainGUI mainGUI = mainSimpleController.get_main_gui();
        root.getChildren().add(mainGUI);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Minecraft");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    private void change_block(BlockPane blockPane, int index) {
        Block block;
        switch (index) {
            case 0 -> {
                block = new DirtBlock();
            }
            case 1 -> {
                block = new GlassBlock();
            }
            case 2 -> {
                block = new RawIronBlock();
            }
            case 3 -> {
                block = new TorchBlock();
            }
            case 4 -> {
                block = new SandBlock();
            }
            default -> {
                block = new NullBlock();
            }
        }
        blockPane.change_block(block);
    }
}