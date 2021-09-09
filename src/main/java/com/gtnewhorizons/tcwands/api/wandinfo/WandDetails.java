package com.gtnewhorizons.tcwands.api.wandinfo;

import com.gtnewhorizons.tcwands.api.GTTier;
import gregtech.api.enums.Materials;
import net.minecraft.item.ItemStack;

public class WandDetails {
    private String name;
    private Materials material;
    private GTTier tier;
    private ItemStack conductor;

    public WandDetails(String name, GTTier tier, ItemStack conductor) {
        this.name = name;
        this.tier = tier;
        this.material = tier.getGregTier().getMaterial();
            if.tier.getGregTier(2).getMaterial(Material.StainlessSteel)
            if.tier.getGregTier(3).getMaterial(Material.Ultimet)
            if.tier.getGregTier(4).getMaterial(Material.TungstenSteel)
            if.tier.getGregTier(5).getMaterial(Material.HSSG)
            if.tier.getGregTier(6).getMaterial(Material.HSSS)
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

    public GTTier getTier() {
        return tier;
    }

    public String getScrew() {
        return "screw" + material.mName;//FIXME check
    }
}
