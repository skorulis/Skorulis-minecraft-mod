package com.skorulis.minecraft;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class StoreUtil extends ReferenceQueue<GUIStore>{

	private int prices[] = new int[BlockUtil.MAX_ITEM];
	public ArrayList<Integer> purchaseable;
	
	private Timer storeTimer;
	
	private static StoreUtil instanace;
	
	public WeakReference<GUIStore> guiRef;
	
	private StoreUtil() {
		purchaseable = new ArrayList<Integer>();
		setPrice(Block.blockClay, 100);
		setPrice(Block.blockDiamond, 9000);
		setPrice(Block.blockGold, 1800);
		setPrice(Block.blockLapis, 450);
		setPrice(Block.blockSnow, 10);
		setPrice(Block.blockSteel, 450);
		setPrice(Block.bookShelf, 500);
		setPrice(Block.brick.blockID,20);
		setPrice(Block.button,50);
		setPrice(Block.cactus,8);
		setPrice(Block.cake.blockID,100);
		setPrice(Block.cloth.blockID,10);
		setPrice(Block.cobblestone.blockID,3);
		setPrice(Block.cobblestoneMossy.blockID,4);
		setPrice(Block.crate.blockID,60);
		setPrice(Block.crops.blockID,20,false);
		setPrice(Block.dirt.blockID,1);
		setPrice(Block.dispenser, 300);
		setPrice(Block.doorSteel.blockID,200,false);
		
		setPrice(Block.plantRed, 5);
		setPrice(Block.plantYellow, 5);
		
		setPrice(Block.stone.blockID,3);
		
		setPrice(Block.sand.blockID,2);
		
		
		
		setPrice(Block.glowStone.blockID,100);
		
		
		setPrice(Block.obsidian.blockID,1500);
		setPrice(Block.gravel.blockID,2);
		
		
		setPrice(Block.glass.blockID,7);
		setPrice(Block.oreCoal.blockID,20,false);
		setPrice(Block.oreDiamond.blockID,1000,false);
		
		
		setPrice(Item.appleRed, 100);
		setPrice(Item.appleGold, 400);
		setPrice(Item.arrow, 300);
		setPrice(Item.axeDiamond, 1200);
		setPrice(Item.axeGold, 500);
		setPrice(Item.axeSteel, 200);
		setPrice(Item.axeStone, 100);
		setPrice(Item.axeWood, 20);
		setPrice(Item.bed, 400);
		setPrice(Item.bone, 50);
		setPrice(Item.cake, 60);
		setPrice(Item.feather, 10);
		
		
		
		setPrice(Item.silk, 50);
		setPrice(Item.coal, getPrice(Block.oreCoal)+2);
		setPrice(Item.diamond, getPrice(Block.oreDiamond)+10);
		setPrice(Item.stick,2);
		
		
		
		
		storeTimer = new Timer();
		scheduleTimer();
		
	}
	
	private void scheduleTimer() {
		long delay = 1000*20;
		storeTimer.schedule(new StoreUpdateTask(), delay);
	}
	
	public static StoreUtil instance() {
		if(instanace==null) {
			instanace= new StoreUtil();
		}
		return instanace;
	}
	
	public void generateInventory(StoreInventory inv) {
		Random random = new Random();
		inv.setDisableCreditUpdate(true);
		int j,k;
		inv.clear();
		int items = 6+random.nextInt(8);
		for(int i=0; i < items; ++i) {
			j = random.nextInt(purchaseable.size());
			k = purchaseable.get(j).intValue();
			inv.addItem(new ItemStack(k,random.nextInt(16),0));
		}
		inv.onInventoryChanged();
		inv.setDisableCreditUpdate(false);
	}
	
	public int maxPurchase(int credits, int itemID) {
		if(itemID >=prices.length || prices[itemID]==0) {
			return Integer.MAX_VALUE;
		}
		int unitCost = prices[itemID];
		return credits/unitCost;
	}
	
	public int getCost(ItemStack stack) {
		if(stack==null || stack.itemID >= prices.length) {
			return 0;
		}
		return prices[stack.itemID]*stack.stackSize;
	}
	
	public void setPrice(int itemID,int price) {
		setPrice(itemID, price,true);
	}
	
	public void setPrice(int itemID,int price,boolean purchaseable) {
		if(itemID < prices.length) {
			prices[itemID] = price;
			this.purchaseable.add(new Integer(itemID));
		} else {
			System.err.println("Could not set price for " + itemID);
		}
	}
	
	public void setPrice(Item item,int price) {
		setPrice(item.shiftedIndex, price);
	}
	
	public void setPrice(Block block, int price) {
		setPrice(block.blockID, price);
	}
	
	public int getPrice(int itemId) {
		return prices[itemId];
	}
	
	public int getPrice(Block b) {
		return getPrice(b.blockID);
	}
	
	public int getPrice(Item i) {
		return getPrice(i.shiftedIndex);
	}
	
	public boolean isStoreOpen() {
		return guiRef!=null && guiRef.get()!=null;
	}
	
	private static class StoreUpdateTask extends TimerTask {

		@Override
		public void run() {
			System.out.println("INVENTORY UPDATE");
			if(!StoreUtil.instance().isStoreOpen()) {
				StoreUtil.instance().generateInventory(StoreBlock.inventory);
			}
			StoreUtil.instance().scheduleTimer();
		}
		
		
	}
	
}
