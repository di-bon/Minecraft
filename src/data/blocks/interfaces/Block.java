package data.blocks.interfaces;

public interface Block extends InventoryBlock {
    String get_blockname();
    char display();
    boolean is_falls_with_gravity();
    boolean is_fall_through();
    boolean is_pickable();
    boolean is_smeltable();
    boolean is_null_block();
}