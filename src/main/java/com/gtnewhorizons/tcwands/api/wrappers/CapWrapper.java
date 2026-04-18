package com.gtnewhorizons.tcwands.api.wrappers;

import net.minecraft.item.ItemStack;

public class CapWrapper {

    private final int costMultiplier;
    private final String name;
    private final ItemStack itemStack;
    private String research;

    public CapWrapper(String name, int costMultiplier) {
        this(name, thaumcraft.api.wands.WandCap.caps.get(name).getItem(), costMultiplier);
    }

    public CapWrapper(String name, ItemStack itemStack, int costMultiplier) {
        this.name = name;
        this.costMultiplier = costMultiplier;
        this.itemStack = itemStack;
        this.research = "CAP_" + name;
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

    public String getResearch() {
        return research;
    }

    public void setResearch(String research) {
        this.research = research;
    }
}
