package com.gameszaum.mineregen.command;

import com.gameszaum.core.Services;
import com.gameszaum.core.command.Command;
import com.gameszaum.core.command.builder.CommandBase;
import com.gameszaum.core.command.helper.CommandHelper;
import com.gameszaum.core.menu.MenuBuilder;
import com.gameszaum.core.menu.helper.Menu;
import com.gameszaum.mineregen.mine.Mine;
import com.gameszaum.mineregen.services.MineService;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MineCommands {

    private static MineService mineService;

    static {
        mineService = Services.get(MineService.class);
    }

    public static void commands() {
        Command.create(new CommandBase() {
            @Override
            public void handler(CommandSender commandSender, CommandHelper commandHelper, String... args) throws Exception {
                Player player = commandHelper.getPlayer(commandSender);

                if (args.length < 1) {
                    player.sendMessage("                 §eMINEREGEN - SYSTEM");
                    player.sendMessage("");
                    player.sendMessage("§cUse /mine set <name>§c.");
                    player.sendMessage("§cUse /mine delete <name>§c.");
                    player.sendMessage("§cUse /mine list§c.");
                    player.sendMessage("");
                    return;
                }

                if (args[0].equalsIgnoreCase("list")) {
                    Menu menu = MenuBuilder.buildMenu(new Menu("Lista de minas", 6));

                    menu.setGlobalAction((p, inventory, itemStack, i, inventoryAction) -> {
                        if (inventoryAction != null) {
                            if (itemStack.getType() != Material.AIR && itemStack.getTypeId() != 0) {

                            }
                        }
                    });

                    menu.showMenu(player);
                }
            }
        }).onlyPlayer().onlyPermission("mine.admin").runAsync().setCommand("mina", "mine");
    }

    public void createMenu(Player player) {
        Menu menu = MenuBuilder.buildMenu(new Menu("Lista de minas", 6));

        mineService.all().forEach(mine -> menu.setItem(1,
                menu.createItem(mine.getBlock().getType(), 1, "§a" + mine.getName(), "§7Clique aqui para ir até a mina.",
                        mine.getBlock().getType().getMaxDurability())));

        menu.setGlobalAction((p, inventory, itemStack, i, inventoryAction) -> {
            if (inventoryAction != null) {
                if (itemStack.getType() != Material.AIR && itemStack.getTypeId() != 0) {
                    Mine mine = mineService.get(itemStack.getItemMeta().getDisplayName().replace("§a", ""));

                    player.closeInventory();
                    player.teleport(mine.getSpawnLocation());
                    player.sendMessage("§e[MineRegen] §fVocê foi teleportado para §e" + mine.getName() + "§f.");
                }
            }
        });

        menu.showMenu(player);
    }

}
