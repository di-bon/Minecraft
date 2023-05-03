package UI;

import data.blocks.AirBlock;
import data.blocks.solids.RawIronBlock;
import data.blocks.SandBlock;
import data.blocks.WaterBlock;
import data.blocks.interfaces.Block;
import data.blocks.interfaces.SmeltableBlock;
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

    public Block getBlockAt(MapCoordinates mapCoordinates) {
        int i = mapCoordinates.getRow();
        int j = mapCoordinates.getColumn();

        return this.map[i][j];
    }

    public boolean isSmeltableBlock(MapCoordinates mapCoordinates) {
        return getBlockAt(mapCoordinates) instanceof SmeltableBlock;
    }

    public void display_on_out() {
        System.out.println("\n");

        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                System.out.print(map[i][j].display() + " ");
            }
            System.out.print("\n");
        }
    }

    public void display_with_numbers() {

        for (int i = 0; i < this.getRows(); i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < this.getColumns(); j++) {
                System.out.print(map[i][j].display() + "  ");
            }
            System.out.print("\n");
        }
    }

    private boolean is_in_bounds(MapCoordinates mapCoordinates) {
        int i = mapCoordinates.getRow();
        int j = mapCoordinates.getColumn();

        return (0 <= i && i < this.getRows()) && (0 <= j && j < this.getColumns());
    }

    private void swap(int x, int y) {

        if (x == this.getRows() - 1) {
            System.out.println("Il blocco non può più cadere");
            return;
        }

        Block tmp = map[x+1][y];
        map[x+1][y] = map[x][y];
        map[x][y] = tmp;
    }

    public void insert_at_cords(MapCoordinates mapCoordinates, Block block) {
        assert is_in_bounds(mapCoordinates) : "Insertion out of bounds";

        int i = mapCoordinates.getRow();
        int j = mapCoordinates.getColumn();

        this.map[i][j] = block;
        if (this.map[i][j].is_falls_with_gravity()) {
            insert_iterative(mapCoordinates);
        }
    }

    private void insert_iterative(MapCoordinates mapCoordinates) {
        int i = mapCoordinates.getRow();
        int j = mapCoordinates.getColumn();

        // Let's avoid ArrayIndex...Error
        if (i == this.getRows() - 1) return;
        if (!map[i+1][j].is_fall_through()) return;

        for (int row = i; row < this.getRows() - 1; row++) {
            if (!map[row + 1][j].is_fall_through()) {
                break;
            }

            swap(row, j);
        }
    }

    private void addRowsOfWater() {
        for (int col = 0; col < this.getColumns(); col++) {
            this.insert_at_cords(new MapCoordinates(0, col), new WaterBlock());
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
            int row = rand.nextInt(this.getRows());
            int col = rand.nextInt(this.getColumns());
            this.insert_at_cords(new MapCoordinates(row, col), b);
        }

        for (int i = 0; i < number_of_tries; i++) {
            Block b = new RawIronBlock();
            int row = rand.nextInt(this.getRows());
            int col = rand.nextInt(this.getColumns());
            this.insert_at_cords(new MapCoordinates(row, col), b);
        }
    }

    public static void main(String[] args) {
        Map map = new Map(10, 10);
        map.addSea(3);
        map.insert_at_cords(new MapCoordinates(0, 5), new SandBlock());
        map.display_with_numbers();
    }
}
