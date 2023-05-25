package com.teampotato.opotato.util.schwarz.chunk;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ChunkStatus implements Comparable<ChunkStatus> {
    private int score = 0;
    private int[] coords = new int[2];

    private Chunk chunk;
    private String world;

    private final HashMap<TileEntityType<?>, Integer> blockEntityScore = new HashMap<TileEntityType<?>, Integer>(){{
            put(TileEntityType.CHEST, 1);
            put(TileEntityType.TRAPPED_CHEST, 2);
            put(TileEntityType.BLAST_FURNACE, 3);
            put(TileEntityType.FURNACE, 3);
            put(TileEntityType.SIGN, 1);
            put(TileEntityType.BEACON, 35);
            put(TileEntityType.HOPPER, 6);
            put(TileEntityType.PISTON, 5);
            put(TileEntityType.DISPENSER, 10);
            put(TileEntityType.MOB_SPAWNER, 60);
    }};

    private final HashMap<EntityType<?>, Integer> livingEntityScore = new HashMap<EntityType<?>, Integer>() {{
            put(EntityType.PLAYER, 15);
            put(EntityType.ZOMBIE, 2);
            put(EntityType.SPIDER, 2);
            put(EntityType.SKELETON, 2);
            put(EntityType.CREEPER, 3);
            put(EntityType.COW, 1);
            put(EntityType.PIG, 1);
            put(EntityType.SHEEP, 1);
            put(EntityType.EYE_OF_ENDER, 15);
            put(EntityType.EGG, 3);
            put(EntityType.FISHING_BOBBER, 2);
            put(EntityType.SNOWBALL, 2);
            put(EntityType.ARROW, 1);
            put(EntityType.PAINTING, 1);
            put(EntityType.ITEM_FRAME, 3);
            put(EntityType.ARMOR_STAND, 4);
            put(EntityType.ITEM, 2);
            put(EntityType.EXPERIENCE_ORB, 3);
            put(EntityType.FIREWORK_ROCKET, 8);
            put(EntityType.ENDER_DRAGON, 85);
            put(EntityType.END_CRYSTAL, 10);
            put(EntityType.WITHER, 55);
            put(EntityType.WITHER_SKULL, 20);
            put(EntityType.TNT, 7);
            put(EntityType.TNT_MINECART, 15);
            put(EntityType.HOPPER_MINECART, 20);
            put(EntityType.SPAWNER_MINECART, 80);
            put(EntityType.ENDERMITE, 5);
    }};

    public ChunkStatus(Chunk chunk) {
        this.chunk = chunk;
        RegistryKey<World> dim = chunk.getLevel().dimension();
        if (dim.equals(World.OVERWORLD)) {
            this.world = "overworld";
        } else if (dim.equals(World.NETHER)) {
            this.world = "nether";
        } else if (dim.equals(World.END)) {
            this.world = "end";
        }
        coords[0] = (chunk.getPos().getMinBlockX() + chunk.getPos().getMaxBlockX()) / 2;
        coords[1] = (chunk.getPos().getMinBlockZ() + chunk.getPos().getMaxBlockZ()) / 2;
    }

    public void genScore() {
        for (TileEntity blockEntity : chunk.getBlockEntities().values()) {
            if (blockEntityScore.get(blockEntity.getType()) != null) score += blockEntityScore.get(blockEntity.getType());
        }

        ChunkPos pos = chunk.getPos();
        for(Entity entity : chunk.getLevel().getEntities(null, new AxisAlignedBB(pos.getMinBlockX(), 0, pos.getMinBlockZ(), pos.getMaxBlockX(), 256, pos.getMaxBlockZ()))) {
            if (livingEntityScore.get(entity.getType()) != null) score += livingEntityScore.get(entity.getType());
        }
    }

    public StringTextComponent genText() {
        return new StringTextComponent("Chunk Center Pos: " + coords[0] + " " + coords[1] + "\nScore: " + score + "\nWorld: " + world);
    }

    public int compareTo(@NotNull ChunkStatus from) {
        int CompareQuantity = from.score;
        return CompareQuantity - score;
    }
}
