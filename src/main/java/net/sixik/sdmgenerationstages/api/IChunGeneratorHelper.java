package net.sixik.sdmgenerationstages.api;

import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkAccess;

public interface IChunGeneratorHelper {


    void createStructureAfter(WorldGenLevel p_223087_, ChunkAccess p_223088_, StructureManager p_223089_);
}
