package com.gtnewhorizons.tcwands.api.wrappers;

import com.gtnewhorizons.tcwands.api.WandType;
import com.gtnewhorizons.tcwands.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwands.api.wandinfo.WandProps;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class WandWrapper extends AbstractWandWrapper {

    public WandWrapper(WandDetails wandDetails, WandProps wandProps, ItemStack rod) {
        this(wandDetails, wandProps);
        setCustomCraftingRod(rod);
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
                'R', getCraftingRod(),
                'M', getDetails().getConductor(),
                'S', getDetails().getScrew(),
                'C', cap.getItem()
        };
    }

    @Override
    public String getDefaultResearchName() {
        return "ROD_" + getDetails().getName();
    }

    @Override
    public @NotNull WandType getType() {
        return WandType.WAND;
    }
}
