package com.github.yilkan0510.fairygrottofinder.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class CommandFindGrotto extends CommandBase {

    // Minecraft blocks representing Jasper in the Fairy Grotto
    private static final Block JASPER_BLOCK = Blocks.stained_glass; // Pink stained glass
    private static final Block JASPER_PANE = Blocks.stained_glass_pane; // Pink stained glass pane
    private static final int PINK_COLOR_METADATA = 6; // Metadata for pink color in stained glass

    @Override
    public String getCommandName() {
        return "findgrotto";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/findgrotto";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        World world = sender.getEntityWorld();
        BlockPos playerPos = sender.getPosition();

        int radius = 100; // Search radius
        BlockPos grottoLocation = null;
        int jasperCount = 0;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos checkPos = playerPos.add(x, y, z);
                    IBlockState state = world.getBlockState(checkPos);

                    if (isJasperBlock(state)) {
                        if (grottoLocation == null) {
                            grottoLocation = checkPos;
                        }

                        // Count Jasper blocks (stained glass or panes)
                        jasperCount++;
                    }
                }
            }
        }

        if (grottoLocation != null) {
            sender.addChatMessage(new ChatComponentText("Fairy Grotto found at: " + grottoLocation));
            sender.addChatMessage(new ChatComponentText("Jasper Blocks/Panes found: " + jasperCount));
        } else {
            sender.addChatMessage(new ChatComponentText("No Fairy Grotto found within range."));
        }
    }

    private boolean isJasperBlock(IBlockState state) {
        Block block = state.getBlock();
        int metadata = block.getMetaFromState(state);
        return (block == JASPER_BLOCK || block == JASPER_PANE) && metadata == PINK_COLOR_METADATA;
    }
}
