package utils;

public class MapCoordinates {
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

    @Override
    public String toString() {
        return "MapCoordinates{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
