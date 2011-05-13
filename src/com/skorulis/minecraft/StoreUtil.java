package com.skorulis.minecraft;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;

public class StoreUtil {

	private int blockPrice[] = new int[BlockUtil.MAX_BLOCK];
	
	private static StoreUtil instanace;
	
	private StoreUtil() {
		blockPrice[Block.blockBed.blockID] = 20;
		blockPrice[Block.blockClay.blockID] = 100;
		blockPrice[Block.blockDiamond.blockID] = 9000;
		blockPrice[Block.blockGold.blockID] = 1800;
		blockPrice[Block.blockLapis.blockID] = 450;
		blockPrice[Block.blockSnow.blockID] = 10;
		blockPrice[Block.blockSteel.blockID] = 450;
		blockPrice[Block.bookShelf.blockID] = 500;
		blockPrice[Block.brick.blockID] = 20;
		blockPrice[Block.button.blockID] = 50;
		blockPrice[Block.cactus.blockID] = 8;
		blockPrice[Block.cake.blockID] = 100;
		blockPrice[Block.cloth.blockID] = 10;
		blockPrice[Block.cobblestone.blockID] = 3;
		blockPrice[Block.cobblestoneMossy.blockID] = 4;
		blockPrice[Block.crate.blockID] = 60;
		blockPrice[Block.crops.blockID] = 20;
		blockPrice[Block.dirt.blockID] = 1;
		blockPrice[Block.doorSteel.blockID] = 200;
		
		blockPrice[Block.stone.blockID] = 3;
		
		blockPrice[Block.sand.blockID] = 2;
		
		
		
		blockPrice[Block.glowStone.blockID] = 100;
		
		
		blockPrice[Block.obsidian.blockID] = 1500;
		blockPrice[Block.gravel.blockID] = 2;
		
		
		blockPrice[Block.glass.blockID] = 7;
		blockPrice[Block.oreCoal.blockID] = 20;
		blockPrice[Block.oreDiamond.blockID] = 1000;
	}
	
	public static StoreUtil instance() {
		if(instanace==null) {
			instanace= new StoreUtil();
		}
		return instanace;
	}
	
	public void generateInventory(StoreInventory inv) {
		Random random = new Random();
		int j;
		for(int i=0; i < 20; ++i) {
			j = random.nextInt(100);
			if(blockPrice[j] > 0) {
				inv.addItem(new ItemStack(Block.blocksList[j],random.nextInt(16)));
			}
		}
	}
	
	public int maxPurchase(int credits, int itemID) {
		if(itemID >=BlockUtil.MAX_BLOCK || blockPrice[itemID]==0) {
			return Integer.MAX_VALUE;
		}
		int unitCost = blockPrice[itemID];
		return credits/unitCost;
	}
	
	public int getCost(ItemStack stack) {
		if(stack==null || stack.itemID >= BlockUtil.MAX_BLOCK) {
			return 0;
		}
		return blockPrice[stack.itemID]*stack.stackSize;
	}
	
}
