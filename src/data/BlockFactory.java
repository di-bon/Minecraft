package data;

import data.blocks.*;
import data.blocks.interfaces.Block;
import data.blocks.SandBlock;
import data.blocks.solids.DirtBlock;
import data.blocks.solids.RawIronBlock;
import data.blocks.solids.TorchBlock;

import java.util.Random;

public class BlockFactory {
    private static final int RAND_UPPERBOUND = 6;

    public BlockFactory() {
    }

    public Block random_block(){
        Random rand = new Random();
        int r = rand.nextInt(RAND_UPPERBOUND);
        switch (r){
            case 0:
                return this.dirt_block();
            case 1:
                return this.raw_iron_block();
            case 2:
                return this.torch_block();
            case 3:
                return this.air_block();
            case 4:
                return this.sand_block();
            case 5:
                return this.water_block();
            default:
                return this.null_block();
        }
    }

    public NullBlock null_block(){
        return new NullBlock();
    }
    public DirtBlock dirt_block(){
        return new DirtBlock();
    }
    public RawIronBlock raw_iron_block(){
    return new RawIronBlock();
}
    public TorchBlock torch_block() {
        return new TorchBlock();
    }
    public AirBlock air_block(){
        return new AirBlock();
    }
    public SandBlock sand_block(){
        return new SandBlock();
    }
    public WaterBlock water_block(){
        return new WaterBlock();
    }




    public Block default_block() {
        return this.air_block();
    }
}