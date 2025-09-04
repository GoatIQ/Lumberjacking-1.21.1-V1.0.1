package net.goatiq.lumberjacking.RecipeManager;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.minecraft.network.codec.PacketCodecs;

public record ChoppingBlockRecipe(Ingredient InputItem, ItemStack OutputItemStack, Ingredient Tool, Integer ChopAmount,Integer ToolDamage) implements Recipe<ChoppingBlockManagerRecipeInput>
{
    @Override
    public DefaultedList<Ingredient> getIngredients()
    {
       DefaultedList<Ingredient> list = DefaultedList.of();
       list.add(this.InputItem);
       list.add(this.Tool);
       return list;
    }

    @Override
    public boolean matches(ChoppingBlockManagerRecipeInput input, World world)
    {
        if(world.isClient()) {
            return false;
        }

        return InputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(ChoppingBlockManagerRecipeInput input, RegistryWrapper.WrapperLookup lookup)
    {
        return OutputItemStack.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup)
    {
        return OutputItemStack;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ChoppingBlockManager.CHOPPING_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ChoppingBlockManager.CHOPPING_RECIPE_TYPE;
    }

    public static class Serializer implements RecipeSerializer<ChoppingBlockRecipe>
    {

        public static final MapCodec<ChoppingBlockRecipe> CODEC = RecordCodecBuilder.mapCodec(inst ->inst.group(
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient").forGetter(ChoppingBlockRecipe::InputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(ChoppingBlockRecipe::OutputItemStack),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("tool").forGetter(ChoppingBlockRecipe::Tool),
                Codec.INT.fieldOf("chop_amount").forGetter(ChoppingBlockRecipe::ChopAmount),
                Codec.INT.fieldOf("tool_damage").forGetter(ChoppingBlockRecipe::ChopAmount)).apply(inst, ChoppingBlockRecipe::new)
        );

        public static final PacketCodec<RegistryByteBuf, ChoppingBlockRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, ChoppingBlockRecipe::InputItem,
                        ItemStack.PACKET_CODEC, ChoppingBlockRecipe::OutputItemStack,
                        Ingredient.PACKET_CODEC, ChoppingBlockRecipe::Tool,
                        PacketCodecs.VAR_INT,ChoppingBlockRecipe::ChopAmount,
                        PacketCodecs.VAR_INT,ChoppingBlockRecipe::ToolDamage,
                        ChoppingBlockRecipe::new
                );

        @Override
        public MapCodec<ChoppingBlockRecipe>codec(){
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf,ChoppingBlockRecipe> packetCodec(){
            return STREAM_CODEC;
        }

    }
}
