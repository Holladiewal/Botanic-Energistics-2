package com.beepbeat.AE2BotaniaCrossOver.Blocks;

import appeng.api.networking.crafting.ICraftingMedium;
import appeng.api.networking.crafting.ICraftingPatternDetails;
import appeng.api.networking.crafting.ICraftingProviderHelper;
import appeng.api.storage.data.IAEItemStack;
import appeng.tile.TileEvent;
import appeng.tile.events.TileEventType;
import appeng.tile.grid.AENetworkTile;
import com.beepbeat.AE2BotaniaCrossOver.Items.RuneAssemblerCraftingPattern;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;

import java.util.ArrayList;
import java.util.List;


public class TileAERuneAssembler extends AENetworkTile implements ICraftingProviderHelper, IInventory {
    List RuneAltarRecipes = BotaniaAPI.runeAltarRecipes;
    private ItemStack[] inventory = new ItemStack[9];
    List availRecipes = new ArrayList();


    public TileAERuneAssembler() {
        setName("runeassembler");
    }

    public TileAERuneAssembler(InventoryPlayer inventory, TileAERuneAssembler tileEntity) {

    }

    @Override
    public void addCraftingOption(ICraftingMedium iCraftingMedium, ICraftingPatternDetails iCraftingPatternDetails) {

    }

    @Override
    public void setEmitable(IAEItemStack iaeItemStack) {

    }

    @TileEvent(TileEventType.TICK)
    public void onTick(){
        availRecipes.clear();
        for (ItemStack stack : inventory){
            if (stack.getItem() instanceof RuneAssemblerCraftingPattern){
                RuneAssemblerCraftingPattern pattern = (RuneAssemblerCraftingPattern) stack.getItem();
                if (pattern.getOutputs() != null)
                availRecipes.add(pattern.getOutputs()[0].getItemStack());
            }
        }
    }

    //region IInventory
    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    /**
     * Returns the stack in slot i
     *
     * @param slot
     */
    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }
    public ItemStack decrStackSize(int slot, int decrAmount)
    {
        if (this.inventory[slot] != null)
        {
            ItemStack itemstack;

            if (this.inventory[slot].stackSize <= decrAmount)
            {
                itemstack = this.inventory[slot];
                this.inventory[slot] = null;
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = this.inventory[slot].splitStack(decrAmount);

                if (this.inventory[slot].stackSize == 0)
                {
                    this.inventory[slot] = null;
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (this.inventory[slot] != null)
        {
            ItemStack itemstack = this.inventory[slot];
            this.inventory[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        this.inventory[slot] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

    /**
     * Returns the name of the inventory
     */
    @Override
    public String getInventoryName() {
        return "inv.EMCRAfter.name";
    }

    /**
     * Returns if the inventory is named
     */
    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     *
     * @param player
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     *
     * @param slot
     * @param stack
     */
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return true;
    }
    //endregion
}
