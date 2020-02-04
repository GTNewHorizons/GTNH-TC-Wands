package com.gtnewhorizons.tcwandprovider.api.wrappers;

import com.gtnewhorizons.tcwandprovider.api.WandCap;
import com.gtnewhorizons.tcwandprovider.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwandprovider.api.wandinfo.WandProps;
import net.minecraft.item.ItemStack;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.common.config.ConfigItems;

public class StaffWrapper extends AbstractWandWrapper {
    public StaffWrapper(WandDetails details, WandProps props) {
        super(details, props, StaffRod.rods.get(details.getName() + "_staff"));
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
    public String getRodName() {
        return super.getRodName() + "_staff";
    }

    @Override
    public String getRecipeName() {
        return "ROD_" + getDetails().getName();
    }
}
