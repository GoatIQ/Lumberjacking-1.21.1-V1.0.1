package net.goatiq.lumberjacking.GeneratedData;

import net.goatiq.lumberjacking.items.ItemClass;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    private String GeneratePath(Item item)
    {
        return item.getTranslationKey().substring(item.getTranslationKey().lastIndexOf('.')+1);
    }

    private void GenSolo(RecipeExporter exporter)
    {

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS,Items.CRAFTING_TABLE)
                .pattern("A")
                .input('A', ItemClass.PLANKS_BLOCK)
                .criterion("has_input", conditionsFromTag(TagKey.of(RegistryKeys.ITEM, Identifier.of("minecraft:planks"))))
                .offerTo(exporter, "minecraft/" + GeneratePath(Items.CRAFTING_TABLE));
    }

    private void GenMultiple(RecipeExporter exporter)
    {
        int a = 0;
        Item input = Items.AIR;
        Item[] output = new Item[10];

        for (int i = 0; i < 11; i++) {
            a = i;
            switch (i) {
                case 0:
                    input = ItemClass.OAK_PLANK;
                    output[0] = Items.OAK_PLANKS;
                    output[1] = Items.OAK_STAIRS;
                    output[2] = Items.OAK_FENCE;
                    output[3] = Items.OAK_FENCE_GATE;
                    output[4] = Items.OAK_DOOR;
                    output[5] = Items.OAK_SLAB;
                    output[6] = Items.OAK_BUTTON;
                    output[7] = Items.OAK_BOAT;
                    output[8] = Items.OAK_SIGN;
                    output[9] = Items.OAK_HANGING_SIGN;
                    break;

                case 1:

                    input = ItemClass.SPRUCE_PLANK;
                    output[0] = Items.SPRUCE_PLANKS;
                    output[1] = Items.SPRUCE_STAIRS;
                    output[2] = Items.SPRUCE_FENCE;
                    output[3] = Items.SPRUCE_FENCE_GATE;
                    output[4] = Items.SPRUCE_DOOR;
                    output[5] = Items.SPRUCE_SLAB;
                    output[6] = Items.SPRUCE_BUTTON;
                    output[7] = Items.SPRUCE_BOAT;
                    output[8] = Items.SPRUCE_SIGN;
                    output[9] = Items.SPRUCE_HANGING_SIGN;
                    break;

                case 2:

                    input = ItemClass.BIRCH_PLANK;
                    output[0] = Items.BIRCH_PLANKS;
                    output[1] = Items.BIRCH_STAIRS;
                    output[2] = Items.BIRCH_FENCE;
                    output[3] = Items.BIRCH_FENCE_GATE;
                    output[4] = Items.BIRCH_DOOR;
                    output[5] = Items.BIRCH_SLAB;
                    output[6] = Items.BIRCH_BUTTON;
                    output[7] = Items.BIRCH_BOAT;
                    output[8] = Items.BIRCH_SIGN;
                    output[9] = Items.BIRCH_HANGING_SIGN;
                    break;

                case 3:

                    input = ItemClass.JUNGLE_PLANK;
                    output[0] = Items.JUNGLE_PLANKS;
                    output[1] = Items.JUNGLE_STAIRS;
                    output[2] = Items.JUNGLE_FENCE;
                    output[3] = Items.JUNGLE_FENCE_GATE;
                    output[4] = Items.JUNGLE_DOOR;
                    output[5] = Items.JUNGLE_SLAB;
                    output[6] = Items.JUNGLE_BUTTON;
                    output[7] = Items.JUNGLE_BOAT;
                    output[8] = Items.JUNGLE_SIGN;
                    output[9] = Items.JUNGLE_HANGING_SIGN;
                    break;

                case 4:

                    input = ItemClass.ACACIA_PLANK;
                    output[0] = Items.ACACIA_PLANKS;
                    output[1] = Items.ACACIA_STAIRS;
                    output[2] = Items.ACACIA_FENCE;
                    output[3] = Items.ACACIA_FENCE_GATE;
                    output[4] = Items.ACACIA_DOOR;
                    output[5] = Items.ACACIA_SLAB;
                    output[6] = Items.ACACIA_BUTTON;
                    output[7] = Items.ACACIA_BOAT;
                    output[8] = Items.ACACIA_SIGN;
                    output[9] = Items.ACACIA_HANGING_SIGN;
                    break;

                case 5:

                    input = ItemClass.DARK_OAK_PLANK;
                    output[0] = Items.DARK_OAK_PLANKS;
                    output[1] = Items.DARK_OAK_STAIRS;
                    output[2] = Items.DARK_OAK_FENCE;
                    output[3] = Items.DARK_OAK_FENCE_GATE;
                    output[4] = Items.DARK_OAK_DOOR;
                    output[5] = Items.DARK_OAK_SLAB;
                    output[6] = Items.DARK_OAK_BUTTON;
                    output[7] = Items.DARK_OAK_BOAT;
                    output[8] = Items.DARK_OAK_SIGN;
                    output[9] = Items.DARK_OAK_HANGING_SIGN;
                    break;

                case 6:

                    input = ItemClass.MANGROVE_PLANK;
                    output[0] = Items.MANGROVE_PLANKS;
                    output[1] = Items.MANGROVE_STAIRS;
                    output[2] = Items.MANGROVE_FENCE;
                    output[3] = Items.MANGROVE_FENCE_GATE;
                    output[4] = Items.MANGROVE_DOOR;
                    output[5] = Items.MANGROVE_SLAB;
                    output[6] = Items.MANGROVE_BUTTON;
                    output[7] = Items.MANGROVE_BOAT;
                    output[8] = Items.MANGROVE_SIGN;
                    output[9] = Items.MANGROVE_HANGING_SIGN;
                    break;

                case 7:

                    input = ItemClass.CHERRY_PLANK;
                    output[0] = Items.CHERRY_PLANKS;
                    output[1] = Items.CHERRY_STAIRS;
                    output[2] = Items.CHERRY_FENCE;
                    output[3] = Items.CHERRY_FENCE_GATE;
                    output[4] = Items.CHERRY_DOOR;
                    output[5] = Items.CHERRY_SLAB;
                    output[6] = Items.CHERRY_BUTTON;
                    output[7] = Items.CHERRY_BOAT;
                    output[8] = Items.CHERRY_SIGN;
                    output[9] = Items.CHERRY_HANGING_SIGN;
                    break;

                case 8:

                    input = ItemClass.BAMBOO_PLANK;
                    output[0] = Items.BAMBOO_PLANKS;
                    output[1] = Items.BAMBOO_STAIRS;
                    output[2] = Items.BAMBOO_FENCE;
                    output[3] = Items.BAMBOO_FENCE_GATE;
                    output[4] = Items.BAMBOO_DOOR;
                    output[5] = Items.BAMBOO_SLAB;
                    output[6] = Items.BAMBOO_BUTTON;
                    //output[7] = Items.BAMBOO_BOAT; not existing
                    output[8] = Items.BAMBOO_SIGN;
                    output[9] = Items.BAMBOO_HANGING_SIGN;
                    break;
                case 9:

                    input = ItemClass.CRIMSON_PLANK;
                    output[0] = Items.CRIMSON_PLANKS;
                    output[1] = Items.CRIMSON_STAIRS;
                    output[2] = Items.CRIMSON_FENCE;
                    output[3] = Items.CRIMSON_FENCE_GATE;
                    output[4] = Items.CRIMSON_DOOR;
                    output[5] = Items.CRIMSON_SLAB;
                    output[6] = Items.CRIMSON_BUTTON;
                    //output[7] = Items.CRIMSON_BOAT; not existing
                    output[8] = Items.CRIMSON_SIGN;
                    output[9] = Items.CRIMSON_HANGING_SIGN;
                    break;
                case 10:

                    input = ItemClass.WARPED_PLANK;
                    output[0] = Items.WARPED_PLANKS;
                    output[1] = Items.WARPED_STAIRS;
                    output[2] = Items.WARPED_FENCE;
                    output[3] = Items.WARPED_FENCE_GATE;
                    output[4] = Items.WARPED_DOOR;
                    output[5] = Items.WARPED_SLAB;
                    output[6] = Items.WARPED_BUTTON;
                    //output[7] = Items.WARPED_BOAT; not existing
                    output[8] = Items.WARPED_SIGN;
                    output[9] = Items.WARPED_HANGING_SIGN;
                    break;

            }

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output[0])
                    .pattern("AA")
                    .pattern("AA")
                    .input('A', input)
                    .criterion("has_input", conditionsFromItem(input))
                    .offerTo(exporter, "minecraft/" + GeneratePath(output[0]));

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output[1])
                    .pattern("A ")
                    .pattern("AA")
                    .input('A', input)
                    .criterion("has_input", conditionsFromItem(input))
                    .offerTo(exporter, "minecraft/" + GeneratePath(output[1]));

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output[2])
                    .pattern("A A")
                    .pattern("ABA")
                    .pattern("A A")
                    .input('A', input)
                    .input('B', Items.STICK)
                    .criterion("has_input", conditionsFromItem(input))
                    .offerTo(exporter, "minecraft/" + GeneratePath(output[2]));

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output[3])
                    .pattern("B B")
                    .pattern("ABA")
                    .pattern("B B")
                    .input('A', input)
                    .input('B', Items.STICK)
                    .criterion("has_input", conditionsFromItem(input))
                    .offerTo(exporter, "minecraft/" + GeneratePath(output[3]));

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output[4])
                    .pattern("AA")
                    .pattern("AA")
                    .pattern("AA")
                    .input('A', input)
                    .criterion("has_input", conditionsFromItem(input))
                    .offerTo(exporter, "minecraft/" + GeneratePath(output[4]));

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output[5])
                    .pattern("AA")
                    .input('A', input)
                    .criterion("has_input", conditionsFromItem(input))
                    .offerTo(exporter, "minecraft/" + GeneratePath(output[5]));

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output[6])
                    .pattern("A")
                    .input('A', input)
                    .criterion("has_input", conditionsFromItem(input))
                    .offerTo(exporter, "minecraft/" + GeneratePath(output[6]));

            if (!(a > 7 && a < 11)) {
                ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output[7])
                        .pattern("B B")
                        .pattern("A A")
                        .pattern("AAA")
                        .input('A', input)
                        .input('B', Items.WOODEN_SHOVEL)
                        .criterion("has_input", conditionsFromItem(input))
                        .offerTo(exporter, "minecraft/" + GeneratePath(output[7]));
            }

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output[8], 3)
                    .pattern("AAA")
                    .pattern("AAA")
                    .pattern(" B ")
                    .input('A', input)
                    .input('B', Items.STICK)
                    .criterion("has_input", conditionsFromItem(input))
                    .offerTo(exporter, "minecraft/" + GeneratePath(output[8]));

            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output[9], 3)
                    .pattern("B B")
                    .pattern("AAA")
                    .pattern("AAA")
                    .input('A', input)
                    .input('B', Items.CHAIN)
                    .criterion("has_input", conditionsFromItem(input))
                    .offerTo(exporter, "minecraft/" + GeneratePath(output[9]));
        }
    }

    @Override
    public void generate(RecipeExporter exporter)
    {
        GenMultiple(exporter);
        GenSolo(exporter);
    }
}