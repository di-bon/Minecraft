package Main;

import UI.Terminal.TextControllers.MainViewController;
import UI.Terminal.TextControllers.MapController;
import UI.Terminal.TextPrinters.MapPrinter;
import data.blocks.AirBlock;
import data.blocks.SandBlock;
import data.blocks.solids.DirtBlock;
import data.logic.MainView;
import data.logic.Map;
import utils.BlockErrorException;
import utils.MapCoordinates;
import utils.WrongCoordinatesException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // gravity test
//        try {
//            Map map = new Map(false);
//            MapPrinter mapPrinter = new MapPrinter(map.getMap());
//            MapController mapController = new MapController(map, mapPrinter);
//
//            mapController.update_and_display();
//            map.insert_at_cords(new MapCoordinates(0, 2), new SandBlock());
//            mapController.update_and_display();
//
//            map.insert_at_cords(new MapCoordinates(4, 0), new DirtBlock());
//            map.insert_at_cords(new MapCoordinates(0, 0), new SandBlock());
//            mapController.update_and_display();
//
//            map.insert_at_cords(new MapCoordinates(4, 0), new AirBlock());
//            mapController.update_and_display();
//
//            map.process_column_gravity(0);
//            mapController.update_and_display();
//
//        } catch (WrongCoordinatesException e) {
//            throw new RuntimeException(e);
//        }


//        Scanner scanner = new Scanner(System.in);
//        int input;
//        int hardness_to_remove = 50;
//
//        MainViewController mainViewController = new MainViewController(new MainView(true));
//        mainViewController.update_and_display();
//
//        do {
//            System.out.println("Insert 1 to pick up a block");
//            System.out.println("Insert 2 to move a block into the furnace");
//            System.out.println("Insert 3 to smelt");
//            System.out.println("Insert 4 to move a block from the furnace to the inventory");
//            System.out.println("Insert 5 to toggle inventory sorting");
//            System.out.println("Insert 0 to exit");
//            input = scanner.nextInt();
//            switch (input) {
//                case 1: {
//                    try {
//                        System.out.print("Row: ");
//                        int row = scanner.nextInt();
//                        System.out.print("Column: ");
//                        int column = scanner.nextInt();
//
//                        MapCoordinates mapCoordinates = new MapCoordinates(row, column);
//                        mainViewController.pick_up_block(mapCoordinates, hardness_to_remove);
//                    } catch (NumberFormatException nfe) {
//                        nfe.printStackTrace();
//                    } catch (BlockErrorException bee) {
//                        bee.printStackTrace();
//                    } catch (WrongCoordinatesException wce) {
//                        wce.printStackTrace();
//                    }
//                    break;
//                }
//                case 2: {
//                    System.out.print("Inventory index: ");
//                    try {
//                        int index = scanner.nextInt();
//                        mainViewController.move_into_furnace_from_inventory(index);
//                    } catch (NumberFormatException nfe) {
//                        nfe.printStackTrace();
//                    } catch (BlockErrorException bee) {
//                        bee.printStackTrace();
//                    } catch (IndexOutOfBoundsException iooe) {
//                        iooe.printStackTrace();
//                    }
//                    break;
//                }
//                case 3: {
//                    mainViewController.smelt();
//                    break;
//                }
//                case 4: {
//                    mainViewController.move_into_inventory_from_furnace();
//                    break;
//                }
//                case 5: {
//                    mainViewController.toggle_inventory_comparator();
//                    break;
//                }
//                default: {
//                }
//            }
//
//            if (input != 0) {
//                mainViewController.update_and_display();
//            }
//        } while(input != 0);
    }
}