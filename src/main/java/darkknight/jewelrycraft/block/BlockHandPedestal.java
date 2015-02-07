package darkknight.jewelrycraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import darkknight.jewelrycraft.tileentity.TileEntityHandPedestal;

public class BlockHandPedestal extends BlockContainer
{
    protected BlockHandPedestal(Material material)
    {
        super(material);
        setBlockBounds(0.2F, 0F, 0.2F, 0.8F, 1.0F, 0.8F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2)
    {
        return new TileEntityHandPedestal();
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9)
    {
        TileEntityHandPedestal te = (TileEntityHandPedestal) world.getTileEntity(i, j, k);
        ItemStack item = entityPlayer.inventory.getCurrentItem();
        if (te != null)
        {
            if (!world.isRemote && te.getHeldItemStack() == null && item != null)
            {
                te.setHeldItemStack(item.copy());
                te.closeHand();
                if (!entityPlayer.capabilities.isCreativeMode)
                {
                    item.stackSize--;
                }
                te.markDirty();
            } else if (entityPlayer.isSneaking())
            {
                if (entityPlayer.inventory.addItemStackToInventory(te.getHeldItemStack()))
                {
                    te.removeHeldItemStack();
                    te.openHand();
                }
            }
            te.markDirty();
        }
        return true;
    }

    public void dropItem(World world, double x, double y, double z, ItemStack stack)
    {
        EntityItem entityitem = new EntityItem(world, x + 0.5, y + 1.5, z + 0.5, stack);
        entityitem.motionX = 0;
        entityitem.motionZ = 0;
        entityitem.motionY = 0.11000000298023224D;
        world.spawnEntityInWorld(entityitem);
    }

    @Override
    public void breakBlock(World world, int i, int j, int k, Block block, int par6)
    {
        TileEntityHandPedestal te = (TileEntityHandPedestal) world.getTileEntity(i, j, k);

        if (te != null && te.getHeldItemStack() != null)
        {
            dropItem(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord, te.getHeldItemStack());
            world.removeTileEntity(i, j, k);
        }

        super.breakBlock(world, i, j, k, block, par6);
    }

    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityLiving, ItemStack par6ItemStack)
    {
        int rotation = MathHelper.floor_double(entityLiving.rotationYaw * 8 / 360 + 0.5) & 7;
        world.setBlockMetadataWithNotify(i, j, k, rotation, 2);
    }

    @Override
    public void registerBlockIcons(IIconRegister icon)
    {
        blockIcon = icon.registerIcon("minecraft:stonebrick");
    }
}
