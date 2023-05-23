package data.logic;

import data.BlockFactory;
import data.blocks.AirBlock;
import data.blocks.NullBlock;
import data.blocks.solids.TorchBlock;
import utils.BlockErrorException;
import data.blocks.WaterBlock;
import data.blocks.interfaces.Block;
import utils.WrongCoordinatesException;
import utils.MapCoordinates;

import java.util.Random;

public class Map {
    private final int rows = MapCoordinates.DIMENSION_ROWS;
    private final int columns = MapCoordinates.DIMENSION_COLUMNS;
    private final Block[][] map;

    public Map(boolean random) {
        this.map = new Block[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                MapCoordinates mapCoordinates = new MapCoordinates(row, column);
                try {
                    this.insert_at_cords(mapCoordinates, new AirBlock());
                } catch (WrongCoordinatesException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        this.addSea(3);
        if (random) {
            this.randomise_map();
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Block[][] getMap() {
        return map;
    }

    public Block getBlockAt(MapCoordinates mapCoordinates) {
        if (!mapCoordinates.is_in_bound()) {
            return new NullBlock();
        }
        int row = mapCoordinates.getRow();
        int column = mapCoordinates.getColumn();

        return this.map[row][column];
    }

    private boolean is_pickable(MapCoordinates mapCoordinates) {
        return this.getBlockAt(mapCoordinates).is_pickable();
    }

    public Block gimme_pickable(MapCoordinates mapCoordinates) throws BlockErrorException, WrongCoordinatesException {
        if (this.is_pickable(mapCoordinates)) {
            Block block = this.getBlockAt(mapCoordinates);
            this.insert_at_cords(mapCoordinates, new AirBlock());
            return block;
        } else {
            throw new BlockErrorException();
        }
    }

    public void mine_block(MapCoordinates mapCoordinates, int hardness_to_remove) {
        this.getBlockAt(mapCoordinates).mine_block(hardness_to_remove);
    }

    // TODO: update process_gravity logic
    public void process_gravity(MapCoordinates coords_start) throws WrongCoordinatesException {
        int currentRow = coords_start.getRow();

        while (currentRow >= 1) {
            MapCoordinates upper_block_coords = new MapCoordinates(currentRow, coords_start.getColumn());
            process_block_gravity(upper_block_coords);
            currentRow--;
        }
    }

    private boolean is_in_bounds(MapCoordinates mapCoordinates) {
        int row = mapCoordinates.getRow();
        int column = mapCoordinates.getColumn();

        return (0 <= row && row < this.getRows() && (0 <= column && column < this.getColumns()));
    }

    private void swap(MapCoordinates mapCoordinates) {
        if (mapCoordinates.getRow() == this.getRows() - 1) {
            return;
        }

        Block tmp = map[mapCoordinates.getRow()+1][mapCoordinates.getColumn()];
        map[mapCoordinates.getRow()+1][mapCoordinates.getColumn()] = map[mapCoordinates.getRow()][mapCoordinates.getColumn()];
        map[mapCoordinates.getRow()][mapCoordinates.getColumn()] = tmp;
    }

    public void insert_at_cords(MapCoordinates mapCoordinates, Block block) throws WrongCoordinatesException {
        if (!is_in_bounds(mapCoordinates)) {
            throw new WrongCoordinatesException();
        }

        int row = mapCoordinates.getRow();
        int column = mapCoordinates.getColumn();

        this.map[row][column] = block;
        if (this.map[row][column].is_falls_with_gravity()) {
            process_block_gravity(mapCoordinates);
        }
    }

    // TODO: update proscess_gravity_block logic
    private void process_block_gravity(MapCoordinates mapCoordinates) throws WrongCoordinatesException {
        int row = mapCoordinates.getRow();
        int column = mapCoordinates.getColumn();

        if (row == this.getRows() - 1) return;

        for (int row_tmp = row; row_tmp < this.getRows() - 1; row_tmp++) {
            if (destroy_on_torch(new MapCoordinates(row + 1, column))) {
                System.out.println("Destroying a block");
                this.insert_at_cords(mapCoordinates, new AirBlock());
                return;
            }

            if (!map[row_tmp + 1][column].is_fall_through()) {
                break;
            }

            swap(new MapCoordinates(row_tmp, column));
        }
    }

    // destroy_on_torch return true if any falling block falls on a torch
    private boolean destroy_on_torch(MapCoordinates falling_block) {
        Block lower_block = this.getBlockAt(new MapCoordinates(falling_block.getRow() + 1, falling_block.getColumn()));
        if (lower_block instanceof TorchBlock) {
            return true;
        }
        return false;
    }

    private void addRowsOfWater() {
        for (int column = 0; column < this.getColumns(); column++) {
            try {
                this.insert_at_cords(new MapCoordinates(0, column), new WaterBlock());
            } catch (WrongCoordinatesException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addRiver() {
        this.addRowsOfWater();
    }

    public void addSea(int depth) {
        for (int i = 0; i < depth; i++) {
            this.addRowsOfWater();
        }
    }

    public void randomise_map() {
        int random_blocks_to_add = 10;
        BlockFactory blockFactory = new BlockFactory();

        Random rand = new Random();
        for (int i = 0 ; i < random_blocks_to_add; i++){
            Block b = blockFactory.random_block();
            int rows = rand.nextInt(MapCoordinates.DIMENSION_ROWS);
            int columns = rand.nextInt(MapCoordinates.DIMENSION_COLUMNS);
            try {
                this.insert_at_cords(new MapCoordinates(rows, columns), b);
            } catch (WrongCoordinatesException wce) {
                wce.printStackTrace();
            }
        }
    }
}
