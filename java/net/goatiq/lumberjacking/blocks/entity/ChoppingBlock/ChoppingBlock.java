package net.goatiq.lumberjacking.blocks.entity.ChoppingBlock;

import com.mojang.serialization.MapCodec;
import net.goatiq.lumberjacking.blocks.entity.BlockEntitiesClass;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ChoppingBlock extends BlockWithEntity implements BlockEntityProvider {

    private int DefDurability = 0;

    private static final VoxelShape SHAPE = ChoppingBlock.createCuboidShape(0,0,0,16,8,16);

    public void SetDurability(Float value)
    {
        this.DefDurability = value.intValue();
    }

    public String GetCompressedTransKey()
    {
        return this.getTranslationKey().substring(this.getTranslationKey().lastIndexOf('.')+1);
    }

    public static final MapCodec<ChoppingBlock> CODEC = ChoppingBlock.createCodec(ChoppingBlock::new);

    public ChoppingBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state)
    {
        return new ChoppingBlockEntity(pos,state,this.DefDurability);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        if (world.isClient()){
            return null;
        }
        return validateTicker(type, BlockEntitiesClass.CHOPPING_BLOCK_ENTITY,
                ((world1, pos, state1, blockEntity) -> blockEntity.tick()));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }



    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(state.getBlock() != newState.getBlock())
        {

            if(world.getBlockEntity(pos) instanceof ChoppingBlockEntity choppingBlockEntity)
            {
                ItemScatterer.spawn(world,pos.getX(),pos.getY(),pos.getZ(), choppingBlockEntity.GetInventory().get(0));
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state,world,pos,newState,moved);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        if (world.getBlockEntity(pos) instanceof ChoppingBlockEntity choppingBlockEntity) {
            if(choppingBlockEntity.TriggerChoppingEnt(player,pos))
            {
                choppingBlockEntity.Update();
                return ActionResult.SUCCESS;

            } else {

                choppingBlockEntity.Update();
                return ActionResult.success(false);
            }

        } else {

            return ActionResult.success(false);

        }
    }
}
