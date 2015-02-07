package darkknight.jewelrycraft.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import darkknight.jewelrycraft.util.JewelryNBT;

public class ItemEarrings extends ItemBaseJewelry
{
    public IIcon gem;

    public ItemEarrings()
    {
    }

    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("jewelrycraft:earrings");
        gem = iconRegister.registerIcon("jewelrycraft:jewelEarrings");
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass)
    {
        if (pass == 0)
        {
            return itemIcon;
        }
        if (pass == 1 && JewelryNBT.gem(stack) != null)
        {
            return gem;
        }
        return itemIcon;
    }
}
