package com.gtnewhorizons.tcwands.api;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Do not load this class before mods register their items in pre-init.
 */
public enum GTTier {

    LV("screwAluminium", getModItem("TwilightForest", "item.nagaScale", Items.wheat)),
    MV("screwStainlessSteel", getModItem("dreamcraft", "LichBone", Items.carrot)),
    HV("screwEnergeticAlloy", getModItem("dreamcraft", "LichBone", Items.carrot)),
    EV("screwVibrantAlloy", getModItem("TwilightForest", "item.fieryBlood", Items.potato)),
    IV("screwTungstenSteel", getModItem("TwilightForest", "item.fieryTears", Items.poisonous_potato)),
    LUV("screwEnderium", getModItem("TwilightForest", "item.carminite", Items.apple)),
    ZPM("screwOriharukon", getModItem("TwilightForest", "item.carminite", Items.apple)),
    UV("screwOsmiridium", getModItem("dreamcraft", "SnowQueenBlood", Items.cake));

    private final String screw;
    private final ItemStack conductor;

    GTTier(String screw, ItemStack conductor) {
        this.screw = screw;
        this.conductor = conductor;
    }

    public String getScrew() {
        return screw;
    }

    public ItemStack getConductor() {
        return conductor;
    }

    private static final GTTier[] values = values();

    /**
     * Returns the next tier. If current tier is the last, it will return itself.
     */
    public GTTier nextTier() {
        if (this.ordinal() >= values.length - 1) {
            return this;
        } else {
            return values[this.ordinal() + 1];
        }
    }

    private static ItemStack getModItem(String mod, String item, Item defaultItem) {
        ItemStack stack = GameRegistry.findItemStack(mod, item, 1);
        return stack == null ? new ItemStack(defaultItem) : stack;
    }
}
