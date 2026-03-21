package dev.tunahanxx.util;

import dev.tunahanxx.PrismChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public final class StringWrap {

    public String adminOnlyInGameUsage() {
        return getMessage("messages.admin.only_in_game_usage");
    }

    public String adminNoPermission() {
        return getMessage("messages.admin.no_permission");
    }

    public String reloadSuccess() {
        return getMessage("messages.admin.reload_success");
    }

    public String onlyInGameUsage() {
        return getMessage("messages.default.only_in_game_usage");
    }

    public String noPermission() {
        return getMessage("messages.default.no_permission");
    }

    public String chatColorNoPermission() {
        return getMessage("messages.default.chatcolor.no_permission");
    }

    public String chatColorSelectedSuccessfully() {
        return getMessage("messages.default.chatcolor.selected_successfully");
    }

    public String chatColorRepleaceSuccessfully() {
        return getMessage("messages.default.chatcolor.repleace_successfully");
    }

    public String chatColorRemoveSuccessfully() {
        return getMessage("messages.default.chatcolor.remove_successfully");
    }

    public String chatColorRemoveFail() {
        return getMessage("messages.default.chatcolor.remove_failed");
    }

    private String getMessage(String path) {
        return ColorTranslate.colorize(getMessagesConfig().getString(path));
    }

    private FileConfiguration getMessagesConfig() {
        return PrismChatColor.getInstance()
                .getPrismFileManager()
                .get("messages.yml")
                .config();
    }
}
