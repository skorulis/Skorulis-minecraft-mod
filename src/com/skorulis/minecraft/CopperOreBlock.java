package com.skorulis.minecraft;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Material;

public class CopperOreBlock extends Block {

	public CopperOreBlock(int j) {
		super(BlockUtil.nextBlockId(),j,Material.pumpkin);
		setHardness(1.5f);
		setResistance(10f);
		setStepSound(Block.soundStoneFootstep);
		setBlockName("Copper Ore");
	}
	
	public int idDropped(int i, Random random)
    {
        return 0;
    }
	
}
