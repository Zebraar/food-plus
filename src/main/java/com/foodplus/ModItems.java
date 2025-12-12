package com.foodplus;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;

import java.util.function.Function;

public class ModItems {
    public static final Item LOAF = register(
            "loaf",
            Item::new,
            new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(6)              // восстанавливает 6 голода/3 окорочка
                            .saturationModifier(0.7f)  // уровень насыщения
                            .build()
            )
    );
    public static final Item APPLE_PIE = register(
            "apple_pie",
            Item::new,
            new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(8)              // восстанавливает 8 голода/4 окорочка
                            .saturationModifier(0.3f)  // высокий уровень насыщения
                            .build()
            )
    );
    public static final Item CARROT_SOUP = register(
            "carrot_soup",
            Item::new,
            new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(6)              // восстанавливает 8 голода/4 окорочка
                            .saturationModifier(0.7f)  // высокий уровень насыщения
                            .build()
            )
    );
    public static final Item SUSHI = register(
            "sushi",
            Item::new,
            new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(6)              // восстанавливает 6 голода/3 окорочка
                            .saturationModifier(0.35f)  // уровень насыщения
                            .build()
            )
    );

    // Метод для инициализации
    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(itemGroup -> {
                itemGroup.accept(LOAF);
                itemGroup.accept(APPLE_PIE);
                itemGroup.accept(CARROT_SOUP);
                itemGroup.accept(SUSHI);
        });
    }
    // Единственный метод register
    public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(FoodPlus.MOD_ID, name)
        );

        Item item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }
}
