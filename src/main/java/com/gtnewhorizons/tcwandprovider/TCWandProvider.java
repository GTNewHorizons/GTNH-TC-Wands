package com.gtnewhorizons.tcwandprovider;

import com.gtnewhorizons.tcwandprovider.api.TCWandAPI;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = TCWandProvider.MODID, name = TCWandProvider.NAME, version = TCWandProvider.VERSION, dependencies = TCWandProvider.DEPENDENCIES)
public class TCWandProvider {
    public static final String MODID = "tcwandprovider";
    public static final String NAME = "TC Wand Provider";
    public static final String VERSION = "GRADLETOKEN_VERSION";

    public static final String DEPENDENCIES =
            "required-after:Thaumcraft;"
                    /* + "required-after:dreamcraft;"*/
                    + "required-after:gregtech;"
                    /* + "required-after:TwilightForest;"*/
                    + "after:ForbiddenMagic;"
                    + "after:TaintedMagic;"
                    + "after:BloodArsenal;"
                    + "after:thaumicbases;"
                    + "after:ThaumicExploration;"
                    + "after:ThaumicTinkerer;";

    @Mod.Instance
    public static TCWandProvider instance;

    private static GTWandRegistry wandRegistry = new GTWandRegistry();

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        TCWandAPI.addRegistry(wandRegistry);
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        TCWandAPI.init();
    }
}
