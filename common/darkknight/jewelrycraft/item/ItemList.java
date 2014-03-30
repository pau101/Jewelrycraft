package darkknight.jewelrycraft.item;

import net.minecraft.item.Item;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import darkknight.jewelrycraft.JewelrycraftMod;
import darkknight.jewelrycraft.config.ConfigHandler;

public class ItemList
{
    public static Item     thiefGloves;
    public static Item     shadowIngot;
    public static Item     molds;
    public static Item     clayMolds;
    public static Item     crystal;
    public static ItemRing     ring;
    public static ItemNecklace     necklace;
    public static Item     guide;

    private static boolean isInitialized = false;

    public static void preInit(FMLPreInitializationEvent e)
    {
        if (!isInitialized)
        {
            thiefGloves = new ItemThiefGloves(ConfigHandler.idThiefGloves - 256).setUnlocalizedName("Jewelrycraft.thiefGloves").setTextureName("jewelrycraft:thiefGloves").setCreativeTab(JewelrycraftMod.jewelrycraft);
            shadowIngot = new Item(ConfigHandler.idShadowIngot - 256).setUnlocalizedName("Jewelrycraft.ingotShadow").setTextureName("jewelrycraft:ingotShadow").setCreativeTab(JewelrycraftMod.jewelrycraft);
            molds = new ItemMolds(ConfigHandler.idMolds - 256).setUnlocalizedName("Jewelrycraft.mold").setTextureName("Mold").setCreativeTab(JewelrycraftMod.jewelrycraft);
            clayMolds = new ItemClayMolds(ConfigHandler.idClayMolds - 256).setUnlocalizedName("Jewelrycraft.mold").setTextureName("Mold").setCreativeTab(JewelrycraftMod.jewelrycraft);
            ring = (ItemRing) new ItemRing(ConfigHandler.idRing - 256).setUnlocalizedName("Jewelrycraft.ring").setTextureName("jewelrycraft:ring");
            crystal = new ItemCrystal(ConfigHandler.idCrystal - 256).setUnlocalizedName("Jewelrycraft.crystal").setTextureName("jewelrycraft:crystal").setCreativeTab(JewelrycraftMod.jewelrycraft);
            necklace = (ItemNecklace) new ItemNecklace(ConfigHandler.idNecklace - 256).setUnlocalizedName("Jewelrycraft.necklace").setTextureName("jewelrycraft:necklace");
            guide = new ItemGuide(ConfigHandler.idGuide - 256).setUnlocalizedName("Jewelrycraft.guide").setTextureName("jewelrycraft:guide").setCreativeTab(JewelrycraftMod.jewelrycraft);

            GameRegistry.registerItem(thiefGloves, "thiefGloves");
            GameRegistry.registerItem(shadowIngot, "shadowIngot");
            GameRegistry.registerItem(molds, "molds");
            GameRegistry.registerItem(clayMolds, "clayMolds");
            GameRegistry.registerItem(ring, "ring");
            GameRegistry.registerItem(necklace, "necklace");
            GameRegistry.registerItem(crystal, "crystal");
            GameRegistry.registerItem(guide, "guide");

            isInitialized = true;
        }
    }
}
