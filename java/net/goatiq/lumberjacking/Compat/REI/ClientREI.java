package net.goatiq.lumberjacking.Compat.REI;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.goatiq.lumberjacking.RecipeManager.ChoppingBlockManager;
import net.goatiq.lumberjacking.RecipeManager.ChoppingBlockRecipe;
import net.goatiq.lumberjacking.blocks.BlockClass;


public class ClientREI implements REIClientPlugin
{
    @Override
    public void registerCategories(CategoryRegistry registry)
    {
        registry.add(new ChoppingBlockCategory());

        registry.addWorkstations(ChoppingBlockCategory.CHOPPING_BLOCK_DISPLAY, EntryStacks.of(BlockClass.OAK_CHOPPING_BLOCK));
        registry.addWorkstations(ChoppingBlockCategory.CHOPPING_BLOCK_DISPLAY, EntryStacks.of(BlockClass.SPRUCE_CHOPPING_BLOCK));
        registry.addWorkstations(ChoppingBlockCategory.CHOPPING_BLOCK_DISPLAY, EntryStacks.of(BlockClass.BIRCH_CHOPPING_BLOCK));
        registry.addWorkstations(ChoppingBlockCategory.CHOPPING_BLOCK_DISPLAY, EntryStacks.of(BlockClass.JUNGLE_CHOPPING_BLOCK));
        registry.addWorkstations(ChoppingBlockCategory.CHOPPING_BLOCK_DISPLAY, EntryStacks.of(BlockClass.ACACIA_CHOPPING_BLOCK));
        registry.addWorkstations(ChoppingBlockCategory.CHOPPING_BLOCK_DISPLAY, EntryStacks.of(BlockClass.DARK_OAK_CHOPPING_BLOCK));
        registry.addWorkstations(ChoppingBlockCategory.CHOPPING_BLOCK_DISPLAY, EntryStacks.of(BlockClass.MANGROVE_CHOPPING_BLOCK));
        registry.addWorkstations(ChoppingBlockCategory.CHOPPING_BLOCK_DISPLAY, EntryStacks.of(BlockClass.CHERRY_CHOPPING_BLOCK));
        registry.addWorkstations(ChoppingBlockCategory.CHOPPING_BLOCK_DISPLAY, EntryStacks.of(BlockClass.BAMBOO_CHOPPING_BLOCK));
        registry.addWorkstations(ChoppingBlockCategory.CHOPPING_BLOCK_DISPLAY, EntryStacks.of(BlockClass.CRIMSON_CHOPPING_BLOCK));
        registry.addWorkstations(ChoppingBlockCategory.CHOPPING_BLOCK_DISPLAY, EntryStacks.of(BlockClass.WARPED_CHOPPING_BLOCK));

    }

    @Override
    public void registerDisplays(DisplayRegistry registry)
    {
        registry.registerRecipeFiller(
                ChoppingBlockRecipe.class,
                ChoppingBlockManager.CHOPPING_RECIPE_TYPE,
                ChoppingBlockDisplay::new);
    }
}
