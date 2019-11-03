package app.string;

import org.jetbrains.annotations.NotNull;

public class Tool {
    @NotNull
    public static String fixString(@NotNull String string) {
        if (string.equals("")) return "";
        StringBuilder result = new StringBuilder();
        for (String s : string.trim().replaceAll(" +", " ").split(" ")) {
            result.append(String.valueOf(s.charAt(0)).toUpperCase()).append(s.substring(1)).append(" ");
        }
        return result.toString().trim();
    }

    @NotNull
    public static String createAcronym(@NotNull String string) {
        StringBuilder result = new StringBuilder();
        for (String s : string.split(" ")) {
            result.append(String.valueOf(s.charAt(0)).toLowerCase());
        }
        return result.toString();
    }
}
