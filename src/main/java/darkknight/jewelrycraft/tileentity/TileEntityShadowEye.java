package darkknight.jewelrycraft.tileentity;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkknight.jewelrycraft.block.BlockHandPedestal;
import darkknight.jewelrycraft.block.BlockList;
import darkknight.jewelrycraft.particles.EntityShadowsFX;
import darkknight.jewelrycraft.util.JewelryNBT;
import darkknight.jewelrycraft.util.PlayerUtils;

public class TileEntityShadowEye extends TileEntity
{
    public int opening, timer, t = 20;
    public boolean active;
    public int field_145926_a;
    public float field_145933_i;
    public float field_145931_j;
    public float field_145930_m;
    public float field_145927_n;
    public ArrayList<ItemStack> pedestalItems = new ArrayList<ItemStack>();

    public TileEntityShadowEye()
    {
        opening = 1;
        timer = 20;
        active = false;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("opening", opening);
        nbt.setInteger("timer", timer);
        nbt.setBoolean("active", active);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        opening = nbt.getInteger("opening");
        timer = nbt.getInteger("timer");
        active = nbt.getBoolean("active");
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        boolean valid = isValidStructure(worldObj, xCoord, yCoord, zCoord, blockMetadata);
        if (active)
        {
            timer--;
        }
        if (opening == 4 && timer <= 0)
        {
            active = false;
        }
        if (!active && timer <= 0 && opening != 1)
        {
            if (t > 0)
            {
                t--;
            }
            if (t <= 0)
            {
                opening--;
                t = 20;
            }
        }
        if (opening == 4)
        {
            addData(worldObj, xCoord, yCoord, zCoord);
            TileEntityHandPedestal target = (TileEntityHandPedestal) worldObj.getTileEntity(xCoord, yCoord - 3, zCoord);
            if (target != null && target.getHeldItemStack() != null)
            {
                JewelryNBT.addModifiers(target.getHeldItemStack(), pedestalItems);
            }
        }
        if (active && timer <= 0)
        {
            if (opening < 4)
            {
                opening++;
                timer = 20;
            }
            if (valid && opening == 4)
            {
                timer = 200;
            } else if (!valid)
            {
                active = false;
                timer = -1;
            }
        }
        EntityPlayer player1 = worldObj.getClosestPlayer(xCoord, yCoord, zCoord, 7F);
        if (player1 != null)
        {
            NBTTagCompound persistTag = PlayerUtils.getModPlayerPersistTag(player1, "Jewelrycraft");
            persistTag.setBoolean("nearStartedRitual", false);
        }
        if (active && opening == 4)
        {
            float din = 6F;
            int i = Minecraft.getMinecraft().gameSettings.particleSetting;

            for (float x = -din; x <= din; x += 0.2F)
            {
                for (float z = -din; z <= din; z += 0.2F)
                {
                    if (x * x + z * z >= din * din - 1 && x * x + z * z <= din * din + 1)
                    {
                        Minecraft.getMinecraft().effectRenderer.addEffect(new EntityShadowsFX(worldObj, xCoord + x + 0.5F, yCoord - 0.5F, zCoord + z + 0.5F, 15F, 0.04F - 0.01F * i));
                    }
                }
            }

            for (int l = 0; l <= 2 - i; l++)
            {
                worldObj.spawnParticle("depthsuspend", xCoord + 6.5F - worldObj.rand.nextInt(9) - worldObj.rand.nextFloat(), yCoord - 2F + worldObj.rand.nextFloat(), zCoord + 6.5F - worldObj.rand.nextInt(9) - worldObj.rand.nextFloat(), 0, 0, 0);
            }
            EntityPlayer player = worldObj.getClosestPlayer(xCoord, yCoord, zCoord, 6F);
            if (player != null)
            {
                NBTTagCompound persistTag = PlayerUtils.getModPlayerPersistTag(player, "Jewelrycraft");
                persistTag.setBoolean("nearStartedRitual", true);
            }
        }
    }

    public boolean isValidStructure(World world, int x, int y, int z, int metadata)
    {
        if (world.getBlockMetadata(x, y, z) == 0 || world.getBlockMetadata(x, y, z) == 2)
        {
            // Layers from top to bottom
            // 1st Layer
            if (world.getBlock(x, y + 1, z) != Blocks.stone_slab || world.getBlockMetadata(x, y + 1, z) != 5)
            {
                return false;
            }
            if (world.getBlock(x + 1, y + 1, z) != Blocks.stone_slab || world.getBlockMetadata(x + 1, y + 1, z) != 5)
            {
                return false;
            }
            if (world.getBlock(x - 1, y + 1, z) != Blocks.stone_slab || world.getBlockMetadata(x - 1, y + 1, z) != 5)
            {
                return false;
            }
            // 2nd Layer
            if (world.getBlock(x + 2, y, z) != Blocks.stone_brick_stairs || world.getBlockMetadata(x + 2, y, z) != 1)
            {
                return false;
            }
            if (world.getBlock(x + 1, y, z) != Blocks.stone_brick_stairs || world.getBlockMetadata(x + 1, y, z) != 4)
            {
                return false;
            }
            if (world.getBlock(x - 1, y, z) != Blocks.stone_brick_stairs || world.getBlockMetadata(x - 1, y, z) != 5)
            {
                return false;
            }
            if (world.getBlock(x - 2, y, z) != Blocks.stone_brick_stairs || world.getBlockMetadata(x - 2, y, z) != 0)
            {
                return false;
            }
            // 3rd Layer
            if (world.getBlock(x + 2, y - 1, z) != Blocks.stonebrick)
            {
                return false;
            }
            if (world.getBlock(x - 2, y - 1, z) != Blocks.stonebrick)
            {
                return false;
            }
            // 4th Layer
            if (world.getBlock(x + 2, y - 2, z) != Blocks.stonebrick)
            {
                return false;
            }
            if (world.getBlock(x - 2, y - 2, z) != Blocks.stonebrick)
            {
                return false;
            }
            // 5th Layer
            if (world.getBlock(x + 2, y - 3, z) != Blocks.stonebrick)
            {
                return false;
            }
            if (world.getBlock(x - 2, y - 3, z) != Blocks.stonebrick)
            {
                return false;
            }
        } else if (world.getBlockMetadata(x, y, z) == 1 || world.getBlockMetadata(x, y, z) == 3)
        {
            // Layers from top to bottom
            // 1st Layer
            if (world.getBlock(x, y + 1, z) != Blocks.stone_slab || world.getBlockMetadata(x, y + 1, z) != 5)
            {
                return false;
            }
            if (world.getBlock(x, y + 1, z + 1) != Blocks.stone_slab || world.getBlockMetadata(x, y + 1, z + 1) != 5)
            {
                return false;
            }
            if (world.getBlock(x, y + 1, z - 1) != Blocks.stone_slab || world.getBlockMetadata(x, y + 1, z - 1) != 5)
            {
                return false;
            }
            // 2nd Layer
            if (world.getBlock(x, y, z + 2) != Blocks.stone_brick_stairs || world.getBlockMetadata(x, y, z + 2) != 3)
            {
                return false;
            }
            if (world.getBlock(x, y, z + 1) != Blocks.stone_brick_stairs || world.getBlockMetadata(x, y, z + 1) != 6)
            {
                return false;
            }
            if (world.getBlock(x, y, z - 1) != Blocks.stone_brick_stairs || world.getBlockMetadata(x, y, z - 1) != 7)
            {
                return false;
            }
            if (world.getBlock(x, y, z - 2) != Blocks.stone_brick_stairs || world.getBlockMetadata(x, y, z - 2) != 2)
            {
                return false;
            }
            // 3rd Layer
            if (world.getBlock(x, y - 1, z + 2) != Blocks.stonebrick)
            {
                return false;
            }
            if (world.getBlock(x, y - 1, z - 2) != Blocks.stonebrick)
            {
                return false;
            }
            // 4th Layer
            if (world.getBlock(x, y - 2, z + 2) != Blocks.stonebrick)
            {
                return false;
            }
            if (world.getBlock(x, y - 2, z - 2) != Blocks.stonebrick)
            {
                return false;
            }
            // 5th Layer
            if (world.getBlock(x, y - 3, z + 2) != Blocks.stonebrick)
            {
                return false;
            }
            if (world.getBlock(x, y - 3, z - 2) != Blocks.stonebrick)
            {
                return false;
            }
        }
        // 3rd Layer
        if (world.getBlock(x - 4, y - 1, z - 4) != BlockList.shadowBlock)
        {
            return false;
        }
        if (world.getBlock(x - 4, y - 1, z + 4) != BlockList.shadowBlock)
        {
            return false;
        }
        if (world.getBlock(x + 4, y - 1, z - 4) != BlockList.shadowBlock)
        {
            return false;
        }
        if (world.getBlock(x + 4, y - 1, z + 4) != BlockList.shadowBlock)
        {
            return false;
        }
        // 4th Layer
        if (world.getBlock(x - 4, y - 2, z - 4) != Blocks.stonebrick)
        {
            return false;
        }
        if (world.getBlock(x - 4, y - 2, z + 4) != Blocks.stonebrick)
        {
            return false;
        }
        if (world.getBlock(x + 4, y - 2, z - 4) != Blocks.stonebrick)
        {
            return false;
        }
        if (world.getBlock(x + 4, y - 2, z + 4) != Blocks.stonebrick)
        {
            return false;
        }
        // 5th Layer
        // Pillars
        if (world.getBlock(x - 4, y - 3, z - 4) != Blocks.stonebrick)
        {
            return false;
        }
        if (world.getBlock(x - 4, y - 3, z + 4) != Blocks.stonebrick)
        {
            return false;
        }
        if (world.getBlock(x + 4, y - 3, z - 4) != Blocks.stonebrick)
        {
            return false;
        }
        if (world.getBlock(x + 4, y - 3, z + 4) != Blocks.stonebrick)
        {
            return false;
        }
        // Pedestals
        if (!(world.getBlock(x, y - 3, z) instanceof BlockHandPedestal))
        {
            return false;
        }
        if (world.getBlock(x - 4, y - 3, z + 2) != BlockList.handPedestal || world.getBlockMetadata(x - 4, y - 3, z + 2) != 1)
        {
            return false;
        }
        if (world.getBlock(x - 5, y - 3, z) != BlockList.handPedestal || world.getBlockMetadata(x - 5, y - 3, z) != 2)
        {
            return false;
        }
        if (world.getBlock(x - 4, y - 3, z - 2) != BlockList.handPedestal || world.getBlockMetadata(x - 4, y - 3, z - 2) != 3)
        {
            return false;
        }

        if (world.getBlock(x - 2, y - 3, z - 4) != BlockList.handPedestal || world.getBlockMetadata(x - 2, y - 3, z - 4) != 3)
        {
            return false;
        }
        if (world.getBlock(x, y - 3, z - 5) != BlockList.handPedestal || world.getBlockMetadata(x, y - 3, z - 5) != 4)
        {
            return false;
        }
        if (world.getBlock(x + 2, y - 3, z - 4) != BlockList.handPedestal || world.getBlockMetadata(x + 2, y - 3, z - 4) != 5)
        {
            return false;
        }

        if (world.getBlock(x + 4, y - 3, z - 2) != BlockList.handPedestal || world.getBlockMetadata(x + 4, y - 3, z - 2) != 5)
        {
            return false;
        }
        if (world.getBlock(x + 5, y - 3, z) != BlockList.handPedestal || world.getBlockMetadata(x + 5, y - 3, z) != 6)
        {
            return false;
        }
        if (world.getBlock(x + 4, y - 3, z + 2) != BlockList.handPedestal || world.getBlockMetadata(x + 4, y - 3, z + 2) != 7)
        {
            return false;
        }

        if (world.getBlock(x + 2, y - 3, z + 4) != BlockList.handPedestal || world.getBlockMetadata(x + 2, y - 3, z + 4) != 7)
        {
            return false;
        }
        if (world.getBlock(x, y - 3, z + 5) != BlockList.handPedestal || world.getBlockMetadata(x, y - 3, z + 5) != 0)
        {
            return false;
        }
        if (world.getBlock(x - 2, y - 3, z + 4) != BlockList.handPedestal || world.getBlockMetadata(x - 2, y - 3, z + 4) != 1)
        {
            return false;
        }
        return true;
    }

    public void addData(World world, int x, int y, int z)
    {
        pedestalItems.clear();
        addPedestalInfo((TileEntityHandPedestal) world.getTileEntity(x + 2, y - 3, z - 4));
        addPedestalInfo((TileEntityHandPedestal) world.getTileEntity(x - 4, y - 3, z + 2));
        addPedestalInfo((TileEntityHandPedestal) world.getTileEntity(x, y - 3, z - 5));
        addPedestalInfo((TileEntityHandPedestal) world.getTileEntity(x - 2, y - 3, z - 4));
        addPedestalInfo((TileEntityHandPedestal) world.getTileEntity(x - 4, y - 3, z - 2));
        addPedestalInfo((TileEntityHandPedestal) world.getTileEntity(x - 5, y - 3, z));
        addPedestalInfo((TileEntityHandPedestal) world.getTileEntity(x + 4, y - 3, z - 2));
        addPedestalInfo((TileEntityHandPedestal) world.getTileEntity(x + 5, y - 3, z));
        addPedestalInfo((TileEntityHandPedestal) world.getTileEntity(x + 4, y - 3, z + 2));
        addPedestalInfo((TileEntityHandPedestal) world.getTileEntity(x + 2, y - 3, z + 4));
        addPedestalInfo((TileEntityHandPedestal) world.getTileEntity(x, y - 3, z + 5));
        addPedestalInfo((TileEntityHandPedestal) world.getTileEntity(x - 2, y - 3, z + 4));
    }

    public void addPedestalInfo(TileEntityHandPedestal pedestal)
    {
        ItemStack heldItemStack;
        if (pedestal != null && (heldItemStack = pedestal.getHeldItemStack()) != null)
        {
            if (pedestalItems.isEmpty())
            {
                pedestalItems.add(heldItemStack.copy());
            } else
            {
                boolean hasItem = false;
                int index = 0;
                for (int ind = 0; ind < pedestalItems.size() && !hasItem; ind++)
                {
                    if (heldItemStack.getItem().equals(pedestalItems.get(ind).getItem()) && heldItemStack.getItemDamage() == pedestalItems.get(ind).getItemDamage())
                    {
                        index = ind;
                        hasItem = true;
                        if (heldItemStack.hasTagCompound() && pedestalItems.get(ind).hasTagCompound() && !heldItemStack.getTagCompound().equals(pedestalItems.get(ind).getTagCompound()))
                        {
                            hasItem = false;

                        }
                    }
                }
                if (!hasItem)
                {
                    pedestalItems.add(heldItemStack.copy());
                } else
                {
                    ItemStack object = pedestalItems.get(index).copy();
                    object.stackSize++;
                    pedestalItems.set(index, object);
                }
            }
        }
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbttagcompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
        readFromNBT(packet.func_148857_g());
        worldObj.func_147479_m(xCoord, yCoord, zCoord);
    }
}
