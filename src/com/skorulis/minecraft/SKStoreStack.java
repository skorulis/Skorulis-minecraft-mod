package com.skorulis.minecraft;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;



public class SKStoreStack extends ItemStack {

	private StoreInventory store;
	
	public SKStoreStack(NBTTagCompound nbttagcompound,StoreInventory store) {
		super(nbttagcompound);
		this.store = store;
	}
	
	public SKStoreStack(ItemStack stack,StoreInventory store) {
		super(stack.itemID,stack.stackSize,stack.getItemDamage());
		this.store = store;
	}
	
	public ItemStack splitStack(int i) {
		ItemStack ret =super.splitStack(i); 
		System.out.println("SPLIT");
		return ret;
	}
	
	public ItemStack copy() {
		return new SKStoreStack(this, store);
	}
	
	public boolean inStore() {
		System.out.println("STORE CHECK");
		for(int i=0; i < store.getSizeInventory(); ++i) {
			if(store.getStackInSlot(i)==this) {
				return true;
			}
		}
		return false;
	}

}
