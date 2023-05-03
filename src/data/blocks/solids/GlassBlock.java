package data.blocks.solids;

public class GlassBlock extends AbstractSolidBlock {
    public GlassBlock() {
        super();
        this.blockname = "GlassBlock";
        this.content = 'G';
    }

    public static void main(String[] args) {
        GlassBlock glassBlock = new GlassBlock();
        System.out.println(glassBlock);
    }
}
