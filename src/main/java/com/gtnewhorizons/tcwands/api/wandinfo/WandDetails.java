package com.gtnewhorizons.tcwands.api.wandinfo;

import net.minecraft.item.ItemStack;

import com.github.bsideup.jabel.Desugar;
import com.gtnewhorizons.tcwands.api.GTTier;
import com.ruling_0.materiallib.api.Material;

import gregtech.api.material.MaterialUtils;

@Desugar
public record WandDetails(String name, GTTier tier, ItemStack conductor) {

    public Material getMaterial() {
        return tier.getMaterial();
    }

    public String getScrew() {
        return "screw" + MaterialUtils.internalName(this.getMaterial());
    }
}
