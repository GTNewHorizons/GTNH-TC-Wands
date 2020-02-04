package com.gtnewhorizons.tcwandprovider.api.wandinfo;

import com.gtnewhorizons.tcwandprovider.api.GTTiers;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Tier;
import net.minecraft.item.ItemStack;

public class WandDetails {
    private String name;
    private Materials material;
    private ItemStack conductor;

    public WandDetails(String name, GTTiers tier, ItemStack conductor) {
        this.name = name;
        this.material = Tier.ELECTRIC[tier.getIndex()].getMaterial();
        this.conductor = conductor;
    }

    public ItemStack getConductor() {
        return conductor;
    }

    public String getName() {
        return name;
    }

    public Materials getMaterial() {
        return material;
    }

    public String getScrew() {
        return "screw" + material.mName;//FIXME check
    }
}
