package com.gameszaum.mineregen.services;

import com.gameszaum.mineregen.mine.Mine;
import com.sk89q.worldedit.regions.Region;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class MineServiceImpl implements MineService {

    private Set<Mine> mineSet;

    public MineServiceImpl(){
        mineSet = new HashSet<>();
    }

    @Override
    public Set<Mine> all() {
        return mineSet;
    }

    @Override
    public Mine getMineFromRegion(Region region) {
        return mineSet.stream().filter(mine -> mine.getRegion() == region).findFirst().orElse(null);
    }

    @Override
    public void create(Mine mine) {
        mineSet.add(mine);
    }

    @Override
    public void remove(String s) {
        mineSet.remove(get(s));
    }

    @Override
    public Mine get(String s) {
        return search(s).findFirst().orElse(null);
    }

    @Override
    public Stream<Mine> search(String s) {
        return mineSet.stream().filter(mine -> mine.getName().equalsIgnoreCase(s));
    }

}
