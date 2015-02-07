package darkknight.jewelrycraft.container;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import darkknight.jewelrycraft.client.GuiGuide;

public class GuiTabModifiers extends GuiTab
{
    public GuiTabModifiers(int id)
    {
        super("Modifiers", id);
    }

    @Override
    public ItemStack getIcon()
    {
        return new ItemStack(Items.blaze_powder);
    }

    @Override
    public void drawBackground(GuiGuide gui, int x, int y, int page)
    {
        String text = "";
        int xPos = page % 2 == 0 ? 107 : -35;
        switch (page)
        {
        case 1:
            text = "This is a test to see if the program works or not! And it does seem to be working. Yaaay! Thank God I made this. This is so much easier :D";
            Page.addImageTextPage(gui, gui.getLeft() + xPos, gui.getTop(), new ItemStack(Items.blaze_powder), text, 40f);
            break;
        default:
            ;
        }
    }

    @Override
    public int getMaxPages()
    {
        return 1;
    }

    @Override
    public void drawForeground(GuiGuide gui, int x, int y, int page)
    {
    }

}
