package io.github.daoshii.ssskyblock.commands;

import io.github.daoshii.ssskyblock.SSSkyblock;
import io.github.daoshii.ssskyblock.managers.SchematicManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;


public class Island implements CommandExecutor {
    private final SSSkyblock plugin;
    public Island(SSSkyblock plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                File pirateIsland = new File(plugin.getDataFolder(), "pirateisland.schem");
                plugin.getLogger().info(pirateIsland + " has been created.");

                SchematicManager islandCreator = new SchematicManager(plugin);
                islandCreator.paste(player.getLocation(), pirateIsland);

                return true;
            }
        }
        return true;
    }
}
