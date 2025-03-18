package com.bretzelfresser.justjerboa.registries;

import com.bretzelfresser.justjerboa.JustJerboa;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTags {


    public static final class Items {


        public static final TagKey<Item> JERBOA_FOOD = tag("jerboa_food");


        public static TagKey<Item> tag(String name) {
            return TagKey.create(ForgeRegistries.Keys.ITEMS, JustJerboa.modLoc(name));
        }
    }
}
