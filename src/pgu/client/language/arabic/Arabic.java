package pgu.client.language.arabic;

import java.util.ArrayList;

import pgu.client.language.Alphabet;
import pgu.client.utils.guava.HashBiMap;
import pgu.client.utils.guava.Lists;

public enum Arabic implements Alphabet {
    INSTANCE;

    private final HashBiMap<String, String> latin2arabic = HashBiMap.create();
    private final ArrayList<String> availableLevels = Lists.newArrayList( //
            GRP_1, GRP_2, GRP_3, GRP_4 //
            //            GRP_H, GRP_M, GRP_Y, GRP_R, GRP_W //
            //            , GRP_KY, GRP_SH, GRP_CH //
            );

    @Override
    public ArrayList<String> availableLevels() {
        return availableLevels;
    }

    private static final String GRP_1 = "a - kh";
    private static final String GRP_2 = "d - DH";
    private static final String GRP_3 = "- - l";
    private static final String GRP_4 = "m - y";

    @Override
    public final HashBiMap<String, String> availableSymbols(final ArrayList<String> selectedLevels) {
        final HashBiMap<String, String> availableSymbols = HashBiMap.create();

        for (final String selectedLevel : selectedLevels) {

            if (GRP_1.equals(selectedLevel)) {
                availableSymbols.put(a, latin2arabic.get(a));
                availableSymbols.put(b, latin2arabic.get(b));
                availableSymbols.put(t, latin2arabic.get(t));
                availableSymbols.put(th, latin2arabic.get(th));
                availableSymbols.put(j, latin2arabic.get(j));
                availableSymbols.put(H, latin2arabic.get(H));
                availableSymbols.put(kh, latin2arabic.get(kh));

            } else if (GRP_2.equals(selectedLevel)) {
                availableSymbols.put(d, latin2arabic.get(d));
                availableSymbols.put(dh, latin2arabic.get(dh));
                availableSymbols.put(r, latin2arabic.get(r));
                availableSymbols.put(z, latin2arabic.get(z));
                availableSymbols.put(s, latin2arabic.get(s));
                availableSymbols.put(sh, latin2arabic.get(sh));
                availableSymbols.put(S, latin2arabic.get(S));
                availableSymbols.put(D, latin2arabic.get(D));
                availableSymbols.put(T, latin2arabic.get(T));
                availableSymbols.put(DH, latin2arabic.get(DH));

            } else if (GRP_3.equals(selectedLevel)) {
                availableSymbols.put(nothing, latin2arabic.get(nothing));
                availableSymbols.put(gh, latin2arabic.get(gh));
                availableSymbols.put(f, latin2arabic.get(f));
                availableSymbols.put(q, latin2arabic.get(q));
                availableSymbols.put(k, latin2arabic.get(k));
                availableSymbols.put(l, latin2arabic.get(l));

            } else if (GRP_4.equals(selectedLevel)) {
                availableSymbols.put(m, latin2arabic.get(m));
                availableSymbols.put(n, latin2arabic.get(n));
                availableSymbols.put(h, latin2arabic.get(h));
                availableSymbols.put(w, latin2arabic.get(w));
                availableSymbols.put(y, latin2arabic.get(y));
                availableSymbols.put(la, latin2arabic.get(la));

                //            } else if (GRP_N.equals(selectedLevel)) {
                //                availableSymbols.put(NA, latin2arabic.get(NA));
                //                availableSymbols.put(NI, latin2arabic.get(NI));
                //                availableSymbols.put(NU, latin2arabic.get(NU));
                //                availableSymbols.put(NE, latin2arabic.get(NE));
                //                availableSymbols.put(NO, latin2arabic.get(NO));
                //
                //            } else if (GRP_H.equals(selectedLevel)) {
                //                availableSymbols.put(HA, latin2arabic.get(HA));
                //                availableSymbols.put(HI, latin2arabic.get(HI));
                //                availableSymbols.put(FU, latin2arabic.get(FU));
                //                availableSymbols.put(HE, latin2arabic.get(HE));
                //                availableSymbols.put(HO, latin2arabic.get(HO));
                //                availableSymbols.put(BA, latin2hiragana.get(BA));
                //                availableSymbols.put(BI, latin2hiragana.get(BI));
                //                availableSymbols.put(BU, latin2hiragana.get(BU));
                //                availableSymbols.put(BE, latin2hiragana.get(BE));
                //                availableSymbols.put(BO, latin2hiragana.get(BO));
                //                availableSymbols.put(PA, latin2hiragana.get(PA));
                //                availableSymbols.put(PI, latin2hiragana.get(PI));
                //                availableSymbols.put(PU, latin2hiragana.get(PU));
                //                availableSymbols.put(PE, latin2hiragana.get(PE));
                //                availableSymbols.put(PO, latin2hiragana.get(PO));

                //            } else if (GRP_M.equals(selectedLevel)) {
                //                availableSymbols.put(MA, latin2arabic.get(MA));
                //                availableSymbols.put(MI, latin2arabic.get(MI));
                //                availableSymbols.put(MU, latin2arabic.get(MU));
                //                availableSymbols.put(ME, latin2arabic.get(ME));
                //                availableSymbols.put(MO, latin2arabic.get(MO));
                //
                //            } else if (GRP_Y.equals(selectedLevel)) {
                //                availableSymbols.put(YA, latin2arabic.get(YA));
                //                availableSymbols.put(YU, latin2arabic.get(YU));
                //                availableSymbols.put(YO, latin2arabic.get(YO));
                //
                //            } else if (GRP_R.equals(selectedLevel)) {
                //                availableSymbols.put(RA, latin2arabic.get(RA));
                //                availableSymbols.put(RI, latin2arabic.get(RI));
                //                availableSymbols.put(RU, latin2arabic.get(RU));
                //                availableSymbols.put(RE, latin2arabic.get(RE));
                //                availableSymbols.put(RO, latin2arabic.get(RO));
                //
                //            } else if (GRP_W.equals(selectedLevel)) {
                //                availableSymbols.put(WA, latin2arabic.get(WA));
                //                availableSymbols.put(WI, latin2arabic.get(WI));
                //                availableSymbols.put(VU, latin2arabic.get(VU));
                //                availableSymbols.put(WE, latin2arabic.get(WE));
                //                availableSymbols.put(WO, latin2arabic.get(WO));
                //
                //            } else if (GRP_KY.equals(selectedLevel)) {
                //                availableSymbols.put(KYA, latin2hiragana.get(KYA));
                //                availableSymbols.put(KYU, latin2hiragana.get(KYU));
                //                availableSymbols.put(KYO, latin2hiragana.get(KYO));
                //                availableSymbols.put(NYA, latin2hiragana.get(NYA));
                //                availableSymbols.put(NYU, latin2hiragana.get(NYU));
                //                availableSymbols.put(NYO, latin2hiragana.get(NYO));
                //                availableSymbols.put(RYA, latin2hiragana.get(RYA));
                //                availableSymbols.put(RYU, latin2hiragana.get(RYU));
                //                availableSymbols.put(RYO, latin2hiragana.get(RYO));
                //                availableSymbols.put(BYA, latin2hiragana.get(BYA));
                //                availableSymbols.put(BYU, latin2hiragana.get(BYU));
                //                availableSymbols.put(BYO, latin2hiragana.get(BYO));

                //            } else if (GRP_SH.equals(selectedLevel)) {
                //                availableSymbols.put(SHA, latin2hiragana.get(SHA));
                //                availableSymbols.put(SHU, latin2hiragana.get(SHU));
                //                availableSymbols.put(SHO, latin2hiragana.get(SHO));
                //                availableSymbols.put(HYA, latin2hiragana.get(HYA));
                //                availableSymbols.put(HYU, latin2hiragana.get(HYU));
                //                availableSymbols.put(HYO, latin2hiragana.get(HYO));
                //                availableSymbols.put(GYA, latin2hiragana.get(GYA));
                //                availableSymbols.put(GYU, latin2hiragana.get(GYU));
                //                availableSymbols.put(GYO, latin2hiragana.get(GYO));
                //                availableSymbols.put(PYA, latin2hiragana.get(PYA));
                //                availableSymbols.put(PYU, latin2hiragana.get(PYU));
                //                availableSymbols.put(PYO, latin2hiragana.get(PYO));

                //            } else if (GRP_CH.equals(selectedLevel)) {
                //                availableSymbols.put(CHA, latin2hiragana.get(CHA));
                //                availableSymbols.put(CHU, latin2hiragana.get(CHU));
                //                availableSymbols.put(CHO, latin2hiragana.get(CHO));
                //                availableSymbols.put(MYA, latin2hiragana.get(MYA));
                //                availableSymbols.put(MYU, latin2hiragana.get(MYU));
                //                availableSymbols.put(MYO, latin2hiragana.get(MYO));
                //                availableSymbols.put(JA, latin2hiragana.get(JA));
                //                availableSymbols.put(JU, latin2hiragana.get(JU));
                //                availableSymbols.put(JO, latin2hiragana.get(JO));

            } else {
                throw new IllegalArgumentException("Unknown level: " + selectedLevel);
            }
        }

        return availableSymbols;
    }

    //    private static final String GRP_H = "H - B - P";
    //    private static final String GRP_M = "M";
    //    private static final String GRP_Y = "Y";
    //    private static final String GRP_R = "R";
    //    private static final String GRP_W = "W";
    //    private static final String GRP_KY = "KY-NRB";
    //    private static final String GRP_SH = "SH-HGP";
    //    private static final String GRP_CH = "CH-MJ";

    private final static String a = "a";
    private final static String b = "b";
    private final static String t = "t";
    private final static String th = "th";
    private final static String j = "j";
    private final static String H = "H";
    private final static String kh = "kh";
    private final static String d = "d";
    private final static String dh = "dh";
    private final static String r = "r";
    private final static String z = "z";
    private final static String s = "s";
    private final static String sh = "sh";
    private final static String S = "S";
    private final static String D = "D";
    private final static String T = "T";
    private final static String DH = "DH";
    private final static String nothing = "-";
    private final static String gh = "gh";
    private final static String f = "f";
    private final static String q = "q";
    private final static String k = "k";
    private final static String l = "l";
    private final static String m = "m";
    private final static String n = "n";
    private final static String h = "h";
    private final static String w = "w";
    private final static String y = "y";
    private final static String la = "la";

    {
        latin2arabic.put(a, "&#65165;");
        latin2arabic.put(b, "&#65167;");
        latin2arabic.put(t, "&#65173;");
        latin2arabic.put(th, "&#65177;");
        latin2arabic.put(j, "&#65181;");
        latin2arabic.put(H, "&#65185;");
        latin2arabic.put(kh, "&#65189;");
        latin2arabic.put(d, "&#65193;");
        latin2arabic.put(dh, "&#65195;");
        latin2arabic.put(r, "&#65197;");
        latin2arabic.put(z, "&#65199;");
        latin2arabic.put(s, "&#65201;");
        latin2arabic.put(sh, "&#65205;");
        latin2arabic.put(S, "&#65209;");
        latin2arabic.put(D, "&#65213;");
        latin2arabic.put(T, "&#65217;");
        latin2arabic.put(DH, "&#65221;");
        latin2arabic.put(nothing, "&#65225;");
        latin2arabic.put(gh, "&#65229;");
        latin2arabic.put(f, "&#65233;");
        latin2arabic.put(q, "&#65237;");
        latin2arabic.put(k, "&#65241;");
        latin2arabic.put(l, "&#65245;");
        latin2arabic.put(m, "&#65249;");
        latin2arabic.put(n, "&#65253;");
        latin2arabic.put(h, "&#65257;");
        latin2arabic.put(w, "&#65261;");
        latin2arabic.put(y, "&#65265;");
        latin2arabic.put(la, "&#65275;");
    }

    private String tsl(final String... alphas) {
        final StringBuilder sb = new StringBuilder();
        for (final String alpha : alphas) {
            sb.append(latin2arabic.get(alpha));
        }
        return sb.toString();
    }

}
