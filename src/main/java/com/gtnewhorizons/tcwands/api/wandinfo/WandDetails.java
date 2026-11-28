package com.gtnewhorizons.tcwands.api.wandinfo;

import net.minecraft.item.ItemStack;

import com.gtnewhorizons.tcwands.api.GTTier;

public record WandDetails(String name, GTTier tier, ItemStack conductor) {}
