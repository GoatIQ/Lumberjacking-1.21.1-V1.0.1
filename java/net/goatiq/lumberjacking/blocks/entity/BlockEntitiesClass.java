package net.goatiq.lumberjacking.blocks.entity;

import net.goatiq.lumberjacking.Lumberjacking;
import net.goatiq.lumberjacking.blocks.BlockClass;
import net.goatiq.lumberjacking.blocks.entity.ChoppingBlock.ChoppingBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockEntitiesClass {

    public static final BlockEntityType<ChoppingBlockEntity> CHOPPING_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Lumberjacking.Namespace,"chopping_block_ent"),
                    BlockEntityType.Builder.create(ChoppingBlockEntity::new,
                            BlockClass.OAK_CHOPPING_BLOCK,
                            BlockClass.SPRUCE_CHOPPING_BLOCK,
                            BlockClass.BIRCH_CHOPPING_BLOCK,
                            BlockClass.JUNGLE_CHOPPING_BLOCK,
                            BlockClass.ACACIA_CHOPPING_BLOCK,
                            BlockClass.DARK_OAK_CHOPPING_BLOCK,
                            BlockClass.MANGROVE_CHOPPING_BLOCK,
                            BlockClass.CHERRY_CHOPPING_BLOCK,
                            BlockClass.BAMBOO_CHOPPING_BLOCK,
                            BlockClass.CRIMSON_CHOPPING_BLOCK,
                            BlockClass.WARPED_CHOPPING_BLOCK
                            ).build(null));

    public static void InitBlockEntities()
    {
        Lumberjacking.LOGGER.info("Registering block entities from mod : " + Lumberjacking.Namespace);

    }
}
