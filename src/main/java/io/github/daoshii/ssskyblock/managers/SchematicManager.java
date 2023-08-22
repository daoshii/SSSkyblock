package io.github.daoshii.ssskyblock.managers;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import io.github.daoshii.ssskyblock.SSSkyblock;
import org.bukkit.Location;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SchematicManager {

    private final SSSkyblock plugin;
    public SchematicManager(SSSkyblock plugin) {this.plugin = plugin;}

    public void paste(Location location, File file) { //method accepts 2 parameters

        ClipboardFormat clipboardFormat = ClipboardFormats.findByFile(file); //read file
        Clipboard islandClipboard;

        BlockVector3 islandLocation = BlockVector3.at(location.getBlockX(), location.getBlockY(), location.getBlockZ()); //location for schematic paste

        if (clipboardFormat != null) { //check if null, then load schematic
            try (ClipboardReader clipboardReader = clipboardFormat.getReader(Files.newInputStream(file.toPath()))) {

                if (location.getWorld() == null) //check if world is null
                    throw new NullPointerException("Failed to paste schematic due to world being null");

                World islandWorld = BukkitAdapter.adapt(location.getWorld()); //get the world

                EditSession islandEditSession = WorldEdit.getInstance().newEditSessionBuilder().world(islandWorld).build(); //use worldedit edit session to create an edit session

                islandClipboard = clipboardReader.read(); //now read the clipboard we already loaded for worldedit and set to clipboard var made above

                Operation islandOperation = new ClipboardHolder(islandClipboard) //use clipboard and edit session to paste schematic
                        .createPaste(islandEditSession)
                        .to(islandLocation)
                        .ignoreAirBlocks(true)
                        .build();

                try {
                    Operations.complete(islandOperation);
                    islandEditSession.close();
                } catch (WorldEditException e) {
                    plugin.getLogger().warning("Failed to paste schematic due to world edit exception");
                }
            } catch (IOException e) {
                plugin.getLogger().warning("Failed to paste schematic due to IO exception");
            }
        } else {
            plugin.getLogger().warning("Failed to paste schematic due to file format not found");
        }
    }
}
