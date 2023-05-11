package UI;

import data.BlockFactory;
import data.blocks.AirBlock;
import data.blocks.NullBlock;
import data.blocks.solids.DirtBlock;
import data.blocks.solids.TorchBlock;
import utils.BlockErrorException;
import data.blocks.solids.RawIronBlock;
import data.blocks.SandBlock;
import data.blocks.WaterBlock;
import data.blocks.interfaces.Block;
import utils.WrongCoordinatesException;
import utils.MapCoordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    private final int rows = MapCoordinates.DIMENSION_ROWS;
    private final int columns = MapCoordinates.DIMENSION_COLUMNS;
    private Block[][] map;

    public Map(boolean random) {
        this.map = new Block[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                MapCoordinates mapCoordinates = new MapCoordinates(row, column);
                this.insert_at_cords(mapCoordinates, new AirBlock());
//                this.map[row][column] = new AirBlock();
            }
        }
        this.addSea(3);
        if (random) {
            this.randomize_map();
        }
    }

    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
    public Block getBlockAt(MapCoordinates mapCoordinates) {
        if (!mapCoordinates.is_in_bound()) {
            return new NullBlock();
        }
        int row = mapCoordinates.getRow();
        int column = mapCoordinates.getColumn();

        return this.map[row][column];
    }

    public boolean isSmeltableBlock(MapCoordinates mapCoordinates) throws BlockErrorException {
        return this.getBlockAt(mapCoordinates).is_smeltable();
    }

    private boolean is_pickable(MapCoordinates mapCoordinates) {
        return this.getBlockAt(mapCoordinates).is_pickable();
    }

    public Block gimme_pickable(MapCoordinates mapCoordinates) throws BlockErrorException {
        if (this.is_pickable(mapCoordinates)) {
            Block block = this.getBlockAt(mapCoordinates);
            this.insert_at_cords(mapCoordinates, new AirBlock());
            return block;
        }
        throw new BlockErrorException();
    }

    public void display_on_out() {
        System.out.println("\n");

        for (int row = 0; row < this.getRows(); row++) {
            for (int column = 0; column < this.getColumns(); column++) {
                System.out.print(map[row][column].display() + " ");
            }
            System.out.print("\n");
        }
    }

    public void display_with_numbers() {

        for (int row = 0; row < this.getRows(); row++) {
            System.out.print(row + "  ");
            for (int column = 0; column < this.getColumns(); column++) {
                System.out.print(map[row][column].display() + "  ");
            }
            System.out.print("\n");
        }
    }

    public void process_gravity(MapCoordinates coords_start) throws WrongCoordinatesException {
//        System.out.println("Applico gravità a " + coords_start);

        int currentRow = coords_start.getRow();

        while (currentRow >= 1) {
            currentRow--;
            MapCoordinates upper_block_coords = new MapCoordinates(currentRow, coords_start.getColumn());
            process_block_gravity(upper_block_coords);
        }
    }

    private boolean is_in_bounds(MapCoordinates mapCoordinates) {
        int row = mapCoordinates.getRow();
        int column = mapCoordinates.getColumn();

        return (0 <= row && row < this.getRows() && (0 <= column && column < this.getColumns()));
    }

    private void swap(MapCoordinates mapCoordinates) {

        if (mapCoordinates.getRow() == this.getRows() - 1) {
            System.out.println("Il blocco non può più cadere");
            return;
        }

        Block tmp = map[mapCoordinates.getRow()+1][mapCoordinates.getColumn()];
        map[mapCoordinates.getRow()+1][mapCoordinates.getColumn()] = map[mapCoordinates.getRow()][mapCoordinates.getColumn()];
        map[mapCoordinates.getRow()][mapCoordinates.getColumn()] = tmp;
    }

    public void insert_at_cords(MapCoordinates mapCoordinates, Block block) {
        assert is_in_bounds(mapCoordinates) : "Insertion out of bounds";

        int row = mapCoordinates.getRow();
        int column = mapCoordinates.getColumn();

        this.map[row][column] = block;
        if (this.map[row][column].is_falls_with_gravity()) {
            process_block_gravity(mapCoordinates);
        }
    }

    private void process_block_gravity(MapCoordinates mapCoordinates) {
        int row = mapCoordinates.getRow();
        int column = mapCoordinates.getColumn();

        // Let's avoid ArrayIndex...Error
        if (row == this.getRows() - 1) return;
        if (!map[row][column].is_falls_with_gravity()) return;

        for (int row_tmp = row; row_tmp < this.getRows() - 1; row_tmp++) {
            if (destroy_on_torch(mapCoordinates)) {
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
            this.insert_at_cords(new MapCoordinates(0, column), new WaterBlock());
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

    public void randomize_map() {
        int random_blocks_to_add = 10;
        BlockFactory blockFactory = new BlockFactory();

        Random rand = new Random();
        for (int i = 0 ; i < random_blocks_to_add; i++){
            Block b = blockFactory.random_block();
            int rows = rand.nextInt(MapCoordinates.DIMENSION_ROWS);
            int columns = rand.nextInt(MapCoordinates.DIMENSION_COLUMNS);
            this.insert_at_cords(new MapCoordinates(rows, columns), b);
        }
    }

    public static void main(String[] args) {
        // Columns of SandBlock
        Map map = new Map(true);
        for (int row = 0; row < map.getRows(); row++) {
            map.insert_at_cords(new MapCoordinates(0, 0), new SandBlock());
        }

        // SandBlock on top of DirtBlock
        map.insert_at_cords(new MapCoordinates(3, 2), new DirtBlock());
        map.insert_at_cords(new MapCoordinates(2, 2), new SandBlock());
        map.insert_at_cords(new MapCoordinates(1, 2), new SandBlock());

        // SandBlock falling on a TorchBlock
        map.insert_at_cords(new MapCoordinates(4, 4), new TorchBlock());
        map.insert_at_cords(new MapCoordinates(0, 4), new SandBlock());

        map.display_with_numbers();

        // Test 1
        MapCoordinates coords_of_removed_sand_block = new MapCoordinates(map.getRows()-1, 0);
        map.insert_at_cords(coords_of_removed_sand_block, new AirBlock());
        try {
            map.process_gravity(coords_of_removed_sand_block);
        } catch (WrongCoordinatesException wce) {
            wce.printStackTrace();
        }

        // Test 2
        MapCoordinates coords_of_removed_dirt_block = new MapCoordinates(3, 2);
        map.insert_at_cords(coords_of_removed_dirt_block, new AirBlock());

        try {
            map.process_gravity(coords_of_removed_dirt_block);
        } catch (WrongCoordinatesException wce) {
            wce.printStackTrace();
        }

        // Test 3
        MapCoordinates coords_of_torch_block = new MapCoordinates(4, 4);
        try {
            map.process_gravity(coords_of_torch_block);
        } catch (WrongCoordinatesException wce) {
            throw new RuntimeException(wce);
        }

        System.out.println("\n\n\n");

        map.display_with_numbers();
    }
}
