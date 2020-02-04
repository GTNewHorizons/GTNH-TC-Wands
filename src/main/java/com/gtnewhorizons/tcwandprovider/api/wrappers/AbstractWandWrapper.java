package com.gtnewhorizons.tcwandprovider.api.wrappers;

import com.gtnewhorizons.tcwandprovider.api.WandCap;
import com.gtnewhorizons.tcwandprovider.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwandprovider.api.wandinfo.WandProps;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigItems;

public abstract class AbstractWandWrapper {
    private WandDetails wandDetails;
    private WandProps wandProps;
    private ItemStack rod;

    public AbstractWandWrapper(WandDetails wandDetails, WandProps wandProps, WandRod rod) {
        this(wandDetails, wandProps, rod.getItem());
    }

    public AbstractWandWrapper(WandDetails wandDetails, WandProps wandProps, ItemStack rod) {
        this.wandDetails = wandDetails;
        this.wandProps = wandProps;
        this.rod = rod;
    }

    public ItemStack getItem(WandCap cap) {
        ItemStack wand = new ItemStack(ConfigItems.itemWandCasting);

        NBTTagCompound wandNbt = writeNBT(cap);

        wand.setTagCompound(wandNbt);
        wand.setItemDamage(getRecipeCost(cap));

        return wand;
    }

    protected NBTTagCompound writeNBT(WandCap cap) {
        NBTTagCompound wandNbt = new NBTTagCompound();
        wandNbt.setString("rod", getRodName());
        wandNbt.setString("cap", cap.getName());

        return wandNbt;
    }

    public int getRecipeCost(WandCap cap) {
        return wandProps.getBaseCost() + wandProps.getCapCost() * cap.getCostMultiplier();
    }

    public abstract Object[] genRecipe(WandCap cap);

    public WandProps getProps() {
        return wandProps;
    }

    public WandDetails getDetails() {
        return wandDetails;
    }

    public abstract String getRecipeName();

    public String getRodName() {
        return wandDetails.getName();
    }
}
