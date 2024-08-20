package net.sixik.sdmgenerationstages.mixin;


import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeResolver;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.PalettedContainer;
import net.sixik.sdmgenerationstages.stage.StageContainer;
import net.sixik.sdmgenerationstages.stage.type.BiomeStage;
import net.sixik.sdmgenerationstages.utils.ChunkHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LevelChunkSection.class)
public abstract class LevelChunkSectionMixin {

    @Inject(method = "fillBiomesFromNoise", at = @At("HEAD"))
    public void sdm$prefillBiomesFromNoise(BiomeResolver p_282075_, Climate.Sampler p_283084_, int p_282310_, int p_281510_, int p_283057_, CallbackInfo ci){

    }

    @Inject(method = "fillBiomesFromNoise", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/chunk/PalettedContainer;getAndSetUnchecked(IIILjava/lang/Object;)Ljava/lang/Object;"),
            locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    public void sdm$fillBiomesFromNoise(BiomeResolver p_282075_, Climate.Sampler p_283084_, int p_282310_, int p_281510_, int p_283057_, CallbackInfo ci, PalettedContainer<Holder<Biome>> palettedcontainer, int i, int j, int k, int l){
        Holder<Biome> biomeHolder = p_282075_.getNoiseBiome(p_282310_ + j, p_281510_ + k, p_283057_ + l, p_283084_);

        if(StageContainer.INSTANCE.BIOMES.isEmpty()) return;

        if(ChunkHelper.registryAccess == null) {
            ci.cancel();
            return;
        }

        RegistryAccess access = ChunkHelper.registryAccess;

        for (BiomeStage biome : StageContainer.INSTANCE.BIOMES) {
            if(biome.biomes.contains(access.registryOrThrow(Registries.BIOME).getKey(biomeHolder.get())) && (ChunkHelper.player == null || !GameStageHelper.hasStage(ChunkHelper.player, biome.stage))){
                ci.cancel();
                return;
            }
        }


    }
}
