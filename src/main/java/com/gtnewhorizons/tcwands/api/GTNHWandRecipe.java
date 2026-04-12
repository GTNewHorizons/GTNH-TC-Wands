package com.gtnewhorizons.tcwands.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import com.gtnewhorizons.tcwands.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwands.api.wrappers.AbstractWandWrapper;
import com.gtnewhorizons.tcwands.api.wrappers.CapWrapper;

import cpw.mods.fml.common.Optional;
import dev.rndmorris.salisarcana.api.IMultipleResearchArcaneRecipe;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.common.lib.research.ResearchManager;

@Optional.Interface(iface = "dev.rndmorris.salisarcana.api.IMultipleResearchArcaneRecipe", modid = "salisarcana")
public class GTNHWandRecipe implements IArcaneRecipe, IMultipleResearchArcaneRecipe {

    private static final int[] SLOT_CAP = new int[] { 6, 2 };
    private static final int[] SLOT_SCREW = new int[] { 1, 3, 5, 7 };
    private static final int[] SLOT_CONDUCTOR = new int[] { 0, 8 };
    protected static final int SLOT_CORE = 4;

    @Override
    public boolean matches(IInventory inv, World world, EntityPlayer player) {
        final ItemStack rodItem = inv.getStackInSlot(SLOT_CORE);
        if (rodItem == null) return false;
        AbstractWandWrapper wrapper = TCWandAPI.getWrapperForRod(rodItem, false);
        if (wrapper == null
                || !ResearchManager.isResearchComplete(player.getCommandSenderName(), wrapper.getResearchName()))
            return false;
        WandDetails details = wrapper.getDetails();
        if (!checkScrews(inv, details)) return false;
        ItemStack conductor = details.getConductor();
        for (int slot : SLOT_CONDUCTOR) {
            if (!OreDictionary.itemMatches(inv.getStackInSlot(slot), conductor, true)) return false;
        }

        return checkCaps(inv, player);
    }

    private boolean checkScrews(IInventory inv, WandDetails details) {
        int screwOredict = OreDictionary.getOreID(details.getScrew());
        for (int slot : screwSlots()) {
            final ItemStack s = inv.getStackInSlot(slot);
            if (s == null || !hasOredict(s, screwOredict)) return false;
        }
        return true;
    }

    protected int[] screwSlots() {
        return SLOT_SCREW;
    }

    protected boolean checkCaps(IInventory inv, EntityPlayer player) {
        ItemStack cap = inv.getStackInSlot(SLOT_CAP[0]);
        CapWrapper capWrapper = TCWandAPI.getWrapperForCap(inv.getStackInSlot(SLOT_CAP[0]));
        return cap != null && capWrapper != null
                && ResearchManager.isResearchComplete(player.getCommandSenderName(), capWrapper.getResearch())
                && OreDictionary.itemMatches(cap, inv.getStackInSlot(SLOT_CAP[1]), true);
    }

    private boolean hasOredict(ItemStack s, int screw) {
        int[] oreIDs = OreDictionary.getOreIDs(s);
        for (int ore : oreIDs) {
            if (ore == screw) return true;
        }
        return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        AbstractWandWrapper wandWrapper = getWandWrapper(inv.getStackInSlot(SLOT_CORE));
        CapWrapper capWrapper = TCWandAPI.getWrapperForCap(inv.getStackInSlot(SLOT_CAP[0]));
        if (wandWrapper == null || capWrapper == null) return null;
        return wandWrapper.getItem(capWrapper);
    }

    @Override
    public int getRecipeSize() {
        return 9;
    }

    @Override
    public AspectList getAspects(IInventory inv) {
        AbstractWandWrapper wandWrapper = getWandWrapper(inv.getStackInSlot(SLOT_CORE));
        CapWrapper capWrapper = TCWandAPI.getWrapperForCap(inv.getStackInSlot(SLOT_CAP[0]));
        if (wandWrapper == null || capWrapper == null) return new AspectList();
        int recipeCost = wandWrapper.getRecipeCost(capWrapper);
        AspectList cost = new AspectList();
        for (Aspect a : Aspect.getPrimalAspects()) {
            cost.add(a, recipeCost);
        }
        return cost;
    }

    protected AbstractWandWrapper getWandWrapper(ItemStack rod) {
        return TCWandAPI.getWrapperForRod(rod, false);
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    @Override
    public AspectList getAspects() {
        return null;
    }

    @Override
    public String getResearch() {
        return "";
    }

    @Override
    public String[] salisArcana$getResearches(IInventory inv, World world, EntityPlayer player) {
        String[] strings = new String[2];
        AbstractWandWrapper rod = getWandWrapper(inv.getStackInSlot(SLOT_CORE));
        if (rod != null) strings[0] = rod.getResearchName();
        CapWrapper cap = TCWandAPI.getWrapperForCap(inv.getStackInSlot(SLOT_CAP[0]));
        if (cap != null) strings[1] = cap.getResearch();
        return strings;
    }
}
