package net.goatiq.lumberjacking.RecipeManager;

import net.goatiq.lumberjacking.Lumberjacking;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ChoppingBlockManager {

    private static final String RecipeManagerName = "chopping_block";

    public static final RecipeSerializer<ChoppingBlockRecipe> CHOPPING_RECIPE_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Lumberjacking.Namespace,RecipeManagerName),new ChoppingBlockRecipe.Serializer()
    );

    public static final RecipeType<ChoppingBlockRecipe> CHOPPING_RECIPE_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(Lumberjacking.Namespace, RecipeManagerName), new RecipeType<ChoppingBlockRecipe>()
            {
                @Override
                public String toString()
                {
                    return RecipeManagerName;
                }
            }
    );

    public static void InitRecipe()
    {
        Lumberjacking.LOGGER.info("Adding custom recipes from " + RecipeManagerName + " from mod : " + Lumberjacking.Namespace);
    }

}
