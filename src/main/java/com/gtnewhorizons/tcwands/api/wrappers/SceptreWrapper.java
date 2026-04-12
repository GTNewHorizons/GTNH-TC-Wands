package com.gtnewhorizons.tcwands.api.wrappers;

import net.minecraft.nbt.NBTTagCompound;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.tcwands.api.WandType;
import com.gtnewhorizons.tcwands.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwands.api.wandinfo.WandProps;

public class SceptreWrapper extends AbstractWandWrapper {

    private final float sceptreCostMultiplier;

    public SceptreWrapper(WandDetails wandDetails, WandProps wandProps, float sceptreCostMultiplier) {
        super(wandDetails, wandProps);
        this.sceptreCostMultiplier = sceptreCostMultiplier;
    }

    public float getSceptreCostMultiplier() {
        return sceptreCostMultiplier;
    }

    @Override
    protected NBTTagCompound writeNBT(CapWrapper cap) {
        NBTTagCompound wandNbt = super.writeNBT(cap);
        wandNbt.setByte("sceptre", (byte) 1);

        return wandNbt;
    }

    @Override
    public int getRecipeCost(CapWrapper cap) {
        return (int) (super.getRecipeCost(cap) * sceptreCostMultiplier);
    }

    @Override
    public @NotNull WandType getType() {
        return WandType.SCEPTRE;
    }
}
