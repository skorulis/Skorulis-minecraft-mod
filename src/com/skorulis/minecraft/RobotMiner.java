package com.skorulis.minecraft;

import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityArrow;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.World;

public class RobotMiner extends EntityChicken {

	public RobotMiner(World world) {
		super(world);
		//texture = "skres/example.png";
		setSize(0.9f, 1.3f);
	}
	
	public void onLivingUpdate() {
		
	}
	
	

}
