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
                this.map[mapCoordinates.getRow()][mapCoordinates.getColumn()] = new AirBlock();
//                try {
//                    this.insert_at_cords(mapCoordinates, new AirBlock());
//                } catch (WrongCoordinatesException e) {
//                    throw new RuntimeException(e);
//                }
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

    private boolean is_in_bounds(MapCoordinates mapCoordinates) {
        int row = mapCoordinates.getRow();
        int column = mapCoordinates.getColumn();

        return (0 <= row && row < this.getRows() && (0 <= column && column < this.getColumns()));
    }

    private void swap(MapCoordinates upper_block) {
        if (upper_block.getRow() == this.getRows() - 1) {
            return;
        }

        Block tmp = map[upper_block.getRow()+1][upper_block.getColumn()];
        map[upper_block.getRow()+1][upper_block.getColumn()] = map[upper_block.getRow()][upper_block.getColumn()];
        map[upper_block.getRow()][upper_block.getColumn()] = tmp;
    }

    public void insert_at_cords(MapCoordinates mapCoordinates, Block block) throws WrongCoordinatesException {
        if (!is_in_bounds(mapCoordinates)) {
            throw new WrongCoordinatesException();
        }

        int row = mapCoordinates.getRow();
        int column = mapCoordinates.getColumn();

        this.map[row][column] = block;

        this.process_column_gravity(mapCoordinates.getColumn());
    }

    public void process_map_gravity() throws WrongCoordinatesException {
        for (int column = 0; column < MapCoordinates.DIMENSION_COLUMNS; column++) {
            this.process_column_gravity(column);
        }
    }

    public void process_column_gravity(int column) throws WrongCoordinatesException {
        for (int row = MapCoordinates.DIMENSION_ROWS - 1; row >= 0; row--) {
            MapCoordinates block_coords = new MapCoordinates(row, column);
            this.process_block_gravity(block_coords);
        }
    }

    private void process_block_gravity(MapCoordinates falling_block_coords) throws WrongCoordinatesException {
//        System.out.println("Falling block is at " + falling_block_coords);

        // this.getBlockAt checks if coords are valid, otherwise it throws a new WrongCoordinateException
        Block falling_block = this.getBlockAt(falling_block_coords);

        // if the block doesn't fall when there is nothing underneath it
        if (!falling_block.is_falls_with_gravity()) {
//            System.out.println("process_block_gravity finished: falling_block.is_falls_with_gravity is false");
            return;
        }

        // if the block is on the last row
        if (falling_block_coords.getRow() == MapCoordinates.DIMENSION_ROWS - 1) {
//            System.out.println("process_block_gravity finished: falling_block_coords.getRow() == MapCoordinates.DIMENSION_ROWS - 1");
            return;
        }

        for (int row = falling_block_coords.getRow(); row < MapCoordinates.DIMENSION_ROWS - 1; row++) {
            MapCoordinates underneath_block_coords = new MapCoordinates(
                row + 1,
                falling_block_coords.getColumn()
            );
            // destroy the falling block if it is going to land on a torch
            if (destroy_on_torch(underneath_block_coords)) {
//                System.out.println("process_block_gravity finished: destroy_on_torch is true");
                this.insert_at_cords(falling_block_coords, new AirBlock());
                return;
            }
            // check if the block can still fall
            if (!this.getBlockAt(underneath_block_coords).is_fall_through()) {
//                System.out.println("process_block_gravity finished: underneath block is not fall through. coords: " + underneath_block_coords);
                return;
            }
//            System.out.println("Swapping blocks. upper: " + falling_block_coords);
            this.swap(falling_block_coords);
            falling_block_coords = underneath_block_coords;
        }
    }

    // destroy_on_torch return true if any falling block falls on a torch
    private boolean destroy_on_torch(MapCoordinates check_if_this_is_torch) {
        Block lower_block = this.getBlockAt(new MapCoordinates(check_if_this_is_torch.getRow() + 1, check_if_this_is_torch.getColumn()));
        return lower_block instanceof TorchBlock;
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
