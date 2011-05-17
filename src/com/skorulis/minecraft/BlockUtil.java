package com.skorulis.minecraft;

import net.minecraft.src.Block;
import net.minecraft.src.ModLoader;

public class BlockUtil {

	public static final int MAX_BLOCK = 256;
	public static final int MAX_ITEM = MAX_BLOCK + 256;
	
	public static void init() {
		
	}
	
	public static int nextBlockId() {
		for(int i=50; i < MAX_BLOCK; ++i) {
			if(Block.blocksList[i]==null) {
				return i;
			}
		}
		return 0; //Will probably cause a crash
	}
	
	public static final Block copperOre;
    public static final Block store;
    
    static {
    	copperOre = (new CopperOreBlock(1));
        store = new StoreBlock(ModLoader.addOverride("/terrain.png", "/skres/shop.png"));
    	//store = new StoreBlock(88);
    	ModLoader.RegisterBlock(store);
        ModLoader.AddName(store, "Store");
        System.out.println("STORE " + store.blockID);
    }
	
    
	
}
