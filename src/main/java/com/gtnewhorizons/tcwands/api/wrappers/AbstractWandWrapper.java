package com.gtnewhorizons.tcwands.api.wrappers;

import com.gtnewhorizons.tcwands.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwands.api.wandinfo.WandProps;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigItems;

public abstract class AbstractWandWrapper {
    private WandDetails wandDetails;
    private WandProps wandProps;

    private String customResearchName;
    private ItemStack rod;

    public AbstractWandWrapper(WandDetails wandDetails, WandProps wandProps) {
        this.wandDetails = wandDetails;
        this.wandProps = wandProps;

        WandRod wandRod = WandRod.rods.get(getRodName());

        if (wandRod == null)
            throw new NullPointerException("Can't find provided wand rod with id: " + getRodName() + ". Be careful to register your custom rod before creating recipes.");

        this.rod = wandRod.getItem();
    }

    public ItemStack getItem(CapWrapper cap) {
        ItemStack wand = new ItemStack(ConfigItems.itemWandCasting);

        NBTTagCompound wandNbt = writeNBT(cap);

        wand.setTagCompound(wandNbt);
        wand.setItemDamage(getRecipeCost(cap));

        return wand;
    }

    protected NBTTagCompound writeNBT(CapWrapper cap) {
        NBTTagCompound wandNbt = new NBTTagCompound();
        wandNbt.setString("rod", getRodName());
        wandNbt.setString("cap", cap.getName());

        return wandNbt;
    }

    public int getRecipeCost(CapWrapper cap) {
        return wandProps.getBaseCost() + wandProps.getCapCost() * cap.getCostMultiplier();
    }

    public abstract Object[] genRecipe(CapWrapper cap);

    public WandProps getProps() {
        return wandProps;
    }

    public WandDetails getDetails() {
        return wandDetails;
    }

    protected abstract String getDefaultResearchName();

    public String getResearchName() {
        return customResearchName != null ? customResearchName : getDefaultResearchName();
    }

    public void setCustomResearchName(String customResearchName) {
        this.customResearchName = customResearchName;
    }

    public ItemStack getRod() {
        return rod;
    }

    public String getRodName() {
        return wandDetails.getName();
    }
}
