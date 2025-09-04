package net.goatiq.lumberjacking.blocks.entity.ChoppingBlock;

import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TallBlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class ChoppingBlockRenderer implements BlockEntityRenderer<ChoppingBlockEntity> {

    public ChoppingBlockRenderer(BlockEntityRendererFactory.Context context)
    {

    }

    @Override
    public void render(ChoppingBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        ItemStack ItemToBeRendered = entity.GetInventory().get(0);

        if (!ItemToBeRendered.isEmpty()) {
            ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
            matrices.push();

            if (ItemToBeRendered.getItem() instanceof BlockItem)
            {
                //for block rendering

                if (ItemToBeRendered.getItem() instanceof TallBlockItem)
                {
                    //basically made for doors
                    matrices.translate(0.5f, 0.525f, 0.5f);
                    matrices.scale(1.5f, 2.0f, 2.0f);
                    matrices.translate(0.0f, entity.Render.GetRenderHeight() * 2f, 0.0f);
                    matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                    matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(entity.Render.GetRenderRotation()));

                } else {

                    matrices.translate(0.5f, 0.875f, 0.5f);
                    matrices.scale(1.5f, 1.5f, 1.5f);
                    matrices.translate(0.0f, entity.Render.GetRenderHeight(), 0.0f);
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.Render.GetRenderRotation()));
                }

            } else {
                //for item rendering
                matrices.translate(0.5f, 0.525f, 0.5f);
                matrices.scale(0.65f, 0.65f, 0.65f);
                matrices.translate(0.0f, entity.Render.GetRenderHeight() * 2f, 0.0f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(entity.Render.GetRenderRotation()));
            }
            itemRenderer.renderItem(ItemToBeRendered, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }
    }

    private int getLightLevel(World world, BlockPos pos)
    {
        int bLight = world.getLightLevel(LightType.BLOCK,pos);
        int sLight = world.getLightLevel(LightType.SKY,pos);
        return LightmapTextureManager.pack(bLight,sLight);
    }
}
