package dev.tunahanxx;

import dev.tunahanxx.commands.chatcolor.ChatColorCommands;
import dev.tunahanxx.commands.PrismChatColorAdminCommands;
import dev.tunahanxx.file.PrismFileManager;
import dev.tunahanxx.gui.chatcolor.ChatColorGuiEvent;
import dev.tunahanxx.modules.chatcolor.event.ChatColorAsyncChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class PrismChatColor extends JavaPlugin {

    private static PrismChatColor instance;

    PrismFileManager prismFileManager;

    @Override
    public void onEnable() {
        initalizePlugin();
    }

    @Override
    public void onDisable() { }

    private void initalizePlugin() {

        instance = this;

        prismFileManager = new PrismFileManager();
        prismFileManager.create("messages.yml");
        prismFileManager.create("guis/chatcolor.yml");

        Objects.requireNonNull(this.getCommand("prismchatcolor")).setExecutor(new PrismChatColorAdminCommands());
        Objects.requireNonNull(this.getCommand("chatcolor")).setExecutor(new ChatColorCommands());
        Objects.requireNonNull(this.getCommand("cc")).setExecutor(new ChatColorCommands());

        getServer().getPluginManager().registerEvents(new ChatColorGuiEvent(), this);
        getServer().getPluginManager().registerEvents(new ChatColorAsyncChatEvent(), this);

    }

    public static PrismChatColor getInstance() {
        return instance;
    }

    public PrismFileManager getPrismFileManager() {
        return prismFileManager;
    }
}