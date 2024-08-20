package net.sixik.sdmgenerationstages.addons.crafttweaker.action;

import com.blamejared.crafttweaker.api.action.base.IRuntimeAction;
import net.minecraft.resources.ResourceLocation;
import net.sixik.sdmgenerationstages.stage.StageContainer;
import net.sixik.sdmgenerationstages.stage.type.FeatureStage;
import net.sixik.sdmgenerationstages.stage.type.StructureStage;

import java.util.ArrayList;
import java.util.List;

public class StructureStageAction implements IRuntimeAction {

    public final String stage;
    public final ResourceLocation[] structures;

    public StructureStageAction(String stage, ResourceLocation[] structures) {
        this.stage = stage;
        this.structures = structures;
    }

    @Override
    public void apply() {
        StageContainer.INSTANCE.put(StageContainer.INSTANCE.STRUCTURES, new StructureStage(stage, new ArrayList<>(List.of(structures))));
    }

    @Override
    public String describe() {
        return "Structures " + structures.toString() + " added to " + stage + ".stage";
    }

    @Override
    public String systemName() {
        return "Generation Stages";
    }
}
