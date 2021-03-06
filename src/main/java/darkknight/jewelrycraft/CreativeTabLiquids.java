package darkknight.jewelrycraft;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import darkknight.jewelrycraft.item.ItemList;
import darkknight.jewelrycraft.util.JewelryNBT;
import darkknight.jewelrycraft.util.JewelrycraftUtil;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CreativeTabLiquids extends CreativeTabs
{
	public static ArrayList<ItemStack> metal = new ArrayList<ItemStack>();

	public CreativeTabLiquids(String par2Str) 
	{
		super(par2Str);
		metal.add(new ItemStack(Items.gold_ingot));
		metal.add(new ItemStack(Items.iron_ingot));
	}

	@Override
	public Item getTabIconItem()
	{
		return ItemList.bucket;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void displayAllReleventItems(List par1List)
	{
		int index = 0, index2 = 0;
		while(index < OreDictionary.getOreNames().length)
		{
            Iterator<ItemStack> i = OreDictionary.getOres(OreDictionary.getOreNames()[index]).iterator();

            while (i.hasNext())
            {
                ItemStack nextStack = i.next();
                
                if ((nextStack.getItem().getUnlocalizedName().toLowerCase().contains("ingot") || nextStack.getItem().getUnlocalizedName().toLowerCase().contains("alloy")) && !metal.contains(nextStack)) metal.add(nextStack);
            }
            index++;
		}   
		for(int i = 0; i < metal.size(); i++) par1List.add(ItemList.bucket.getModifiedItemStack(metal.get(i)));
	}

}
