package com.gtnewhorizons.tcwandprovider.api;

import com.gtnewhorizons.tcwandprovider.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwandprovider.api.wandinfo.WandProps;
import com.gtnewhorizons.tcwandprovider.api.wrappers.SceptreWrapper;
import com.gtnewhorizons.tcwandprovider.api.wrappers.StaffWrapper;
import com.gtnewhorizons.tcwandprovider.api.wrappers.WandWrapper;
import net.minecraft.item.ItemStack;

public class WandCreator {
    private WandDetails wandDetails;
    private WandProps wandProps = null;
    private WandProps staffProps = null;

    public WandCreator(WandDetails props) {
        wandDetails = props;
    }

    public WandCreator(String name, GTTiers tier, ItemStack conductor) {
        this(new WandDetails(name, tier, conductor));
    }

    public WandCreator regWand(WandProps wandProps) {
        this.wandProps = wandProps;
        TCWandAPI.regWandWrapper(new WandWrapper(wandDetails, wandProps));
        return this;
    }

    public WandCreator regWand(int baseCost, int capCost) {
        return regWand(new WandProps(baseCost, capCost));
    }

    public WandCreator regWand(int baseCost, int capCost, ItemStack rod) {
        this.wandProps = new WandProps(baseCost, capCost);
        TCWandAPI.regWandWrapper(new WandWrapper(wandDetails, wandProps, rod));
        return this;
    }

    public WandCreator regStaff(WandProps staffProps) {
        this.staffProps = staffProps;
        TCWandAPI.regWandWrapper(new StaffWrapper(wandDetails, staffProps));
        return this;
    }

    public WandCreator regStaff(int baseCost, int capCost) {
        return regStaff(new WandProps(baseCost, capCost));
    }

    public WandCreator regSceptre(WandProps sceptreProps, float sceptreCostMultiplier) {
        TCWandAPI.regWandWrapper(new SceptreWrapper(wandDetails, sceptreProps, sceptreCostMultiplier));
        return this;
    }

    public WandCreator regSceptre(int baseCost, int capCost, float sceptreCostMultiplier) {
        return regSceptre(new WandProps(baseCost, capCost), sceptreCostMultiplier);
    }

    public WandCreator regSceptre(float sceptreCostMultiplier) {
        if (staffProps == null && wandProps == null) {
            throw new IllegalStateException("You can't use regSceptre(sceptreCostMultiplier) without calling regWand(...) or regStaff(...).\nIf you want to reg only a Sceptre, use regSceptre(sceptreProps, sceptreCostMultiplier)");
        }

        TCWandAPI.regWandWrapper(new SceptreWrapper(wandDetails, wandProps != null ? wandProps : staffProps, sceptreCostMultiplier));
        return this;
    }
}
