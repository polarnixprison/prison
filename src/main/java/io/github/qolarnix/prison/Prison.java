package io.github.qolarnix.prison;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Prison extends JavaPlugin {

    public static Prison plugin;
    private Region region;
    private Blocks blockManager;

    @Override
    public void onEnable() {
        plugin = this;
        region = new Region();
        blockManager = new Blocks();

        getLogger().info("Prison plugin enabled!");
        saveDefaultConfig();

        // region
        Bukkit.getPluginManager().registerEvents(region, this);

        // commands
        getCommand("prison").setExecutor(new Commands());

        // blocks
        blockManager.initializeBlocks();
        Bukkit.getPluginManager().registerEvents(blockManager, this);
    }

    public Blocks getBlocks() {
        return blockManager;
    }

    public Region getRegion() {
        return region;
    }

    @Override
    public void onDisable() {
        getLogger().info("Prison plugin disabled!");
    }
}
