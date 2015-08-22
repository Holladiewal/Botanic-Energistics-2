package pct.botanic.energistics.GUI;

import pct.botanic.energistics.Blocks.TileAERuneAssembler;
import pct.botanic.energistics.GUI.Container.ContainerRuneAssembler;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * Created by beepbeat on 22.08.2015.
 */
public class GUIRuneAssembler extends GUICrossOver {
    TileAERuneAssembler te;

    public GUIRuneAssembler(InventoryPlayer playerInventory, TileAERuneAssembler te) {
        super(new ContainerRuneAssembler(playerInventory, te), "RuneAssembler", playerInventory);
        this.te = te;

    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

    }

}
