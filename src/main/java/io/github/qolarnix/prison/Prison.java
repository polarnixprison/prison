package io.github.qolarnix.prison;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Prison extends JavaPlugin {

    public static Prison plugin;
    private Region region;

    @Override
    public void onEnable() {
        plugin = this;
        region = new Region();

        getLogger().info("Prison plugin enabled!");
        saveDefaultConfig();

        // classes
        Bukkit.getPluginManager().registerEvents(region, this);

        // commands
        getCommand("prison").setExecutor(new Commands());
    }

    public Region getRegion() {
        return region;
    }

    @Override
    public void onDisable() {
        getLogger().info("Prison plugin disabled!");
    }
}
