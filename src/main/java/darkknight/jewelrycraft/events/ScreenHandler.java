package darkknight.jewelrycraft.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import darkknight.jewelrycraft.util.JewelrycraftUtil;

public class ScreenHandler extends Gui
{
    private Minecraft mc;

    public static NBTTagCompound tagCache;
    public static int cooldown;

    public ScreenHandler(Minecraft mc)
    {
        super();
        this.mc = mc;
    }

    @SubscribeEvent
    public void renderScreen(RenderGameOverlayEvent event)
    {
        if (event.isCancelable() || event.type != ElementType.ALL || tagCache == null)
        {
            return;
        }
        mc.renderEngine.bindTexture(new ResourceLocation("jewelrycraft", "textures/gui/curses.png"));
        int count = 0;
        for (String l : JewelrycraftUtil.curseValues.keySet())
        {
            if (tagCache.getByte(l) > -1)
            {
                int tag = JewelrycraftUtil.curseValues.get(l);
                int size = 16;
                Gui.drawRect(0, (size + 6) * count, 24 + mc.fontRenderer.getStringWidth(l.split(":")[1]), 4 + (size + 6) * count + 16, 0xaf000000);
                Gui.drawRect(2, 2 + (size + 6) * count, 22 + mc.fontRenderer.getStringWidth(l.split(":")[1]), 2 + (size + 6) * count + 16, 0x95700064);
                count++;
            }
        }
        count = 0;
        for (String l : JewelrycraftUtil.curseValues.keySet())
        {
            if (tagCache.getByte(l) > -1)
            {
                int tag = JewelrycraftUtil.curseValues.get(l);
                int size = 16;
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(GL11.GL_LIGHTING);
                drawTexturedModalRect(2, 2 + (size + 6) * count, tag % size * size, tag / size * size, size, size);
                count++;
            }
        }
        count = 0;
        for (String l : JewelrycraftUtil.curseValues.keySet())
        {
            if (tagCache.getByte(l) > -1)
            {
                int tag = JewelrycraftUtil.curseValues.get(l);
                int size = 16;
                mc.fontRenderer.drawStringWithShadow(l.split(":")[1], 20, 7 + (size + 6) * count, 16777215);
                if (tagCache.getByte(l) == 1)
                {
                    mc.fontRenderer.drawStringWithShadow("Leech", mc.fontRenderer.getStringWidth(l.split(":")[1]) + 25, 7 + (size + 6) * count, 16777215);
                }
                count++;
            }
        }
    }
}