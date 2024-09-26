package me.neovitalism.neoecobridge.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ColorUtil {
    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]){6}");

    public static String parseColour(String input) {
        if(input == null) return null;
        Matcher matcher = HEX_PATTERN.matcher(input);
        while (matcher.find()) {
            final ChatColor hexColor = ChatColor.of(matcher.group().substring(1));
            final String before = input.substring(0, matcher.start());
            final String after = input.substring(matcher.end());
            input = before + hexColor + after;
            matcher = HEX_PATTERN.matcher(input);
        }
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
