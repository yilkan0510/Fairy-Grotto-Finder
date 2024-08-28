package com.github.yilkan0510.fairygrottofinder.init;

import com.github.yilkan0510.fairygrottofinder.config.GrottoRadiusConfig;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class CommandFindGrotto extends CommandBase {

    private static final Block JASPER_BLOCK = Blocks.stained_glass;
    private static final Block JASPER_PANE = Blocks.stained_glass_pane;
    private static final int PINK_COLOR_METADATA = 6;

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
        findGrotto(sender, GrottoRadiusConfig.radius);
    }

    public void findGrotto(ICommandSender sender, int radius) {
        World world = sender.getEntityWorld();
        BlockPos playerPos = sender.getPosition();

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
                        jasperCount++;
                    }
                }
            }
        }

        if (grottoLocation != null) {
            String message = EnumChatFormatting.GREEN + "Fairy Grotto found at: " +
                    EnumChatFormatting.YELLOW + "[" + grottoLocation.getX() + ", " + grottoLocation.getY() + ", " + grottoLocation.getZ() + "]\n" +
                    EnumChatFormatting.GREEN + "Jasper Blocks/Panes found: " +
                    EnumChatFormatting.AQUA + jasperCount;
            sender.addChatMessage(new ChatComponentText(message));
        } else {
            String message = EnumChatFormatting.RED + "No Fairy Grotto found within range.";
            sender.addChatMessage(new ChatComponentText(message));
        }
    }

    private boolean isJasperBlock(IBlockState state) {
        Block block = state.getBlock();
        int metadata = block.getMetaFromState(state);
        return (block == JASPER_BLOCK || block == JASPER_PANE) && metadata == PINK_COLOR_METADATA;
    }
}
