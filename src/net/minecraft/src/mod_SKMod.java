package net.minecraft.src;



import com.skorulis.minecraft.BlockUtil;
import com.skorulis.minecraft.MinerItem;
import com.skorulis.minecraft.SKDataManager;
import com.skorulis.minecraft.SKRecipes;

import net.minecraft.client.Minecraft;
import net.minecraft.src.BaseMod;
import net.minecraft.src.CraftingManager;


public class mod_SKMod extends BaseMod{
	
	public mod_SKMod() {
		BlockUtil.init();
		
		
		SKRecipes.addRecipes(CraftingManager.getInstance());
	}

	public static void loadStart(String file) {
		SKDataManager.loadStart(file);
	}
	
	@Override
	public String Version() {
		return "0.01a";
	}
	
	public void OnTickInGame(Minecraft craft) {
		super.OnTickInGame(craft);
		System.out.print("tick");
	}
		
	
}
