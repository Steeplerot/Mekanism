package mekanism.common.item;

import mekanism.client.render.ModelCustomArmor;
import mekanism.client.render.ModelCustomArmor.ArmorModel;
import mekanism.common.Mekanism;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGasMask extends ItemArmor
{
	public ItemGasMask(int id)
	{
		super(id, EnumHelper.addArmorMaterial("GASMASK", 0, new int[] {0, 0, 0, 0}, 0), 0, 0);
		setCreativeTab(Mekanism.tabMekanism);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
    public boolean isValidArmor(ItemStack stack, int armorType, Entity entity)
    {
    	return armorType == 0;
    }
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
		return "mekanism:render/NullArmor.png";
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
    {
		ModelCustomArmor model = ModelCustomArmor.INSTANCE;
		model.modelType = ArmorModel.GASMASK;
        return model;
    }
	
	@ForgeSubscribe
	public void onEntityAttacked(LivingAttackEvent event)
	{
		EntityLivingBase base = event.entityLiving;
		
		if(base.getCurrentItemOrArmor(4) != null && base.getCurrentItemOrArmor(4).getItem() instanceof ItemGasMask)
		{
			ItemGasMask mask = (ItemGasMask)base.getCurrentItemOrArmor(4).getItem();
			
			if(base.getCurrentItemOrArmor(3) != null && base.getCurrentItemOrArmor(3).getItem() instanceof ItemScubaTank)
			{
				ItemScubaTank tank = (ItemScubaTank)base.getCurrentItemOrArmor(3).getItem();
				
				if(tank.getFlowing(base.getCurrentItemOrArmor(3)) && tank.getGas(base.getCurrentItemOrArmor(3)) != null)
				{
					event.setCanceled(true);
				}
			}
		}
	}
}
