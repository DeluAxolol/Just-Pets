package com.bretzelfresser.justpets.entity;

import com.bretzelfresser.justpets.jerboavariants.JerboaVariant;
import com.bretzelfresser.justpets.registries.ModEntityPredicateTypes;
import com.bretzelfresser.justpets.registries.ModJerboaVariants;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class JerboaVariantPredicate implements EntitySubPredicate {

    protected final ResourceLocation variant;

    public JerboaVariantPredicate(ResourceKey<JerboaVariant> variant) {
        this(variant.location());
    }

    public JerboaVariantPredicate(ResourceLocation variant) {
        this.variant = variant;
    }

    private ResourceLocation getVariant() {
        return variant;
    }

    @Override
    public boolean matches(Entity entity, ServerLevel serverLevel, @Nullable Vec3 vec3) {
        if (entity instanceof Jerboa jerboa) {
            return serverLevel.registryAccess().registryOrThrow(ModJerboaVariants.JERBOA_VARIANT_REGISTRY_KEY).getKey(jerboa.getVariant()).equals(this.variant);
        }
        return false;
    }

    @Override
    public JsonObject serializeCustomData() {
        JsonObject obj = new JsonObject();
        obj.addProperty("variant", this.variant.toString());
        return obj;
    }

    @Override
    public Type type() {
        return ModEntityPredicateTypes.JERBOA_VARIANT_PREDICATE_TYPE;
    }

    public static JerboaVariantPredicate fromJson(JsonObject obj){
        return new JerboaVariantPredicate(ResourceLocation.parse(obj.get("variant").getAsString()));
    }
}
