package com.gtnewhorizons.tcwandprovider;

import com.gtnewhorizons.tcwandprovider.api.IWandRegistry;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GTWandRegistry implements IWandRegistry {
    private static final ItemStack NAGA = GT_ModHandler.getModItem("TwilightForest", "item.nagaScale", 1, 0, new ItemStack(Items.wheat)),
            LICH = GT_ModHandler.getModItem("dreamcraft", "item.LichBone", 1, 0, new ItemStack(Items.carrot)),
            HYDRA = GT_ModHandler.getModItem("TwilightForest", "item.fieryBlood", 1, 0, new ItemStack(Items.potato)),
            GHAST = GT_ModHandler.getModItem("TwilightForest", "item.fieryTears", 1, 0, new ItemStack(Items.poisonous_potato)),
            CARMINITE = GT_ModHandler.getModItem("TwilightForest", "item.carminite", 1, 0, new ItemStack(Items.apple)),
            QUEEN = GT_ModHandler.getModItem("dreamcraft", "item.SnowQueenBlood", 1, 0, new ItemStack(Items.cake));

    @Override
    public void register() {
//        new WandCreator("wood", GTTiers.MV, NAGA).regWand();
//        cores.add(new WandCore("wood", GT_ModHandler.getModItem("Forestry", "oakStick", 1, 0, new ItemStack(Items.stick)), MV, NAGA, 0, 5, 2F));
//        cores.add(new WandCore("greatwood", HV, LICH, 20, 5, 2F));
//        cores.add(new WandCore("reed", EV, HYDRA, 50, 10, 1.5F));
//        cores.add(new WandCore("blaze", EV, HYDRA, 50, 10, 1.5F));
//        cores.add(new WandCore("obsidian", EV, HYDRA, 50, 10, 1.5F));
//        cores.add(new WandCore("ice", EV, HYDRA, 50, 10, 1.5F));
//        cores.add(new WandCore("quartz", EV, HYDRA, 50, 10, 1.5F));
//        cores.add(new WandCore("silverwood", IV, GHAST, 75, 15, 1.4F));
//        cores.add(new WandCore("greatwood_staff", EV, HYDRA, 75, 15, 1.4F));
//        cores.add(new WandCore("reed_staff", IV, GHAST, 125, 15, 1.5F));
//        cores.add(new WandCore("blaze_staff", IV, GHAST, 125, 15, 1.5F));
//        cores.add(new WandCore("obsidian_staff", IV, GHAST, 125, 15, 1.5F));
//        cores.add(new WandCore("ice_staff", IV, GHAST, 125, 15, 1.5F));
//        cores.add(new WandCore("quartz_staff", IV, GHAST, 125, 15, 1.5F));
//        cores.add(new WandCore("bone_staff", IV, GHAST, 125, 15, 1.5F));
//        cores.add(new WandCore("silverwood_staff", LUV, CARMINITE, 150, 15 , 1.2F));
//        cores.add(new WandCore("primal_staff", ZPM, CARMINITE, 175, 20, 1.6F));
//
//        cores.add(new WandCore("profane", HV, LICH, 25, 5, 2F));
//        cores.add(new WandCore("tainted", IV, GHAST, 125, 15, 1.5F));
//        cores.add(new WandCore("blood", IV, GHAST, 125, 15, 1.5F));
//        cores.add(new WandCore("blood_staff", LUV, CARMINITE, 150, 15, 1.2F));
//        cores.add(new WandCore("infernal", IV, GHAST, 125, 15, 1.5F));
//        cores.add(new WandCore("livingwood", IV, GHAST, 75, 15, 1.4F));
//        cores.add(new WandCore("dreamwood", IV, GHAST, 75, 15, 1.4F));
//        cores.add(new WandCore("dreamwood_staff", LUV, CARMINITE, 150, 15, 1.2F));
//        cores.add(new WandCore("witchwood", IV, GHAST, 75, 15, 1.4F));
//        cores.add(new WandCore("witchwood_staff",LUV, CARMINITE, 150, 15, 1.2F));
//
//        cores.add(new WandCore("ICHORCLOTH", UV, QUEEN, 250, 25, 1.2F));
//
//        cores.add(new WandCore("blood_wood",IV, GHAST, 130, 15, 1.2F));
//        cores.add(new WandCore("blood_wood_staff", LUV, CARMINITE, 175, 20, 1.6F));
//
//        cores.add(new WandCore("warpwood", IV, GHAST, 135, 15, 1.2F));
//        cores.add(new WandCore("warpwood_staff", IV, GHAST, 200, 25, 1.2F));
//
//        cores.add(new WandCore("tbthaumium", EV, HYDRA, 60, 10, 1.5F));
//        cores.add(new WandCore("tbvoid", IV, GHAST, 160, 15, 1.2F));
//
//        cores.add(new WandCore("AMBER", HV, LICH, 20, 5, 2F));
//        cores.add(new WandCore("AMBER_staff", EV, HYDRA, 75, 15, 1.4F));
//        cores.add(new WandCore("TRANSMUTATION", EV, HYDRA, 50, 10, 1.5F));
//        cores.add(new WandCore("TRANSMUTATION_staff", IV, GHAST, 125, 15, 1.5F));
//        //	cores.add(new WandCore("BREAD",HV, LICH, 20, 5, 2F));
//        //	cores.add(new WandCore("ledox", ZPM, CARMINITE, 250, 15, 1.4F));
    }
}
