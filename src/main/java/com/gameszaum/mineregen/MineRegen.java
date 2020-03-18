package com.gameszaum.mineregen;

import com.gameszaum.core.Services;
import com.gameszaum.core.plugin.GamesPlugin;
import com.gameszaum.mineregen.command.MineCommands;
import com.gameszaum.mineregen.listener.MineListener;
import com.gameszaum.mineregen.services.MineService;
import com.gameszaum.mineregen.services.MineServiceImpl;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;

public final class MineRegen extends GamesPlugin {

    @Override
    public void load() {
        Services.create(this);
        Services.add(MineService.class, new MineServiceImpl());
    }

    @Override
    public void enable() {
        if (getWorldEdit() != null && getWorldGuard() != null) {
            MineCommands.commands();
            registerListeners(new MineListener());

            System.out.println("[MineRegen] System enabled sucessfully.");
        } else {
            Bukkit.getPluginManager().disablePlugin(this);
            System.out.println("[MineRegen] WorldEdit or WorldGuard didn't found it, plugin disabling...");
        }
    }

    @Override
    public void disable() {
        System.out.println("[MineRegen] System disabled sucessfully.");
    }

    public static WorldEditPlugin getWorldEdit() {
        return (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
    }

    public static WorldGuardPlugin getWorldGuard() {
        return (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
    }

}
