package util;

import java.sql.Date;

public class DateParser {

    public static Date parse(String raw) {
        if (raw == null) return null;

        raw = raw.trim();
        if (raw.isEmpty()) return null;

        try {
            return Date.valueOf(raw);
        } catch (Exception ignored) {}

        raw = raw.replace("/", "-");
        String[] parts = raw.split("-");

        if (parts.length == 3) {
            String day = pad(parts[0]);
            String month = pad(parts[1]);
            String year = parts[2];

            if (year.length() != 4 && parts[0].length() == 4) {
                year = parts[0];
                month = pad(parts[1]);
                day = pad(parts[2]);
            }

            return Date.valueOf(year + "-" + month + "-" + day);
        }

        throw new IllegalArgumentException("Unparseable date: " + raw);
    }

    private static String pad(String s) {
        return (s.length() == 1) ? "0" + s : s;
    }
}
