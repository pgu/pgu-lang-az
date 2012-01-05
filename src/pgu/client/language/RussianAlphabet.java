package pgu.client.language;

import java.util.Arrays;
import java.util.List;

import pgu.client.myguava.HashBiMap;

public class RussianAlphabet {

    private static final HashBiMap<String, String> latin2russian = HashBiMap.create();
    private static final List<String> availableLevels;

    public static List<String> availableLevels() {
        return availableLevels;
    }

    public static final HashBiMap<String, String> availableSymbols(final List<String> selectedLevels) {
        final HashBiMap<String, String> availableSymbols = HashBiMap.create();

        for (final String selectedLevel : selectedLevels) {

            if (A.equals(selectedLevel)) {
                availableSymbols.put("A", latin2russian.get("A"));
                availableSymbols.put("B", latin2russian.get("B"));
                availableSymbols.put("V", latin2russian.get("V"));
                availableSymbols.put("G", latin2russian.get("G"));
                availableSymbols.put("D", latin2russian.get("D"));
            }
        }

        return availableSymbols;
    }

    private static final String A = "0 -";

    static {
        availableLevels = Arrays.asList( //
                A);
        latin2russian.put("A", "&#x0410;");
        latin2russian.put("B", "&#x0411;");
        latin2russian.put("V", "&#x0412;");
        latin2russian.put("G", "&#x0413;");
        latin2russian.put("D", "&#x0414;");
        latin2russian.put("YE", "&#x0415;");
        latin2russian.put("YO", "&#x0401;");
        latin2russian.put("&#x0290;", "&#x0416;");
        latin2russian.put("Z", "&#x0417;");
        latin2russian.put("I", "&#x0418;");
        latin2russian.put("Y", "&#x0419;");
        latin2russian.put("K", "&#x041A;");
        latin2russian.put("L", "&#x041B;");
        latin2russian.put("M", "&#x041C;");
        latin2russian.put("N", "&#x041D;");
        latin2russian.put("O", "&#x041E;");
        latin2russian.put("P", "&#x041F;");
        latin2russian.put("R", "&#x0420;");
        latin2russian.put("S", "&#x0421;");
        latin2russian.put("T", "&#x0422;");
        latin2russian.put("U", "&#x0423;");
        latin2russian.put("F", "&#x0424;");
        latin2russian.put("X", "&#x0425;");
        latin2russian.put("TS", "&#x0426;");
        latin2russian.put("CH", "&#x0427;");
        latin2russian.put("&#x0282;", "&#x0428;");
        latin2russian.put("&#x0285;&#x0285;", "&#x0429;");
        latin2russian.put("&#x0268;", "&#x042B;");
        latin2russian.put("E", "&#x042D;");
        latin2russian.put("YU", "&#x042E;");
        latin2russian.put("YA", "&#x042F;");

    }

}
