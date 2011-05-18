package com.skorulis.minecraft;

import java.awt.datatransfer.ClipboardOwner;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;

public class MinerItem extends Item {

	public MinerItem(int i) {
		super(i);
		setIconIndex(ModLoader.addOverride("/gui/items.png", "/skres/minerItem.png"));
		setItemName("Miner Robot");
		setMaxStackSize(1);
	}
	
	public ItemStack onItemRightClick(ItemStack itemstack,World world,EntityPlayer player) {
		EntityChicken c = new EntityChicken(world);
		c.setPosition(player.posX, player.posY, player.posZ+5);
		world.entityJoinedWorld(c);
		itemstack.stackSize=0;
		return itemstack;
	}
	

	
	
}
