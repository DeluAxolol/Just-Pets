package com.bretzelfresser.datagen;

import com.bretzelfresser.JustJerboa;
import com.bretzelfresser.registries.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Tags.Items.SEEDS), RecipeCategory.BUILDING_BLOCKS, ModItems.DRIED_GRASS_SEEDS, 0.1F, 200).unlockedBy("has_seeds", has(Tags.Items.SEEDS)).save(recipeOutput);
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(Tags.Items.SEEDS), RecipeCategory.BUILDING_BLOCKS, ModItems.DRIED_GRASS_SEEDS, 0.1F, 100).unlockedBy("has_seeds", has(Tags.Items.SEEDS)).save(recipeOutput, JustJerboa.modLoc("smoking_" + getItemName(ModItems.DRIED_GRASS_SEEDS)));
    }
}
