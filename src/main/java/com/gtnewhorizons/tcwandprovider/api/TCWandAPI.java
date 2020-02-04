package com.gtnewhorizons.tcwandprovider.api;

import com.gtnewhorizons.tcwandprovider.api.wrappers.AbstractWandWrapper;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.lib.crafting.ArcaneSceptreRecipe;
import thaumcraft.common.lib.crafting.ArcaneWandRecipe;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class TCWandAPI {
    private static ArrayList<Object> craftingRecipes;
    private static ArrayList<IWandRegistry> registries = new ArrayList<>();
    private static ArrayList<AbstractWandWrapper> wandWrappers = new ArrayList<>();
    private static ArrayList<WandCap> caps = new ArrayList<>();

    static {
        try {
            Field f = ThaumcraftApi.class.getDeclaredField("craftingRecipes");
            f.setAccessible(true);
            craftingRecipes = (ArrayList<Object>) f.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Call it during {@link cpw.mods.fml.common.event.FMLInitializationEvent}
     */
    public static void addRegistry(IWandRegistry registry) {
        registries.add(registry);
    }

    public static void init() {
        removeTCWands();

        for (IWandRegistry registry : registries) {
            registry.register();
        }

        makeWands();
        dispose();
    }

    /**
     * Registers the wand.
     * Should be called from {@link IWandRegistry#register()}
     */
    public static void regWandWrapper(AbstractWandWrapper wandWrapper) {
        wandWrappers.add(wandWrapper);
    }

    /**
     * Registers the cap.
     * Should be called from {@link IWandRegistry#register()}
     */
    public static void regCap(WandCap cap) {
        caps.add(cap);
    }

    private static void makeWands() {
        for (AbstractWandWrapper wandWrapper : wandWrappers) {
            for (WandCap cap : caps) {
                regRecipe(wandWrapper, cap);
            }
        }
    }

    private static void regRecipe(AbstractWandWrapper wandWrapper, WandCap cap) {
        AspectList aspects = new AspectList();
        for (Aspect a : Aspect.getPrimalAspects())
            aspects.add(a, wandWrapper.getRecipeCost(cap));

        ItemStack wand = wandWrapper.getItem(cap);
        ThaumcraftApi.addArcaneCraftingRecipe(wandWrapper.getRecipeName(), wand, aspects, wandWrapper.genRecipe(cap));

        //FIXME why so?
//        if (core.getName().equals("warpwood"))
//            ThaumcraftApi.addArcaneCraftingRecipe("RoD_WarpwoodGTNH", wand, wandAspects, getRecipe(core, cap, false));
//        else if (core.getName().equals("warpwood_staff"))
//            ThaumcraftApi.addArcaneCraftingRecipe("RoD_Warpwood_StaffGTNH" + core, wand, wandAspects, getRecipe(core, cap, false));
//        else
//            ThaumcraftApi.addArcaneCraftingRecipe("ROD_" + core, wand, wandAspects, getRecipe(core, cap, false));
    }

    private static void removeTCWands() {
        craftingRecipes.removeIf(r -> r instanceof ArcaneWandRecipe || r instanceof ArcaneSceptreRecipe);
    }

    private static void dispose() {
        registries = null;
        wandWrappers = null;
        caps = null;
    }
}
