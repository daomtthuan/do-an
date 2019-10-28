package plugin;

import org.jetbrains.annotations.NotNull;

/**
 * The type Tool.
 */
public final class StringTool {

    /**
     * Fix string string.
     *
     * @param string the string
     *
     * @return the string
     */
    @NotNull
    public static String fixString(@NotNull String string) {
        if (string.equals("")) return "";
        StringBuilder result = new StringBuilder();
        for (String s : string.trim().replaceAll(" +", " ").split(" ")) {
            result.append(String.valueOf(s.charAt(0)).toUpperCase()).append(s.substring(1)).append(" ");
        }
        return result.toString().trim();
    }
}
