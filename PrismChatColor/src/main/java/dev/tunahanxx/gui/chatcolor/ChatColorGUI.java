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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatColorGUI {

    private static final int CHAT_COLOR_SIZE = 27;

    private static final Map<String, Color> NAMED_COLORS = new HashMap<>();
    private static final Map<Character, Color> LEGACY_COLORS = new HashMap<>();

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

        String oneColorString = config.getString("chatcolor.guis.color.1_color");
        Color oneColor = parseColor(oneColorString);
        ItemStack oneColorChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta oneMeta = (LeatherArmorMeta) oneColorChest.getItemMeta();
        oneMeta.addItemFlags(ItemFlag.HIDE_DYE);
        oneMeta.setDisplayName(ColorTranslate.colorize(config.getString("chatcolor.guis.name.1_name")));
        oneMeta.setColor(oneColor);
        oneColorChest.setItemMeta(oneMeta);

        String secondColorString = config.getString("chatcolor.guis.color.2_color");
        Color secondColor = parseColor(secondColorString);
        ItemStack secondColorChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta secondMeta = (LeatherArmorMeta) secondColorChest.getItemMeta();
        secondMeta.addItemFlags(ItemFlag.HIDE_DYE);
        secondMeta.setDisplayName(ColorTranslate.colorize(config.getString("chatcolor.guis.name.2_name")));
        secondMeta.setColor(secondColor);
        secondColorChest.setItemMeta(secondMeta);

        String threeColorString = config.getString("chatcolor.guis.color.3_color");
        Color threeColor = parseColor(threeColorString);
        ItemStack threeColorChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta threeMeta = (LeatherArmorMeta) secondColorChest.getItemMeta();
        threeMeta.addItemFlags(ItemFlag.HIDE_DYE);
        threeMeta.setDisplayName(ColorTranslate.colorize(config.getString("chatcolor.guis.name.3_name")));
        threeMeta.setColor(threeColor);
        threeColorChest.setItemMeta(threeMeta);

        String fourColorString = config.getString("chatcolor.guis.color.4_color");
        Color fourColor = parseColor(fourColorString);
        ItemStack fourColorChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta fourMeta = (LeatherArmorMeta) fourColorChest.getItemMeta();
        fourMeta.addItemFlags(ItemFlag.HIDE_DYE);
        fourMeta.setDisplayName(ColorTranslate.colorize(config.getString("chatcolor.guis.name.4_name")));
        fourMeta.setColor(fourColor);
        fourColorChest.setItemMeta(fourMeta);

        String fiveColorString = config.getString("chatcolor.guis.color.5_color");
        Color fiveColor = parseColor(fiveColorString);
        ItemStack fiveColorChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta fiveMeta = (LeatherArmorMeta) fiveColorChest.getItemMeta();
        fiveMeta.addItemFlags(ItemFlag.HIDE_DYE);
        fiveMeta.setDisplayName(ColorTranslate.colorize(config.getString("chatcolor.guis.name.5_name")));
        fiveMeta.setColor(fiveColor);
        fiveColorChest.setItemMeta(fiveMeta);

        String sixColorString = config.getString("chatcolor.guis.color.6_color");
        Color sixColor = parseColor(sixColorString);
        ItemStack sixColorChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta sixMeta = (LeatherArmorMeta) sixColorChest.getItemMeta();
        sixMeta.addItemFlags(ItemFlag.HIDE_DYE);
        sixMeta.setDisplayName(ColorTranslate.colorize(config.getString("chatcolor.guis.name.6_name")));
        sixMeta.setColor(sixColor);
        sixColorChest.setItemMeta(sixMeta);

        String sevenColorString = config.getString("chatcolor.guis.color.7_color");
        Color sevenColor = parseColor(sevenColorString);
        ItemStack sevenColorChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta sevenMeta = (LeatherArmorMeta) sevenColorChest.getItemMeta();
        sevenMeta.addItemFlags(ItemFlag.HIDE_DYE);
        sevenMeta.setDisplayName(ColorTranslate.colorize(config.getString("chatcolor.guis.name.7_name")));
        sevenMeta.setColor(sevenColor);
        sevenColorChest.setItemMeta(sevenMeta);

        String eightColorString = config.getString("chatcolor.guis.color.8_color");
        Color eightColor = parseColor(eightColorString);
        ItemStack eightColorChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta eightMeta = (LeatherArmorMeta) eightColorChest.getItemMeta();
        eightMeta.addItemFlags(ItemFlag.HIDE_DYE);
        eightMeta.setDisplayName(ColorTranslate.colorize(config.getString("chatcolor.guis.name.8_name")));
        eightMeta.setColor(eightColor);
        eightColorChest.setItemMeta(eightMeta);

        String nineColorString = config.getString("chatcolor.guis.color.9_color");
        Color nineColor = parseColor(nineColorString);
        ItemStack nineColorChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta nineMeta = (LeatherArmorMeta) nineColorChest.getItemMeta();
        nineMeta.addItemFlags(ItemFlag.HIDE_DYE);
        nineMeta.setDisplayName(ColorTranslate.colorize(config.getString("chatcolor.guis.name.9_name")));
        nineMeta.setColor(nineColor);
        nineColorChest.setItemMeta(nineMeta);

        ItemStack clearColorItem = new ItemStack(Material.valueOf(config.getString("chatcolor.guis.clear_color_item.material")));
        ItemMeta clearColorItemMeta = clearColorItem.getItemMeta();
        clearColorItemMeta.setDisplayName(ColorTranslate.colorize(config.getString("chatcolor.guis.clear_color_item.name")));
        List<String> lore = config.getStringList("chatcolor.guis.clear_color_item.lore")
                .stream()
                .map(ColorTranslate::colorize)
                .toList();
        clearColorItemMeta.setLore(lore);
        clearColorItem.setItemMeta(clearColorItemMeta);

        gui.setItem(9, oneColorChest);
        gui.setItem(10, secondColorChest);
        gui.setItem(11, threeColorChest);
        gui.setItem(12, fourColorChest);
        gui.setItem(13, fiveColorChest);
        gui.setItem(14, sixColorChest);
        gui.setItem(15, sevenColorChest);
        gui.setItem(16, eightColorChest);
        gui.setItem(17, nineColorChest);
        gui.setItem(22, clearColorItem);

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