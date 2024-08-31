package com.github.yilkan0510.fairygrottofinder.modules;

import com.github.yilkan0510.fairygrottofinder.utils.RenderUtils;
import com.github.yilkan0510.fairygrottofinder.utils.Structure;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldScanner {

    @SubscribeEvent
    public void onChunkLoad(ChunkEvent.Load event) {
        Chunk chunk = event.getChunk();
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 170; y++) {
                for (int z = 0; z < 16; z++) {
                    if (Structure.isFairyGrotto(chunk, x, y, z)) {
                        BlockPos pos = new BlockPos(chunk.xPosition * 16 + x, y, chunk.zPosition * 16 + z);
                        RenderUtils.renderFairyGrotto(pos);
                    }
                }
            }
        }
    }
}
