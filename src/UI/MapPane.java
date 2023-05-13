package UI;

import data.blocks.AirBlock;
import data.blocks.interfaces.Block;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import utils.BlockErrorException;
import utils.MapCoordinates;
import utils.WrongCoordinatesException;

public class MapPane extends GridPane {
    private final int NUMBER_OF_ROWS = MapCoordinates.DIMENSION_ROWS;
    private final int NUMBER_OF_COLUMNS = MapCoordinates.DIMENSION_COLUMNS;

    private Map map;
    public MapPane() {
        super();
        this.initialise();
    }

    private void initialise() {
        this.setAlignment(Pos.CENTER);
        for (int column = 0; column < this.NUMBER_OF_COLUMNS; column++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setMinWidth(BlockPane.DIM_SQUARE);
            columnConstraints.setMaxWidth(BlockPane.DIM_SQUARE);
            this.getColumnConstraints().add(columnConstraints);
        }
        for (int row = 0; row < this.NUMBER_OF_ROWS; row++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(BlockPane.DIM_SQUARE);
            rowConstraints.setMaxHeight(BlockPane.DIM_SQUARE);
            this.getRowConstraints().add(rowConstraints);
        }
        this.map = new Map(false);
        this.copy_map();
    }

    private static Node getElementAt(GridPane gp, MapCoordinates mapCoordinates) {
        for (Node x : gp.getChildren()) {
            if ((GridPane.getRowIndex(x) == mapCoordinates.getRow()) && (GridPane.getColumnIndex(x) == mapCoordinates.getColumn())) {
                return x;
            }
        }
        return null;
    }

    public BlockPane get_block_pane_at_coords(MapCoordinates mapCoordinates) throws WrongCoordinatesException {
        return (BlockPane) MapPane.getElementAt(this, mapCoordinates);
    }

    // New name for 'insert_block_pane_at'
    private void set_cell_aux(MapCoordinates block_coords, BlockPane block_to_set) throws WrongCoordinatesException {
        if (!block_coords.is_in_bound()) {
            throw new WrongCoordinatesException();
        }
        this.add(block_to_set, block_coords.getColumn(), block_coords.getRow());
    }

    private void initialise_air() throws WrongCoordinatesException {
        for (int column = 0; column < this.NUMBER_OF_COLUMNS; column++) {
            for (int row = 0; row < this.NUMBER_OF_ROWS; row++) {
                MapCoordinates coords = new MapCoordinates(row, column);
                this.set_cell(coords, new BlockPane(new AirBlock()));
            }
        }
    }

    public void set_cell(MapCoordinates mapCoordinates, BlockPane block_to_set) throws WrongCoordinatesException {
        BlockPane blockPane = this.get_block_pane_at_coords(mapCoordinates);
        if (blockPane != null) {
            blockPane.getChildren().clear();
            blockPane.change_block(block_to_set.get_block());
        } else {
            blockPane = new BlockPane(block_to_set.get_block());
            this.set_cell_aux(mapCoordinates, blockPane);
        }
    }

    public void insert_block_at(MapCoordinates mapCoordinates, Block block) throws WrongCoordinatesException {
        this.map.insert_at_cords(mapCoordinates, block);
//        this.map.process_gravity(mapCoordinates);
        this.copy_map_column(mapCoordinates.getColumn());
    }

    public void copy_map() {
        for (int column = 0; column < this.map.getColumns(); column++) {
            try {
                this.copy_map_column(column);
            } catch (WrongCoordinatesException wce) {
                wce.printStackTrace();
            }
        }
    }

    private void copy_map_column(int column) throws WrongCoordinatesException {
        for (int row = 0; row < this.map.getRows(); row++) {
            MapCoordinates mapCoordinates = new MapCoordinates(row, column);
            Block block = this.map.getBlockAt(mapCoordinates);
            this.set_cell(mapCoordinates, new BlockPane(block));
        }
    }

//    public Block pick_block_at(MapCoordinates mapCoordinates) throws WrongCoordinatesException, BlockErrorException {
//        Block block = this.map.gimme_pickable(mapCoordinates);
//        this.set_cell(mapCoordinates, new BlockPane(new AirBlock()));
//        return block;
//    }

    public void randomise_map() {
        this.map.randomise_map();

        for (int row = 0; row < this.NUMBER_OF_ROWS; row++) {
            for (int column = 0; column < this.NUMBER_OF_COLUMNS; column++) {
                MapCoordinates map_coords = new MapCoordinates(row, column);
                Block block = map.getBlockAt(map_coords);
                try {
                    this.set_cell(map_coords, new BlockPane(block));
                } catch (WrongCoordinatesException wce) {
                    wce.printStackTrace();
                }
            }
        }
    }
}
