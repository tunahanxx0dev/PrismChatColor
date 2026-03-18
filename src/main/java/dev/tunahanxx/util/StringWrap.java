package dev.tunahanxx.util;

import dev.tunahanxx.PrismChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public final class StringWrap {

    public String adminOnlyInGameUsage() {
        var pfm = PrismChatColor.getInstance().getPrismFileManager();
        FileConfiguration mfc = pfm.get("messages.yml").config();
        return ColorTranslate.colorize(mfc.getString("messages.admin.only_in_game_usage"));
    }

    public String adminNoPermission() {
        var pfm = PrismChatColor.getInstance().getPrismFileManager();
        FileConfiguration mfc = pfm.get("messages.yml").config();
        return ColorTranslate.colorize(mfc.getString("messages.admin.no_permission"));
    }

    public String reloadSuccess() {
        var pfm = PrismChatColor.getInstance().getPrismFileManager();
        FileConfiguration mfc = pfm.get("messages.yml").config();
        return ColorTranslate.colorize(mfc.getString("messages.admin.reload_success"));
    }

    public String onlyInGameUsage() {
        var pfm = PrismChatColor.getInstance().getPrismFileManager();
        FileConfiguration mfc = pfm.get("messages.yml").config();
        return ColorTranslate.colorize(mfc.getString("messages.default.only_in_game_usage"));
    }

    public String noPermission() {
        var pfm = PrismChatColor.getInstance().getPrismFileManager();
        FileConfiguration mfc = pfm.get("messages.yml").config();
        return ColorTranslate.colorize(mfc.getString("messages.default.no_permission"));
    }

    public String chatColorNoPermission() {
        var pfm = PrismChatColor.getInstance().getPrismFileManager();
        FileConfiguration mfc = pfm.get("messages.yml").config();
        return ColorTranslate.colorize(mfc.getString("messages.default.chatcolor.no_permission"));
    }

    public String chatColorSelectedSuccessfully() {
        var pfm = PrismChatColor.getInstance().getPrismFileManager();
        FileConfiguration mfc = pfm.get("messages.yml").config();
        return ColorTranslate.colorize(mfc.getString("messages.default.chatcolor.selected_successfully"));
    }

    public String chatColorRepleaceSuccessfully() {
        var pfm = PrismChatColor.getInstance().getPrismFileManager();
        FileConfiguration mfc = pfm.get("messages.yml").config();
        return ColorTranslate.colorize(mfc.getString("messages.default.chatcolor.repleace_successfully"));
    }

    public String chatColorRemoveSuccessfully() {
        var pfm = PrismChatColor.getInstance().getPrismFileManager();
        FileConfiguration mfc = pfm.get("messages.yml").config();
        return ColorTranslate.colorize(mfc.getString("messages.default.chatcolor.remove_successfully"));
    }

    public String chatColorRemoveFail() {
        var pfm = PrismChatColor.getInstance().getPrismFileManager();
        FileConfiguration mfc = pfm.get("messages.yml").config();
        return ColorTranslate.colorize(mfc.getString("messages.default.chatcolor.remove_failed"));
    }
}