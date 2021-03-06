package darkknight.jewelrycraft.renders;

import org.lwjgl.opengl.GL11;

import darkknight.jewelrycraft.item.ItemList;
import darkknight.jewelrycraft.model.ModelMolder;
import darkknight.jewelrycraft.tileentity.TileEntityMolder;
import darkknight.jewelrycraft.util.JewelryNBT;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileEntityMolderRender extends TileEntitySpecialRenderer
{
    ModelMolder modelMolder = new ModelMolder();
    String      texture     = "textures/tileentities/Molder.png";

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        TileEntityMolder me = (TileEntityMolder) te;

        ResourceLocation blockTexture = new ResourceLocation("jewelrycraft", texture);
        Minecraft.getMinecraft().renderEngine.bindTexture(blockTexture);
        int block = me.getBlockMetadata();

        GL11.glPushMatrix();
        if (block == 0)
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        else if (block == 1){
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
        }
        else if (block == 2)
            GL11.glRotatef(180F, 1.0F, 0.0F, 0.0F);
        else if (block == 3)
            GL11.glRotatef(180F, 1.0F, 0.0F, 1.0F);
        modelMolder.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        if (me != null)
        {
            if (me.hasMold)
            {
                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);
                EntityItem entityitem = new EntityItem(te.getWorldObj(), 0.0D, 0.0D, 0.0D, me.mold);
                entityitem.getEntityItem().stackSize = 1;
                entityitem.hoverStart = 0.0F;
                GL11.glTranslatef(0F, 1.312F, -0.25F);
                GL11.glScalef(1.25F, 1.0F, 1.25F);
                GL11.glRotatef(90F, 1F, 0F, 0f);
                RenderItem.renderInFrame = true;
                if(entityitem != null) RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
                RenderItem.renderInFrame = false;
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();
            }
            if (me.hasJewelBase && me.jewelBase.getIconIndex() != null && me.jewelBase.getIconIndex().getIconName() != "")
            {
                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);
                EntityItem entityitem = new EntityItem(te.getWorldObj(), 0.0D, 0.0D, 0.0D, me.jewelBase);
                entityitem.getEntityItem().stackSize = 1;
                entityitem.hoverStart = 0.0F;
                GL11.glTranslatef(0F, 1.312F, -0.25F);
                GL11.glScalef(1.25F, 1.0F, 1.25F);
                GL11.glRotatef(90F, 1F, 0F, 0f);
                RenderItem.renderInFrame = true;
                if(entityitem != null) RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
                RenderItem.renderInFrame = false;
                GL11.glColor4f(1, 1F, 1F, 1.0F);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();
            }
            if(me.hasMoltenMetal && me.moltenMetal != null && me.moltenMetal != new ItemStack(Item.getItemById(0), 0, 0))
            {
                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);
                ItemStack metal = new ItemStack(ItemList.metal);
                JewelryNBT.addMetal(metal, new ItemStack(me.moltenMetal.getItem()));
                EntityItem moltenMetal = new EntityItem(te.getWorldObj(), 0.0D, 0.0D, 0.0D, metal);
                moltenMetal.getEntityItem().stackSize = 1;
                moltenMetal.hoverStart = 0.0F;

                GL11.glTranslatef(-0F, 1.43f - 0.115f*me.quantity, -0.3F);
                GL11.glScalef(1.2F, 1.0F, 1.45F);
                GL11.glRotatef(90F, 1F, 0F, 0f);
                RenderItem.renderInFrame = true;
                RenderManager.instance.renderEntityWithPosYaw(moltenMetal, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
                RenderItem.renderInFrame = false;
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();
            }
        }
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public void adjustLightFixture(World world, int i, int j, int k, Block block)
    {
        Tessellator tess = Tessellator.instance;
        float brightness = block.getLightOpacity(world, i, j, k);
        int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
        int modulousModifier = skyLight % 65536;
        int divModifier = skyLight / 65536;
        tess.setColorOpaque_F(brightness, brightness, brightness);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) modulousModifier, divModifier);
    }

}
