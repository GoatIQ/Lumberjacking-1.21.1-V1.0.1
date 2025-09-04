package net.goatiq.lumberjacking.Compat.REI;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.goatiq.lumberjacking.Lumberjacking;
import net.goatiq.lumberjacking.blocks.BlockClass;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.TallBlockItem;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class ChoppingBlockCategory  implements DisplayCategory<BasicDisplay>
{

    public static final Identifier ARROW_IMG = Identifier.of(Lumberjacking.Namespace,"compat/rei/textures/gui/arrow.png");
    public static final Identifier BG_IMG = Identifier.of(Lumberjacking.Namespace,"compat/rei/textures/gui/background.png");

    public static final CategoryIdentifier<ChoppingBlockDisplay> CHOPPING_BLOCK_DISPLAY =
            CategoryIdentifier.of(Lumberjacking.Namespace,"");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return CHOPPING_BLOCK_DISPLAY;
    }

    @Override
    public Text getTitle()
    {
        return Text.of("Chopping block");
    }

    @Override
    public Renderer getIcon()
    {
        return EntryStacks.of(BlockClass.OAK_CHOPPING_BLOCK.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds)
    {
        List<Widget> widgets = new LinkedList<>();

        if(display instanceof ChoppingBlockDisplay ChoppingDisplay)
        {

            Rectangle BackGroundTextureRectangle = new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), this.getDisplayHeight());

            Rectangle ToolSlotOrigin = new Rectangle(BackGroundTextureRectangle.getX() + 40,BackGroundTextureRectangle.getY() + 5,24,24);
            Rectangle ArrowTextureRectangle = new Rectangle(ToolSlotOrigin.getX() - 40, ToolSlotOrigin.getY() + 12, 40, 40);

            Rectangle InputSlotOrigin = new Rectangle(ToolSlotOrigin.getX(),ToolSlotOrigin.getY() + 24,24,24);
            Rectangle BaseSlotOrigin = new Rectangle(InputSlotOrigin.getX()-4,InputSlotOrigin.getY()+2 ,InputSlotOrigin.getWidth() + 8,InputSlotOrigin.getHeight() + 8);

            Rectangle OutputSlotOrigin = new Rectangle(InputSlotOrigin.getX() + 64,InputSlotOrigin.getY()+4,20,20);
            Point ChoppingLabelOrigin = new Point(ArrowTextureRectangle.getX() + 25 ,ArrowTextureRectangle.getCenterY()-10);
            Point ConsumeLabelOrigin = new Point(ToolSlotOrigin.getX() + 50 ,ToolSlotOrigin.getY()+8);
            Point ArrowOrigin = new Point(InputSlotOrigin.getX() + 35, InputSlotOrigin.getY() + 4);

            widgets.add(Widgets.createTexturedWidget(BG_IMG,BackGroundTextureRectangle,0.0f,0.0f,BackGroundTextureRectangle.getWidth(),BackGroundTextureRectangle.getHeight()));
            widgets.add(Widgets.createTexturedWidget(ARROW_IMG,ArrowTextureRectangle,0.0f,0.0f,ArrowTextureRectangle.getWidth(),ArrowTextureRectangle.getHeight()));

            widgets.add(Widgets.createArrow(ArrowOrigin));

            widgets.add(Widgets.createLabel(ChoppingLabelOrigin,Text.of("x " + ChoppingDisplay.RecipeFound.value().ChopAmount().toString()))
                    .color(0xFF404040, 0xFFBBBBBB)
                    .noShadow());

            widgets.add(Widgets.createSlot(BaseSlotOrigin)
                    .entries(EntryIngredients.of(BlockClass.OAK_CHOPPING_BLOCK.asItem()))
                    .disableBackground()
                    .notInteractable()
                    .disableHighlight().disableTooltips());

            Item ItemToCheck = ChoppingDisplay.RecipeFound.value().InputItem().getMatchingStacks()[0].getItem();
            if (!(ItemToCheck instanceof BlockItem) || (ItemToCheck instanceof TallBlockItem))
            {
                InputSlotOrigin.y = InputSlotOrigin.y - 5;
            }

            widgets.add(Widgets.createSlot(InputSlotOrigin)
                    .entries(ChoppingDisplay.getInputEntries().get(0))
                    .disableBackground()
                    .markInput());


            widgets.add(Widgets.createSlot(ToolSlotOrigin)
                    .entries(ChoppingDisplay.getInputEntries().get(1))
                    .disableBackground()
                    .markInput());

            widgets.add(Widgets.createSlot(OutputSlotOrigin)
                    .entries(ChoppingDisplay.getOutputEntries().get(0))
                    .disableBackground()
                    .markOutput());

            if(ChoppingDisplay.RecipeFound.value().ToolDamage() == -1)
            {
                widgets.add(Widgets.createLabel(ConsumeLabelOrigin,Text.of("Consume"))
                        .color(0xFF4040, 0xFF4040)
                        .noShadow());
            }

        }
        return widgets;
    }

    @Override
    public int getDisplayHeight()
    {
        return 70;
    }
}
