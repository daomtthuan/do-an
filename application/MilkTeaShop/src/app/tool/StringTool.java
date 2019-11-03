package app.tool;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class StringTool {
    @Contract(pure = true)
    private StringTool() {
    }

    @NotNull
    public static java.lang.String fixString(@NotNull java.lang.String string) {
        if (string.equals("")) return "";
        StringBuilder result = new StringBuilder();
        for (java.lang.String s : string.trim().replaceAll(" +", " ").split(" ")) {
            result.append(java.lang.String.valueOf(s.charAt(0)).toUpperCase()).append(s.substring(1)).append(" ");
        }
        return result.toString().trim();
    }

    @NotNull
    public static java.lang.String createAcronym(@NotNull java.lang.String string) {
        StringBuilder result = new StringBuilder();
        for (java.lang.String s : string.split(" ")) {
            result.append(java.lang.String.valueOf(s.charAt(0)).toLowerCase());
        }
        return result.toString();
    }
}
