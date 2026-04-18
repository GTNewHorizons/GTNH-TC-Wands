package com.gtnewhorizons.tcwands.api.wrappers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.tcwands.api.WandType;
import com.gtnewhorizons.tcwands.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwands.api.wandinfo.WandProps;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.common.config.ConfigItems;

public class SceptreWrapper extends AbstractWandWrapper {

    private final float sceptreCostMultiplier;

    public SceptreWrapper(WandDetails wandDetails, WandProps wandProps, float sceptreCostMultiplier) {
        super(wandDetails, wandProps);
        this.sceptreCostMultiplier = sceptreCostMultiplier;
    }

    public float getSceptreCostMultiplier() {
        return sceptreCostMultiplier;
    }

    @Override
    protected NBTTagCompound writeNBT(CapWrapper cap) {
        NBTTagCompound wandNbt = super.writeNBT(cap);
        wandNbt.setByte("sceptre", (byte) 1);

        return wandNbt;
    }

    @Override
    public int getRecipeCost(CapWrapper cap) {
        return (int) (super.getRecipeCost(cap) * sceptreCostMultiplier);
    }

    @Override
    public @NotNull WandType getType() {
        return WandType.SCEPTRE;
    }

    @Override
    public ShapedArcaneRecipe getRecipe(CapWrapper cap) {
        ItemStack wand = getItem(cap);
        AspectList vis = new AspectList();
        int cost = getRecipeCost(cap);
        for (Aspect a : Aspect.getPrimalAspects()) {
            vis.add(a, cost);
        }
        ItemStack conductor = getDetails().conductor();
        String screw = getDetails().getScrew();
        ItemStack capItem = cap.getItem();
        return new ShapedArcaneRecipe(
                null,
                wand,
                vis,
                "XCP",
                "SRC",
                "CSX",
                'X',
                conductor,
                'S',
                screw,
                'C',
                capItem,
                'R',
                getCraftingRod(),
                'P',
                new ItemStack(ConfigItems.itemResource, 1, 15));
    }
}
