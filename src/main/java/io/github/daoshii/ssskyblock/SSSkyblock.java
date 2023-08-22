package io.github.daoshii.ssskyblock;

import io.github.daoshii.ssskyblock.commands.Island;
import org.bukkit.plugin.java.JavaPlugin;

public final class SSSkyblock extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("SSSkyblock is enabled!");

        getCommand("island").setExecutor(new Island(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("SSSkyblock is disabled!");
    }
}
