package net.sixik.sdmgenerationstages.stage.type;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class FeatureStage extends StageType{

    public List<ResourceLocation> features = new ArrayList<>();

    public FeatureStage(String stage, List<ResourceLocation> features) {
        super(stage);
        this.features = features;
    }

    @Override
    public <T, U> boolean execute(T object, U object1) {
        return false;
    }

    @Override
    public boolean add(StageType stageType) {
        if(stageType instanceof FeatureStage structureStage) {
            if(structureStage.stage.equals(stage)) {
                this.features.addAll(structureStage.features);
                return true;
            }
        }
        return false;
    }
}
