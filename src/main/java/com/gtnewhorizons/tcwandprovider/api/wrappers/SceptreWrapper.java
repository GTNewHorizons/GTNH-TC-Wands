package com.gtnewhorizons.tcwandprovider.api.wrappers;

import com.gtnewhorizons.tcwandprovider.api.WandCap;
import com.gtnewhorizons.tcwandprovider.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwandprovider.api.wandinfo.WandProps;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigItems;

public class SceptreWrapper extends AbstractWandWrapper {
    private float sceptreCostMultiplier;

    public SceptreWrapper(WandDetails wandDetails, WandProps wandProps, float sceptreCostMultiplier) {
        super(wandDetails, wandProps, WandRod.rods.get(wandDetails.getName()));
        this.sceptreCostMultiplier = sceptreCostMultiplier;
    }

    public float getSceptreCostMultiplier() {
        return sceptreCostMultiplier;
    }

    @Override
    protected NBTTagCompound writeNBT(WandCap cap) {
        NBTTagCompound wandNbt = super.writeNBT(cap);
        wandNbt.setByte("sceptre", (byte) 1);

        return wandNbt;
    }

    @Override
    public int getRecipeCost(WandCap cap) {
        return (int) (super.getRecipeCost(cap) * getSceptreCostMultiplier());
    }

    @Override
    public Object[] genRecipe(WandCap cap) {
        return new Object[]{
                "MCP",
                "SRC",
                "CSM",
                'R', getItem(cap),
                'M', getDetails().getConductor(),
                'S', getDetails().getScrew(),
                'C', cap.getItem(),
                'P', new ItemStack(ConfigItems.itemResource, 1, 15) //Primal Charm
        };
    }

    @Override
    public String getRecipeName() {
        String name = getDetails().getName();
        return "SCEPTRE_" + (name.endsWith("_staff") ? name.substring(0, name.length() - 6) : name);
    }
}
