package net.sixik.sdmgenerationstages.addons.crafttweaker;


import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import net.minecraft.resources.ResourceLocation;
import net.sixik.sdmgenerationstages.addons.crafttweaker.action.BiomeStageAction;
import net.sixik.sdmgenerationstages.addons.crafttweaker.action.FeatureStageAction;
import net.sixik.sdmgenerationstages.addons.crafttweaker.action.StructureStageAction;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.generationstages.GenerationStages")
public class CTMethods {

    @ZenCodeType.Method
    public static void addStructureStage(String stage, ResourceLocation structure){
        CraftTweakerAPI.apply(new StructureStageAction(stage, new ResourceLocation[]{structure}));
    }

    @ZenCodeType.Method
    public static void addStructureStage(String stage, ResourceLocation[] structure){
        CraftTweakerAPI.apply(new StructureStageAction(stage, structure));
    }

    @ZenCodeType.Method
    public static void addFeatureStages(String stage, ResourceLocation feature){
        CraftTweakerAPI.apply(new FeatureStageAction(stage, new ResourceLocation[]{feature}));
    }

    @ZenCodeType.Method
    public static void addFeatureStages(String stage, ResourceLocation[] feature){
        CraftTweakerAPI.apply(new FeatureStageAction(stage, feature));
    }

    @ZenCodeType.Method
    public static void addBiomeStages(String stage, ResourceLocation biome){
        CraftTweakerAPI.apply(new BiomeStageAction(stage, new ResourceLocation[]{biome}));
    }

    @ZenCodeType.Method
    public static void addBiomeStages(String stage, ResourceLocation[] biome){
        CraftTweakerAPI.apply(new BiomeStageAction(stage, biome));
    }
}
