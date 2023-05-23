package UI.JavaFX.Graphical;

import UI.JavaFX.Controllers.MainSimpleController;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import utils.MapCoordinates;
import utils.WrongCoordinatesException;

public class MapPane extends GridPane {
    private final int NUMBER_OF_ROWS = MapCoordinates.DIMENSION_ROWS;
    private final int NUMBER_OF_COLUMNS = MapCoordinates.DIMENSION_COLUMNS;
    private final MainSimpleController mainSimpleController;

    public MapPane(MainSimpleController mainSimpleController) {
        super();
        this.mainSimpleController = mainSimpleController;
        this.initialise();
    }

    private void initialise() {
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
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
    private void set_cell_aux(MapCoordinates block_coords, BlockPaneExternal block_to_set) throws WrongCoordinatesException {
        if (!block_coords.is_in_bound()) {
            throw new WrongCoordinatesException();
        }
        this.add(block_to_set, block_coords.getColumn(), block_coords.getRow());
    }

    public void set_cell(MapCoordinates mapCoordinates, BlockPaneExternal block_to_set) throws WrongCoordinatesException {
        BlockPaneExternal blockPane = (BlockPaneExternal) this.get_block_pane_at_coords(mapCoordinates);
        if (blockPane != null) {
            blockPane.getChildren().clear();
            blockPane.change_block(block_to_set.get_block());
        } else {
            blockPane = new BlockPaneExternal(block_to_set.get_block(), mapCoordinates, this.mainSimpleController);
            this.set_cell_aux(mapCoordinates, blockPane);
        }
    }
}
