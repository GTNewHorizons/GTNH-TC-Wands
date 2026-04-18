package com.gtnewhorizons.tcwands.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import com.github.bsideup.jabel.Desugar;
import com.gtnewhorizons.tcwands.api.wrappers.AbstractWandWrapper;
import com.gtnewhorizons.tcwands.api.wrappers.CapWrapper;
import com.gtnewhorizons.tcwands.api.wrappers.SceptreWrapper;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.lib.crafting.ArcaneSceptreRecipe;
import thaumcraft.common.lib.crafting.ArcaneWandRecipe;

public class TCWandAPI {

    private static final ArrayList<IWandRegistry> registries = new ArrayList<>();
    private static final ArrayList<AbstractWandWrapper> wandWrappers = new ArrayList<>();
    private static final Map<String, Map<Boolean, AbstractWandWrapper>> rodTagToWrapper = new HashMap<>();
    private static final ArrayList<CapWrapper> caps = new ArrayList<>();
    private static final HashMap<String, CapWrapper> capTagToWrapper = new HashMap<>();

    /**
     * Call it during {@link cpw.mods.fml.common.event.FMLInitializationEvent}
     */
    public static void addRegistry(IWandRegistry registry) {
        registries.add(registry);
    }

    public static void init() {
        GTTier.init();

        removeTCWands();

        for (IWandRegistry registry : registries) {
            registry.register();
        }

        makeWands();
    }

    /**
     * Registers the wand. Should be called from {@link IWandRegistry#register()}
     */
    public static void regWandWrapper(AbstractWandWrapper wandWrapper) {
        wandWrappers.add(wandWrapper);
        rodTagToWrapper
                .computeIfAbsent(wandWrapper.getRodName(), k -> new HashMap<>())
                .put(wandWrapper instanceof SceptreWrapper, wandWrapper);
    }

    /**
     * Registers the cap. Should be called from {@link IWandRegistry#register()}
     */
    public static void regCap(CapWrapper cap) {
        caps.add(cap);
        capTagToWrapper.put(cap.getName(), cap);
    }

    @SuppressWarnings("unchecked")
    private static void makeWands() {
        ThaumcraftApi.getCraftingRecipes().add(new GTNHWandRecipe());
        ThaumcraftApi.getCraftingRecipes().add(new GTNHScepterRecipe());
    }

    /**
     * Adds rod or replaces existing one. You still need to register wand recipe.
     */
    public static void makeRod(String name, int capacity, ItemStack item, int craftCost, IWandRodOnUpdate update,
            boolean glowing, ResourceLocation texture) {
        WandRod rod = new WandRod(name, capacity, item, craftCost, texture);
        rod.setGlowing(glowing);
        if (update != null) rod.setOnUpdate(update);
    }

    /**
     * Adds staff or replaces existing one. You still need to register wand recipe.
     *
     * @param name the name of staff. Should NOT contain '_staff' postfix, because it will be added automatically.
     */
    public static void makeStaff(String name, int capacity, ItemStack item, int cost, IWandRodOnUpdate update,
            boolean glowing, ResourceLocation texture) {
        StaffRod staff = new StaffRod(name, capacity, item, cost, texture);
        staff.setGlowing(glowing);
        if (update != null) staff.setOnUpdate(update);
    }

    /**
     * Adds cap or replaces existing one. You still need to register wand recipe.
     *
     * @param name     name of cap
     * @param stack    the actual item that makes up this cap and will be used to generate the wand recipes
     * @param discount amount by which all aspect costs are multiplied (if you want to provide a discount, use value
     *                 between 0 and 1)
     * @param cost     cost to craft this wand. Combined with the rod cost.
     * @param texture  texture that will be used for the ingame wand cap
     */
    public static void makeCap(String name, ItemStack stack, float discount, int cost, ResourceLocation texture) {
        WandCap cap = new WandCap(name, discount, stack, cost);
        cap.setTexture(texture);
    }

    /**
     * Adds cap or replaces existing one. You still need to register wand recipe.
     *
     * @param name            name of cap
     * @param stack           the actual item that makes up this cap and will be used to generate the wand recipes
     * @param discount        amount by which all aspect costs are multiplied (if you want to provide a discount, use
     *                        value between 0 and 1)
     * @param list            specifies a list of primal aspects that use the special discount figure instead of the
     *                        normal discount
     * @param discountSpecial amount by which the specified aspect costs are multiplied (if you want to provide a
     *                        discount, use value between 0 and 1)
     * @param cost            cost to craft this wand. Combined with the rod cost.
     * @param texture         texture that will be used for the ingame wand cap
     */
    public static void makeCap(String name, ItemStack stack, float discount, List<Aspect> list, float discountSpecial,
            int cost, ResourceLocation texture) {
        WandCap cap = new WandCap(name, discount, list, discountSpecial, stack, cost);
        cap.setTexture(texture);
    }

    @SuppressWarnings("unchecked")
    private static void removeTCWands() {
        ThaumcraftApi.getCraftingRecipes()
                .removeIf(r -> r instanceof ArcaneWandRecipe || r instanceof ArcaneSceptreRecipe);
    }

    public static ArrayList<CapWrapper> getCaps() {
        return caps;
    }

    public static ArrayList<AbstractWandWrapper> getWandWrappers() {
        return wandWrappers;
    }

    public static AbstractWandWrapper getWrapperForRod(String tag, boolean scepter) {
        Map<Boolean, AbstractWandWrapper> inner = rodTagToWrapper.get(tag);
        return inner != null ? inner.get(scepter) : null;
    }

    public static AbstractWandWrapper getWrapperForRod(ItemStack item, boolean scepter) {
        for (WandRod rod : WandRod.rods.values()) {
            if (OreDictionary.itemMatches(rod.getItem(), item, true)) {
                return getWrapperForRod(rod, scepter);
            }
        }
        return null;
    }

    public static AbstractWandWrapper getWrapperForRod(WandRod rod, boolean scepter) {
        return getWrapperForRod(rod.getTag(), scepter);
    }

    public static CapWrapper getWrapperForCap(String tag) {
        return capTagToWrapper.get(tag);
    }

    public static CapWrapper getWrapperForCap(ItemStack item) {
        for (WandCap cap : WandCap.caps.values()) {
            if (OreDictionary.itemMatches(cap.getItem(), item, true)) return getWrapperForCap(cap);
        }
        return null;
    }

    public static CapWrapper getWrapperForCap(WandCap cap) {
        return getWrapperForCap(cap.getTag());
    }
}
