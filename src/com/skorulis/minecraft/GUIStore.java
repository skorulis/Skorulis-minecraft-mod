package com.skorulis.minecraft;


import org.lwjgl.opengl.GL11;

import net.minecraft.src.ContainerChest;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiChest;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.IInventory;
public class GUIStore extends GuiContainer {
	
	public GUIStore(EntityPlayer entityplayer,IInventory inv) {
		this(entityplayer.inventory,inv);
	}
	
	public GUIStore(IInventory iinventory, IInventory iinventory1)
    {
        super(new ContainerChest(iinventory, iinventory1));
        inventoryRows = 0;
        upperChestInventory = iinventory;
        lowerChestInventory = iinventory1;
        field_948_f = false;
        char c = '\336';
        int i = c - 108;
        inventoryRows = iinventory1.getSizeInventory() / 9;
        ySize = i + inventoryRows * 18;
    }

    protected void drawGuiContainerForegroundLayer()
    {
        fontRenderer.drawString(lowerChestInventory.getInvName(), 8, 6, 0x404040);
        fontRenderer.drawString(upperChestInventory.getInvName(), 8, (ySize - 96) + 2, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float f)
    {
        int i = mc.renderEngine.getTexture("/gui/container.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(i);
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        drawTexturedModalRect(j, k, 0, 0, xSize, inventoryRows * 18 + 17);
        drawTexturedModalRect(j, k + inventoryRows * 18 + 17, 0, 126, xSize, 96);
    }

    private IInventory upperChestInventory;
    private IInventory lowerChestInventory;
    private int inventoryRows;
	
}
