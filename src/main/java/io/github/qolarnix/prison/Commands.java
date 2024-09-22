package io.github.qolarnix.prison;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        String regionName = args[1];
        Region region = Prison.plugin.getRegion();

        if(args.length != 2 || !args[0].equalsIgnoreCase("setregion")) {
            player.sendMessage("Usage: /prison setregion <name>");
        }

        Location[] selectedRegion = region.getPlayerSelections(player.getUniqueId());

        if(selectedRegion == null || selectedRegion[0] == null || selectedRegion[1] == null) {
            player.sendMessage("You need to select a region before saving it.");
            return true;
        }

        saveRegionToFile(regionName, selectedRegion);
        player.sendMessage("Region " + regionName + " saved successfully!");

        return true;
    }

    private void saveRegionToFile(String regionName, Location[] selectedRegion) {
        FileConfiguration config = Prison.plugin.getConfig();

        config.set("regions." + regionName + ".pos1.x", selectedRegion[0].getBlockX());
        config.set("regions." + regionName + ".pos1.y", selectedRegion[0].getBlockY());
        config.set("regions." + regionName + ".pos1.z", selectedRegion[0].getBlockZ());

        config.set("regions." + regionName + ".pos2.x", selectedRegion[1].getBlockX());
        config.set("regions." + regionName + ".pos2.y", selectedRegion[1].getBlockY());
        config.set("regions." + regionName + ".pos2.z", selectedRegion[1].getBlockZ());

        Prison.plugin.saveConfig();
    }
}
