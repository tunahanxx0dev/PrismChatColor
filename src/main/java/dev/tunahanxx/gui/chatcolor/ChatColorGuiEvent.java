package dev.tunahanxx.gui.chatcolor;

import dev.tunahanxx.PrismChatColor;
import dev.tunahanxx.api.ChatColorAPI;
import dev.tunahanxx.util.StringWrap;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ChatColorGuiEvent implements Listener {

    public static final Map<UUID, String> colorPlayerMap = new ConcurrentHashMap<>();
    private static StringWrap stringWrap = new StringWrap();

    @EventHandler
    public void onChatColorGuiClick(InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player player)) return;

        Inventory topInventory = event.getView().getTopInventory();
        InventoryHolder holder = topInventory.getHolder();

        if (!(holder instanceof ChatColorGuiHOLDER)) return;

        event.setCancelled(true);

        if (event.getClickedInventory() == null) return;
        if (!event.getClickedInventory().equals(topInventory)) return;

        int slot = event.getRawSlot();
        FileConfiguration config = getConfig();

        switch (slot) {
            case 9 -> {
                if (!player.hasPermission("prismchatcolor.chatcolor-one")) {
                    player.sendMessage(stringWrap.chatColorNoPermission());
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
                    return;
                }
                colorSelect(player, config.getString("chatcolor.guis.color.1_color"));
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            }
            case 10 -> {
                if (!player.hasPermission("prismchatcolor.chatcolor-second")) {
                    player.sendMessage(stringWrap.chatColorNoPermission());
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
                    return;
                }
                colorSelect(player, config.getString("chatcolor.guis.color.2_color"));
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            }
            case 11 -> {
                if (!player.hasPermission("prismchatcolor.chatcolor-three")) {
                    player.sendMessage(stringWrap.chatColorNoPermission());
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
                    return;
                }
                colorSelect(player, config.getString("chatcolor.guis.color.3_color"));
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            }
            case 12 -> {
                if (!player.hasPermission("prismchatcolor.chatcolor-four")) {
                    player.sendMessage(stringWrap.chatColorNoPermission());
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
                    return;
                }
                colorSelect(player, config.getString("chatcolor.guis.color.4_color"));
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            }
            case 13 -> {
                if (!player.hasPermission("prismchatcolor.chatcolor-five")) {
                    player.sendMessage(stringWrap.chatColorNoPermission());
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
                    return;
                }
                colorSelect(player, config.getString("chatcolor.guis.color.5_color"));
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            }
            case 14 -> {
                if (!player.hasPermission("prismchatcolor.chatcolor-six")) {
                    player.sendMessage(stringWrap.chatColorNoPermission());
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
                    return;
                }
                colorSelect(player, config.getString("chatcolor.guis.color.6_color"));
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            }
            case 15 -> {
                if (!player.hasPermission("prismchatcolor.chatcolor-seven")) {
                    player.sendMessage(stringWrap.chatColorNoPermission());
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
                    return;
                }
                colorSelect(player, config.getString("chatcolor.guis.color.7_color"));
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            }
            case 16 -> {
                if (!player.hasPermission("prismchatcolor.chatcolor-eight")) {
                    player.sendMessage(stringWrap.chatColorNoPermission());
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
                    return;
                }
                colorSelect(player, config.getString("chatcolor.guis.color.8_color"));
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            }
            case 17 -> {
                if (!player.hasPermission("prismchatcolor.chatcolor-nine")) {
                    player.sendMessage(stringWrap.chatColorNoPermission());
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
                    return;
                }
                colorSelect(player, config.getString("chatcolor.guis.color.9_color"));
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            }
            case 22 -> {
                if (!player.hasPermission("prismchatcolor.chatcolor-clear-color")) {
                    player.sendMessage(stringWrap.chatColorNoPermission());
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
                    return;
                }
                if (!colorPlayerMap.containsKey(player.getUniqueId())) {
                    player.sendMessage(stringWrap.chatColorRemoveFail());
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
                    return;
                }
                colorRemove(player);
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            }
            default -> player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 20, 1);
        }
    }

    // Oyuncu Renk Seçimi Kaybolmamasını Yapmak Gerekirse Dosyaya Veri Kaydetme Sistemi Yaz.
    private void colorSelect(Player player, String colorName) {
        if (colorName == null || colorName.isEmpty()) {
            player.sendMessage(stringWrap.chatColorSelectedSuccessfully());
            return;
        }
        colorPlayerMap.put(player.getUniqueId(), colorName);
        player.sendMessage(stringWrap.chatColorRepleaceSuccessfully());
    }

    private void colorRemove(Player player) {
        colorPlayerMap.remove(player.getUniqueId());
        player.sendMessage(stringWrap.chatColorRemoveSuccessfully());
    }

    private FileConfiguration getConfig() {
        return PrismChatColor.getInstance()
                .getPrismFileManager()
                .get("guis/chatcolor.yml")
                .config();
    }
}