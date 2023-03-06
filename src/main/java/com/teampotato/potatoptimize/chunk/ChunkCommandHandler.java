package com.teampotato.potatoptimize.chunk;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.chunk.LevelChunk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChunkCommandHandler {
    private static List<ChunkStatus> scores = new ArrayList<ChunkStatus>();

    public static void AnalyseChunk(CommandSourceStack commandSource) {
        List<LevelChunk> currentWorldChunkList = ChunkData.loadedChunks;
        scores.clear();
        for (LevelChunk chunk : currentWorldChunkList) {
            scores.add(new ChunkStatus(chunk));
        }

        for (ChunkStatus chunkStatus : scores) {
            chunkStatus.genScore();
        }
        Collections.sort(scores);

        commandSource.sendSuccess(new TextComponent("Chunk Analyse Report \n"), false);
        for (int i = 0; i < 10; i++) {
            if (i >= scores.size()) {
                break;
            }
            ChunkStatus chunkStatus = scores.get(i);
            commandSource.sendSuccess(chunkStatus.genText(), false);
        }
    }
}
