package io.github.qolarnix.prison;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Blocks implements Listener {

    private final Map<String, ItemStack> customBlocks = new HashMap<>();

    @EventHandler
    public void joinItems(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemStack shinyStone = getCustomBlock("shiny_stone");

        player.getInventory().addItem(shinyStone);
    }

    public void initializeBlocks() {
        registerShinyStone(
                "shiny_stone",
                Material.STONE,
                Component.text("Shiny Stone")
                        .color(NamedTextColor.AQUA)
                        .decorate(TextDecoration.BOLD),
                Component.text("Rare stone found only in certain regions")
                        .color(NamedTextColor.GRAY),
                Enchantment.DENSITY, 10
        );
    }

    private void registerShinyStone(String id, Material material, Component displayName, Component lore, Enchantment enchantment, int enchantmentLevel) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();

        if(meta != null) {
            meta.displayName(displayName);
            meta.lore(List.of(lore));
            meta.addEnchant(enchantment, enchantmentLevel, true);
            meta.getPersistentDataContainer().set(
                    new NamespacedKey(Prison.plugin, id),
                    PersistentDataType.STRING, id
            );

            itemStack.setItemMeta(meta);
        }

        customBlocks.put(id, itemStack);
    }

    // util for getting custom block
    public ItemStack getCustomBlock(String id) {
        return customBlocks.get(id);
    }
}
