package net.goatiq.lumberjacking.mixin;

import net.goatiq.lumberjacking.Lumberjacking;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@org.spongepowered.asm.mixin.Mixin(AbstractBlock.AbstractBlockState.class)
public class Mixin
{
	//this code modifies the strength value of the block making it harder or softer
	@Inject(
			method = "getHardness(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)F",
			at = @At("HEAD"),
			cancellable = true
	)
	private void injected(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> ActualStrength)
	{
		if(Lumberjacking.ConfigData.getOrDefault("enable",0.0f) == 1)
		{
			SetBlockStrength(world,pos,ActualStrength,BlockTags.OAK_LOGS);
			SetBlockStrength(world,pos,ActualStrength,BlockTags.SPRUCE_LOGS);
			SetBlockStrength(world,pos,ActualStrength,BlockTags.BIRCH_LOGS);
			SetBlockStrength(world,pos,ActualStrength,BlockTags.JUNGLE_LOGS);
			SetBlockStrength(world,pos,ActualStrength,BlockTags.ACACIA_LOGS);
			SetBlockStrength(world,pos,ActualStrength,BlockTags.DARK_OAK_LOGS);
			SetBlockStrength(world,pos,ActualStrength,BlockTags.MANGROVE_LOGS);
			SetBlockStrength(world,pos,ActualStrength,BlockTags.CHERRY_LOGS);
			SetBlockStrength(world,pos,ActualStrength,BlockTags.CRIMSON_STEMS);
			SetBlockStrength(world,pos,ActualStrength,BlockTags.WARPED_STEMS);
		}
	}

	private void SetBlockStrength(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> ActualStrength, TagKey<Block> Tag)
	{
		if (world.getBlockState(pos).isIn(Tag))
		{
			ActualStrength.setReturnValue(Lumberjacking.ConfigData.getOrDefault(Tag.getTranslationKey().substring(Tag.getTranslationKey().lastIndexOf('.')+1),2.0f));
		}
	}
}
