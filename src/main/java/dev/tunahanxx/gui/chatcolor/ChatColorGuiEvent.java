package dev.tunahanxx.gui.chatcolor;

import dev.tunahanxx.PrismChatColor;
import dev.tunahanxx.util.StringWrap;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ChatColorGuiEvent implements Listener {

    private static final int FIRST_COLOR_SLOT = 9;
    private static final int LAST_COLOR_SLOT = 17;
    private static final int CLEAR_COLOR_SLOT = 22;
    private static final Map<Integer, String> COLOR_PERMISSIONS = createColorPermissions();
    public static final Map<UUID, String> colorPlayerMap = new ConcurrentHashMap<>();
    private static final StringWrap stringWrap = new StringWrap();

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
        if (slot >= FIRST_COLOR_SLOT && slot <= LAST_COLOR_SLOT) {
            handleColorSelection(player, config, slot);
            return;
        }
        if (slot == CLEAR_COLOR_SLOT) {
            handleColorClear(player);
            return;
        }
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 20, 1);
    }

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

    private void handleColorSelection(Player player, FileConfiguration config, int slot) {
        String permission = COLOR_PERMISSIONS.get(slot);
        if (permission == null) {
            return;
        }
        if (!player.hasPermission(permission)) {
            denyAndClose(player);
            return;
        }
        int index = slot - FIRST_COLOR_SLOT + 1;
        colorSelect(player, config.getString("chatcolor.guis.color." + index + "_color"));
        succeedAndClose(player);
    }

    private void handleColorClear(Player player) {
        if (!player.hasPermission("prismchatcolor.chatcolor-clear-color")) {
            denyAndClose(player);
            return;
        }
        if (!colorPlayerMap.containsKey(player.getUniqueId())) {
            player.sendMessage(stringWrap.chatColorRemoveFail());
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
            return;
        }
        colorRemove(player);
        succeedAndClose(player);
    }

    private void denyAndClose(Player player) {
        player.sendMessage(stringWrap.chatColorNoPermission());
        player.closeInventory();
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
    }

    private void succeedAndClose(Player player) {
        player.closeInventory();
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
    }

    private static Map<Integer, String> createColorPermissions() {
        Map<Integer, String> permissions = new HashMap<>();
        permissions.put(9, "prismchatcolor.chatcolor-one");
        permissions.put(10, "prismchatcolor.chatcolor-second");
        permissions.put(11, "prismchatcolor.chatcolor-three");
        permissions.put(12, "prismchatcolor.chatcolor-four");
        permissions.put(13, "prismchatcolor.chatcolor-five");
        permissions.put(14, "prismchatcolor.chatcolor-six");
        permissions.put(15, "prismchatcolor.chatcolor-seven");
        permissions.put(16, "prismchatcolor.chatcolor-eight");
        permissions.put(17, "prismchatcolor.chatcolor-nine");
        return permissions;
    }
}
