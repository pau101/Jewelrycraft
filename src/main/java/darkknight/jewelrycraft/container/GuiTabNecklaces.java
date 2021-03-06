package darkknight.jewelrycraft.container;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import darkknight.jewelrycraft.client.GuiGuide;
import darkknight.jewelrycraft.item.ItemList;
import darkknight.jewelrycraft.util.JewelryNBT;
import darkknight.jewelrycraft.util.JewelrycraftUtil;

public class GuiTabNecklaces extends GuiTab
{
    int jValues;
    public GuiTabNecklaces(int id)
    {
        super("Necklaces", id);
        jValues = 0;
    }
    
    public ItemStack getIcon()
    {        
        ItemStack it = new ItemStack(ItemList.necklace);
        JewelryNBT.addMetal(it, new ItemStack(Items.iron_ingot));
        JewelryNBT.addJewel(it, new ItemStack(Blocks.redstone_block)); 
        return it;
    }

    @Override
    public void drawBackground(GuiGuide gui, int x, int y, int page)
    {        
        ArrayList<String> text = new ArrayList<String>();
        ArrayList<ItemStack> jewels = new ArrayList<ItemStack>();
        ItemStack item = new ItemStack(ItemList.necklace);
        int xPos = (page%2==0)?107:-35;
        switch(page)
        {    
            case 1: 
                if(del == 0) values++;
                del++;
                if(del >= 300) del = 0;
                if(values > JewelrycraftUtil.metal.size() - 1) values = 0;

                JewelryNBT.addMetal(item, JewelrycraftUtil.metal.get(values));
                JewelryNBT.addJewel(item, new ItemStack(Items.ender_pearl));

                text.add(EnumChatFormatting.DARK_GREEN + "Jewel: " + EnumChatFormatting.BLACK + "Ender Pearl");
                text.add(EnumChatFormatting.DARK_GREEN + "Modifier: " + EnumChatFormatting.BLACK + "None");
                text.add(EnumChatFormatting.DARK_GREEN + "Ingot: " + EnumChatFormatting.BLACK + "Any");
                text.add("  This ring teleports");
                text.add("you and anyone around ");
                text.add("in any location from the");
                text.add("same dimension. Just");
                text.add("right click once in a");
                text.add("location to se the ");
                text.add("position. Then right ");
                text.add("click any time you want");
                Page.addImageTextPage(gui, gui.getLeft() + xPos, gui.getTop(), item, text, 50f, 0, -10, false, 45, 0);
                break;
            case 2: 
                text.add("to teleport there.");
                text.add(EnumChatFormatting.DARK_RED + "\u00a7nAltar Effect");
                text.add("  This teleports anyone");
                text.add("or anything that steps");
                text.add("or goes near the altar");
                text.add("to the necklaces");
                text.add("coordonates, as long");
                text.add("as that is in the same");
                text.add("dimension.");
                Page.addTextPage(gui, gui.getLeft() + xPos, gui.getTop(), text);
                break;
            case 3: 
                if(del == 0) values++;
                del++;
                if(del >= 300) del = 0;
                if(values > JewelrycraftUtil.metal.size() - 1) values = 0;

                JewelryNBT.addMetal(item, JewelrycraftUtil.metal.get(values));
                JewelryNBT.addJewel(item, new ItemStack(Items.ender_pearl));
                JewelryNBT.addModifier(item, new ItemStack(Items.bed));

                text.add(EnumChatFormatting.DARK_GREEN + "Jewel: " + EnumChatFormatting.BLACK + "Ender Pearl");
                text.add(EnumChatFormatting.DARK_GREEN + "Modifier: " + EnumChatFormatting.BLACK + "Bed");
                text.add(EnumChatFormatting.DARK_GREEN + "Ingot: " + EnumChatFormatting.BLACK + "Any");
                text.add("  Just like the other");
                text.add("necklace this teleports");
                text.add("you and anybody close");
                text.add("to you to the set");
                text.add("destination. The only");
                text.add("difference is that you");
                text.add("can travel between");
                text.add("dimensions with this.");
                Page.addImageTextPage(gui, gui.getLeft() + xPos, gui.getTop(), item, text, 50f, 0, -10, false, 45, 0);
                break;
            case 4: 
                text.add(EnumChatFormatting.DARK_RED + "\u00a7nAltar Effect");
                text.add("  This teleports anyone");
                text.add("or anything that step");
                text.add("on or around the altar");
                text.add("to the set location, with");
                text.add("the benefit of it being");
                text.add("capable of");
                text.add("inter-dimensional");
                text.add("travels.");
                Page.addTextPage(gui, gui.getLeft() + xPos, gui.getTop(), text);
                break;
            case 5:                 
                jewels.add(null);
                jewels.add(new ItemStack(Items.diamond));
                jewels.add(new ItemStack(Items.emerald));
                jewels.add(new ItemStack(Items.nether_star));
                
                if(del == 0) { values++; jValues++;}
                del++;
                if(del >= 300) del = 0;
                if(values > JewelrycraftUtil.metal.size() - 1) values = 0;
                if(jValues > jewels.size() - 1) jValues = 0;

                JewelryNBT.addMetal(item, JewelrycraftUtil.metal.get(values));
                JewelryNBT.addJewel(item, jewels.get(jValues));
                JewelryNBT.addModifier(item, new ItemStack(Items.blaze_powder));

                text.add(EnumChatFormatting.DARK_GREEN + "Jewel: " + EnumChatFormatting.BLACK + "None, Diamond");
                text.add("Emerald or Nether Star");
                text.add(EnumChatFormatting.DARK_GREEN + "Modifier: " + EnumChatFormatting.BLACK + "Blaze Powder");
                text.add(EnumChatFormatting.DARK_GREEN + "Ingot: " + EnumChatFormatting.BLACK + "Any");
                text.add("  This necklace gives");
                text.add("you and those around");
                text.add("you " + EnumChatFormatting.DARK_RED + "Fire Resistance");
                text.add("when activated and in");
                text.add("your inventory. To");
                text.add("deactivate it simply");
                text.add("right click with it.");
                Page.addImageTextPage(gui, gui.getLeft() + xPos, gui.getTop(), item, text, 50f, 0, -10, false, 45, 0);
                break;
            case 6: 
                text.add("Depending on the jewel");
                text.add("used, you and the");
                text.add("others get " + EnumChatFormatting.DARK_RED + "Fire");
                text.add(EnumChatFormatting.DARK_RED + "Resistance" + EnumChatFormatting.BLACK + " I if you");
                text.add("haven't got any jewel,");
                text.add("II for Diamond,");
                text.add("III for Emerald and");
                text.add("VIII for Nether Star.");
                Page.addTextPage(gui, gui.getLeft() + xPos, gui.getTop(), text);
                break;
            case 7:                 
                jewels.add(null);
                jewels.add(new ItemStack(Items.diamond));
                jewels.add(new ItemStack(Items.emerald));
                jewels.add(new ItemStack(Items.nether_star));
                
                if(del == 0) { values++; jValues++;}
                del++;
                if(del >= 300) del = 0;
                if(values > JewelrycraftUtil.metal.size() - 1) values = 0;
                if(jValues > jewels.size() - 1) jValues = 0;

                JewelryNBT.addMetal(item, JewelrycraftUtil.metal.get(values));
                JewelryNBT.addJewel(item, jewels.get(jValues));
                JewelryNBT.addModifier(item, new ItemStack(Items.sugar));

                text.add(EnumChatFormatting.DARK_GREEN + "Jewel: " + EnumChatFormatting.BLACK + "None, Diamond");
                text.add("Emerald or Nether Star");
                text.add(EnumChatFormatting.DARK_GREEN + "Modifier: " + EnumChatFormatting.BLACK + "Sugar");
                text.add(EnumChatFormatting.DARK_GREEN + "Ingot: " + EnumChatFormatting.BLACK + "Any");
                text.add("  This necklace gives");
                text.add("you and those around");
                text.add("you " + EnumChatFormatting.DARK_RED + "Speed" + EnumChatFormatting.BLACK + " when");
                text.add("activated and in your");
                text.add("inventory. To");
                text.add("deactivate it simply");
                text.add("right click with it.");
                Page.addImageTextPage(gui, gui.getLeft() + xPos, gui.getTop(), item, text, 50f, 0, -10, false, 45, 0);
                break;
            case 8: 
                text.add("Depending on the jewel");
                text.add("used, you and the");
                text.add("others get " + EnumChatFormatting.DARK_RED + "Speed" + EnumChatFormatting.BLACK + " I");
                text.add("if you haven't got any");
                text.add("jewel, II for Diamond,");
                text.add("III for Emerald and");
                text.add("VIII for Nether Star.");
                Page.addTextPage(gui, gui.getLeft() + xPos, gui.getTop(), text);
                break;
            case 9:                 
                jewels.add(null);
                jewels.add(new ItemStack(Items.diamond));
                jewels.add(new ItemStack(Items.emerald));
                jewels.add(new ItemStack(Items.nether_star));
                
                if(del == 0) { values++; jValues++;}
                del++;
                if(del >= 300) del = 0;
                if(values > JewelrycraftUtil.metal.size() - 1) values = 0;
                if(jValues > jewels.size() - 1) jValues = 0;

                JewelryNBT.addMetal(item, JewelrycraftUtil.metal.get(values));
                JewelryNBT.addJewel(item, jewels.get(jValues));
                JewelryNBT.addModifier(item, new ItemStack(Items.iron_pickaxe));

                text.add(EnumChatFormatting.DARK_GREEN + "Jewel: " + EnumChatFormatting.BLACK + "None, Diamond");
                text.add("Emerald or Nether Star");
                text.add(EnumChatFormatting.DARK_GREEN + "Modifier: " + EnumChatFormatting.BLACK + "Iron Pickaxe");
                text.add(EnumChatFormatting.DARK_GREEN + "Ingot: " + EnumChatFormatting.BLACK + "Any");
                text.add("  This necklace gives");
                text.add("you and those around");
                text.add("you " + EnumChatFormatting.DARK_RED + "Haste" + EnumChatFormatting.BLACK + " when");
                text.add("activated and in your");
                text.add("inventory. To");
                text.add("deactivate it simply");
                text.add("right click with it.");
                Page.addImageTextPage(gui, gui.getLeft() + xPos, gui.getTop(), item, text, 50f, 0, -10, false, 45, 0);
                break;
            case 10: 
                text.add("Depending on the jewel");
                text.add("used, you and the");
                text.add("others get " + EnumChatFormatting.DARK_RED + "Haste" + EnumChatFormatting.BLACK + " I");
                text.add("if you haven't got any");
                text.add("jewel, II for Diamond,");
                text.add("III for Emerald and");
                text.add("VIII for Nether Star.");
                Page.addTextPage(gui, gui.getLeft() + xPos, gui.getTop(), text);
                break;
            case 11:                 
                jewels.add(null);
                jewels.add(new ItemStack(Items.diamond));
                jewels.add(new ItemStack(Items.emerald));
                jewels.add(new ItemStack(Items.nether_star));
                
                if(del == 0) { values++; jValues++;}
                del++;
                if(del >= 300) del = 0;
                if(values > JewelrycraftUtil.metal.size() - 1) values = 0;
                if(jValues > jewels.size() - 1) jValues = 0;

                JewelryNBT.addMetal(item, JewelrycraftUtil.metal.get(values));
                JewelryNBT.addJewel(item, jewels.get(jValues));
                JewelryNBT.addModifier(item, new ItemStack(Items.feather));

                text.add(EnumChatFormatting.DARK_GREEN + "Jewel: " + EnumChatFormatting.BLACK + "None, Diamond");
                text.add("Emerald or Nether Star");
                text.add(EnumChatFormatting.DARK_GREEN + "Modifier: " + EnumChatFormatting.BLACK + "Feather");
                text.add(EnumChatFormatting.DARK_GREEN + "Ingot: " + EnumChatFormatting.BLACK + "Any");
                text.add("  This necklace gives");
                text.add("you and those around");
                text.add("you " + EnumChatFormatting.DARK_RED + "Jump Boost" + EnumChatFormatting.BLACK + " when");
                text.add("activated and in your");
                text.add("inventory. To");
                text.add("deactivate it simply");
                text.add("right click with it.");
                Page.addImageTextPage(gui, gui.getLeft() + xPos, gui.getTop(), item, text, 50f, 0, -10, false, 45, 0);
                break;
            case 12: 
                text.add("Depending on the jewel");
                text.add("used, you and the");
                text.add("others get " + EnumChatFormatting.DARK_RED + "Jump Boost");
                text.add("I if you haven't got any");
                text.add("jewel, II for Diamond,");
                text.add("III for Emerald and");
                text.add("VIII for Nether Star.");
                Page.addTextPage(gui, gui.getLeft() + xPos, gui.getTop(), text);
                break;
            case 13:                 
                jewels.add(null);
                jewels.add(new ItemStack(Items.diamond));
                jewels.add(new ItemStack(Items.emerald));
                jewels.add(new ItemStack(Items.nether_star));
                
                if(del == 0) { values++; jValues++;}
                del++;
                if(del >= 300) del = 0;
                if(values > JewelrycraftUtil.metal.size() - 1) values = 0;
                if(jValues > jewels.size() - 1) jValues = 0;

                JewelryNBT.addMetal(item, JewelrycraftUtil.metal.get(values));
                JewelryNBT.addJewel(item, jewels.get(jValues));
                JewelryNBT.addModifier(item, new ItemStack(Items.potionitem, 1, 8270));

                text.add(EnumChatFormatting.DARK_GREEN + "Jewel: " + EnumChatFormatting.BLACK + "None, Diamond");
                text.add("Emerald or Nether Star");
                text.add(EnumChatFormatting.DARK_GREEN + "Modifier: " + EnumChatFormatting.BLACK + "8min Potion of");
                text.add("Invisibility");
                text.add(EnumChatFormatting.DARK_GREEN + "Ingot: " + EnumChatFormatting.BLACK + "Any");
                text.add("  This necklace gives");
                text.add("you and those around");
                text.add("you " + EnumChatFormatting.DARK_RED + "Invisibility" + EnumChatFormatting.BLACK + " when");
                text.add("activated and in your");
                text.add("inventory. To");
                text.add("deactivate it simply");
                Page.addImageTextPage(gui, gui.getLeft() + xPos, gui.getTop(), item, text, 50f, 0, -10, false, 45, 0);
                break;
            case 14: 
                text.add("right click with it.");
                text.add("Depending on the jewel");
                text.add("used, you and the");
                text.add("others get " + EnumChatFormatting.DARK_RED + "Invisibility" + EnumChatFormatting.BLACK + " I");
                text.add("if you haven't got any");
                text.add("jewel, II for Diamond,");
                text.add("III for Emerald and");
                text.add("VIII for Nether Star.");
                Page.addTextPage(gui, gui.getLeft() + xPos, gui.getTop(), text);
                break;
            case 15:                 
                jewels.add(null);
                jewels.add(new ItemStack(Items.diamond));
                jewels.add(new ItemStack(Items.emerald));
                jewels.add(new ItemStack(Items.nether_star));
                
                if(del == 0){values++; jValues++;}
                del++;
                if(del >= 300) del = 0;
                if(values > JewelrycraftUtil.metal.size() - 1) values = 0;
                if(jValues > jewels.size() - 1) jValues = 0;

                JewelryNBT.addMetal(item, JewelrycraftUtil.metal.get(values));
                JewelryNBT.addJewel(item, jewels.get(jValues));
                JewelryNBT.addModifier(item, new ItemStack(Items.dye, 1, 15));

                text.add(EnumChatFormatting.DARK_GREEN + "Jewel: " + EnumChatFormatting.BLACK + "None, Diamond");
                text.add("Emerald or Nether Star");
                text.add(EnumChatFormatting.DARK_GREEN + "Modifier: " + EnumChatFormatting.BLACK + "Bone Meal");
                text.add(EnumChatFormatting.DARK_GREEN + "Ingot: " + EnumChatFormatting.BLACK + "Any");
                text.add("  This hydrates the");
                text.add("farm blocks under you");
                text.add("in a defined area. If");
                text.add("you right click with this,");
                text.add("it will help plants in");
                text.add("that area grow faster.");
                text.add("The are it affects is");
                Page.addImageTextPage(gui, gui.getLeft() + xPos, gui.getTop(), item, text, 50f, 0, -10, false, 45, 0);
                break;
            case 16: 
                text.add("determined by the jewel");
                text.add("used. For none, the");
                text.add("area is a block, diamond");
                text.add("is 3x3, emerald 5x5 and");
                text.add("nether star 11x11.");
                text.add(EnumChatFormatting.DARK_RED + "\u00a7nAltar Effect");
                text.add("  In the altar, this");
                text.add("has the ability to");
                text.add("hydrate any tilted land");
                text.add("and speed up the");
                text.add("growth of plants in a");
                text.add("7x3x7 area.");
                Page.addTextPage(gui, gui.getLeft() + xPos, gui.getTop(), text);
                break;
            case 17: 
                JewelryNBT.addMetal(item, new ItemStack(ItemList.shadowIngot));
                JewelryNBT.addJewel(item, new ItemStack(Items.nether_star));
                JewelryNBT.addModifier(item, new ItemStack(Items.diamond_pickaxe));

                text.add(EnumChatFormatting.DARK_GREEN + "Jewel: " + EnumChatFormatting.BLACK + "Nether Star");
                text.add(EnumChatFormatting.DARK_GREEN + "Modifier: " + EnumChatFormatting.BLACK + "Diamond Pick");
                text.add(EnumChatFormatting.DARK_GREEN + "Ingot: " + EnumChatFormatting.BLACK + "Shadow Ingot");
                text.add("  This will break all");
                text.add("blocks in a 3x3x1 area.");
                text.add("Just right click on a");
                text.add("block and let the mining");
                text.add("begin.");
                Page.addImageTextPage(gui, gui.getLeft() + xPos, gui.getTop(), item, text, 50f, 0, -10, false, 45, 0);
                break;
            default:;
        }
    }

    public int getMaxPages()
    {
        return 17;
    }

    @Override
    public void drawForeground(GuiGuide gui, int x, int y, int page)
    {        
    }

}
