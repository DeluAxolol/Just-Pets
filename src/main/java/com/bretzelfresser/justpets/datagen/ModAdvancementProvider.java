package com.bretzelfresser.justpets.datagen;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.BredAnimalsTrigger;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.bretzelfresser.justpets.JustPets;
import com.bretzelfresser.justpets.entity.JerboaVariantPredicate;
import com.bretzelfresser.justpets.registries.ModItems;
import com.bretzelfresser.justpets.registries.ModJerboaVariants;

public class ModAdvancementProvider extends ForgeAdvancementProvider {
    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(ModAdvancementProvider::generatePinkAdvancement));
    }

    private static void generatePinkAdvancement(HolderLookup.Provider provider, Consumer<Advancement> advancementConsumer, ExistingFileHelper existingFileHelper) {

        Advancement.Builder builder = Advancement.Builder.advancement();
        builder.parent(AdvancementSubProvider.createPlaceholder("minecraft:story/root"));

        builder.display(
                // The advancement icon. Can be an ItemStack or an ItemLike.
                ModItems.DRIED_GRASS_SEEDS.get(),
                // The advancement title and description. Don't forget to add translations for these!
                Component.translatable("advancements." + JustPets.MODID + ".pink_davancement.title"),
                Component.translatable("advancements." + JustPets.MODID + ".pink_davancement.description"),
                // The background texture. Use null if you don't want a background texture (for non-root advancements).
                null,
                // The frame type. Valid values are AdvancementType.TASK, CHALLENGE, or GOAL.
                FrameType.GOAL,
                // Whether to show the advancement toast or not.
                true,
                // Whether to announce the advancement into chat or not.
                true,
                // Whether the advancement should be hidden or not.
                false
        );

        builder.rewards(
                // Alternatively, use addExperience() to add to an existing builder.
                AdvancementRewards.Builder.experience(100)
        );
        builder.addCriterion("breed_pink_jerboa", BredAnimalsTrigger.TriggerInstance.bredAnimals(EntityPredicate.Builder.entity().subPredicate(new JerboaVariantPredicate(ModJerboaVariants.PINK))));

        builder.save(advancementConsumer, JustPets.modLoc("pink_breeding_advancement").toString());
    }
}
