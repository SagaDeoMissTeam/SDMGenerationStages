package net.sixik.sdmgenerationstages.addons.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.bracket.BracketHandlers;
import com.blamejared.crafttweaker.api.bracket.ResourceLocationBracketHandler;
import com.blamejared.crafttweaker.api.command.CommandUtilities;
import com.blamejared.crafttweaker.api.plugin.CraftTweakerPlugin;
import com.blamejared.crafttweaker.api.plugin.ICommandRegistrationHandler;
import com.blamejared.crafttweaker.api.plugin.ICraftTweakerPlugin;
import com.blamejared.crafttweaker.natives.resource.ExpandResourceLocation;
import com.mojang.brigadier.Command;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.sixik.sdmgenerationstages.SDMGenerationStages;

import java.util.List;
import java.util.Map;


@CraftTweakerPlugin(SDMGenerationStages.MODID + ":sdm")
@SuppressWarnings("unused")
public class GenerationStagesPlugin implements ICraftTweakerPlugin {


    @Override
    public void registerCommands(ICommandRegistrationHandler handler) {
        sdm$registerCommands(handler);
    }


    public static void sdm$registerCommands(final ICommandRegistrationHandler handler) {
        handler.registerDump("gen_stages_biome", Component.empty(), builder -> {
            builder.executes(context -> {
                parseBiomes(context.getSource());
                CommandUtilities.send(CommandUtilities.openingLogFile(Component.translatable("crafttweaker.command.list.check.log", new Object[]{CommandUtilities.makeNoticeable(Component.translatable("Dumped keys")), CommandUtilities.getFormattedLogFile()}).withStyle(ChatFormatting.GREEN)), context.getSource().getPlayer());
                return Command.SINGLE_SUCCESS;
            });
        });
        handler.registerDump("gen_stages_features", Component.empty(), builder -> {
            builder.executes(context -> {
                parseFeatures(context.getSource());
                CommandUtilities.send(CommandUtilities.openingLogFile(Component.translatable("crafttweaker.command.list.check.log", new Object[]{CommandUtilities.makeNoticeable(Component.translatable("Dumped keys")), CommandUtilities.getFormattedLogFile()}).withStyle(ChatFormatting.GREEN)), context.getSource().getPlayer());
                return Command.SINGLE_SUCCESS;
            });
        });
        handler.registerDump("gen_stages_structures", Component.empty(), builder -> {
            builder.executes(context -> {
                parseStructures(context.getSource());
                CommandUtilities.send(CommandUtilities.openingLogFile(Component.translatable("crafttweaker.command.list.check.log", new Object[]{CommandUtilities.makeNoticeable(Component.translatable("Dumped keys")), CommandUtilities.getFormattedLogFile()}).withStyle(ChatFormatting.GREEN)), context.getSource().getPlayer());
                return Command.SINGLE_SUCCESS;
            });
        });
    }



    private static void parseBiomes(CommandSourceStack source){
        var d1 = source.getLevel().registryAccess().registryOrThrow(Registries.BIOME);
        for (Map.Entry<ResourceKey<Biome>, Biome> entry : d1.entrySet()) {
            CraftTweakerAPI.getLogger("Generation Stages Biomes").info(ExpandResourceLocation.getCommandString(d1.getKey(entry.getValue())));
        }
    }

    private static void parseFeatures(CommandSourceStack source){
        var d1 = source.getLevel().registryAccess().registryOrThrow(Registries.FEATURE);
        for (Map.Entry<ResourceKey<Feature<?>>, Feature<?>> entry : d1.entrySet()) {
            CraftTweakerAPI.getLogger("Generation Stages Features").info(ExpandResourceLocation.getCommandString(d1.getKey(entry.getValue())));
        }
    }

    private static void parseStructures(CommandSourceStack source){
        var d1 = source.getLevel().registryAccess().registryOrThrow(Registries.STRUCTURE);
        for (Map.Entry<ResourceKey<Structure>, Structure> entry : d1.entrySet()) {
            CraftTweakerAPI.getLogger("Generation Stages Structures").info(ExpandResourceLocation.getCommandString(d1.getKey(entry.getValue())));
        }
    }
}
