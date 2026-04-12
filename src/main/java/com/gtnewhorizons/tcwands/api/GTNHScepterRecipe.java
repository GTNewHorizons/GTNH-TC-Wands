package com.gtnewhorizons.tcwands.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizons.tcwands.api.wrappers.AbstractWandWrapper;
import com.gtnewhorizons.tcwands.api.wrappers.CapWrapper;

import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.research.ResearchManager;

public class GTNHScepterRecipe extends GTNHWandRecipe {

    private static final int[] SLOT_CAP = new int[] { 6, 5, 1 };
    private static final int[] SLOT_SCREW = new int[] { 3, 7 };
    private static final int SLOT_CHARM = 2;
    private static final ItemStack CHARM = new ItemStack(ConfigItems.itemResource, 1, 15);

    @Override
    public boolean matches(IInventory inv, World world, EntityPlayer player) {
        return super.matches(inv, world, player) && checkPrimalCharm(inv);
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        AbstractWandWrapper wandWrapper = getWandWrapper(inv.getStackInSlot(SLOT_CORE));
        CapWrapper capWrapper = TCWandAPI.getWrapperForCap(inv.getStackInSlot(SLOT_CAP[0]));
        if (wandWrapper == null || capWrapper == null) return null;
        return wandWrapper.getItem(capWrapper);
    }

    @Override
    protected AbstractWandWrapper getWandWrapper(ItemStack rod) {
        return TCWandAPI.getWrapperForRod(rod, true);
    }

    private static boolean checkPrimalCharm(IInventory inv) {
        return OreDictionary.itemMatches(inv.getStackInSlot(SLOT_CHARM), CHARM, true);
    }

    @Override
    protected int[] screwSlots() {
        return SLOT_SCREW;
    }

    @Override
    protected boolean checkCaps(IInventory inv, EntityPlayer player) {
        ItemStack cap = inv.getStackInSlot(SLOT_CAP[0]);
        CapWrapper capWrapper = TCWandAPI.getWrapperForCap(inv.getStackInSlot(SLOT_CAP[0]));
        return cap != null && capWrapper != null
                && ResearchManager.isResearchComplete(player.getCommandSenderName(), capWrapper.getResearch())
                && OreDictionary.itemMatches(cap, inv.getStackInSlot(SLOT_CAP[1]), true)
                && OreDictionary.itemMatches(cap, inv.getStackInSlot(SLOT_CAP[2]), true);
    }

    @Override
    public String getResearch() {
        return "SCEPTRE";
    }

    @Override
    public String[] salisArcana$getResearches(IInventory inv, World world, EntityPlayer player) {
        String[] strings = new String[3];
        AbstractWandWrapper rod = TCWandAPI.getWrapperForRod(inv.getStackInSlot(SLOT_CORE), true);
        if (rod != null) strings[0] = rod.getResearchName();
        CapWrapper cap = TCWandAPI.getWrapperForCap(inv.getStackInSlot(SLOT_CAP[0]));
        if (cap != null) strings[1] = cap.getResearch();
        strings[2] = "SCEPTRE";
        return strings;
    }
}
