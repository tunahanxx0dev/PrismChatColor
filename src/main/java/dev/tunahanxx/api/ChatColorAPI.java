package dev.tunahanxx.api;

import dev.tunahanxx.gui.chatcolor.ChatColorGUI;
import dev.tunahanxx.gui.chatcolor.ChatColorGuiEvent;
import dev.tunahanxx.gui.chatcolor.ChatColorGuiHOLDER;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

import java.util.Map;
import java.util.UUID;

public class ChatColorAPI {

    public static void onChatColorGUI(Player player) {
        var chatColorGUI = new ChatColorGUI();
        chatColorGUI.openInventory(player);
    }

    public static InventoryHolder onChatColorGuiHOLDER(Integer size, String title) {
        return new ChatColorGuiHOLDER(size, title);
    }

    public static Map<UUID, String> onChatColorGuiColorSelectMap() {
        return ChatColorGuiEvent.colorPlayerMap;
    }
}