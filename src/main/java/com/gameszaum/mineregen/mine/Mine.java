package com.gameszaum.mineregen.mine;

import com.sk89q.worldedit.regions.CuboidRegion;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.block.Block;

@Data
public class Mine {

    private String name;
    private int blocks;
    private Location spawnLocation;
    private CuboidRegion region;
    private Block block;

}
