package biomesoplenty.common.world;

import java.util.List;
import java.util.Random;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;

import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.layer.GenLayerBOP;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class WorldChunkManagerBOP extends WorldChunkManager {

    public WorldChunkManagerBOP(World world) {
        super();

        GenLayer[] agenlayer = GenLayerBOP.initializeAllBiomeGenerators(
            world.getSeed(),
            world.getWorldInfo()
                .getTerrainType());
        agenlayer = getModdedBiomeGenerators(
            world.getWorldInfo()
                .getTerrainType(),
            world.getSeed(),
            agenlayer);
        ObfuscationReflectionHelper
            .setPrivateValue(WorldChunkManager.class, this, agenlayer[0], "genBiomes", "field_76944_d");
        ObfuscationReflectionHelper
            .setPrivateValue(WorldChunkManager.class, this, agenlayer[1], "biomeIndexLayer", "field_76945_e");
    }

    @Override
    public ChunkPosition findBiomePosition(int x, int z, int radius, List biomesToSpawnIn, Random random) {
        int spawnSearchRadius = BOPConfigurationMisc.spawnSearchRadius;

        return super.findBiomePosition(x, z, spawnSearchRadius, biomesToSpawnIn, random);
    }
}
