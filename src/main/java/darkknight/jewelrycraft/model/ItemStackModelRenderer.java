package darkknight.jewelrycraft.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

/**
 * 
 * @author Paul Fulham (pau101)
 *
 */
public class ItemStackModelRenderer extends ModelRenderer
{
    private RenderItem renderItem;

    private TextureManager textureManager;

    private ResourceLocation resetResourceLocation;

    private EntityItem entityItem;

    public ItemStackModelRenderer(ModelBase modelBase, ResourceLocation resetResourceLocation)
    {
        super(modelBase);
        renderItem = (RenderItem) RenderManager.instance.entityRenderMap.get(EntityItem.class);
        textureManager = Minecraft.getMinecraft().getTextureManager();
        this.resetResourceLocation = resetResourceLocation;
    }

    public void setItemStack(ItemStack itemStack)
    {
        if (itemStack == null || itemStack.getItem() == null)
        {
            entityItem = null;
        } else
        {
            entityItem = new EntityItem(null, 0, 0, 0, itemStack);
            entityItem.hoverStart = 0;
        }
    }

    @Override
    public void render(float scale)
    {
        if (!isHidden && showModel && entityItem != null)
        {
            if (textureManager == null)
            {
                textureManager = Minecraft.getMinecraft().getTextureManager();
            }
            GL11.glPushMatrix();
            GL11.glTranslatef(offsetX, offsetY, offsetZ);
            GL11.glTranslatef(rotationPointX * scale, rotationPointY * scale, rotationPointZ * scale);
            GL11.glRotatef(rotateAngleZ * (180 / (float) Math.PI), 0, 0, 1);
            GL11.glRotatef(rotateAngleY * (180 / (float) Math.PI), 0, 1, 0);
            GL11.glRotatef(rotateAngleX * (180 / (float) Math.PI), 1, 0, 0);
            renderItem.doRender(entityItem, 0, 0, 0, 0, 0);
            GL11.glPopMatrix();
            textureManager.bindTexture(resetResourceLocation);
        }
    }
}
