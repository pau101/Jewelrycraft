/*
 * Mod made by DarkKnight during the Modjam 3
 * It's an awesome mod
 * I love me! :D
 */

package darkknight.jewelrycraft;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import darkknight.jewelrycraft.block.BlockList;
import darkknight.jewelrycraft.config.ConfigHandler;
import darkknight.jewelrycraft.container.GuiHandler;
import darkknight.jewelrycraft.events.BucketHandler;
import darkknight.jewelrycraft.events.EntityEventHandler;
import darkknight.jewelrycraft.item.ItemList;
import darkknight.jewelrycraft.lib.Reference;
import darkknight.jewelrycraft.recipes.CraftingRecipes;
import darkknight.jewelrycraft.util.JewelrycraftUtil;
import darkknight.jewelrycraft.worldGen.Generation;
import darkknight.jewelrycraft.worldGen.village.ComponentJewelry;
import darkknight.jewelrycraft.worldGen.village.JCTrades;
import darkknight.jewelrycraft.worldGen.village.VillageJewelryHandler;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION)
public class JewelrycraftMod
{
    @Instance(Reference.MODID)
    public static JewelrycraftMod instance;

    @Metadata(Reference.MODID)
    public static ModMetadata metadata;

    @SidedProxy(clientSide = "darkknight.jewelrycraft.client.ClientProxy", serverSide = "darkknight.jewelrycraft.CommonProxy")
    public static CommonProxy proxy;
    
    public static final Logger logger = Logger.getLogger("Jewelrycraft");
    public static File dir;
	//TODO Look at how you did in ChowTime for files

    public static CreativeTabs jewelrycraft = new CreativeTabs("JewelryCraft")
    {
        @Override
        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(BlockList.jewelCraftingTable);
        }
    };
    public static CreativeTabs rings = new CreativeTabRings("Rings");
    public static CreativeTabs necklaces = new CreativeTabNecklaces("Necklaces");
    public static CreativeTabs liquids = new CreativeTabLiquids("Liquids");
	public static NBTTagCompound saveData = new NBTTagCompound();
	public static File liquidsConf;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) throws IOException
    {
        ConfigHandler.preInit(e);
        BlockList.preInit(e);
        ItemList.preInit(e);
        CraftingRecipes.preInit(e);
        OreDictionary.registerOre("ingotShadow", new ItemStack(ItemList.shadowIngot));
        OreDictionary.registerOre("ingot2", new ItemStack(ItemList.ingot2));
        OreDictionary.registerOre("ingot3", new ItemStack(ItemList.ingot3));
        OreDictionary.registerOre("ingot4", new ItemStack(ItemList.ingot4));
        OreDictionary.registerOre("ingot5", new ItemStack(ItemList.ingot5));
        OreDictionary.registerOre("ingot6", new ItemStack(ItemList.ingot6));
        OreDictionary.registerOre("ingot7", new ItemStack(ItemList.ingot7));
        OreDictionary.registerOre("oreShadow", new ItemStack(BlockList.shadowOre));
        
        VillagerRegistry.instance().registerVillagerId(3000);
        VillagerRegistry.instance().registerVillageTradeHandler(3000, new JCTrades());
        VillagerRegistry.instance().registerVillageCreationHandler(new VillageJewelryHandler());
        try
        {
            MapGenStructureIO.func_143031_a(ComponentJewelry.class, "Jewelrycraft:Jewelry");
        }
        catch (Throwable e2)
        {
            logger.severe("Error registering Jewelrycraft Structures with Vanilla Minecraft: this is expected in versions earlier than 1.7.2");
        }
        MinecraftForge.EVENT_BUS.register(new EntityEventHandler());   
        MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
        BucketHandler.INSTANCE.buckets.put(BlockList.moltenMetal, ItemList.bucket);
        
        proxy.registerRenderers();
        ModMetadata metadata = e.getModMetadata();

        List<String> authorList = new ArrayList<String>();
        authorList.add("DarkKnight (or sor1n)");
        authorList.add("bspkrs");
        dir = e.getModConfigurationDirectory();

        metadata.autogenerated = false;
        metadata.authorList = authorList;
        metadata.url = "https://github.com/sor1n/Modjam-Mod";
    }

    @EventHandler
    public void init(FMLInitializationEvent e)
    {
        GameRegistry.registerWorldGenerator(new Generation(), 0);
        new GuiHandler();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {   
        JewelrycraftUtil.addMetals();
        JewelrycraftUtil.addStuff();
        JewelrycraftUtil.jamcrafters();
    }
}
