package net.sixik.sdmgenerationstages.mixin;


import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.sixik.sdmgenerationstages.stage.StageContainer;
import net.sixik.sdmgenerationstages.stage.type.FeatureStage;
import net.sixik.sdmgenerationstages.utils.ChunkHelper;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

@Mixin(PlacedFeature.class)
public abstract class PlacedFeatureMixin {

    @Shadow public abstract Holder<ConfiguredFeature<?, ?>> feature();

    @Inject(method = "placeWithContext", at = @At("HEAD"), cancellable = true)
    public void sdm$placeWithContext(PlacementContext placementContext, RandomSource randomSource, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir){
        WorldGenLevel level = placementContext.getLevel();
        ResourceLocation feature = level.registryAccess().registryOrThrow(Registries.FEATURE).getKey(feature().get().feature());

        for (FeatureStage featureStage : StageContainer.INSTANCE.FEATURES) {
            if(featureStage.features.contains(feature)) {
                if(!ChunkHelper.isLevelHavePlayers(level)) {
                    cir.setReturnValue(false);
                    return;
                }

                Player player = ChunkHelper.getNearestPlayer(level, blockPos);
                if(player == null || !GameStageHelper.hasStage(player, featureStage.stage)) {
                    cir.setReturnValue(false);
                    return;
                }
            }
        }
    }
}
