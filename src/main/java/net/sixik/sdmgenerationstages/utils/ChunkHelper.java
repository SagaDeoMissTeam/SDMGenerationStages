package net.sixik.sdmgenerationstages.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import java.util.List;

public class ChunkHelper {

    public static Player player = null;
    public static RegistryAccess registryAccess = null;

    public static boolean isLevelHavePlayers(LevelAccessor level) {
        return !level.players().isEmpty();
    }

    public static Player getNearestPlayer(LevelAccessor level, BlockPos pos){
        List<? extends Player> players = level.players();

        Player nearestPlayer = null;
        double minDistance = Double.MAX_VALUE;

        for (Player player : players) {
            double distance = player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
            if (distance < minDistance) {
                minDistance = distance;
                nearestPlayer = player;
            }
        }

        return nearestPlayer;
    }
}
