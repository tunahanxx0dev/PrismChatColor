package dev.tunahanxx.modules.chatcolor.event;

import dev.tunahanxx.gui.chatcolor.ChatColorGuiEvent;
import dev.tunahanxx.util.ColorTranslate;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatColorAsyncChatEvent implements Listener {

    @EventHandler
    public void onChatColor(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String selectedColor = ChatColorGuiEvent.colorPlayerMap.get(player.getUniqueId());
        if (selectedColor == null || selectedColor.isEmpty()) {
            return;
        }
        event.setMessage(ColorTranslate.colorize(selectedColor + event.getMessage()));
    }
}