package com.gtnewhorizons.tcwands.api.wrappers;

import com.gtnewhorizons.tcwands.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwands.api.wandinfo.WandProps;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.common.config.ConfigItems;

public class SceptreWrapper extends AbstractWandWrapper {
    private float sceptreCostMultiplier;

    public SceptreWrapper(WandDetails wandDetails, WandProps wandProps, float sceptreCostMultiplier) {
//        super(wandDetails, wandProps, WandRod.rods.get(wandDetails.getName()));
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
    public Object[] genRecipe(CapWrapper cap) {
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
    public String getDefaultResearchName() {
        String name = getDetails().getName();
        return "SCEPTRE_" + (name.endsWith("_staff") ? name.substring(0, name.length() - 6) : name);//FIXME maybe only "SCEPTRE"?
    }
}
