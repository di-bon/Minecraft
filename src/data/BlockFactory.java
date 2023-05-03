package data;

import data.blocks.*;
import data.blocks.interfaces.Block;
import data.blocks.solids.DirtBlock;
import data.blocks.solids.RawIronBlock;
import data.blocks.SandBlock;

import java.util.Random;

public class BlockFactory {
    private static final int RAND_UPPERBOUND = 3;

    public BlockFactory() {
    }

    public Block random_block(){
        Random rand = new Random();
        int r = rand.nextInt(RAND_UPPERBOUND);
        switch (r){
            case 0:
                return this.raw_iron_block();
            case 1:
                return this.sand_block();
            case 2:
                return this.dirt_block();
            default:
                return this.null_block();
        }
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
    public NullBlock null_block(){
        return new NullBlock();
    }
    public RawIronBlock raw_iron_block(){
        return new RawIronBlock();
    }
    public DirtBlock dirt_block(){
        return new DirtBlock();
    }
}