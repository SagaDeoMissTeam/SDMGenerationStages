package net.sixik.sdmgenerationstages.stage.type;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class BiomeStage extends StageType{

    public List<ResourceLocation> biomes = new ArrayList<>();
    public BiomeStage(String stage, List<ResourceLocation> biomes) {
        super(stage);
        this.biomes = biomes;
    }

    @Override
    public <T, U> boolean execute(T object, U object1) {
        return false;
    }

    @Override
    public boolean add(StageType stageType) {
        if(stageType instanceof BiomeStage structureStage) {
            if(structureStage.stage.equals(stage)) {
                this.biomes.addAll(structureStage.biomes);
                return true;
            }
        }
        return false;
    }
}
