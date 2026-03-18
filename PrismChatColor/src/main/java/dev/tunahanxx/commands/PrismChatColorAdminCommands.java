package dev.tunahanxx.commands;

import dev.tunahanxx.PrismChatColor;
import dev.tunahanxx.gui.chatcolor.ChatColorGUI;
import dev.tunahanxx.util.ColorTranslate;
import dev.tunahanxx.util.StringWrap;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public final class PrismChatColorAdminCommands implements TabExecutor {

    private static final StringWrap stringWrap = new StringWrap();

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command c, @NotNull String l, @NotNull String @NotNull [] a) {

        var pfm = PrismChatColor.getInstance().getPrismFileManager();

        FileConfiguration mfcConfig = pfm.get("messages.yml").config();

        List<String> helpText = mfcConfig.getStringList("messages.admin.help");

        if (!(s instanceof Player player)) {
            s.sendMessage(stringWrap.adminOnlyInGameUsage());
            return true;
        }

        if (!player.hasPermission("prismchatcolor.admin")) {
            player.sendMessage(stringWrap.adminNoPermission());
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
            return true;
        }

        if (a.length >= 1 && a[0].equalsIgnoreCase("reload")) {
            pfm.reloadAll();
            ChatColorGUI chatColorGUI = new ChatColorGUI();
            chatColorGUI.updateOpenInventories();
            player.sendMessage(stringWrap.reloadSuccess());
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            return true;
        }

        if (a.length >= 1 && a[0].equalsIgnoreCase("help")) {
            for (String text : helpText) {
                player.sendMessage(ColorTranslate.colorize(text));
            }
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            return true;
        }

        for (String text : helpText) {
            player.sendMessage(ColorTranslate.colorize(text));
        }
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 10, 1);
        return true;
    }

    @Override
    public @NonNull List<String> onTabComplete(@NotNull CommandSender s, @NotNull Command c, @NotNull String l, @NotNull String @NotNull [] a) {

        List<String> argsList = new ArrayList<>();

        if (a.length == 1) {
            argsList.add("reload");
            argsList.add("help");
        }

        return argsList;
    }
}