package com.gtnewhorizons.tcwands.api;

import java.util.function.Supplier;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import gregtech.api.enums.Tier;
import gregtech.api.util.GT_ModHandler;
import thaumcraft.common.config.ConfigItems;

public enum GTTier {

    STEAM(0, () -> new ItemStack(ConfigItems.itemShard, 1, 32767)),
    LV(1, () -> GT_ModHandler.getModItem("TwilightForest", "item.nagaScale", 1, 0, new ItemStack(Items.wheat))),
    MV(2, () -> GT_ModHandler.getModItem("dreamcraft", "item.LichBone", 1, 0, new ItemStack(Items.carrot))),
    HV(3, () -> GT_ModHandler.getModItem("dreamcraft", "item.LichBone", 1, 0, new ItemStack(Items.carrot))),
    EV(4, () -> GT_ModHandler.getModItem("TwilightForest", "item.fieryBlood", 1, 0, new ItemStack(Items.potato))),
    IV(5, () -> GT_ModHandler
            .getModItem("TwilightForest", "item.fieryTears", 1, 0, new ItemStack(Items.poisonous_potato))),
    LUV(6, () -> GT_ModHandler.getModItem("TwilightForest", "item.carminite", 1, 0, new ItemStack(Items.apple))),
    ZPM(7, () -> GT_ModHandler.getModItem("TwilightForest", "item.carminite", 1, 0, new ItemStack(Items.apple))),
    UV(8, () -> GT_ModHandler.getModItem("dreamcraft", "item.SnowQueenBlood", 1, 0, new ItemStack(Items.cake)));

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
