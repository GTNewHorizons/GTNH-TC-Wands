package com.gtnewhorizons.tcwands.api;

import com.gtnewhorizons.tcwands.api.wandinfo.WandDetails;
import com.gtnewhorizons.tcwands.api.wandinfo.WandProps;
import com.gtnewhorizons.tcwands.api.wrappers.*;
import net.minecraft.item.ItemStack;
import org.intellij.lang.annotations.MagicConstant;

public class WandRecipeCreator {
    public static final int WAND = 0;
    public static final int STAFF = 1;
    private String name;
    private WandDetails wandDetails = null;
    private WandDetails staffDetails = null;
    private WandProps wandProps = null;
    private WandProps staffProps = null;

    private String customResearchName = null;

    /**
     * The name of rod material.
     * Shouldn't contain _staff postfix, it will be substituted automatically if you register a staff or staff-sceptre recipe.
     */
    public WandRecipeCreator(String name) {
        this.name = name;
    }

    /**
     * Applies custom research name to the next registered wand,staff, etc recipe.
     *
     * @param customResearchName name to be applied
     */
    public WandRecipeCreator applyCustomResearchName(String customResearchName) {
        this.customResearchName = customResearchName;

        return this;
    }

    /**
     * Registers wand recipe with provided base, cap cost and GT tier.
     * Conductor will be got from tier.
     */
    public WandRecipeCreator regWandRecipe(int baseCost, int capCost, GTTier tier) {
        return regWandRecipe(baseCost, capCost, tier, tier.getConductor());
    }

    /**
     * Registers wand recipe with provided base, cap cost, GT tier and conductor.
     */
    public WandRecipeCreator regWandRecipe(int baseCost, int capCost, GTTier tier, ItemStack conductor) {
        this.wandProps = new WandProps(baseCost, capCost);
        this.wandDetails = new WandDetails(name, tier, conductor);
        regWandWrapper(new WandWrapper(wandDetails, wandProps));
        return this;
    }

    /**
     * Registers wand recipe with provided base, cap cost, GT tier and custom rod itemstack, that will be used for crafting.
     * Conductor will be got from tier.
     */
    public WandRecipeCreator regWandRecipe(int baseCost, int capCost, ItemStack rod, GTTier tier) {
        return regWandRecipe(baseCost, capCost, rod, tier, tier.getConductor());
    }

    /**
     * Registers wand recipe with provided base, cap cost, GT tier, conductor and custom rod itemstack, that will be used for crafting.
     */
    public WandRecipeCreator regWandRecipe(int baseCost, int capCost, ItemStack rod, GTTier tier, ItemStack conductor) {
        this.wandProps = new WandProps(baseCost, capCost);
        this.wandDetails = new WandDetails(name, tier, conductor);
        regWandWrapper(new WandWrapper(wandDetails, wandProps, rod));
        return this;
    }

    /**
     * Register staff recipe, where its GT level and conductor depends on wand.
     */
    public WandRecipeCreator regWandDepStaffRecipe(int baseCost, int capCost) {
        if (wandDetails == null) {
            throw new IllegalStateException("You can't use regStaffRecipe(int baseCost, int capCost) without calling regWandRecipe(...).");
        }

        this.staffProps = new WandProps(baseCost, capCost);
        regWandWrapper(new StaffWrapper(wandDetails, staffProps));
        return this;
    }

    /**
     * Register staff recipe with provided base, cap cost and with level one higher tier and its standard conductor.
     */
    public WandRecipeCreator regUpwardStaffRecipe(int baseCost, int capCost) {
        GTTier tier = wandDetails.getTier().nextTier();
        return regStaffRecipe(baseCost, capCost, tier);
    }

    /**
     * Register staff recipe with provided base, cap cost and GT tier.
     * Conductor will be got from tier.
     */
    public WandRecipeCreator regStaffRecipe(int baseCost, int capCost, GTTier tier) {
        return regStaffRecipe(baseCost, capCost, tier, tier.getConductor());
    }

    /**
     * Register staff recipe with provided base, cap cost, GT tier and conductor.
     */
    public WandRecipeCreator regStaffRecipe(int baseCost, int capCost, GTTier tier, ItemStack conductor) {
        this.staffProps = new WandProps(baseCost, capCost);
        this.staffDetails = new WandDetails(name, tier, conductor);
        regWandWrapper(new StaffWrapper(staffDetails, staffProps));
        return this;
    }

    /**
     * Register sceptre recipe, where sceptre characteristics are got from registered wand.
     * Mustn't be called before any {@link #regWandRecipe} method.
     *
     * @param sceptreCostMultiplier multiplier that will be applied on total recipe cost.
     */
    public WandRecipeCreator regSceptreRecipe(float sceptreCostMultiplier) {
        return regSceptreRecipe(WAND, sceptreCostMultiplier);
    }

    /**
     * Register sceptre recipe, where sceptre characteristics are got from registered wand.
     * Mustn't be called before any {@link #regStaffRecipe} method.
     *
     * @param sceptreCostMultiplier multiplier that will be applied on total recipe cost.
     */
    public WandRecipeCreator regStaffSceptreRecipe(float sceptreCostMultiplier) {
        return regSceptreRecipe(STAFF, sceptreCostMultiplier);
    }

    /**
     * Register sceptre.
     * Mustn't be called before any {@link #regWandRecipe} or {@link #regStaffRecipe} method (depends on {@code parentType} param).
     *
     * @param sceptreCostMultiplier multiplier that will be applied on total recipe cost.
     */
    public WandRecipeCreator regSceptreRecipe(@MagicConstant(intValues = {WAND, STAFF}) int parentType, float sceptreCostMultiplier) {
        SceptreWrapper sceptreWrapper;
        if (parentType == WAND) {
            if (wandDetails == null || wandProps == null) {
                throw new IllegalStateException("You can't use regSceptreRecipe(...) with flag " + parentType + " without calling regWandRecipe(...) method.");
            }

            sceptreWrapper = new SceptreWrapper(wandDetails, wandProps, sceptreCostMultiplier);
        } else if (parentType == STAFF) {
            if (staffDetails == null || staffProps == null) {
                throw new IllegalStateException("You can't use regSceptreRecipe(...) with flag " + parentType + " without calling regStaffRecipe(...) method.");
            }
            sceptreWrapper = new StaffSceptreWrapper(staffDetails, staffProps, sceptreCostMultiplier);
        } else {
            throw new UnsupportedOperationException("Provided unknown type: " + parentType);
        }

        regWandWrapper(sceptreWrapper);
        return this;
    }

    private void regWandWrapper(AbstractWandWrapper wandWrapper) {
        applyCustomizations(wandWrapper);
        TCWandAPI.regWandWrapper(wandWrapper);
    }

    private void applyCustomizations(AbstractWandWrapper wandWrapper) {
        if (customResearchName != null) {
            wandWrapper.setCustomResearchName(customResearchName);
            customResearchName = null;
        }
    }
}
