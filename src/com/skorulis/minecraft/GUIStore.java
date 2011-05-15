package com.skorulis.minecraft;


import org.lwjgl.opengl.GL11;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiChest;
import net.minecraft.src.IInventory;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.RenderItem;
import net.minecraft.src.Slot;
import net.minecraft.src.StringTranslate;
public class GUIStore extends GuiChest {
	
	private static RenderItem itemRenderer = new RenderItem();
	
	public GUIStore(EntityPlayer entityplayer,IInventory inv) {
		super(entityplayer.inventory, inv);
	}
	
	public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawGuiContainerBackgroundLayer(f);
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 1.0F, 0.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(k, l, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        Slot slot = null;
        for(int i1 = 0; i1 < inventorySlots.slots.size(); i1++)
        {
            Slot slot1 = (Slot)inventorySlots.slots.get(i1);
            drawSlotInventory(slot1);
            if(getIsMouseOverSlot(slot1, i, j))
            {
                slot = slot1;
                GL11.glDisable(2896 /*GL_LIGHTING*/);
                GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
                int j1 = slot1.xDisplayPosition;
                int l1 = slot1.yDisplayPosition;
                drawGradientRect(j1, l1, j1 + 16, l1 + 16, 0x80ffffff, 0x80ffffff);
                GL11.glEnable(2896 /*GL_LIGHTING*/);
                GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
            }
        }

        InventoryPlayer inventoryplayer = mc.thePlayer.inventory;
        if(inventoryplayer.getItemStack() != null)
        {
            GL11.glTranslatef(0.0F, 0.0F, 32F);
            itemRenderer.renderItemIntoGUI(fontRenderer, mc.renderEngine, inventoryplayer.getItemStack(), i - k - 8, j - l - 8);
            itemRenderer.renderItemOverlayIntoGUI(fontRenderer, mc.renderEngine, inventoryplayer.getItemStack(), i - k - 8, j - l - 8);
        }
        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(2896 /*GL_LIGHTING*/);
        GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
        drawGuiContainerForegroundLayer();
        if(inventoryplayer.getItemStack() == null && slot != null && slot.getHasStack())
        {
            String s = (new StringBuilder()).append("").append(StringTranslate.getInstance().translateNamedKey(slot.getStack().func_20109_f())).toString().trim();
            s += " (" + StoreUtil.instance().getPrice(slot.getStack().itemID) + ")";
            if(s.length() > 0)
            {
                int k1 = (i - k) + 12;
                int i2 = j - l - 12;
                int j2 = fontRenderer.getStringWidth(s);
                drawGradientRect(k1 - 3, i2 - 3, k1 + j2 + 3, i2 + 8 + 3, 0xc0000000, 0xc0000000);
                fontRenderer.drawStringWithShadow(s, k1, i2, -1);
            }
        }
        GL11.glPopMatrix();
        GL11.glEnable(2896 /*GL_LIGHTING*/);
        GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
    }
	
	private boolean getIsMouseOverSlot(Slot slot, int i, int j)
    {
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        i -= k;
        j -= l;
        return i >= slot.xDisplayPosition - 1 && i < slot.xDisplayPosition + 16 + 1 && j >= slot.yDisplayPosition - 1 && j < slot.yDisplayPosition + 16 + 1;
    }
	
	private void drawSlotInventory(Slot slot)
    {
        int i = slot.xDisplayPosition;
        int j = slot.yDisplayPosition;
        ItemStack itemstack = slot.getStack();
        if(itemstack == null)
        {
            int k = slot.getBackgroundIconIndex();
            if(k >= 0)
            {
                GL11.glDisable(2896 /*GL_LIGHTING*/);
                mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/gui/items.png"));
                drawTexturedModalRect(i, j, (k % 16) * 16, (k / 16) * 16, 16, 16);
                GL11.glEnable(2896 /*GL_LIGHTING*/);
                return;
            }
        }
        itemRenderer.renderItemIntoGUI(fontRenderer, mc.renderEngine, itemstack, i, j);
        itemRenderer.renderItemOverlayIntoGUI(fontRenderer, mc.renderEngine, itemstack, i, j);
    }
	
	
}
