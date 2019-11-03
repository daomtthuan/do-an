package app.tool;

import org.jetbrains.annotations.Contract;

public class Number {
    @Contract(pure = true)
    private Number() {

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
