package com.gtnewhorizons.tcwandprovider;

import cpw.mods.fml.common.Mod;

@Mod(modid = TCWandProvider.MODID, name = TCWandProvider.NAME, version = TCWandProvider.VERSION, dependencies = TCWandProvider.DEPENDENCIES)
public class TCWandProvider {
    public static final String MODID = "tcwandprovider";
    public static final String NAME = "TC Wand Provider";
    public static final String VERSION = "GRADLETOKEN_VERSION";

    public static final String DEPENDENCIES =
            "required-after:Thaumcraft;"
                    + "required-after:dreamcraft;"
                    + "required-after:gregtech;"
                    + "required-after:TwilightForest;"
                    + "after:ForbiddenMagic;"
                    + "after:TaintedMagic;"
                    + "after:BloodArsenal;"
                    + "after:thaumicbases;"
                    + "after:ThaumicExploration;"
                    + "after:ThaumicTinkerer;";

    @Mod.Instance
    public static TCWandProvider instance;
}
