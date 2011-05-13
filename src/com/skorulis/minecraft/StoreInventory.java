package com.skorulis.minecraft;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTBase;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.SKTagCompound;

public class StoreInventory implements IInventory{

	private ItemStack contents[];
	private int credits;
	
	
	public StoreInventory() {
		contents = new ItemStack[36];
		credits = 10000;
	}
	
	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return contents[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		System.out.println("DEC " + i + " - " + j);
		if(contents[i] != null)
        {
			int max = StoreUtil.instance().maxPurchase(credits, contents[i].itemID);
			if(max==0) {
				return null;
			}
			if(max < j) {
				j = max;
			}
			if(contents[i].stackSize <= j)
            {
                ItemStack itemstack = contents[i];
                int cost = StoreUtil.instance().getCost(itemstack);
                if(credits >= cost) {
                	credits-=cost;
                	contents[i] = null;
                	onInventoryChanged();
                	return itemstack;
                }
                
                return null;
            }
            ItemStack itemstack1 = contents[i].splitStack(j);
            int cost = StoreUtil.instance().getCost(itemstack1);
            if(credits >= cost) {
            	credits-=cost;
            	if(contents[i].stackSize == 0)
                {
                    contents[i] = null;
                }
            	onInventoryChanged();
            	return itemstack1;
            }
            
        }
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		System.out.println("SET " + i + " - " + itemstack);
		contents[i] = new SKStoreStack(itemstack,this);
		credits+=StoreUtil.instance().getCost(itemstack);
        if(itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
        onInventoryChanged();
	}
	
	public void addItem(ItemStack stack) {
		for(int i=0; i < contents.length; ++i) {
			if(contents[i]==null) {
				contents[i] = stack;
				break;
			}
		}
	}

	@Override
	public String getInvName() {
		return "Store (" + credits + ")";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void onInventoryChanged() {
		System.out.println("INV CHANGED " + calcValue());
		
		saveInventory(SKDataManager.worldDir);
	}
	
	public int calcValue() {
		int total = 0;
		for(int i=0; i < getSizeInventory(); ++i) {
			total+=StoreUtil.instance().getCost(contents[i]);
		}
		return total;
	}
	
	public void saveInventory(String world) {
		
		try {
			File f = new File(world,"store.dat");
			f.createNewFile();
			DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
			SKTagCompound comp = new SKTagCompound();
			comp.setInteger("credits", credits);
			writeToNBT(comp);
			comp.writeTagContents(out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadInventroy(String world) {
		try {
			File f = new File(world,"store.dat");
			DataInputStream input = new DataInputStream(new FileInputStream(f));
			SKTagCompound comp = new SKTagCompound();
			comp.readTagContents(input);
			credits = comp.getInteger("credits");
			readFromNBT(comp);
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("No store exists for " + world);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
        contents = new ItemStack[getSizeInventory()];
        for(int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xff;
            if(j >= 0 && j < contents.length)
            {
                contents[j] = new SKStoreStack(nbttagcompound1,this);
            }
        }

    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < contents.length; i++)
        {
            if(contents[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                contents[i].writeToNBT(nbttagcompound1);
                nbttaglist.setTag(nbttagcompound1);
            }
        }

        nbttagcompound.setTag("Items", nbttaglist);
    }

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

	
	
}
