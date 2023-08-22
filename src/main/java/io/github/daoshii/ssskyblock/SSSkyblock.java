package io.github.daoshii.ssskyblock;

import io.github.daoshii.ssskyblock.managers.CommandManager;
import io.github.daoshii.ssskyblock.managers.SchematicManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SSSkyblock extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("SSSkyblock is enabled!");

        new CommandManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("SSSkyblock is disabled!");
    }
}
