package dev.tunahanxx.gui.chatcolor;

import dev.tunahanxx.PrismChatColor;
import dev.tunahanxx.util.ColorTranslate;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChatColorGUI {

    private static final int CHAT_COLOR_SIZE = 27;

    private static final Map<String, Color> NAMED_COLORS = new LinkedHashMap<>();
    private static final Map<Character, Color> LEGACY_COLORS = new LinkedHashMap<>();
    private static final int FIRST_COLOR_SLOT = 9;
    private static final int COLOR_COUNT = 9;
    private static final int CLEAR_COLOR_SLOT = 22;

    static {
        NAMED_COLORS.put("BLACK", Color.fromRGB(0, 0, 0));
        NAMED_COLORS.put("DARK_BLUE", Color.fromRGB(0, 0, 170));
        NAMED_COLORS.put("DARK_GREEN", Color.fromRGB(0, 170, 0));
        NAMED_COLORS.put("DARK_AQUA", Color.fromRGB(0, 170, 170));
        NAMED_COLORS.put("DARK_RED", Color.fromRGB(170, 0, 0));
        NAMED_COLORS.put("DARK_PURPLE", Color.fromRGB(170, 0, 170));
        NAMED_COLORS.put("GOLD", Color.fromRGB(255, 170, 0));
        NAMED_COLORS.put("GRAY", Color.fromRGB(170, 170, 170));
        NAMED_COLORS.put("GREY", Color.fromRGB(170, 170, 170));
        NAMED_COLORS.put("DARK_GRAY", Color.fromRGB(85, 85, 85));
        NAMED_COLORS.put("DARK_GREY", Color.fromRGB(85, 85, 85));
        NAMED_COLORS.put("BLUE", Color.fromRGB(85, 85, 255));
        NAMED_COLORS.put("GREEN", Color.fromRGB(85, 255, 85));
        NAMED_COLORS.put("AQUA", Color.fromRGB(85, 255, 255));
        NAMED_COLORS.put("RED", Color.fromRGB(255, 85, 85));
        NAMED_COLORS.put("LIGHT_PURPLE", Color.fromRGB(255, 85, 255));
        NAMED_COLORS.put("YELLOW", Color.fromRGB(255, 255, 85));
        NAMED_COLORS.put("WHITE", Color.fromRGB(255, 255, 255));
        NAMED_COLORS.put("PINK", Color.fromRGB(255, 105, 180));
        NAMED_COLORS.put("ORANGE", Color.fromRGB(255, 165, 0));
        NAMED_COLORS.put("PURPLE", Color.fromRGB(128, 0, 128));

        LEGACY_COLORS.put('0', Color.fromRGB(0, 0, 0));
        LEGACY_COLORS.put('1', Color.fromRGB(0, 0, 170));
        LEGACY_COLORS.put('2', Color.fromRGB(0, 170, 0));
        LEGACY_COLORS.put('3', Color.fromRGB(0, 170, 170));
        LEGACY_COLORS.put('4', Color.fromRGB(170, 0, 0));
        LEGACY_COLORS.put('5', Color.fromRGB(170, 0, 170));
        LEGACY_COLORS.put('6', Color.fromRGB(255, 170, 0));
        LEGACY_COLORS.put('7', Color.fromRGB(170, 170, 170));
        LEGACY_COLORS.put('8', Color.fromRGB(85, 85, 85));
        LEGACY_COLORS.put('9', Color.fromRGB(85, 85, 255));
        LEGACY_COLORS.put('a', Color.fromRGB(85, 255, 85));
        LEGACY_COLORS.put('b', Color.fromRGB(85, 255, 255));
        LEGACY_COLORS.put('c', Color.fromRGB(255, 85, 85));
        LEGACY_COLORS.put('d', Color.fromRGB(255, 85, 255));
        LEGACY_COLORS.put('e', Color.fromRGB(255, 255, 85));
        LEGACY_COLORS.put('f', Color.fromRGB(255, 255, 255));
    }

    public void openInventory(Player player) {
        String title = getMenuTitle();
        ChatColorGuiHOLDER holder = new ChatColorGuiHOLDER(CHAT_COLOR_SIZE, title);
        Inventory gui = holder.getInventory();
        fillInventory(gui);
        player.openInventory(gui);
    }

    public void fillInventory(Inventory gui) {
        gui.clear();

        FileConfiguration config = getConfig();
        for (int index = 1; index <= COLOR_COUNT; index++) {
            gui.setItem(FIRST_COLOR_SLOT + index - 1, createColorItem(config, index));
        }
        gui.setItem(CLEAR_COLOR_SLOT, createClearColorItem(config));

        String materialType = config.getString("chatcolor.guis.fill_item.material");
        assert materialType != null;
        Material material = Material.valueOf(materialType.toUpperCase());
        ItemStack filler = createFillerItem(material, ColorTranslate.colorize(config.getString("chatcolor.guis.fill_item.name")));
        fillEmptySlots(gui, filler);
    }

    public void updateOpenInventories() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getOpenInventory().getTopInventory().getHolder() instanceof ChatColorGuiHOLDER) {
                Inventory top = player.getOpenInventory().getTopInventory();
                fillInventory(top);
                player.updateInventory();
            }
        }
    }

    private FileConfiguration getConfig() {
        return PrismChatColor.getInstance()
                .getPrismFileManager()
                .get("guis/chatcolor.yml")
                .config();
    }

    private String getMenuTitle() {
        return ColorTranslate.colorize(getConfig().getString("chatcolor.guis.title"));
    }

    public static void fillEmptySlots(Inventory inventory, ItemStack fillerItem) {
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack currentItem = inventory.getItem(i);
            if (currentItem == null || currentItem.getType() == Material.AIR) {
                inventory.setItem(i, fillerItem);
            }
        }
    }

    public static ItemStack createFillerItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createColorItem(FileConfiguration config, int index) {
        ItemStack colorItem = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) colorItem.getItemMeta();
        if (meta == null) {
            return colorItem;
        }

        meta.addItemFlags(ItemFlag.HIDE_DYE);
        meta.setDisplayName(ColorTranslate.colorize(config.getString("chatcolor.guis.name." + index + "_name")));
        meta.setColor(parseColor(config.getString("chatcolor.guis.color." + index + "_color")));
        colorItem.setItemMeta(meta);
        return colorItem;
    }

    private ItemStack createClearColorItem(FileConfiguration config) {
        String materialName = config.getString("chatcolor.guis.clear_color_item.material");
        assert materialName != null;

        ItemStack clearColorItem = new ItemStack(Material.valueOf(materialName.toUpperCase()));
        ItemMeta meta = clearColorItem.getItemMeta();
        if (meta == null) {
            return clearColorItem;
        }

        meta.setDisplayName(ColorTranslate.colorize(config.getString("chatcolor.guis.clear_color_item.name")));
        List<String> lore = config.getStringList("chatcolor.guis.clear_color_item.lore")
                .stream()
                .map(ColorTranslate::colorize)
                .toList();
        meta.setLore(lore);
        clearColorItem.setItemMeta(meta);
        return clearColorItem;
    }

    private Color parseColor(String input) {
        if (input == null || input.isEmpty()) {
            return Color.WHITE;
        }
        input = input.trim();
        if (input.startsWith("&#")) {
            input = "#" + input.substring(2);
        }
        if (input.startsWith("#")) {
            String hex = input.substring(1);
            if (hex.length() != 6) {
                return Color.WHITE;
            }
            try {
                return Color.fromRGB(Integer.parseInt(hex, 16));
            } catch (NumberFormatException e) {
                return Color.WHITE;
            }
        }
        if (input.contains(",")) {
            String[] rgb = input.split(",");
            if (rgb.length != 3) {
                return Color.WHITE;
            }
            try {
                return Color.fromRGB(
                        Integer.parseInt(rgb[0].trim()),
                        Integer.parseInt(rgb[1].trim()),
                        Integer.parseInt(rgb[2].trim())
                );
            } catch (NumberFormatException e) {
                return Color.WHITE;
            }
        }
        if (input.length() == 2 && input.charAt(0) == '&') {
            char code = Character.toLowerCase(input.charAt(1));
            return LEGACY_COLORS.getOrDefault(code, Color.WHITE);
        }
        Color namedColor = NAMED_COLORS.get(input.toUpperCase());
        return namedColor != null ? namedColor : Color.WHITE;
    }
}
