package darkknight.jewelrycraft.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class ItemRender implements IItemRenderer
{
    TileEntitySpecialRenderer render;
    public TileEntity entity;
    ModelBase model;

    public ItemRender(TileEntitySpecialRenderer render, TileEntity entity, ModelBase model)
    {
        this.entity = entity;
        this.render = render;
        this.model = model;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        if (type == IItemRenderer.ItemRenderType.ENTITY)
        {
            GL11.glRotatef(180, 0, 1, 0);
            GL11.glTranslatef(-0.5F, -0.5F, -0.4F);
        }
        render.renderTileEntityAt(entity, 0.0D, 0.0D, 0.0D, 0.0F);
    }

}
