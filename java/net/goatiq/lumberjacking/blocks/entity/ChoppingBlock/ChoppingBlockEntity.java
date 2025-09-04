package net.goatiq.lumberjacking.blocks.entity.ChoppingBlock;

import net.goatiq.lumberjacking.Lumberjacking;
import net.goatiq.lumberjacking.RecipeManager.ChoppingBlockManager;
import net.goatiq.lumberjacking.RecipeManager.ChoppingBlockManagerRecipeInput;
import net.goatiq.lumberjacking.RecipeManager.ChoppingBlockRecipe;
import net.goatiq.lumberjacking.blocks.entity.BlockEntitiesClass;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class ChoppingBlockEntity extends BlockEntity
{
    public RenderClass Render = new RenderClass();
    private Optional<List<RecipeEntry<ChoppingBlockRecipe>>> RecipesFound = Optional.empty();
    private RecipeEntry<ChoppingBlockRecipe> ActiveRecipe;
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1,ItemStack.EMPTY);
    private int ChoppingProgress = 0;
    private long TimeTriggered = 0L;
    private int Durability = 0;

    private boolean TickChoppingEnt()
    {
        if (this.world != null)
        {
            if (this.TimeTriggered <= this.world.getTime() - 2)
            {
                this.TimeTriggered = this.world.getTime();
                return true;

            } else {

                return false;
            }
        } else {

            return false;
        }
    }

    private boolean HasRecipe(PlayerEntity player, BlockPos pos)
    {
        if (this.getWorld() != null)
        {
            this.RecipesFound = Optional.of(this.getWorld().getRecipeManager().getAllMatches(ChoppingBlockManager.CHOPPING_RECIPE_TYPE, new ChoppingBlockManagerRecipeInput(this.inventory.get(0)), this.getWorld()));
            if (this.RecipesFound.isPresent())
            {
                for (int i = 0; i < this.RecipesFound.get().size(); i++)
                {
                    if (this.RecipesFound.get().get(i).value().Tool().test(player.getMainHandStack()))
                    {
                        if(ActiveRecipe != this.RecipesFound.get().get(i))
                        {
                            this.ActiveRecipe = this.RecipesFound.get().get(i);
                            this.ChoppingProgress = 0;
                        }

                        if(player.getMainHandStack().isDamageable())
                        {
                            player.getMainHandStack().damage((this.ActiveRecipe.value().ToolDamage()),player, EquipmentSlot.MAINHAND);
                        }
                        return true;
                    }
                }
            }
            return false;

        } else {

            return false;

        }
    }

    private void Chop(PlayerEntity player, BlockPos pos)
    {
        this.ChoppingProgress = this.ChoppingProgress + 1;
        if (this.ChoppingProgress < this.ActiveRecipe.value().ChopAmount())
        {
            this.world.playSound(null, pos, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 0.8f, 0.8f);
            this.Render.SetRenderUp(true);
            this.Render.SetRenderRotation(ThreadLocalRandom.current().nextInt(-15, 16));

        } else {

            this.ChoppingProgress = 0;
            this.CraftOutput(player, world, pos);
        }

        if(this.Durability != -1)
        {
            this.Durability = this.Durability - 1;
            if(this.Durability <= 0)
            {
                this.Destroy();
            }
        }
    }

    private void CraftOutput(PlayerEntity player, World world, BlockPos pos)
    {
        this.inventory.set(0, ItemStack.EMPTY);
        world.playSound(null, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.5f, 1f);
        ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), this.ActiveRecipe.value().OutputItemStack().copy());

        if(this.ActiveRecipe.value().ToolDamage() == -1)
        {
            player.getMainHandStack().decrement(1);
        }

        this.Reset();
    }

    private void Reset()
    {
        this.Render.Reset();
        this.TimeTriggered = 0L;
        this.ChoppingProgress = 0;
        this.inventory.set(0,ItemStack.EMPTY);
        this.RecipesFound = Optional.empty();
        this.Update();
    }

    private void Destroy()
    {
        if(this.world !=null)
        {
            this.world.removeBlock(this.pos,false);
            this.world.playSound(null,this.pos, SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR,SoundCategory.BLOCKS,0.2f,0.9f);
        }
    }

    public DefaultedList<ItemStack> GetInventory()
    {
        return this.inventory;
    }

    public ChoppingBlockEntity(BlockPos pos, BlockState state)
    {
        super(BlockEntitiesClass.CHOPPING_BLOCK_ENTITY, pos, state);

    }

    public ChoppingBlockEntity(BlockPos pos, BlockState state, int Durability)
    {
        super(BlockEntitiesClass.CHOPPING_BLOCK_ENTITY, pos, state);
        this.Durability = Durability;

    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup){
        return createNbt(registryLookup);
    }
    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);

        nbt.put("Item",this.inventory.get(0).encodeAllowEmpty(registryLookup));
        nbt.putInt("ChoppingProgress",this.ChoppingProgress);
        nbt.putInt("RenderRotation",this.Render.RenderRotation);
        nbt.putFloat("RenderHeight",this.Render.RenderHeight);
        nbt.putBoolean("Rendering",this.Render.GetRenderUp());
        nbt.putInt("Durability",this.Durability);
        nbt.putLong("TimeTriggered",this.TimeTriggered);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);

        this.ChoppingProgress = nbt.getInt("ChoppingProgress");
        this.Render.RenderRotation = nbt.getInt("RenderRotation");
        this.Render.RenderHeight = nbt.getFloat("RenderHeight");
        this.Render.SetRenderUp(nbt.getBoolean("Rendering"));
        this.Durability = nbt.getInt("Durability");
        this.TimeTriggered = nbt.getLong("TimeTriggered");
        this.inventory.set(0,ItemStack.fromNbtOrEmpty(registryLookup,nbt.getCompound("Item")));
        Lumberjacking.LOGGER.info("nbt value is : " + nbt.toString());
    }

    public boolean TriggerChoppingEnt(PlayerEntity player, BlockPos pos)
    {
        if(!this.world.isClient())
        {
            if(this.TickChoppingEnt())
            {
                if(player.isSneaking())
                {
                    if(!this.inventory.get(0).isEmpty())
                    {
                        player.getInventory().insertStack(this.inventory.get(0).copy());
                        this.Reset();
                        this.world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.5f, 1f);
                    }

                } else {

                    if(player.getMainHandStack().getItem() != Items.AIR)
                    {
                        if(this.inventory.get(0).isEmpty())
                        {
                            ItemStack BufferItemStack = player.getMainHandStack().copy();
                            BufferItemStack.setCount(1);
                            this.inventory.set(0,BufferItemStack);
                            player.getMainHandStack().decrement(1);
                            this.world.playSound(null, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 0.8f, 1f);
                            return true;

                        } else {

                            if (this.HasRecipe(player, pos))
                            {
                                this.Chop(player, pos);
                                return true;
                            }
                        }
                    }
                }

            }
        }
        return false;
    }
    public void Update() {
        if (this.world != null)
        {
            markDirty();
            this.world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        }
    }

    public void tick()
    {
        if(this.Render.Animate())
        {
            this.Update();
        }
    }

    public static class RenderClass {

        private boolean RenderUp = false;
        private float RenderHeight = 0.0f;
        private int RenderRotation = 0;

        public void Reset()
        {
            this.RenderUp = false;
            this.RenderHeight = 0.0f;
            this.RenderRotation = 0;
        }

        public boolean Animate()
        {
            if (this.RenderUp) {

                if (this.RenderHeight < 0.030f)
                {
                    this.RenderHeight = this.RenderHeight + 0.015f;

                } else {

                    this.RenderUp = false;

                }

            } else {

                if (this.RenderHeight > 0.00f)
                {
                    this.RenderHeight = this.RenderHeight - 0.015f;

                } else {

                    return false;
                }
            }
            return true;
        }

        public boolean GetRenderUp()
        {
            return this.RenderUp;
        }

        public void SetRenderUp(boolean value)
        {
            this.RenderUp = value;
        }

        public float GetRenderHeight() {
            return this.RenderHeight;
        }

        public void SetRenderRotation(int value)
        {
            this.RenderRotation = value;
        }

        public int GetRenderRotation()
        {
            return this.RenderRotation;
        }
    }
}

