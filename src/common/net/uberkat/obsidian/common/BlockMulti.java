package net.uberkat.obsidian.common;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.*;

/**
 * Block object for handling multiple IDs. 
 * 0: Platinum Ore
 * 1: Platinum Block
 * 2: Redstone Block
 * 3: Refined Obsidian
 * 4: Coal Block
 * 5: Refined Glowstone
 * @author AidanBrady
 *
 */
public class BlockMulti extends Block
{
	public BlockMulti(int i)
	{
		super(i, Material.iron);
		setCreativeTab(CreativeTabs.tabBlock);
		setRequiresSelfNotify();
	}
	
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		switch(meta)
		{
			case 0:
				return 3;
			case 1:
				return 2;
			case 2:
				return 1;
			case 3:
				return 0;
			case 4:
				return 10;
			case 5:
				return 11;
		}
		return 0;
	}
	
	public int damageDropped(int i)
	{
		return i;
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int i, CreativeTabs creativetabs, List list)
	{
		list.add(new ItemStack(i, 1, 0));
		list.add(new ItemStack(i, 1, 1));
		list.add(new ItemStack(i, 1, 2));
		list.add(new ItemStack(i, 1, 3));
		list.add(new ItemStack(i, 1, 4));
		list.add(new ItemStack(i, 1, 5));
	}
	
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int i1, float f1, float f2, float f3)
    {
    	int metadata = world.getBlockMetadata(x, y, z);
    	
    	if(metadata == 3)
    	{
    		if(entityplayer.isSneaking())
    		{
    			entityplayer.openGui(ObsidianIngots.instance, 19, world, x, y, z);
    			return true;
    		}
    	}
        return false;
    }
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityliving)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		switch(metadata)
		{
			case 0:
				setHardness(3F).setResistance(5F);
				break;
			case 1:
				setHardness(5F).setResistance(10F);
				break;
			case 2:
				setHardness(5F).setResistance(10F);
				break;
			case 3:
				setHardness(10F).setResistance(15F).setLightValue(0.5F);
				break;
			case 4:
				setHardness(5F).setResistance(10F);
				break;
			case 5:
				setHardness(5F).setResistance(10F).setLightValue(0.875F);
				break;
		}
	}
	
	public String getTextureFile()
	{
		return "/obsidian/terrain.png";
	}
}