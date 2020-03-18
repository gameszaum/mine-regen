package com.gameszaum.mineregen.services;

import com.gameszaum.core.services.Model;
import com.gameszaum.mineregen.mine.Mine;
import com.sk89q.worldedit.regions.Region;

import java.util.Set;

public interface MineService extends Model<String, Mine> {

    Set<Mine> all();

    Mine getMineFromRegion(Region region);

}
