package com.skorulis.minecraft;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class SKWorldGen extends WorldGenerator {

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		/*if (random.nextInt(1) == 0) {
		   // Loops 64 times, this could create up to 64 TNT
		   for (int z = 0; z < 64; z++)
		   {
		      int i1 = i + random.nextInt(8) - random.nextInt(8);
		      int j1 = j + random.nextInt(8) - random.nextInt(8);
		      int k1 = k + random.nextInt(8) - random.nextInt(8);
		      // Checks that there is nothing in the current location and there is grass beneath.
		      if(world.getBlockId(i1, j1, k1) == 0 && world.getBlockId(i1, j1 - 1, k1) == Block.grass.blockID && Block.tnt.canPlaceBlockAt(world, i1, j1, k1))
		      {
		         world.setBlockAndMetadata(i1, j1, k1, Block.tnt.blockID, random.nextInt(4));
		      }
		   }
		}*/
		
		return true;
	}

}
