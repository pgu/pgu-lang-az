package pgu.client.language.korean;

import java.util.ArrayList;

import pgu.client.language.Alphabet;
import pgu.client.utils.guava.HashBiMap;
import pgu.client.utils.guava.Lists;

public enum Korean implements Alphabet {
    INSTANCE;

    private final HashBiMap<String, String> latin2korean    = HashBiMap.create();
    private final ArrayList<String>         availableLevels = Lists.newArrayList( //
            GRP_1, GRP_2, GRP_3, GRP_4 //
            // GRP_H, GRP_M, GRP_Y, GRP_R, GRP_W //
            // , GRP_KY, GRP_SH, GRP_CH //
            );

    @Override
    public ArrayList<String> availableLevels() {
        return availableLevels;
    }

    private static final String GRP_1 = "g - ch";
    private static final String GRP_2 = "k - jj";
    private static final String GRP_3 = "a - eu";
    private static final String GRP_4 = "i - ui";

    @Override
    public final HashBiMap<String, String> availableSymbols(final ArrayList<String> selectedLevels) {
        final HashBiMap<String, String> availableSymbols = HashBiMap.create();

        for (final String selectedLevel : selectedLevels) {

            if (GRP_1.equals(selectedLevel)) {
                availableSymbols.put(g, latin2korean.get(g));
                availableSymbols.put(n, latin2korean.get(n));
                availableSymbols.put(d, latin2korean.get(d));
                availableSymbols.put(r, latin2korean.get(r));
                availableSymbols.put(m, latin2korean.get(m));
                availableSymbols.put(b, latin2korean.get(b));
                availableSymbols.put(s, latin2korean.get(s));
                availableSymbols.put(ng, latin2korean.get(ng));
                availableSymbols.put(j, latin2korean.get(j));
                availableSymbols.put(ch, latin2korean.get(ch));

            } else if (GRP_2.equals(selectedLevel)) {
                availableSymbols.put(k, latin2korean.get(k));
                availableSymbols.put(t, latin2korean.get(t));
                availableSymbols.put(p, latin2korean.get(p));
                availableSymbols.put(h, latin2korean.get(h));
                availableSymbols.put(kk, latin2korean.get(kk));
                availableSymbols.put(tt, latin2korean.get(tt));
                availableSymbols.put(pp, latin2korean.get(pp));
                availableSymbols.put(ss, latin2korean.get(ss));
                availableSymbols.put(jj, latin2korean.get(jj));

            } else if (GRP_3.equals(selectedLevel)) {
                availableSymbols.put(a, latin2korean.get(a));
                availableSymbols.put(ya, latin2korean.get(ya));
                availableSymbols.put(eo, latin2korean.get(eo));
                availableSymbols.put(yeo, latin2korean.get(yeo));
                availableSymbols.put(o, latin2korean.get(o));
                availableSymbols.put(yo, latin2korean.get(yo));
                availableSymbols.put(u, latin2korean.get(u));
                availableSymbols.put(yu, latin2korean.get(yu));
                availableSymbols.put(eu, latin2korean.get(eu));

            } else if (GRP_4.equals(selectedLevel)) {
                availableSymbols.put(i, latin2korean.get(i));
                availableSymbols.put(ae, latin2korean.get(ae));
                availableSymbols.put(yae, latin2korean.get(yae));
                availableSymbols.put(e, latin2korean.get(e));
                availableSymbols.put(ye, latin2korean.get(ye));
                availableSymbols.put(wa, latin2korean.get(wa));
                availableSymbols.put(wae, latin2korean.get(wae));
                availableSymbols.put(oe, latin2korean.get(oe));
                availableSymbols.put(wo, latin2korean.get(wo));
                availableSymbols.put(we, latin2korean.get(we));
                availableSymbols.put(wi, latin2korean.get(wi));
                availableSymbols.put(ui, latin2korean.get(ui));

            } else {
                throw new IllegalArgumentException("Unknown level: " + selectedLevel);
            }
        }

        return availableSymbols;
    }

    private final static String g   = "g";
    private final static String n   = "n";
    private final static String d   = "d";
    private final static String r   = "r";
    private final static String m   = "m";
    private final static String b   = "b";
    private final static String s   = "s";
    private final static String ng  = "ng";
    private final static String j   = "j";
    private final static String ch  = "ch";
    private final static String k   = "k";
    private final static String t   = "t";
    private final static String p   = "p";
    private final static String h   = "h";
    private final static String kk  = "kk";
    private final static String tt  = "tt";
    private final static String pp  = "pp";
    private final static String ss  = "ss";
    private final static String jj  = "jj";
    private final static String a   = "a";
    private final static String ya  = "ya";
    private final static String eo  = "eo";
    private final static String yeo = "yeo";
    private final static String o   = "o";
    private final static String yo  = "yo";
    private final static String u   = "u";
    private final static String yu  = "yu";
    private final static String eu  = "eu";
    private final static String i   = "i";
    private final static String ae  = "ae";
    private final static String yae = "yae";
    private final static String e   = "e";
    private final static String ye  = "ye";
    private final static String wa  = "wa";
    private final static String wae = "wae";
    private final static String oe  = "oe";
    private final static String wo  = "wo";
    private final static String we  = "we";
    private final static String wi  = "wi";
    private final static String ui  = "ui";

    {
        latin2korean.put(g, "&#12593;");
        latin2korean.put(n, "&#12596;");
        latin2korean.put(d, "&#12599;");
        latin2korean.put(r, "&#12601;");
        latin2korean.put(m, "&#12609;");
        latin2korean.put(b, "&#12610;");
        latin2korean.put(s, "&#12613;");
        latin2korean.put(ng, "&#12615;");
        latin2korean.put(j, "&#12616;");
        latin2korean.put(ch, "&#12618;");
        latin2korean.put(k, "&#12619;");
        latin2korean.put(t, "&#12620;");
        latin2korean.put(p, "&#12621;");
        latin2korean.put(h, "&#12622;");
        latin2korean.put(kk, "&#12594;");
        latin2korean.put(tt, "&#12600;");
        latin2korean.put(pp, "&#12611;");
        latin2korean.put(ss, "&#12614;");
        latin2korean.put(jj, "&#12617;");
        latin2korean.put(a, "&#12623;");
        latin2korean.put(ya, "&#12625;");
        latin2korean.put(eo, "&#12627;");
        latin2korean.put(yeo, "&#12629;");
        latin2korean.put(o, "&#12631;");
        latin2korean.put(yo, "&#12635;");
        latin2korean.put(u, "&#12636;");
        latin2korean.put(yu, "&#12640;");
        latin2korean.put(eu, "&#12641;");
        latin2korean.put(i, "&#12643;");
        latin2korean.put(ae, "&#12624;");
        latin2korean.put(yae, "&#12626;");
        latin2korean.put(e, "&#12628;");
        latin2korean.put(ye, "&#12630;");
        latin2korean.put(wa, "&#12632;");
        latin2korean.put(wae, "&#12633;");
        latin2korean.put(oe, "&#12634;");
        latin2korean.put(wo, "&#12637;");
        latin2korean.put(we, "&#12638;");
        latin2korean.put(wi, "&#12639;");
        latin2korean.put(ui, "&#12642;");
    }

    private String tsl(final String... alphas) {
        final StringBuilder sb = new StringBuilder();
        for (final String alpha : alphas) {
            sb.append(latin2korean.get(alpha));
        }
        return sb.toString();
    }

    @Override
    public HashBiMap<String, String> getAllLatin2Symbol() {
        return latin2korean;
    }

}
