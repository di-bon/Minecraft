package Main;

import data.Block;
import data.Map;

import java.util.Scanner;

public class Main {
    private static Map map;
    private static final int ROWS = 10;
    private static final int COLUMNS = 20;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        create_default_map(ROWS, COLUMNS);
        while (true) {
            map.display_on_out();
            insert_block();
        }
    }

    private static void create_default_map(int rows, int columns) {
        map = new Map(rows,columns);
    }

    // TODO: add 'throw new CustomException()' to print stack trace
    private static void check_user_input(int x, int y, int x_min, int x_max, int y_min, int y_max) {
        if (x < x_min || x >= x_max || y < y_min || y >= y_max) {
            System.err.println("Error: invalid input for map cords.");
            System.err.println("x: " + x + ", y: " + y
                    + ", x_min: " + x_min + ", x_max: " + x_max
                    + ", y_min: " + y_min + ", y_max: " + y_max);
            // TODO: throw new CustomException();
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.exit(1);
        }
    }

    private static void insert_block() {
        System.out.print("Enter row: ");
        int row = scanner.nextInt();

        System.out.print("Enter column: ");
        int col = scanner.nextInt();

        System.out.print("Enter type: ");
        char type = scanner.next().charAt(0);

        boolean falls_with_gravity;
        boolean fall_through;

        // TODO: replace with something better
        switch (type) {
            case 'S' -> {
                falls_with_gravity = true;
                fall_through = false;
            }
            case 'A' -> {
                falls_with_gravity = false;
                fall_through = true;
            }
            // case 'T':
            default -> {
                falls_with_gravity = false;
                fall_through = false;
            }
        }

        System.out.println("Inserting at: (" + row + ", " + col + "), type: " + type);


        // check_user_input(row, col, 0, map.get_rows(), 0, map.get_columns());

        map.insert_at_cords(row, col, new Block(type, falls_with_gravity, fall_through));
    }
}