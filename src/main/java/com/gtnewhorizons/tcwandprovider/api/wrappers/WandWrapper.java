package com.gtnewhorizons.tcwandprovider.api.wrappers;

import com.gtnewhorizons.tcwandprovider.api.WandCap;
import com.gtnewhorizons.tcwandprovider.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwandprovider.api.wandinfo.WandProps;
import net.minecraft.item.ItemStack;
import thaumcraft.api.wands.WandRod;

public class WandWrapper extends AbstractWandWrapper {
    public WandWrapper(WandDetails wandDetails, WandProps wandProps) {
        super(wandDetails, wandProps, WandRod.rods.get(wandDetails.getName()));
    }

    public WandWrapper(WandDetails wandDetails, WandProps wandProps, ItemStack rod) {
        super(wandDetails, wandProps, rod);
    }

    @Override
    public Object[] genRecipe(WandCap cap) {
        return new Object[]{
                "MSC",
                "SRS",
                "CSM",
                'R', getItem(cap),
                'M', getDetails().getConductor(),
                'S', getDetails().getScrew(),
                'C', cap.getItem()
        };
    }

    @Override
    public String getRecipeName() {
        return "ROD_" + getDetails().getName();
    }
}
