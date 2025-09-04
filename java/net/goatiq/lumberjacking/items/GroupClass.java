package net.goatiq.lumberjacking.items;

import net.goatiq.lumberjacking.Lumberjacking;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import net.goatiq.lumberjacking.blocks.BlockClass;

public class GroupClass {

    public static void InitItemGroups()
    {
        Lumberjacking.LOGGER.info("Adding " + Lumberjacking.Namespace + " item group");
    }
    public static final ItemGroup ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(Lumberjacking.Namespace, "modtab"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(BlockClass.OAK_CHOPPING_BLOCK))
                    .displayName(Text.translatable("itemgroup.lumberjacking.group_name"))
                    .entries((displayContext, entries) ->
                    {
                        //blocks
                        entries.add(BlockClass.OAK_CHOPPING_BLOCK);
                        entries.add(BlockClass.SPRUCE_CHOPPING_BLOCK);
                        entries.add(BlockClass.BIRCH_CHOPPING_BLOCK);
                        entries.add(BlockClass.JUNGLE_CHOPPING_BLOCK);
                        entries.add(BlockClass.ACACIA_CHOPPING_BLOCK);
                        entries.add(BlockClass.DARK_OAK_CHOPPING_BLOCK);
                        entries.add(BlockClass.MANGROVE_CHOPPING_BLOCK);
                        entries.add(BlockClass.CHERRY_CHOPPING_BLOCK);
                        entries.add(BlockClass.BAMBOO_CHOPPING_BLOCK);
                        entries.add(BlockClass.CRIMSON_CHOPPING_BLOCK);
                        entries.add(BlockClass.WARPED_CHOPPING_BLOCK);

                        //items
                        entries.add(ItemClass.OAK_PLANK);
                        entries.add(ItemClass.SPRUCE_PLANK);
                        entries.add(ItemClass.BIRCH_PLANK);
                        entries.add(ItemClass.JUNGLE_PLANK);
                        entries.add(ItemClass.ACACIA_PLANK);
                        entries.add(ItemClass.DARK_OAK_PLANK);
                        entries.add(ItemClass.MANGROVE_PLANK);
                        entries.add(ItemClass.CHERRY_PLANK);
                        entries.add(ItemClass.BAMBOO_PLANK);
                        entries.add(ItemClass.CRIMSON_PLANK);
                        entries.add(ItemClass.WARPED_PLANK);
                    }).build());
}
