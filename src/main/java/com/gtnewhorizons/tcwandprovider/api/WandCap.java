package com.gtnewhorizons.tcwandprovider.api;

import net.minecraft.item.ItemStack;

public class WandCap {
    private int costMultiplier;
    private String name;
    private ItemStack itemStack;

    public WandCap(String name, int costMultiplier) {
        this(name, thaumcraft.api.wands.WandCap.caps.get(name).getItem(), costMultiplier);
    }

    public WandCap(String name, ItemStack itemStack, int costMultiplier) {
        this.name = name;
        this.costMultiplier = costMultiplier;
        this.itemStack = itemStack;
    }

    public String getName() {
        return name;
    }

    public int getCostMultiplier() {
        return costMultiplier;
    }

    public ItemStack getItem() {
        return itemStack;
    }
}
