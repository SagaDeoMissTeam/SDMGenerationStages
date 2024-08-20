package net.sixik.sdmgenerationstages.stage.type;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class StructureStage extends StageType{

    public List<ResourceLocation> structures;

    public StructureStage(String stage, List<ResourceLocation> structures) {
        super(stage);
        this.structures = structures;
    }

    @Override
    public <T, U> boolean execute(T object, U object1) {
        return false;
    }

    @Override
    public boolean add(StageType stageType) {
        if(stageType instanceof StructureStage structureStage) {
            if(structureStage.stage.equals(stage)) {
                this.structures.addAll(structureStage.structures);
                return true;
            }
        }

        return false;
    }
}
