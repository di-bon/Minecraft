package UI;

import data.blocks.AirBlock;
import data.blocks.solids.DirtBlock;
import data.blocks.solids.TorchBlock;
import data.exceptions.BlockErrorException;
import data.blocks.solids.RawIronBlock;
import data.blocks.SandBlock;
import data.blocks.WaterBlock;
import data.blocks.interfaces.Block;
import data.exceptions.WrongCoordinatesException;
import utils.MapCoordinates;

import java.util.Random;

public class Map {
    private final int rows;
    private final int columns;
    private Block[][] map;

    public Map(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.map = new Block[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.map[i][j] = new AirBlock();
            }
        }
    }

    public Map() {
        this(5, 5);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Block getBlockAt(MapCoordinates mapCoordinates) throws WrongCoordinatesException {
        int row = mapCoordinates.getRow();
        int column = mapCoordinates.getColumn();

        try {
            return this.map[row][column];
        }
        catch (IndexOutOfBoundsException ioobe) {
            ioobe.printStackTrace();
            throw new WrongCoordinatesException();
        }
    }

    public boolean isSmeltableBlock(MapCoordinates mapCoordinates) throws BlockErrorException, WrongCoordinatesException {
        return this.getBlockAt(mapCoordinates).is_smeltable();
    }

    private boolean is_pickable(MapCoordinates mapCoordinates) throws WrongCoordinatesException {
        return this.getBlockAt(mapCoordinates).is_pickable();
    }

    public Block gimme_pickable(MapCoordinates mapCoordinates) throws BlockErrorException, WrongCoordinatesException {
        if (this.is_pickable(mapCoordinates)) {
            return this.getBlockAt(mapCoordinates);
        }
        throw new BlockErrorException();
//        return this.is_pickable(mapCoordinates) ? this.getBlockAt(mapCoordinates) : new NullBlock();
//        return this.getBlockAt(mapCoordinates);
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
        try {
            Block lower_block = this.getBlockAt(new MapCoordinates(falling_block.getRow() + 1, falling_block.getColumn()));
            if (lower_block instanceof TorchBlock) {
                return true;
            }
        } catch (WrongCoordinatesException wce) {
            wce.printStackTrace();
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

    public void randomize_map(int number_of_tries) {
        Random rand = new Random();
        for (int i = 0 ; i < number_of_tries; i++){
            Block b = new SandBlock();
            int rows = rand.nextInt(this.getRows());
            int columns = rand.nextInt(this.getColumns());
            this.insert_at_cords(new MapCoordinates(rows, columns), b);
        }

        for (int i = 0; i < number_of_tries; i++) {
            Block b = new RawIronBlock();
            int row = rand.nextInt(this.getRows());
            int col = rand.nextInt(this.getColumns());
            this.insert_at_cords(new MapCoordinates(row, col), b);
        }

        for (int i = 0; i < number_of_tries; i++) {
            Block b = new TorchBlock();
            int row = rand.nextInt(this.getRows());
            int col = rand.nextInt(this.getColumns());
            this.insert_at_cords(new MapCoordinates(row, col), b);
        }
    }

    public static void main(String[] args) {
        // Columns of SandBlock
        Map map = new Map(5, 5);
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
