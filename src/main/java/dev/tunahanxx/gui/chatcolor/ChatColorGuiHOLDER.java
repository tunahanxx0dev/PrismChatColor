package dev.tunahanxx.gui.chatcolor;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jspecify.annotations.NonNull;

public class ChatColorGuiHOLDER implements InventoryHolder {

    private final Inventory inventory;

    public ChatColorGuiHOLDER(int size, String title) {
        this.inventory = Bukkit.createInventory(this, size, title);
    }

    @Override
    public @NonNull Inventory getInventory() {
        return inventory;
    }
}