package com.gtnewhorizons.tcwands.api.wandinfo;

import net.minecraft.item.ItemStack;

import com.github.bsideup.jabel.Desugar;
import com.gtnewhorizons.tcwands.api.GTTier;

import gregtech.api.enums.Materials;

@Desugar
public record WandDetails(String name, GTTier tier, ItemStack conductor) {

    public Materials getMaterial() {
        return tier.getMaterial();
    }

    public String getScrew() {
        return "screw" + this.getMaterial().mName;// FIXME check
    }
}
