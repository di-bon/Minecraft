package data;

public class Map {
    private final int rows;
    private final int columns;
    private Block[][] map;

    public Map(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        map = new Block[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = new Block();
            }
        }
    }

    public void display_on_out() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(map[i][j].get_content() + " ");
            }
            System.out.print("\n");
        }
    }

    public void change_cell(int x, int y, char newContent) {
        assert_coordinates(x, y);
        map[x][y].set_content(newContent);
    }

    // TODO: add 'throw new CustomException()' to print stack trace
    private void assert_coordinates(int x, int y) {
        assert(is_in_bounds(x, rows) && is_in_bounds(y, columns));
//        if (!is_in_bounds(x, rows) || !is_in_bounds(y, columns)) {
//            System.err.println("Error: coordinates out of bounds.");
//            System.err.println("x: " + x + ", y: " + y + ", rows: " + rows + ", columns: " + columns);
//            // TODO: throw new CustomException();
//            try {
//                throw new Exception();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            System.exit(1);
//        }
    }

    private void swap(int x, int y) {
        assert_coordinates(x, y);
        if (y == rows - 1) {
            return;
        }
        Block tmp = map[x+1][y];
        map[x+1][y] = map[x][y];
        map[x][y] = tmp;
    }

    public void insert_at_cords(int x, int y, Block block) {
        assert_coordinates(x, y);
        map[x][y] = block;
        if (map[x][y].get_falls_with_gravity()) {
            //insert_rec(x, y);
            insert_iter(x, y);
        }
    }

    private void insert_rec(int x, int y) {
        assert_coordinates(x, y);
        /*
        Useless?

        if (!map[x][y].get_falls_with_gravity()) {
            return;
        }
        */
        if (x == rows - 1) {
            return;
        }
        if (!map[x+1][y].get_fall_through()) {
            return;
        }
        swap(x, y);
        // insert_rec(x+1, y);
    }

    private void insert_iter(int x, int y) {
        assert_coordinates(x, y);
        if (x == rows - 1) {
            return;
        }
        if (!map[x+1][y].get_fall_through()) {
            return;
        }
        for (int i = x; i < rows - 1; i++) {
            if (!map[i + 1][y].get_fall_through()) {
                break;
            }
            swap(i, y);
        }
    }

    private boolean is_in_bounds(int value, int max) {
        return value >= 0 && value < max;
    }

    public int get_rows() {
        return this.rows;
    }

    public int get_columns() {
        return this.columns;
    }
}
