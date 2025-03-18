package com.bretzelfresser.justjerboa.datagen;

import com.bretzelfresser.justjerboa.JustJerboa;
import com.bretzelfresser.justjerboa.registries.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> recipeOutput) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Tags.Items.SEEDS), RecipeCategory.BUILDING_BLOCKS, ModItems.DRIED_GRASS_SEEDS.get(), 0.1F, 200).unlockedBy("has_seeds", has(Tags.Items.SEEDS)).save(recipeOutput);
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(Tags.Items.SEEDS), RecipeCategory.BUILDING_BLOCKS, ModItems.DRIED_GRASS_SEEDS.get(), 0.1F, 100).unlockedBy("has_seeds", has(Tags.Items.SEEDS)).save(recipeOutput, JustJerboa.modLoc("smoking_" + getItemName(ModItems.DRIED_GRASS_SEEDS.get())));
    }
}
