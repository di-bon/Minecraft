package data.blocks.interfaces;

public interface Block {
    char display();
    char getContent();
    boolean is_falls_with_gravity();
    boolean is_fall_through();
}
