package darkknight.jewelrycraft.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;

public class ItemRing extends ItemBase
{
    public ItemStack ingot;
    public PotionEffect effect;

    public ItemRing(int par1)
    {
        super(par1);
        this.setMaxStackSize(1);
    }

    public ItemRing(int par1, ItemStack ingot)
    {
        this(par1);
        this.ingot = ingot;
    }

    public ItemRing(int par1, ItemStack ingot, PotionEffect effect)
    {
        this(par1, ingot);
        this.effect = effect;
    }
    
    public int getColor(ItemStack par1ItemStack)
    {
        return 65535;
    }
    
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        if(ingot != null) list.add(EnumChatFormatting.GRAY + ingot.getDisplayName());
        if(effect != null) list.add(EnumChatFormatting.GREEN + effect.getEffectName());
    }
}
