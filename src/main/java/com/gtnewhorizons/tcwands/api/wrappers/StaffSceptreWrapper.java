package com.gtnewhorizons.tcwands.api.wrappers;

import com.gtnewhorizons.tcwands.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwands.api.wandinfo.WandProps;

public class StaffSceptreWrapper extends SceptreWrapper {
    public StaffSceptreWrapper(WandDetails wandDetails, WandProps wandProps, float sceptreCostMultiplier) {
        super(wandDetails, wandProps, sceptreCostMultiplier);
    }

    @Override
    public String getRodName() {
        return super.getRodName() + "_staff";
    }
}
