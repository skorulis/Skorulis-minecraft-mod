package com.skorulis.minecraft;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.IInventory;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class StoreBlock extends Block {

	public static StoreInventory inventory;
	
	public StoreBlock(int j) {
		super(BlockUtil.nextBlockId(),j,Material.cactus);
		setHardness(1.5f);
		setResistance(10f);
		setStepSound(Block.soundStoneFootstep);
		setBlockName("Store");
	}
	
	 public void onBlockClicked(World world, int i, int j, int k, EntityPlayer entityplayer) {
		 
	 }
	 
	 public static StoreInventory getInventory() {
		 if(inventory==null) {
			 inventory = new StoreInventory();
		 }
		 return inventory;
	 }
	 
	 public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
		 ((EntityPlayerSP)entityplayer).displayGUIStore(getInventory());
		 return true;
	 }
	
}
