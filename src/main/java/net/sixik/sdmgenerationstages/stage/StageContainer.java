package net.sixik.sdmgenerationstages.stage;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.sixik.sdmgenerationstages.stage.type.BiomeStage;
import net.sixik.sdmgenerationstages.stage.type.FeatureStage;
import net.sixik.sdmgenerationstages.stage.type.StageType;
import net.sixik.sdmgenerationstages.stage.type.StructureStage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StageContainer extends SimplePreparableReloadListener<Void> {

    public static StageContainer INSTANCE = new StageContainer();

    public List<StructureStage> STRUCTURES = new ArrayList<>();
    public List<FeatureStage> FEATURES = new ArrayList<>();
    public List<BiomeStage> BIOMES = new ArrayList<>();

    public <U extends StageType, T extends List<U>> void put(T list, U type) {
        for (U u : list) {
            if(u.add(type)){
                return;
            }
        }

        list.add(type);
    }


    @Override
    protected Void prepare(ResourceManager p_10796_, ProfilerFiller p_10797_) {
        return null;
    }

    @Override
    protected void apply(Void p_10793_, ResourceManager p_10794_, ProfilerFiller p_10795_) {
        STRUCTURES.clear();
        BIOMES.clear();
        FEATURES.clear();
    }
}
