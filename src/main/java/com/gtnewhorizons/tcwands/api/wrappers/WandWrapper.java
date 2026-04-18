package com.gtnewhorizons.tcwands.api.wrappers;

import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.tcwands.api.WandType;
import com.gtnewhorizons.tcwands.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwands.api.wandinfo.WandProps;

public class WandWrapper extends AbstractWandWrapper {

    public WandWrapper(WandDetails wandDetails, WandProps wandProps, ItemStack rod) {
        this(wandDetails, wandProps);
        setCustomCraftingRod(rod);
    }

    public WandWrapper(WandDetails wandDetails, WandProps wandProps) {
        super(wandDetails, wandProps);
    }

    @Override
    public @NotNull WandType getType() {
        return WandType.WAND;
    }
}
