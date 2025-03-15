package com.bretzelfresser.registries;

import com.bretzelfresser.JustJerboa;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModTags {


    public static final class Items{


        public static final TagKey<Item> JERBOA_FOOD = tag("jerboa_food");


        public static TagKey<Item> tag(String name){
            return TagKey.create(Registries.ITEM, JustJerboa.modLoc(name));
        }
    }
}
