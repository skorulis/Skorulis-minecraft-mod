package com.skorulis.minecraft;

import net.minecraft.src.BaseMod;
import net.minecraft.src.Block;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

public class SKRecipes {

	public static void addRecipes(CraftingManager man) {
		String storePattern[] = { "XXX", "# #","!!!"};
		//ModLoader.AddRecipe(new ItemStack(BlockUtil.store), new Object[]{"##","##",Character.valueOf('#'),Block.dirt });
		ModLoader.AddRecipe(new ItemStack(BlockUtil.store), new Object[]{storePattern,Character.valueOf('X'),Block.cloth,Character.valueOf('#'),Item.stick,Character.valueOf('!'),Block.planks });
	}
	
}
