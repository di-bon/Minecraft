package data.logic;

import data.blocks.NullBlock;
import utils.BlockErrorException;
import data.blocks.interfaces.Block;
import data.blocks.interfaces.SmeltableBlock;
import utils.MapCoordinates;
import utils.WrongCoordinatesException;

public class MainView {
    private final Map map;
    private final Furnace furnace;
    private final Inventory inventory;
    private final float pickaxe_multiplier = 2.0f;

    public MainView(boolean random) {
        map = new Map(random);
        furnace = new Furnace();
        inventory = new Inventory();
    }

    public Map get_map() {
        return this.map;
    }

    public Furnace get_furnace() {
        return furnace;
    }

    public Inventory get_inventory() {
        return inventory;
    }

    public void move_into_furnace_from_inventory(int index) throws BlockErrorException {
        SmeltableBlock smeltableBlock = this.inventory.get_smeltable_item(index);
        this.furnace.setInput(smeltableBlock);
        this.inventory.remove_block_from_inventory(index);
    }

    public void smelt() {
        Block smelted_block = this.furnace.smelt();
        if (!smelted_block.is_null_block()) {
            this.inventory.add_block(smelted_block);
        }
    }

    public void move_into_inventory_from_furnace() {
        Block block = this.furnace.get_input();
        if (block instanceof NullBlock) {
            return;
        }
        this.inventory.add_block(block);
    }

    public void pick_up_block(MapCoordinates mapCoordinates) throws BlockErrorException, WrongCoordinatesException {
        Block block = this.map.gimme_pickable(mapCoordinates);
        block.reset_hardness();
        this.inventory.add_block(block);
    }

    public void mine_block(MapCoordinates mapCoordinates, int hardness_to_remove, boolean is_using_pickaxe) throws BlockErrorException {
        if (is_using_pickaxe) {
            hardness_to_remove *= this.pickaxe_multiplier;
        }
        if (this.map.getBlockAt(mapCoordinates).is_pickable()) {
            this.map.mine_block(mapCoordinates, hardness_to_remove);
        }
    }

    public boolean is_pickable(MapCoordinates mapCoordinates) {
        return this.map.getBlockAt(mapCoordinates).is_pickable();
    }

    public int get_block_current_hardness(MapCoordinates mapCoordinates) {
        return this.map.getBlockAt(mapCoordinates).get_current_hardness();
    }

    public void insert_block_at(Block block, MapCoordinates mapCoordinates) throws WrongCoordinatesException {
        this.map.insert_at_cords(mapCoordinates, block);
    }

    public void toggle_inventory_comparator() {
        this.inventory.toggle_comparator();
    }
}
