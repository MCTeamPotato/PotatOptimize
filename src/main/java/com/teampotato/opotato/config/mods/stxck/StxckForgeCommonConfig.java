package com.teampotato.opotato.config.mods.stxck;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class StxckForgeCommonConfig implements StxckCommonConfig {
    private final ForgeConfigSpec.DoubleValue maxMergeDistanceHorizontal;
    private final ForgeConfigSpec.DoubleValue maxMergeDistanceVertical;
    private final ForgeConfigSpec.IntValue maxSize;
    private final ForgeConfigSpec.BooleanValue enableForUnstackableItem;
    private final ForgeConfigSpec.ConfigValue<List<? extends String>> itemBlackList;


    public StxckForgeCommonConfig(ForgeConfigSpec.@NotNull Builder builder) {
        builder.push("Item merge settings");

        maxMergeDistanceHorizontal = builder
                .comment("The maximum horizontal block distance over which dropped items attempt to merge with each other.")
                .comment("Default: 1.25, Minecraft default: 0.5")
                .defineInRange("maxMergeDistanceHorizontal", 1.25d, 0.5d, 10d);

        maxMergeDistanceVertical = builder
                .comment("The maximum vertical block distance over which dropped items attempt to merge with each other.")
                .comment("Default: 0.0, Minecraft default: 0.0")
                .defineInRange("maxMergeDistanceVertical", 0d, 0d, 10d);

        maxSize = builder
                .comment("The maximum number of extra items that an item entity can hold.")
                .comment(String.format("Default: %d", Integer.MAX_VALUE))
                .defineInRange("maxSize", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);

        enableForUnstackableItem = builder
                .comment("Enable for merging non-stackable item.")
                .comment("Should be used with caution while playing with other mods.")
                .comment("Default: false")
                .define("enableForUnstackableItem", false);

        itemBlackList = builder
                .comment("The list of items that should not exceed their original max stack size.")
                .comment("You can achieve the same feature by using the item tag \"#staaaaaaaaaaaack:blacklist\" as well.")
                .comment("e.g., [\"minecraft:diamond_block\", \"minecraft:coal\"]")
                .defineList("itemBlackList", ObjectArrayList::new, o -> o instanceof String);

        builder.pop();
    }
    @Override
    public double getMaxMergeDistanceHorizontal() {
        return maxMergeDistanceHorizontal.get();
    }

    @Override
    public double getMaxMergeDistanceVertical() {
        return maxMergeDistanceVertical.get();
    }

    @Override
    public int getMaxSize() {
        return maxSize.get();
    }

    @Override
    public boolean isEnableForUnstackableItem() {
        return enableForUnstackableItem.get();
    }

    @Override
    public List<? extends String> getItemBlackList() {
        return itemBlackList.get();
    }
}
