package data;

public class Block {
    private char content;
    private static final char DEFAULT_CONTENT = '.';
    private final boolean falls_with_gravity;
    private static final boolean DEFAULT_FALLS_WITH_GRAVITY = false;
    private final boolean fall_through;
    private static final boolean DEFAULT_FALL_THROUGH = true;

    public Block(char content, boolean falls_with_gravity, boolean fall_through) {
        this.content = content;
        this.falls_with_gravity = falls_with_gravity;
        this.fall_through = fall_through;
    }

    public Block() {
        this(DEFAULT_CONTENT, DEFAULT_FALLS_WITH_GRAVITY, DEFAULT_FALL_THROUGH);
    }

    public char display() {
        return this.content;
    }

    public void set_to_default() {
        this.content = DEFAULT_CONTENT;
    }

    public void set_content(char newContent) {
        this.content = newContent;
    }

    public char get_content() {
        return this.content;
    }

    public boolean get_falls_with_gravity() {
        return this.falls_with_gravity;
    }

    public boolean get_fall_through() {
        return this.fall_through;
    }

    public static boolean is_default(Block block) {
        return block.content == DEFAULT_CONTENT;
    }
}
