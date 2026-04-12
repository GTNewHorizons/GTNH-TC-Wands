package com.gtnewhorizons.tcwands.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizons.tcwands.api.wrappers.AbstractWandWrapper;
import com.gtnewhorizons.tcwands.api.wrappers.CapWrapper;
import com.gtnewhorizons.tcwands.api.wrappers.SceptreWrapper;

import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.research.ResearchManager;

public class GTNHScepterRecipe extends GTNHWandRecipe {

    private static final int[] SLOT_CAP = new int[] { 1, 5, 6 };
    private static final int[] SLOT_SCREW = new int[] { 3, 7 };
    private static final int SLOT_CHARM = 2;
    private static final ItemStack CHARM = new ItemStack(ConfigItems.itemResource, 1, 15);

    @Override
    public boolean matches(IInventory craftingTable, World world, EntityPlayer player) {
        return super.matches(craftingTable, world, player) && checkPrimalCharm(craftingTable);
    }

    @Override
    public ItemStack getCraftingResult(IInventory craftingTable) {
        AbstractWandWrapper wandWrapper = getWandWrapper(craftingTable.getStackInSlot(SLOT_CORE));
        CapWrapper capWrapper = getCapWrapper(craftingTable);
        if (wandWrapper == null || capWrapper == null) return null;
        return wandWrapper.getItem(capWrapper);
    }

    private static boolean checkPrimalCharm(IInventory craftingTable) {
        return OreDictionary.itemMatches(craftingTable.getStackInSlot(SLOT_CHARM), CHARM, true);
    }

    @Override
    protected CapWrapper getCapWrapper(IInventory craftingTable) {
        ItemStack cap = craftingTable.getStackInSlot(SLOT_CAP[0]);
        for (CapWrapper wrapper : TCWandAPI.getCaps()) {
            if (OreDictionary.itemMatches(cap, wrapper.getItem(), true)) {
                return wrapper;
            }
        }
        return null;
    }

    @Override
    protected int[] screwSlots() {
        return SLOT_SCREW;
    }

    @Override
    protected AbstractWandWrapper getWandWrapper(ItemStack rodItem) {
        for (AbstractWandWrapper wrapper : TCWandAPI.getWandWrappers()) {
            if (!(wrapper instanceof SceptreWrapper)) continue;
            if (OreDictionary.itemMatches(rodItem, wrapper.getCraftingRod(), true)) {
                return wrapper;
            }
        }
        return null;
    }

    @Override
    protected boolean checkCaps(IInventory craftingTable, EntityPlayer player) {
        ItemStack cap = craftingTable.getStackInSlot(SLOT_CAP[0]);
        CapWrapper capWrapper = getCapWrapper(craftingTable);
        return cap != null && capWrapper != null
                && ResearchManager.isResearchComplete(player.getCommandSenderName(), capWrapper.getResearch())
                && OreDictionary.itemMatches(cap, craftingTable.getStackInSlot(SLOT_CAP[1]), true)
                && OreDictionary.itemMatches(cap, craftingTable.getStackInSlot(SLOT_CAP[2]), true);
    }

    @Override
    public String getResearch() {
        return "SCEPTRE";
    }

    @Override
    public String[] salisArcana$getResearches(IInventory inv, World world, EntityPlayer player) {
        String[] strings = new String[3];
        strings[0] = getWandWrapper(inv.getStackInSlot(SLOT_CORE)).getResearchName();
        strings[1] = getCapWrapper(inv).getResearch();
        strings[2] = "SCEPTRE";
        return strings;
    }
}
