package net.goatiq.lumberjacking.Compat.REI;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.goatiq.lumberjacking.RecipeManager.ChoppingBlockRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;

import java.util.List;

public class ChoppingBlockDisplay extends BasicDisplay
{
    public RecipeEntry<ChoppingBlockRecipe> RecipeFound;

    public ChoppingBlockDisplay(RecipeEntry<ChoppingBlockRecipe> recipe)
    {
        super(
                List.of(
                        EntryIngredients.ofIngredient(recipe.value().InputItem()),
                        EntryIngredients.ofIngredient(recipe.value().Tool())),
                List.of(EntryIngredient.of(EntryStacks.of(recipe.value().OutputItemStack()))));
        this.RecipeFound = recipe;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return ChoppingBlockCategory.CHOPPING_BLOCK_DISPLAY;
    }
}
