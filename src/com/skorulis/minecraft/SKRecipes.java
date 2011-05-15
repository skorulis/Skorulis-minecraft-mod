package com.skorulis.minecraft;

import net.minecraft.src.Block;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class SKRecipes {

	public void addRecipes(CraftingManager man) {
		String storePattern[] = { "XXX", "# #","!!!"};
		man.addRecipe(new ItemStack(BlockUtil.store), new Object[]{storePattern,Character.valueOf('X'),Block.cloth,Character.valueOf('#'),Item.stick,Character.valueOf('!'),Block.planks });
	}
	
}
