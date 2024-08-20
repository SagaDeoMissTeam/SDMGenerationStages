package net.sixik.sdmgenerationstages.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.BiomeResolver;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.sixik.sdmgenerationstages.utils.ChunkHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ChunkAccess.class)
public class ChunkAccessMixin {

    public ChunkAccess thisChunkAccess = (ChunkAccess)(Object)this;

    @Inject(
            method = "fillBiomesFromNoise",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/chunk/ChunkAccess;getHeightAccessorForGeneration()Lnet/minecraft/world/level/LevelHeightAccessor;")
            ,locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true
    )
    public void sdm$fillBiomesFromNoise(BiomeResolver p_187638_, Climate.Sampler p_187639_, CallbackInfo ci, ChunkPos chunkpos, int i, int j){
        LevelAccessor levelAccessor = thisChunkAccess.getWorldForge();
        if(levelAccessor == null || !ChunkHelper.isLevelHavePlayers(levelAccessor)) {
            return;
        }

        ChunkHelper.registryAccess = levelAccessor.registryAccess();
        ChunkHelper.player = ChunkHelper.getNearestPlayer(levelAccessor, chunkpos.getWorldPosition());

    }
}
