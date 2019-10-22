package library;

import org.jetbrains.annotations.NotNull;

/**
 * The type Tool.
 */
public final class Tool {
    /**
     * Fix string string.
     *
     * @param string the string
     * @return the string
     */
    public static String fixString(@NotNull String string) {
        StringBuilder result = new StringBuilder();
        for (String s : string.trim().replaceAll(" +", " ").split(" ")) {
            result.append(String.valueOf(s.charAt(0)).toUpperCase()).append(s.substring(1)).append(" ");
        }
        return result.toString().trim();
    }
}
