package com.beepbeat.AE2BotaniaCrossOver.GUI.Container;

import com.beepbeat.AE2BotaniaCrossOver.Blocks.TileAERuneAssembler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by beepbeat on 22.08.2015.
 */
public class ContainerRuneAssembler extends ContainerCrossoverMod {
    TileAERuneAssembler te;

    public ContainerRuneAssembler(InventoryPlayer playerInventory, TileAERuneAssembler te){
        addPlayerSlots(playerInventory, 8, 84);

        //left row
        addSlotToContainer(new Slot(te, 0, 62, 53));
        addSlotToContainer(new Slot(te, 1, 62, 35));
        addSlotToContainer(new Slot(te, 2, 62, 17));

        //middle row
        addSlotToContainer(new Slot(te, 3, 80, 53));
        addSlotToContainer(new Slot(te, 4, 80, 35));
        addSlotToContainer(new Slot(te, 5, 80, 17));

        //right row
        addSlotToContainer(new Slot(te, 6, 98, 53));
        addSlotToContainer(new Slot(te, 7, 98, 35));
        addSlotToContainer(new Slot(te, 8, 98, 17));

        this.te = te;
    }




    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex){
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(slotIndex);

        if(slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            //From here change accordingly...
            if(slotIndex < 9) {
                if(!mergeItemStack(itemstack1, 6, 42, true)) {
                    return null;
                }
            } else {
                //Shift click single items only.
                if(itemstack1.stackSize == 1) {
                    for(int i = 0; i < 6; i++) {
                        Slot shiftedInSlot = (Slot)inventorySlots.get(i);
                        if(!shiftedInSlot.getHasStack() && shiftedInSlot.isItemValid(itemstack1)) mergeItemStack(itemstack1, i, i + 1, false);
                    }
                }
            }
            //...till here.

            if(itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }

            if(itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }


}
