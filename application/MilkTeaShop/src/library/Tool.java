package library;

import org.jetbrains.annotations.NotNull;

public final class Tool {
    public static String fixString(@NotNull String string) {
        StringBuilder newString = new StringBuilder();
        for (String s : string.trim().replaceAll(" ", " ").toLowerCase().split(" ")) {
            newString.append(s.replaceFirst(String.valueOf(s.charAt(0)), String.valueOf(s.charAt(0)).toUpperCase()));
        }
        return newString.toString();
    }
}
