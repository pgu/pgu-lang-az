package pgu.client.language;

import java.util.ArrayList;

import pgu.client.utils.guava.HashBiMap;
import pgu.client.utils.guava.Lists;

public enum GreekAlphabet implements HasLevels {
    INSTANCE;

    private final HashBiMap<String, String> latin2greek = HashBiMap.create();
    private final ArrayList<String> availableLevels = Lists.newArrayList(A_Z, ET_M, N_S, T_0M);

    private static final String A_Z = "A - Z";
    private static final String ET_M = "ET - M";
    private static final String N_S = "N - S";
    private static final String T_0M = "T - OM";

    {
        latin2greek.put("A", "&#x0391;");
        latin2greek.put("B", "&#x0392;");
        latin2greek.put("G", "&#x0393;");
        latin2greek.put("D", "&#x0394;");
        latin2greek.put("E", "&#x0395;");
        latin2greek.put("Z", "&#x0396;");
        latin2greek.put("ET", "&#x0397;");
        latin2greek.put("TH", "&#x0398;");
        latin2greek.put("I", "&#x0399;");
        latin2greek.put("K", "&#x039a;");
        latin2greek.put("L", "&#x039b;");
        latin2greek.put("M", "&#x039c;");
        latin2greek.put("N", "&#x039d;");
        latin2greek.put("X", "&#x039e;");
        latin2greek.put("O", "&#x039f;");
        latin2greek.put("P", "&#x03a0;");
        latin2greek.put("R", "&#x03a1;");
        latin2greek.put("S", "&#x03a3;");
        latin2greek.put("T", "&#x03a4;");
        latin2greek.put("U", "&#x03a5;");
        latin2greek.put("PH", "&#x03a6;");
        latin2greek.put("CH", "&#x03a7;");
        latin2greek.put("PS", "&#x03a8;");
        latin2greek.put("OM", "&#x03a9;");
    }

    @Override
    public ArrayList<String> availableLevels() {
        return availableLevels;
    }

    @Override
    public final HashBiMap<String, String> availableSymbols(final ArrayList<String> selectedLevels) {
        final HashBiMap<String, String> availableSymbols = HashBiMap.create();
        for (final String selectedLevel : selectedLevels) {

            if (A_Z.equals(selectedLevel)) {
                availableSymbols.put("A", latin2greek.get("A"));
                availableSymbols.put("B", latin2greek.get("B"));
                availableSymbols.put("G", latin2greek.get("G"));
                availableSymbols.put("D", latin2greek.get("D"));
                availableSymbols.put("E", latin2greek.get("E"));
                availableSymbols.put("Z", latin2greek.get("Z"));

            } else if (ET_M.equals(selectedLevel)) {
                availableSymbols.put("ET", latin2greek.get("ET"));
                availableSymbols.put("TH", latin2greek.get("TH"));
                availableSymbols.put("I", latin2greek.get("I"));
                availableSymbols.put("K", latin2greek.get("K"));
                availableSymbols.put("L", latin2greek.get("L"));
                availableSymbols.put("M", latin2greek.get("M"));

            } else if (N_S.equals(selectedLevel)) {
                availableSymbols.put("N", latin2greek.get("N"));
                availableSymbols.put("X", latin2greek.get("X"));
                availableSymbols.put("O", latin2greek.get("O"));
                availableSymbols.put("P", latin2greek.get("P"));
                availableSymbols.put("R", latin2greek.get("R"));
                availableSymbols.put("S", latin2greek.get("S"));

            } else if (T_0M.equals(selectedLevel)) {
                availableSymbols.put("T", latin2greek.get("T"));
                availableSymbols.put("U", latin2greek.get("U"));
                availableSymbols.put("PH", latin2greek.get("PH"));
                availableSymbols.put("CH", latin2greek.get("CH"));
                availableSymbols.put("PS", latin2greek.get("PS"));
                availableSymbols.put("OM", latin2greek.get("OM"));

            } else {
                throw new IllegalArgumentException("Unknown level: " + selectedLevel);
            }
        }
        return availableSymbols;
    }

}
