package com.gtnewhorizons.tcwands.api;

import java.util.function.Supplier;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.enums.Tier;
import gregtech.api.util.GTModHandler;

public enum GTTier {

    LV(0, () -> GTModHandler.getModItem("TwilightForest", "item.nagaScale", 1, 0, new ItemStack(Items.wheat))),
    MV(1, () -> GTModHandler.getModItem("dreamcraft", "item.LichBone", 1, 0, new ItemStack(Items.carrot))),
    HV(2, () -> GTModHandler.getModItem("dreamcraft", "item.LichBone", 1, 0, new ItemStack(Items.carrot))),
    EV(3, () -> GTModHandler.getModItem("TwilightForest", "item.fieryBlood", 1, 0, new ItemStack(Items.potato))),
    IV(4, () -> GTModHandler
            .getModItem("TwilightForest", "item.fieryTears", 1, 0, new ItemStack(Items.poisonous_potato))),
    LUV(5, () -> GTModHandler.getModItem("TwilightForest", "item.carminite", 1, 0, new ItemStack(Items.apple))),
    ZPM(6, () -> GTModHandler.getModItem("TwilightForest", "item.carminite", 1, 0, new ItemStack(Items.apple))),
    UV(7, () -> GTModHandler.getModItem("dreamcraft", "item.SnowQueenBlood", 1, 0, new ItemStack(Items.cake)));

    private static final GTTier[] tiers;

    static {
        tiers = new GTTier[values().length];
        for (int i = 0; i < values().length; i++) {
            GTTier tier = values()[i];
            tiers[tier.getIndex()] = tier;
        }
    }

    private int index;
    private Supplier<ItemStack> wandConductorSupplier;

    GTTier(int index, Supplier<ItemStack> wandConductorSupplier) {
        this.index = index;
        this.wandConductorSupplier = wandConductorSupplier;
    }

    public static void init() {
        for (GTTier tier : values()) {// is needed for optimization
            ItemStack conductor = tier.wandConductorSupplier.get();
            tier.wandConductorSupplier = () -> conductor;
        }
    }

    public int getIndex() {
        return index;
    }

    public Tier getGregTier() {
        return Tier.ELECTRIC[getIndex() + 1];
    }

    public ItemStack getConductor() {
        return wandConductorSupplier.get();
    }

    /**
     * Returns the next tier. If current tier is the last, it will return itself.
     */
    public GTTier nextTier() {
        if (getIndex() >= tiers.length - 1) {
            return this;
        } else return tiers[getIndex() + 1];
    }
}
