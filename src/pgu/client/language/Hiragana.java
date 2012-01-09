package pgu.client.language;

import java.util.Arrays;
import java.util.List;

import pgu.client.utils.guava.HashBiMap;

public class Hiragana {

    private static final HashBiMap<String, String> latin2hiragana = HashBiMap.create();
    private static final List<String> availableLevels;

    public static List<String> availableLevels() {
        return availableLevels;
    }

    public static final HashBiMap<String, String> availableSymbols(final List<String> selectedLevels) {
        final HashBiMap<String, String> availableSymbols = HashBiMap.create();

        for (final String selectedLevel : selectedLevels) {

            if (A.equals(selectedLevel)) {
                availableSymbols.put("A", latin2hiragana.get("A"));
                availableSymbols.put("I", latin2hiragana.get("I"));
                availableSymbols.put("U", latin2hiragana.get("U"));
                availableSymbols.put("E", latin2hiragana.get("E"));
                availableSymbols.put("O", latin2hiragana.get("O"));
                availableSymbols.put("N", latin2hiragana.get("N"));

            }
            if (K.equals(selectedLevel)) {
                availableSymbols.put("KA", latin2hiragana.get("KA"));
                availableSymbols.put("KI", latin2hiragana.get("KI"));
                availableSymbols.put("KU", latin2hiragana.get("KU"));
                availableSymbols.put("KE", latin2hiragana.get("KE"));
                availableSymbols.put("KO", latin2hiragana.get("KO"));
                availableSymbols.put("GA", latin2hiragana.get("GA"));
                availableSymbols.put("GI", latin2hiragana.get("GI"));
                availableSymbols.put("GU", latin2hiragana.get("GU"));
                availableSymbols.put("GE", latin2hiragana.get("GE"));
                availableSymbols.put("GO", latin2hiragana.get("GO"));
            }
            if (S.equals(selectedLevel)) {
                availableSymbols.put("SA", latin2hiragana.get("SA"));
                availableSymbols.put("SHI", latin2hiragana.get("SHI"));
                availableSymbols.put("SU", latin2hiragana.get("SU"));
                availableSymbols.put("SE", latin2hiragana.get("SE"));
                availableSymbols.put("SO", latin2hiragana.get("SO"));
                availableSymbols.put("ZA", latin2hiragana.get("ZA"));
                availableSymbols.put("ZI", latin2hiragana.get("ZI"));
                availableSymbols.put("ZU", latin2hiragana.get("ZU"));
                availableSymbols.put("ZE", latin2hiragana.get("ZE"));
                availableSymbols.put("ZO", latin2hiragana.get("ZO"));
            }
            if (T.equals(selectedLevel)) {
                availableSymbols.put("TA", latin2hiragana.get("TA"));
                availableSymbols.put("CHI", latin2hiragana.get("CHI"));
                availableSymbols.put("TSU", latin2hiragana.get("TSU"));
                availableSymbols.put("TE", latin2hiragana.get("TE"));
                availableSymbols.put("TO", latin2hiragana.get("TO"));
                availableSymbols.put("DA", latin2hiragana.get("DA"));
                availableSymbols.put("DI", latin2hiragana.get("DI"));
                availableSymbols.put("DU", latin2hiragana.get("DU"));
                availableSymbols.put("DE", latin2hiragana.get("DE"));
                availableSymbols.put("DO", latin2hiragana.get("DO"));
            }
            if (N.equals(selectedLevel)) {
                availableSymbols.put("NA", latin2hiragana.get("NA"));
                availableSymbols.put("NI", latin2hiragana.get("NI"));
                availableSymbols.put("NU", latin2hiragana.get("NU"));
                availableSymbols.put("NE", latin2hiragana.get("NE"));
                availableSymbols.put("NO", latin2hiragana.get("NO"));
            }
            if (H.equals(selectedLevel)) {
                availableSymbols.put("HA", latin2hiragana.get("HA"));
                availableSymbols.put("HI", latin2hiragana.get("HI"));
                availableSymbols.put("FU", latin2hiragana.get("FU"));
                availableSymbols.put("HE", latin2hiragana.get("HE"));
                availableSymbols.put("HO", latin2hiragana.get("HO"));
                availableSymbols.put("BA", latin2hiragana.get("BA"));
                availableSymbols.put("BI", latin2hiragana.get("BI"));
                availableSymbols.put("BU", latin2hiragana.get("BU"));
                availableSymbols.put("BE", latin2hiragana.get("BE"));
                availableSymbols.put("BO", latin2hiragana.get("BO"));
                availableSymbols.put("PA", latin2hiragana.get("PA"));
                availableSymbols.put("PI", latin2hiragana.get("PI"));
                availableSymbols.put("PU", latin2hiragana.get("PU"));
                availableSymbols.put("PE", latin2hiragana.get("PE"));
                availableSymbols.put("PO", latin2hiragana.get("PO"));
            }
            if (M.equals(selectedLevel)) {
                availableSymbols.put("MA", latin2hiragana.get("MA"));
                availableSymbols.put("MI", latin2hiragana.get("MI"));
                availableSymbols.put("MU", latin2hiragana.get("MU"));
                availableSymbols.put("ME", latin2hiragana.get("ME"));
                availableSymbols.put("MO", latin2hiragana.get("MO"));
            }
            if (Y.equals(selectedLevel)) {
                availableSymbols.put("YA", latin2hiragana.get("YA"));
                availableSymbols.put("YU", latin2hiragana.get("YU"));
                availableSymbols.put("YO", latin2hiragana.get("YO"));
            }
            if (R.equals(selectedLevel)) {
                availableSymbols.put("RA", latin2hiragana.get("RA"));
                availableSymbols.put("RI", latin2hiragana.get("RI"));
                availableSymbols.put("RU", latin2hiragana.get("RU"));
                availableSymbols.put("RE", latin2hiragana.get("RE"));
                availableSymbols.put("RO", latin2hiragana.get("RO"));
            }
            if (W.equals(selectedLevel)) {
                availableSymbols.put("WA", latin2hiragana.get("WA"));
                availableSymbols.put("WI", latin2hiragana.get("WI"));
                availableSymbols.put("VU", latin2hiragana.get("VU"));
                availableSymbols.put("WE", latin2hiragana.get("WE"));
                availableSymbols.put("WO", latin2hiragana.get("WO"));
            }
        }

        return availableSymbols;
    }

    private static final String A = "0 -";
    private static final String K = "1 k";
    private static final String S = "2 s";
    private static final String T = "3 t";
    private static final String N = "4 n";
    private static final String H = "5 h";
    private static final String M = "6 m";
    private static final String Y = "7 y";
    private static final String R = "8 r";
    private static final String W = "9 w";

    static {
        availableLevels = Arrays.asList( //
                A, K, S, T, N, //
                H, M, Y, R, W);

        latin2hiragana.put("A", "&#x3042;");
        latin2hiragana.put("I", "&#x3044;");
        latin2hiragana.put("U", "&#x3046;");
        latin2hiragana.put("E", "&#x3048;");
        latin2hiragana.put("O", "&#x304A;");
        latin2hiragana.put("KA", "&#x304B;");
        latin2hiragana.put("GA", "&#x304C;");
        latin2hiragana.put("KI", "&#x304D;");
        latin2hiragana.put("GI", "&#x304E;");
        latin2hiragana.put("KU", "&#x304F;");
        latin2hiragana.put("GU", "&#x3050;");
        latin2hiragana.put("KE", "&#x3051;");
        latin2hiragana.put("GE", "&#x3052;");
        latin2hiragana.put("KO", "&#x3053;");
        latin2hiragana.put("GO", "&#x3054;");
        latin2hiragana.put("SA", "&#x3055;");
        latin2hiragana.put("ZA", "&#x3056;");
        latin2hiragana.put("SHI", "&#x3057;");
        latin2hiragana.put("ZI", "&#x3058;");
        latin2hiragana.put("SU", "&#x3059;");
        latin2hiragana.put("ZU", "&#x305A;");
        latin2hiragana.put("SE", "&#x305B;");
        latin2hiragana.put("ZE", "&#x305C;");
        latin2hiragana.put("SO", "&#x305D;");
        latin2hiragana.put("ZO", "&#x305E;");
        latin2hiragana.put("TA", "&#x305F;");
        latin2hiragana.put("DA", "&#x3060;");
        latin2hiragana.put("CHI", "&#x3061;");
        latin2hiragana.put("DI", "&#x3062;");
        latin2hiragana.put("TSU", "&#x3064;");
        latin2hiragana.put("DU", "&#x3065;");
        latin2hiragana.put("TE", "&#x3066;");
        latin2hiragana.put("DE", "&#x3067;");
        latin2hiragana.put("TO", "&#x3068;");
        latin2hiragana.put("DO", "&#x3069;");
        latin2hiragana.put("NA", "&#x306A;");
        latin2hiragana.put("NI", "&#x306B;");
        latin2hiragana.put("NU", "&#x306C;");
        latin2hiragana.put("NE", "&#x306D;");
        latin2hiragana.put("NO", "&#x306E;");
        latin2hiragana.put("HA", "&#x306F;");
        latin2hiragana.put("BA", "&#x3070;");
        latin2hiragana.put("PA", "&#x3071;");
        latin2hiragana.put("HI", "&#x3072;");
        latin2hiragana.put("BI", "&#x3073;");
        latin2hiragana.put("PI", "&#x3074;");
        latin2hiragana.put("FU", "&#x3075;");
        latin2hiragana.put("BU", "&#x3076;");
        latin2hiragana.put("PU", "&#x3077;");
        latin2hiragana.put("HE", "&#x3078;");
        latin2hiragana.put("BE", "&#x3079;");
        latin2hiragana.put("PE", "&#x307A;");
        latin2hiragana.put("HO", "&#x307B;");
        latin2hiragana.put("BO", "&#x307C;");
        latin2hiragana.put("PO", "&#x307D;");
        latin2hiragana.put("MA", "&#x307E;");
        latin2hiragana.put("MI", "&#x307F;");
        latin2hiragana.put("MU", "&#x3080;");
        latin2hiragana.put("ME", "&#x3081;");
        latin2hiragana.put("MO", "&#x3082;");
        latin2hiragana.put("YA", "&#x3084;");
        latin2hiragana.put("YU", "&#x3086;");
        latin2hiragana.put("YO", "&#x3088;");
        latin2hiragana.put("RA", "&#x3089;");
        latin2hiragana.put("RI", "&#x308A;");
        latin2hiragana.put("RU", "&#x308B;");
        latin2hiragana.put("RE", "&#x308C;");
        latin2hiragana.put("RO", "&#x308D;");
        latin2hiragana.put("WA", "&#x308F;");
        latin2hiragana.put("WI", "&#x3090;");
        latin2hiragana.put("WE", "&#x3091;");
        latin2hiragana.put("WO", "&#x3092;");
        latin2hiragana.put("N", "&#x3093;");
        latin2hiragana.put("VU", "&#x3094;");

    }
}