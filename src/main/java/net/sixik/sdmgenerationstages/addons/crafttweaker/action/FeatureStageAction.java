package net.sixik.sdmgenerationstages.addons.crafttweaker.action;

import com.blamejared.crafttweaker.api.action.base.IRuntimeAction;
import net.minecraft.resources.ResourceLocation;
import net.sixik.sdmgenerationstages.stage.StageContainer;
import net.sixik.sdmgenerationstages.stage.type.FeatureStage;

import java.util.ArrayList;
import java.util.List;

public class FeatureStageAction implements IRuntimeAction {

    public final String stage;
    public final ResourceLocation[] features;

    public FeatureStageAction(String stage, ResourceLocation[] features) {
        this.stage = stage;
        this.features = features;
    }

    @Override
    public void apply() {
        StageContainer.INSTANCE.put(StageContainer.INSTANCE.FEATURES, new FeatureStage(stage, new ArrayList<>(List.of(features))));
    }

    @Override
    public String describe() {
        return "Features " + features.toString() + " added to " + stage + ".stage";
    }

    @Override
    public String systemName() {
        return "Generation Stages";
    }
}
