package Main;

import data.blocks.AirBlock;
import UI.Map;
import utils.MapCoordinates;

import java.util.Scanner;

public class Main {
    private static Map map;
    private static final int ROWS = 5;
    private static final int COLUMNS = 10;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        map.addSea(3);
//        while (true) {
//            MAP.DISPLAY_WITH_NUMBERS();
//            insert_block();
//        }
    }

    private static void insert_block() {
        System.out.print("Enter row: ");
        int row = scanner.nextInt();

        System.out.print("Enter column: ");
        int col = scanner.nextInt();

        map.insert_at_cords(new MapCoordinates(row, col), new AirBlock());
    }
}