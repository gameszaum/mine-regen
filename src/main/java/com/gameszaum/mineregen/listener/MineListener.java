package com.gameszaum.mineregen.listener;

import com.gameszaum.core.Services;
import com.gameszaum.core.plugin.GamesCore;
import com.gameszaum.mineregen.MineRegen;
import com.gameszaum.mineregen.mine.Mine;
import com.gameszaum.mineregen.services.MineService;
import com.sk89q.worldedit.*;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.blocks.BlockType;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class MineListener implements Listener {

    private MineService mineService;
    private WorldEditPlugin worldEdit;

    public MineListener() {
        mineService = Services.get(MineService.class);
        worldEdit = MineRegen.getWorldEdit();
    }

    @EventHandler
    void onMineEvent(BlockBreakEvent event) throws IncompleteRegionException {
        Player player = event.getPlayer();
        LocalSession session = worldEdit.getSession(player);
        Region region = session.getSelection(session.getSelectionWorld());

        if (region != null) {
            Mine mine = mineService.getMineFromRegion(region);

            if (mine != null) {
                int area = region.getArea();
                int percentage = (area * (50 / mine.getBlocks()));

                if (percentage < 50) {
                    Bukkit.getScheduler().scheduleAsyncDelayedTask(GamesCore.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            CuboidRegion cuboidRegion = new CuboidRegion(region.getMinimumPoint(), region.getMaximumPoint());
                        }
                    });
                }
            } else {
                player.sendMessage("mine null.");
            }
        }
    }

}
