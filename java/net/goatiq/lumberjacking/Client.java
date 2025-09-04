package net.goatiq.lumberjacking;

import net.goatiq.lumberjacking.blocks.entity.BlockEntitiesClass;
import net.goatiq.lumberjacking.blocks.entity.ChoppingBlock.ChoppingBlockRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Lumberjacking.LOGGER.info("init client");
        BlockEntityRendererFactories.register(BlockEntitiesClass.CHOPPING_BLOCK_ENTITY, ChoppingBlockRenderer::new);

    }
}
