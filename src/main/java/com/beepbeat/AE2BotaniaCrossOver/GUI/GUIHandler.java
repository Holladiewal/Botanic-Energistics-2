package com.beepbeat.AE2BotaniaCrossOver.GUI;

import com.beepbeat.AE2BotaniaCrossOver.Blocks.TileAERuneAssembler;
import com.beepbeat.AE2BotaniaCrossOver.GUI.Container.ContainerRuneAssembler;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by beepbeat on 22.08.2015.
 */
public class GUIHandler implements IGuiHandler {
    public enum GUIIDs{RuneAssembler}

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (GUIIDs.values()[ID]){
            case RuneAssembler:
                return new ContainerRuneAssembler(player.inventory, (TileAERuneAssembler) world.getTileEntity(x, y, z));
        }

        throw new IllegalArgumentException("No Container with ID " + ID);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (GUIIDs.values()[ID]){
            case RuneAssembler:
                return new GUIRuneAssembler(player.inventory, (TileAERuneAssembler) world.getTileEntity(x, y, z));
        }
        throw new IllegalArgumentException("No GUI with ID " + ID);
    }
}
