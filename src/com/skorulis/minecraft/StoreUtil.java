package com.skorulis.minecraft;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.src.Block;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.IRecipe;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ShapedRecipes;
import net.minecraft.src.ShapelessRecipes;
import net.minecraft.src.TileEntity;

public class StoreUtil extends ReferenceQueue<GUIStore>{

	private int prices[] = new int[BlockUtil.MAX_ITEM];
	public ArrayList<Integer> purchaseable;
	
	private Timer storeTimer;
	
	private static StoreUtil instanace;
	
	public WeakReference<GUIStore> guiRef;
	
	private StoreUtil() {
		purchaseable = new ArrayList<Integer>();

		
		setPrice(Block.bookShelf, 500);
		setPrice(Block.brick, 20);
		setPrice(Block.button,50);
		setPrice(Block.cactus,8);
		setPrice(Block.cake.blockID,100);
		setPrice(Block.cloth.blockID,10);
		setPrice(Block.cobblestone.blockID,3);
		setPrice(Block.cobblestoneMossy.blockID,4);
		setPrice(Block.crops.blockID,20,false);
		setPrice(Block.dirt.blockID,1,false);
		setPrice(Block.dispenser, 300);
		setPrice(Block.doorSteel.blockID,200,false);
		setPrice(Block.fire.blockID, 10,false);
		setPrice(Block.glowStone.blockID,100);
		setPrice(Block.mushroomBrown, 20);
		setPrice(Block.mushroomRed, 20);
		setPrice(Block.netherrack, 5);
		setPrice(Block.oreIron.blockID, 45,false);
		setPrice(Block.oreGold.blockID,90,false);
		setPrice(Block.planks, 2);
		setPrice(Block.plantRed, 5);
		setPrice(Block.plantYellow, 5);
		setPrice(Block.pumpkin, 40);
		setPrice(Block.sapling, 5);
		setPrice(Block.stone.blockID,3);
		setPrice(Block.sand.blockID,2);

		
		setPrice(Block.obsidian.blockID,1500);
		setPrice(Block.gravel.blockID,2,false);
		
		
		setPrice(Block.glass.blockID,7);
		setPrice(Block.oreCoal.blockID,20,false);
		setPrice(Block.oreDiamond.blockID,1000,false);
		setPrice(Block.reed, 15);
		setPrice(Block.wood, 8);
		
		setPrice(Block.pressurePlatePlanks,getPrice(Block.planks)*2);
		setPrice(Block.pressurePlateStone,getPrice(Block.stone)*2);
		
		setPrice(Item.appleRed, 50);
		setPrice(Item.arrow, 300);
		setPrice(Item.bed, 400);
		setPrice(Item.bone, 50);
		setPrice(Item.book, 40);
		setPrice(Item.bucketLava, 160);
		setPrice(Item.bucketMilk, 170);
		setPrice(Item.bucketWater,155);
		setPrice(Item.clay,40);
		setPrice(Item.coal, getPrice(Block.oreCoal));
		setPrice(Item.diamond, getPrice(Block.oreDiamond));
		setPrice(Item.feather, 10);
		setPrice(Item.ingotIron, 50);
		setPrice(Item.ingotGold,200);
		setPrice(Item.gunpowder, 20);
		setPrice(Item.snowball, 3);
		setPrice(Item.dyePowder, 8);
		setPrice(Item.egg, 20);
		setPrice(Item.flint, 10);
		setPrice(Item.paper, 20);
		setPrice(Item.leather, 30);
		setPrice(Item.lightStoneDust, 60);
		
		setPrice(Item.porkRaw, 40);
		setPrice(Item.porkCooked, 50);
		setPrice(Item.redstone, 25);
		setPrice(Item.reed, 10);
		setPrice(Item.sugar,30);
		setPrice(Item.silk, 50);
		setPrice(Item.stick,2);
		setPrice(Item.wheat, 10);
		
		
		
		
		
		
		
		
		
		
		
		
		for(int i = 0; i < CraftingManager.getInstance().getRecipeList().size(); i++)
        {
			IRecipe r = (IRecipe) CraftingManager.getInstance().getRecipeList().get(i);
			int totalCost=0;
			if(r instanceof ShapedRecipes) {
				ShapedRecipes s = (ShapedRecipes)r;
				for(int j=0; j < s.recipeItems.length; ++j) {
					if(s.recipeItems[j]!=null) {
						if(getCost(s.recipeItems[j])==0) {
							System.out.println("MISSING" + s.recipeItems[j].func_20109_f());
						}
						totalCost+=getCost(s.recipeItems[j]);
					}
				}
			} else if(r instanceof ShapelessRecipes) {
				ShapelessRecipes sr = (ShapelessRecipes)r;
				for(int j=0; j < sr.recipeItems.size(); ++j) {
					totalCost+=getCost((ItemStack) sr.recipeItems.get(j));
					if(getCost((ItemStack) sr.recipeItems.get(j))==0) {
						
						System.out.println("MISSING" + ((ItemStack) sr.recipeItems.get(j)).func_20109_f());
					}
				}
			} else {
				System.out.println("THERE IS ANOTHER " + r.getClass());
			}
			totalCost = Math.max(1, totalCost);
			if(getPrice(r.func_25117_b().itemID)==0) {
				//Prevent overwriting of already set value
				setPrice(r.func_25117_b().itemID, totalCost/r.func_25117_b().stackSize);
			}
			
			
        }
	
		
		storeTimer = new Timer();
		scheduleTimer();
		
	}
	
	private void scheduleTimer() {
		long delay = 1000*300 + (new Random()).nextInt(1000*300);
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
