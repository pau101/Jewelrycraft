package darkknight.jewelrycraft.particles;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class EntityShadowsFX extends EntityFX
{
    float moteParticleScale;

    public EntityShadowsFX(World world, double x, double y, double z, float size, float maxAge)
    {
        super(world, x, y, z, 0D, 0D, 0D);
        particleMaxAge = (int) (28D / (Math.random() * 0.3D + 0.7D) * maxAge);
        particleGravity = 0F;
        motionX = motionY = motionZ = 0;
        particleScale *= size;
        moteParticleScale = particleScale;
        noClip = true;
        setSize(0.01F, 0.01F);
    }

    @Override
    public void renderParticle(Tessellator tessellator, float partialTicks, float minX, float minY, float minZ, float maxX, float maxZ)
    {
        tessellator.draw();
        ResourceLocation particle = new ResourceLocation("jewelrycraft", "textures/particle/shadows.png");
        Minecraft.getMinecraft().renderEngine.bindTexture(particle);
        GL11.glColor4f(1, 1, 1, 1);
        tessellator.startDrawingQuads();
        tessellator.setBrightness(getBrightnessForRender(0));
        float scale = 0.1F * particleScale;
        float x = (float) (posX - interpPosX);
        float y = (float) (posY - interpPosY);
        float z = (float) (posZ - interpPosZ);
        tessellator.setColorRGBA_F(0F, 0F, 0F, 0.5F);
        tessellator.addVertexWithUV(x - minX * scale - maxX * scale, y - minY * scale, z - minZ * scale - maxZ * scale, 0, 0);
        tessellator.addVertexWithUV(x - minX * scale + maxX * scale, y + minY * scale, z - minZ * scale + maxZ * scale, 1, 0);
        tessellator.addVertexWithUV(x + minX * scale + maxX * scale, y + minY * scale, z + minZ * scale + maxZ * scale, 1, 1);
        tessellator.addVertexWithUV(x + minX * scale - maxX * scale, y - minY * scale, z + minZ * scale - maxZ * scale, 0, 1);
        tessellator.draw();
        tessellator.startDrawingQuads();
    }

    @Override
    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        motionX = motionY = motionZ = 0;

        if (particleAge++ >= particleMaxAge)
        {
            setDead();
        }
        AxisAlignedBB axisalignedbb = boundingBox.expand(16.0D, 16.0D, 16.0D);
        List list1 = worldObj.getEntitiesWithinAABB(Entity.class, axisalignedbb);
        if (!worldObj.isRemote && list1 != null && !list1.isEmpty())
        {
            Iterator iterator = list1.iterator();

            while (iterator.hasNext())
            {
                Entity entity = (Entity) iterator.next();
                if (entity != null && posX <= entity.posX + 0.5F && posX >= entity.posX - 0.5F && posZ <= entity.posZ + 0.5F && posZ >= entity.posZ - 0.5F)
                {
                    entity.attackEntityFrom(DamageSource.anvil, 100F);
                }
                if (entity instanceof EntityThrowable)
                {
                    ((EntityThrowable) entity).setDead();
                }
            }
        }
    }

    @Override
    public int getFXLayer()
    {
        return 2;
    }

}
