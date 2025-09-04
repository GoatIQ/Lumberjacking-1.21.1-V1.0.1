package net.goatiq.lumberjacking.blocks;

import me.shedaniel.autoconfig.ConfigData;
import net.goatiq.lumberjacking.Lumberjacking;
import net.goatiq.lumberjacking.blocks.entity.ChoppingBlock.ChoppingBlock;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.slot.FurnaceFuelSlot;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class BlockClass {

    private static ChoppingBlock CreateChoppingBlock(float strength)
    {
        if(Lumberjacking.ConfigData.getOrDefault("enable",0.0f) != 1)
        {
            strength = 2.0f;
        }
        return new ChoppingBlock(AbstractBlock.Settings.create()
                .strength(strength)
                .burnable()
                .sounds(BlockSoundGroup.WOOD));
    }

    private static void RegisterChoppingBlock(String name, ChoppingBlock block)
    {
        Registry.register(Registries.ITEM,Identifier.of(Lumberjacking.Namespace, name),new BlockItem(block,new Item.Settings()));
        //Create the instance of an item to create the "item" for this block
        Registry.register(Registries.BLOCK, Identifier.of(Lumberjacking.Namespace, name), block);
        block.SetDurability(Lumberjacking.ConfigData.getOrDefault(block.GetCompressedTransKey(),-1.0f));
    }

    public static final ChoppingBlock OAK_CHOPPING_BLOCK = CreateChoppingBlock(Lumberjacking.ConfigData.getOrDefault("oak_logs",2.0f));
    public static final ChoppingBlock SPRUCE_CHOPPING_BLOCK = CreateChoppingBlock(Lumberjacking.ConfigData.getOrDefault("spruce_logs",2.0f));
    public static final ChoppingBlock BIRCH_CHOPPING_BLOCK = CreateChoppingBlock(Lumberjacking.ConfigData.getOrDefault("birch_logs",2.0f));
    public static final ChoppingBlock JUNGLE_CHOPPING_BLOCK = CreateChoppingBlock(Lumberjacking.ConfigData.getOrDefault("jungle_logs",2.0f));
    public static final ChoppingBlock ACACIA_CHOPPING_BLOCK =CreateChoppingBlock(Lumberjacking.ConfigData.getOrDefault("acacia_logs",2.0f));
    public static final ChoppingBlock DARK_OAK_CHOPPING_BLOCK = CreateChoppingBlock(Lumberjacking.ConfigData.getOrDefault("dark_oak_logs",2.0f));
    public static final ChoppingBlock MANGROVE_CHOPPING_BLOCK = CreateChoppingBlock(Lumberjacking.ConfigData.getOrDefault("mangrove_logs",2.0f));
    public static final ChoppingBlock CHERRY_CHOPPING_BLOCK = CreateChoppingBlock(Lumberjacking.ConfigData.getOrDefault("cherry_logs",2.0f));
    public static final ChoppingBlock BAMBOO_CHOPPING_BLOCK = CreateChoppingBlock(Lumberjacking.ConfigData.getOrDefault("bamboo_logs",2.0f));
    public static final ChoppingBlock CRIMSON_CHOPPING_BLOCK = CreateChoppingBlock(Lumberjacking.ConfigData.getOrDefault("crimson_logs",2.0f));
    public static final ChoppingBlock WARPED_CHOPPING_BLOCK = CreateChoppingBlock(Lumberjacking.ConfigData.getOrDefault("warped_logs",2.0f));

    public static final TagKey<Block> CHOPPING_BLOCKS = TagKey.of(RegistryKeys.BLOCK,Identifier.of(Lumberjacking.Namespace,"chopping_blocks"));

    public static void InitBlocks()
    {

        RegisterChoppingBlock("oak_chopping_block",OAK_CHOPPING_BLOCK);
        RegisterChoppingBlock("spruce_chopping_block",SPRUCE_CHOPPING_BLOCK);
        RegisterChoppingBlock("birch_chopping_block",BIRCH_CHOPPING_BLOCK);
        RegisterChoppingBlock("jungle_chopping_block",JUNGLE_CHOPPING_BLOCK);
        RegisterChoppingBlock("acacia_chopping_block",ACACIA_CHOPPING_BLOCK);
        RegisterChoppingBlock("dark_oak_chopping_block",DARK_OAK_CHOPPING_BLOCK);
        RegisterChoppingBlock("mangrove_chopping_block",MANGROVE_CHOPPING_BLOCK);
        RegisterChoppingBlock("cherry_chopping_block",CHERRY_CHOPPING_BLOCK);
        RegisterChoppingBlock("bamboo_chopping_block",BAMBOO_CHOPPING_BLOCK);
        RegisterChoppingBlock("crimson_chopping_block",CRIMSON_CHOPPING_BLOCK);
        RegisterChoppingBlock("warped_chopping_block",WARPED_CHOPPING_BLOCK);

        Lumberjacking.LOGGER.info("Adding " + Lumberjacking.Namespace + " blocks to MC");
    }
}
