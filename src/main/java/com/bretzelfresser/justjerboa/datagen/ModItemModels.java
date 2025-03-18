package com.bretzelfresser.justjerboa.datagen;

import com.bretzelfresser.justjerboa.JustJerboa;
import com.bretzelfresser.justjerboa.registries.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModels extends ItemModelProvider {

    public final ModelFile generated = getExistingFile(mcLoc("item/generated"));
    public final ModelFile handheld = getExistingFile(mcLoc("item/handheld"));
    public final ModelFile spawnEgg = getExistingFile(mcLoc("item/template_spawn_egg"));

    public ModItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, JustJerboa.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simple(ModItems.DRIED_GRASS_SEEDS.get());
        simpleSpawnEgg(ModItems.JERBOA_SPAWN_EGG.get());
    }

    protected void simpleSpawnEgg(ItemLike egg){
        getBuilder(name(egg).toString()).parent(spawnEgg);
    }

    private void withTexture(ItemLike item, String textureName) {
        ResourceLocation name = name(item);
        getBuilder(name.toString()).parent(generated).texture("layer0", "item/" + textureName);
    }

    private void handheld(Item... items) {
        for (Item item : items) {
            ResourceLocation name = name(item);
            getBuilder(name.toString()).parent(handheld).texture("layer0", "item/" + name.getPath());

        }
    }

    private void simple(Item... items) {
        for (Item item : items) {
            ResourceLocation name = name(item);
            getBuilder(name.toString()).parent(generated).texture("layer0", "item/" + name.getPath());
        }
    }

    private void simple(ItemLike... items) {
        for (ItemLike itemProvider : items) {
            simple(itemProvider.asItem());
        }
    }

    protected ResourceLocation name(ItemLike item){
        return BuiltInRegistries.ITEM.getKey(item.asItem());
    }
}
