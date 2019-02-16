package e1;

import java.util.HashMap;

public class SevenSegmentMapping {

    static HashMap<String, String> strangeDigits = new HashMap<>();

    static {
        strangeDigits.put(
                " _ " +
                "| |" +
                "|_|", "0");

        strangeDigits.put(
                "   " +
                "  |" +
                "  |", "1");

        strangeDigits.put(
                " _ " +
                " _|" +
                "|_ ", "2");

        strangeDigits.put(
                " _ " +
                " _|" +
                " _|", "3");

        strangeDigits.put(
                "   " +
                "|_|" +
                "  |", "4");

        strangeDigits.put(
                " _ " +
                "|_ " +
                " _|", "5");

        strangeDigits.put(
                " _ " +
                "|_ " +
                "|_|", "6");

        strangeDigits.put(
                " _ " +
                "  |" +
                "  |", "7");

        strangeDigits.put(
                " _ " +
                "|_|" +
                "|_|", "8");

        strangeDigits.put(
                " _ " +
                "|_|" +
                " _|", "9");

        strangeDigits.put(
                "   " +
                "___" +
                "   ", "-");

        strangeDigits.put(
                "   " +
                "   " +
                " . ", ".");

        strangeDigits.put(
                "   " +
                "   " +
                "   ", " ");
    }
}
