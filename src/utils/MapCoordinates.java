package utils;

public class MapCoordinates {
    public static final int DIMENSION_COLUMNS = 10;
    public static final int DIMENSION_ROWS = 10;
    private final int row;
    private final int column;

    public MapCoordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean is_in_bound() {
        return this.getRow() < DIMENSION_ROWS && this.getColumn() < DIMENSION_COLUMNS;
    }

    @Override
    public String toString() {
        return "MapCoordinates{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
