package pgu.client.language;

import com.google.appengine.repackaged.com.google.common.collect.HashBiMap;

public class Hiragana {

    private static final HashBiMap<String, String> latin2hiragana = HashBiMap.create();

    public static String toLatin(final String hiragana) {
        initMap();
        return latin2hiragana.get(hiragana);
    }

    public static String toHiragana(final String latin) {
        initMap();
        return latin2hiragana.inverse().get(latin);
    }

    private static void initMap() {
        if (latin2hiragana.isEmpty()) {
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
}
