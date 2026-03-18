package dev.tunahanxx.util;

import net.md_5.bungee.api.ChatColor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ColorTranslate {

    private static final Pattern HEX_PATTERN = Pattern.compile("(?i)(?<!<)(#[A-F0-9]{6}|&#[A-F0-9]{6})");
    private static final Pattern GRADIENT_PATTERN = Pattern.compile("(?i)<#([A-F0-9]{6}(?::#[A-F0-9]{6})*)>(.*?)</#>");

    private ColorTranslate() { }

    public static String colorize(String text) {

        if (text == null || text.isEmpty()) {
            return "";
        }

        text = applyGradients(text);
        text = applyHex(text);

        return org.bukkit.ChatColor.translateAlternateColorCodes('&', text);
    }

    private static String applyHex(String text) {

        Matcher matcher = HEX_PATTERN.matcher(text);
        StringBuilder builder = new StringBuilder();

        while (matcher.find()) {
            String color = matcher.group(1).replace("&", "");
            matcher.appendReplacement(builder, Matcher.quoteReplacement(ChatColor.of(color).toString()));
        }

        matcher.appendTail(builder);

        return builder.toString();
    }

    private static String applyGradients(String text) {

        Matcher matcher = GRADIENT_PATTERN.matcher(text);
        StringBuilder builder = new StringBuilder();

        while (matcher.find()) {
            String colorGroup = matcher.group(1);
            String content = matcher.group(2);
            String replacement = multiGradient(content, parseColors(colorGroup));
            matcher.appendReplacement(builder, Matcher.quoteReplacement(replacement));
        }

        matcher.appendTail(builder);

        return builder.toString();
    }

    public static String multiGradient(String text, List<Color> colors) {

        if (text == null || text.isEmpty()) {
            return "";
        }

        if (colors == null || colors.isEmpty()) {
            return text;
        }

        if (colors.size() == 1) {
            return ChatColor.of(colors.getFirst()) + text;
        }

        int length = text.length();

        if (length == 1) {
            return ChatColor.of(colors.getFirst()) + text;
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            double progress = (double) i / (length - 1);
            Color color = interpolate(colors, progress);
            builder.append(ChatColor.of(color)).append(text.charAt(i));
        }

        return builder.toString();
    }

    private static Color interpolate(List<Color> colors, double progress) {

        if (progress <= 0.0D) {
            return colors.getFirst();
        }

        if (progress >= 1.0D) {
            return colors.getLast();
        }

        double scaled = progress * (colors.size() - 1);
        int index = (int) Math.floor(scaled);
        double localProgress = scaled - index;

        Color start = colors.get(index);
        Color end = colors.get(index + 1);

        int red = (int) Math.round(start.getRed() + (end.getRed() - start.getRed()) * localProgress);
        int green = (int) Math.round(start.getGreen() + (end.getGreen() - start.getGreen()) * localProgress);
        int blue = (int) Math.round(start.getBlue() + (end.getBlue() - start.getBlue()) * localProgress);

        return new Color(red, green, blue);
    }

    private static List<Color> parseColors(String input) {

        List<Color> colors = new ArrayList<>();
        String[] split = input.split(":");

        for (String part : split) {
            colors.add(Color.decode(part));
        }

        return colors;
    }
}