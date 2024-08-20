package net.sixik.sdmgenerationstages.addons.crafttweaker.action;

import com.blamejared.crafttweaker.api.action.base.IRuntimeAction;
import net.minecraft.resources.ResourceLocation;
import net.sixik.sdmgenerationstages.stage.StageContainer;
import net.sixik.sdmgenerationstages.stage.type.BiomeStage;

import java.util.ArrayList;
import java.util.List;

public class BiomeStageAction implements IRuntimeAction {

    public final String stage;
    public final ResourceLocation[] biomes;

    public BiomeStageAction(String stage, ResourceLocation[] biomes){
        this.stage = stage;
        this.biomes = biomes;
    }

    @Override
    public void apply() {
        StageContainer.INSTANCE.put(StageContainer.INSTANCE.BIOMES, new BiomeStage(stage, new ArrayList<>(List.of(biomes))));
    }

    @Override
    public String describe() {
        return "Biomes " + biomes.toString() + " added to " + stage + ".stage";
    }

    @Override
    public String systemName() {
        return "Generation Stages";
    }
}
