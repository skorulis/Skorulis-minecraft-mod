package com.skorulis.minecraft;

import net.minecraft.src.Block;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.ItemStack;

public class SKRecipes {

	public void addRecipes(CraftingManager man) {
		man.addRecipe(new ItemStack(Block.store), new Object[]{"##","##",Character.valueOf('#'),Block.dirt});
	}
	
}
