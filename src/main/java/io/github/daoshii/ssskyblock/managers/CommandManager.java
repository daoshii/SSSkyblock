package io.github.daoshii.ssskyblock.managers;

import io.github.daoshii.ssskyblock.SSSkyblock;
import io.github.daoshii.ssskyblock.commands.Island;

public class CommandManager {
    private final SSSkyblock plugin;
    public CommandManager(SSSkyblock plugin) {
        this.plugin = plugin;
    }
    public void registerCommands() {
        plugin.getCommand("island").setExecutor(new Island(plugin));
    }
}
