package io.github.qolarnix.prison;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.UUID;

public class Region implements Listener {

    private final HashMap<UUID, Location[]> playerSelections = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        boolean regionSelectTool = event.getPlayer()
                .getInventory()
                .getItemInMainHand()
                .getType() == Material.STONE_SHOVEL;

        if(regionSelectTool) {
            Action action = event.getAction();

            if(!playerSelections.containsKey(playerId)) {
                playerSelections.put(playerId, new Location[2]);
            }

            if(action == Action.LEFT_CLICK_BLOCK) {
                Location pos1 = event.getClickedBlock().getLocation();
                playerSelections.get(playerId)[0] = pos1;
                player.sendMessage("Position 1 set to: " + formatLocation(pos1));
                event.setCancelled(true);
            }
            else if(action == Action.RIGHT_CLICK_BLOCK) {
                Location pos2 = event.getClickedBlock().getLocation();
                playerSelections.get(playerId)[1] = pos2;
                player.sendMessage("Position 2 set to: " + formatLocation(pos2));
                event.setCancelled(true);
            }
        }
    }

    // format util
    private String formatLocation(Location loc) {
        return "X: " + loc.getBlockX() + ", Y: " + loc.getBlockY() + ", Z: " + loc.getBlockZ();
    }

    public Location[] getPlayerSelections(UUID playerId) {
        return playerSelections.get(playerId);
    }
}
