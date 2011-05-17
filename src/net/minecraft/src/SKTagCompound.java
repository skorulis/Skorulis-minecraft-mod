package net.minecraft.src;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Iterator;


public class SKTagCompound extends NBTTagCompound {

	public void writeTagContents(DataOutput dataoutput) throws IOException
    {
        super.writeTagContents(dataoutput);
    }

    public void readTagContents(DataInput datainput) throws IOException
    {
        super.readTagContents(datainput);
    }
	
}
