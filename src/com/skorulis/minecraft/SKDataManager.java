package com.skorulis.minecraft;

public class SKDataManager {

	public static String worldDir;
	
	public static void loadStart(String file) {
		worldDir = file;
		StoreBlock.getInventory().loadInventroy(worldDir);
	}
	
}
