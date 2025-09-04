package net.goatiq.lumberjacking.items;

import net.goatiq.lumberjacking.Lumberjacking;
import net.goatiq.lumberjacking.blocks.BlockClass;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ItemClass {

    private static void RegisterItem(String name, Item item)
    {
        Registry.register(Registries.ITEM, Identifier.of(Lumberjacking.Namespace, name), item);
    }
    public static final TagKey<Item> PLANKS_BLOCK = TagKey.of(RegistryKeys.ITEM,Identifier.of(Identifier.DEFAULT_NAMESPACE,"planks_block"));

    public static final Item OAK_PLANK = new Item(new Item.Settings());
    public static final Item SPRUCE_PLANK = new Item(new Item.Settings());
    public static final Item BIRCH_PLANK = new Item(new Item.Settings());
    public static final Item JUNGLE_PLANK = new Item(new Item.Settings());
    public static final Item ACACIA_PLANK = new Item(new Item.Settings());
    public static final Item DARK_OAK_PLANK = new Item(new Item.Settings());
    public static final Item MANGROVE_PLANK = new Item(new Item.Settings());
    public static final Item CHERRY_PLANK = new Item(new Item.Settings());
    public static final Item BAMBOO_PLANK = new Item(new Item.Settings());
    public static final Item CRIMSON_PLANK = new Item(new Item.Settings());
    public static final Item WARPED_PLANK = new Item(new Item.Settings());

    public static void InitItems()
    {
        RegisterItem("oak_plank",OAK_PLANK);
        RegisterItem("spruce_plank", SPRUCE_PLANK);
        RegisterItem("birch_plank", BIRCH_PLANK);
        RegisterItem("jungle_plank", JUNGLE_PLANK);
        RegisterItem("acacia_plank", ACACIA_PLANK);
        RegisterItem("dark_oak_plank", DARK_OAK_PLANK);
        RegisterItem("mangrove_plank", MANGROVE_PLANK);
        RegisterItem("cherry_plank", CHERRY_PLANK);
        RegisterItem("bamboo_plank", BAMBOO_PLANK);
        RegisterItem("crimson_plank", CRIMSON_PLANK);
        RegisterItem("warped_plank", WARPED_PLANK);
        Lumberjacking.LOGGER.info("Adding " + Lumberjacking.Namespace + " items to MC");
    }
}
