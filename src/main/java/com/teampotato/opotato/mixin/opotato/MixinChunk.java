package com.teampotato.opotato.mixin.opotato;

import com.teampotato.opotato.util.MixinUtil;
import net.minecraft.entity.Entity;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

@Mixin(Chunk.class)
public class MixinChunk {
    @Shadow @Final private World level;
    @Shadow @Final private ClassInheritanceMultiMap<Entity>[] entitySections;

    /**
     * @author Kasuliax
     * @reason see the performance impact of getEntitiesOfClass
     */
    @Overwrite
    public <T extends Entity> void getEntitiesOfClass(Class<? extends T> cs, AxisAlignedBB aabb, List<T> list, @Nullable Predicate<? super T> predicate) {
        MixinUtil.getEntitiesOfClass(cs, aabb, list, predicate, level, entitySections);
    }
}
