package com.gtnewhorizons.tcwands.api.wrappers;

import com.gtnewhorizons.tcwands.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwands.api.wandinfo.WandProps;
import net.minecraft.init.Items;
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
                'R', customRod != null ? customRod : getItem(cap),
                'M', getDetails().getConductor(),
                'S', new ItemStack(Items.diamond_axe),//getDetails().getScrew()FIXME
                'C', cap.getItem()
        };
    }

    @Override
    public String getDefaultResearchName() {
        return "ROD_" + getDetails().getName();
    }
}
