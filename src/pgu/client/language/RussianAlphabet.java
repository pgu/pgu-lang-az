package pgu.client.language;

import java.util.Arrays;
import java.util.List;

import pgu.client.utils.guava.HashBiMap;

public class RussianAlphabet {

    private static final HashBiMap<String, String> latin2russian = HashBiMap.create();
    private static final List<String>              availableLevels;

    public static List<String> availableLevels() {
        return availableLevels;
    }

    public static final HashBiMap<String, String> availableSymbols(final List<String> selectedLevels) {
        final HashBiMap<String, String> availableSymbols = HashBiMap.create();

        for (final String selectedLevel : selectedLevels) {

            if (G_1.equals(selectedLevel)) {
                availableSymbols.put("A", latin2russian.get("A"));
                availableSymbols.put("B", latin2russian.get("B"));
                availableSymbols.put("V", latin2russian.get("V"));
                availableSymbols.put("G", latin2russian.get("G"));
                availableSymbols.put("D", latin2russian.get("D"));
            }
            if (G_2.equals(selectedLevel)) {
                availableSymbols.put("YE", latin2russian.get("YE"));
                availableSymbols.put("YO", latin2russian.get("YO"));
                availableSymbols.put("&#x0290;", latin2russian.get("&#x0290;"));
                availableSymbols.put("Z", latin2russian.get("Z"));
                availableSymbols.put("I", latin2russian.get("I"));
                availableSymbols.put("Y", latin2russian.get("Y"));
            }
            if (G_3.equals(selectedLevel)) {
                availableSymbols.put("K", latin2russian.get("K"));
                availableSymbols.put("L", latin2russian.get("L"));
                availableSymbols.put("M", latin2russian.get("M"));
                availableSymbols.put("N", latin2russian.get("N"));
                availableSymbols.put("O", latin2russian.get("O"));
            }
            if (G_4.equals(selectedLevel)) {
                availableSymbols.put("P", latin2russian.get("P"));
                availableSymbols.put("R", latin2russian.get("R"));
                availableSymbols.put("S", latin2russian.get("S"));
                availableSymbols.put("T", latin2russian.get("T"));
                availableSymbols.put("U", latin2russian.get("U"));
                availableSymbols.put("F", latin2russian.get("F"));
            }
            if (G_5.equals(selectedLevel)) {
                availableSymbols.put("X", latin2russian.get("X"));
                availableSymbols.put("TS", latin2russian.get("TS"));
                availableSymbols.put("CH", latin2russian.get("CH"));
                availableSymbols.put("&#x0282;", latin2russian.get("&#x0282;"));
                availableSymbols.put("&#x0285;", latin2russian.get("&#x0285;"));
                availableSymbols.put("&#x0268;", latin2russian.get("&#x0268;"));
                availableSymbols.put("E", latin2russian.get("E"));
                availableSymbols.put("YU", latin2russian.get("YU"));
                availableSymbols.put("YA", latin2russian.get("YA"));
            }
        }

        return availableSymbols;
    }

    private static final String G_1 = "A - D";
    private static final String G_2 = "YE - Y";
    private static final String G_3 = "K - O";
    private static final String G_4 = "P - F";
    private static final String G_5 = "X - YA";

    static {
        availableLevels = Arrays.asList(G_1, G_2, G_3, G_4, G_5);

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
        latin2russian.put("&#x0285;", "&#x0429;");
        latin2russian.put("&#x0268;", "&#x042B;");
        latin2russian.put("E", "&#x042D;");
        latin2russian.put("YU", "&#x042E;");
        latin2russian.put("YA", "&#x042F;");

    }

}
