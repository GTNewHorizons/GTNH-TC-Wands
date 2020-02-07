package com.gtnewhorizons.tcwands.api.wrappers;

import com.gtnewhorizons.tcwands.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwands.api.wandinfo.WandProps;
import net.minecraft.item.ItemStack;

public class WandWrapper extends AbstractWandWrapper {
    private ItemStack customRod = null;

    public WandWrapper(WandDetails wandDetails, WandProps wandProps, ItemStack rod) {
        this(wandDetails, wandProps);
        this.customRod = rod;
    }

    public WandWrapper(WandDetails wandDetails, WandProps wandProps) {
        super(wandDetails, wandProps);
    }

    @Override
    public Object[] genRecipe(CapWrapper cap) {
        return new Object[]{
                "MSC",
                "SRS",
                "CSM",
                'R', customRod != null ? customRod : getRod(),
                'M', getDetails().getConductor(),
                'S', getDetails().getScrew(),
                'C', cap.getItem()
        };
    }

    @Override
    public String getDefaultResearchName() {
        return "ROD_" + getDetails().getName();
    }
}
