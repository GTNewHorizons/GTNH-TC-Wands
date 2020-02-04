package com.gtnewhorizons.tcwandprovider.api;

public enum GTTiers {
    LV(1),
    MV(2),
    HV(3),
    EV(4),
    IV(5),
    LUV(6),
    ZPM(7),
    UV(8);

    private int index;

    GTTiers(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
