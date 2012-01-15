package pgu.client.language;

import java.util.ArrayList;

import pgu.client.utils.guava.HashBiMap;
import pgu.client.utils.guava.Lists;

public enum Hiragana implements HasLevels {
    INSTANCE;

    private final HashBiMap<String, String> latin2hiragana = HashBiMap.create();
    private final ArrayList<String> availableLevels = Lists.newArrayList( //
            GRP_A, GRP_K, GRP_S, GRP_T, GRP_N, //
            GRP_H, GRP_M, GRP_Y, GRP_R, GRP_W, //
            GRP_KY, GRP_SH, GRP_CH);

    @Override
    public ArrayList<String> availableLevels() {
        return availableLevels;
    }

    @Override
    public final HashBiMap<String, String> availableSymbols(final ArrayList<String> selectedLevels) {
        final HashBiMap<String, String> availableSymbols = HashBiMap.create();

        for (final String selectedLevel : selectedLevels) {

            if (GRP_A.equals(selectedLevel)) {
                availableSymbols.put(A, latin2hiragana.get(A));
                availableSymbols.put(I, latin2hiragana.get(I));
                availableSymbols.put(U, latin2hiragana.get(U));
                availableSymbols.put(E, latin2hiragana.get(E));
                availableSymbols.put(O, latin2hiragana.get(O));
                availableSymbols.put(N, latin2hiragana.get(N));

            } else if (GRP_K.equals(selectedLevel)) {
                availableSymbols.put(KA, latin2hiragana.get(KA));
                availableSymbols.put(KI, latin2hiragana.get(KI));
                availableSymbols.put(KU, latin2hiragana.get(KU));
                availableSymbols.put(KE, latin2hiragana.get(KE));
                availableSymbols.put(KO, latin2hiragana.get(KO));
                availableSymbols.put(GA, latin2hiragana.get(GA));
                availableSymbols.put(GI, latin2hiragana.get(GI));
                availableSymbols.put(GU, latin2hiragana.get(GU));
                availableSymbols.put(GE, latin2hiragana.get(GE));
                availableSymbols.put(GO, latin2hiragana.get(GO));

            } else if (GRP_S.equals(selectedLevel)) {
                availableSymbols.put(SA, latin2hiragana.get(SA));
                availableSymbols.put(SHI, latin2hiragana.get(SHI));
                availableSymbols.put(SU, latin2hiragana.get(SU));
                availableSymbols.put(SE, latin2hiragana.get(SE));
                availableSymbols.put(SO, latin2hiragana.get(SO));
                availableSymbols.put(ZA, latin2hiragana.get(ZA));
                availableSymbols.put(S_JI, latin2hiragana.get(S_JI));
                availableSymbols.put(S_ZU, latin2hiragana.get(S_ZU));
                availableSymbols.put(ZE, latin2hiragana.get(ZE));
                availableSymbols.put(ZO, latin2hiragana.get(ZO));

            } else if (GRP_T.equals(selectedLevel)) {
                availableSymbols.put(TA, latin2hiragana.get(TA));
                availableSymbols.put(CHI, latin2hiragana.get(CHI));
                availableSymbols.put(TSU, latin2hiragana.get(TSU));
                availableSymbols.put(TE, latin2hiragana.get(TE));
                availableSymbols.put(TO, latin2hiragana.get(TO));
                availableSymbols.put(DA, latin2hiragana.get(DA));
                availableSymbols.put(T_JI, latin2hiragana.get(T_JI));
                availableSymbols.put(T_ZU, latin2hiragana.get(T_ZU));
                availableSymbols.put(DE, latin2hiragana.get(DE));
                availableSymbols.put(DO, latin2hiragana.get(DO));

            } else if (GRP_N.equals(selectedLevel)) {
                availableSymbols.put(NA, latin2hiragana.get(NA));
                availableSymbols.put(NI, latin2hiragana.get(NI));
                availableSymbols.put(NU, latin2hiragana.get(NU));
                availableSymbols.put(NE, latin2hiragana.get(NE));
                availableSymbols.put(NO, latin2hiragana.get(NO));

            } else if (GRP_H.equals(selectedLevel)) {
                availableSymbols.put(HA, latin2hiragana.get(HA));
                availableSymbols.put(HI, latin2hiragana.get(HI));
                availableSymbols.put(FU, latin2hiragana.get(FU));
                availableSymbols.put(HE, latin2hiragana.get(HE));
                availableSymbols.put(HO, latin2hiragana.get(HO));
                availableSymbols.put(BA, latin2hiragana.get(BA));
                availableSymbols.put(BI, latin2hiragana.get(BI));
                availableSymbols.put(BU, latin2hiragana.get(BU));
                availableSymbols.put(BE, latin2hiragana.get(BE));
                availableSymbols.put(BO, latin2hiragana.get(BO));
                availableSymbols.put(PA, latin2hiragana.get(PA));
                availableSymbols.put(PI, latin2hiragana.get(PI));
                availableSymbols.put(PU, latin2hiragana.get(PU));
                availableSymbols.put(PE, latin2hiragana.get(PE));
                availableSymbols.put(PO, latin2hiragana.get(PO));

            } else if (GRP_M.equals(selectedLevel)) {
                availableSymbols.put(MA, latin2hiragana.get(MA));
                availableSymbols.put(MI, latin2hiragana.get(MI));
                availableSymbols.put(MU, latin2hiragana.get(MU));
                availableSymbols.put(ME, latin2hiragana.get(ME));
                availableSymbols.put(MO, latin2hiragana.get(MO));

            } else if (GRP_Y.equals(selectedLevel)) {
                availableSymbols.put(YA, latin2hiragana.get(YA));
                availableSymbols.put(YU, latin2hiragana.get(YU));
                availableSymbols.put(YO, latin2hiragana.get(YO));

            } else if (GRP_R.equals(selectedLevel)) {
                availableSymbols.put(RA, latin2hiragana.get(RA));
                availableSymbols.put(RI, latin2hiragana.get(RI));
                availableSymbols.put(RU, latin2hiragana.get(RU));
                availableSymbols.put(RE, latin2hiragana.get(RE));
                availableSymbols.put(RO, latin2hiragana.get(RO));

            } else if (GRP_W.equals(selectedLevel)) {
                availableSymbols.put(WA, latin2hiragana.get(WA));
                availableSymbols.put(WI, latin2hiragana.get(WI));
                availableSymbols.put(VU, latin2hiragana.get(VU));
                availableSymbols.put(WE, latin2hiragana.get(WE));
                availableSymbols.put(WO, latin2hiragana.get(WO));

            } else if (GRP_KY.equals(selectedLevel)) {
                availableSymbols.put(KYA, latin2hiragana.get(KYA));
                availableSymbols.put(KYU, latin2hiragana.get(KYU));
                availableSymbols.put(KYO, latin2hiragana.get(KYO));
                availableSymbols.put(NYA, latin2hiragana.get(NYA));
                availableSymbols.put(NYU, latin2hiragana.get(NYU));
                availableSymbols.put(NYO, latin2hiragana.get(NYO));
                availableSymbols.put(RYA, latin2hiragana.get(RYA));
                availableSymbols.put(RYU, latin2hiragana.get(RYU));
                availableSymbols.put(RYO, latin2hiragana.get(RYO));
                availableSymbols.put(BYA, latin2hiragana.get(BYA));
                availableSymbols.put(BYU, latin2hiragana.get(BYU));
                availableSymbols.put(BYO, latin2hiragana.get(BYO));

            } else if (GRP_SH.equals(selectedLevel)) {
                availableSymbols.put(SHA, latin2hiragana.get(SHA));
                availableSymbols.put(SHU, latin2hiragana.get(SHU));
                availableSymbols.put(SHO, latin2hiragana.get(SHO));
                availableSymbols.put(HYA, latin2hiragana.get(HYA));
                availableSymbols.put(HYU, latin2hiragana.get(HYU));
                availableSymbols.put(HYO, latin2hiragana.get(HYO));
                availableSymbols.put(GYA, latin2hiragana.get(GYA));
                availableSymbols.put(GYU, latin2hiragana.get(GYU));
                availableSymbols.put(GYO, latin2hiragana.get(GYO));
                availableSymbols.put(PYA, latin2hiragana.get(PYA));
                availableSymbols.put(PYU, latin2hiragana.get(PYU));
                availableSymbols.put(PYO, latin2hiragana.get(PYO));

            } else if (GRP_CH.equals(selectedLevel)) {
                availableSymbols.put(CHA, latin2hiragana.get(CHA));
                availableSymbols.put(CHU, latin2hiragana.get(CHU));
                availableSymbols.put(CHO, latin2hiragana.get(CHO));
                availableSymbols.put(MYA, latin2hiragana.get(MYA));
                availableSymbols.put(MYU, latin2hiragana.get(MYU));
                availableSymbols.put(MYO, latin2hiragana.get(MYO));
                availableSymbols.put(JA, latin2hiragana.get(JA));
                availableSymbols.put(JU, latin2hiragana.get(JU));
                availableSymbols.put(JO, latin2hiragana.get(JO));

            } else {
                throw new IllegalArgumentException("Unknown level: " + selectedLevel);
            }
        }

        return availableSymbols;
    }

    private static final String GRP_A = "A";
    private static final String GRP_K = "K - G";
    private static final String GRP_S = "S - Z";
    private static final String GRP_T = "T - D";
    private static final String GRP_N = "N";
    private static final String GRP_H = "H - B - P";
    private static final String GRP_M = "M";
    private static final String GRP_Y = "Y";
    private static final String GRP_R = "R";
    private static final String GRP_W = "W";
    private static final String GRP_KY = "KY-NRB";
    private static final String GRP_SH = "SH-HGP";
    private static final String GRP_CH = "CH-MJ";

    private final static String A = "A";
    private final static String I = "I";
    private final static String U = "U";
    private final static String E = "E";
    private final static String O = "O";
    private final static String KA = "KA";
    private final static String GA = "GA";
    private final static String KI = "KI";
    private final static String GI = "GI";
    private final static String KU = "KU";
    private final static String GU = "GU";
    private final static String KE = "KE";
    private final static String GE = "GE";
    private final static String KO = "KO";
    private final static String GO = "GO";
    private final static String SA = "SA";
    private final static String ZA = "ZA";
    private final static String SHI = "SHI";
    private final static String S_JI = "(S)JI";
    private final static String SU = "SU";
    private final static String S_ZU = "(S)ZU";
    private final static String SE = "SE";
    private final static String ZE = "ZE";
    private final static String SO = "SO";
    private final static String ZO = "ZO";
    private final static String TA = "TA";
    private final static String DA = "DA";
    private final static String CHI = "CHI";
    private final static String T_JI = "(T)JI";
    private final static String TSU = "TSU";
    private final static String T_ZU = "(T)ZU";
    private final static String TE = "TE";
    private final static String DE = "DE";
    private final static String TO = "TO";
    private final static String DO = "DO";
    private final static String NA = "NA";
    private final static String NI = "NI";
    private final static String NU = "NU";
    private final static String NE = "NE";
    private final static String NO = "NO";
    private final static String HA = "HA";
    private final static String BA = "BA";
    private final static String PA = "PA";
    private final static String HI = "HI";
    private final static String BI = "BI";
    private final static String PI = "PI";
    private final static String FU = "FU";
    private final static String BU = "BU";
    private final static String PU = "PU";
    private final static String HE = "HE";
    private final static String BE = "BE";
    private final static String PE = "PE";
    private final static String HO = "HO";
    private final static String BO = "BO";
    private final static String PO = "PO";
    private final static String MA = "MA";
    private final static String MI = "MI";
    private final static String MU = "MU";
    private final static String ME = "ME";
    private final static String MO = "MO";
    private final static String YA = "YA";
    private final static String YU = "YU";
    private final static String YO = "YO";
    private final static String RA = "RA";
    private final static String RI = "RI";
    private final static String RU = "RU";
    private final static String RE = "RE";
    private final static String RO = "RO";
    private final static String WA = "WA";
    private final static String WI = "WI";
    private final static String VU = "VU";
    private final static String WE = "WE";
    private final static String WO = "WO";
    private final static String N = "N";
    private final static String KYA = "KYA";
    private final static String KYU = "KYU";
    private final static String KYO = "KYO";
    private final static String NYA = "NYA";
    private final static String NYU = "NYU";
    private final static String NYO = "NYO";
    private final static String RYA = "RYA";
    private final static String RYU = "RYU";
    private final static String RYO = "RYO";
    private final static String BYA = "BYA";
    private final static String BYU = "BYU";
    private final static String BYO = "BYO";
    private final static String SHA = "SHA";
    private final static String SHU = "SHU";
    private final static String SHO = "SHO";
    private final static String HYA = "HYA";
    private final static String HYU = "HYU";
    private final static String HYO = "HYO";
    private final static String GYA = "GYA";
    private final static String GYU = "GYU";
    private final static String GYO = "GYO";
    private final static String PYA = "PYA";
    private final static String PYU = "PYU";
    private final static String PYO = "PYO";
    private final static String CHA = "CHA";
    private final static String CHU = "CHU";
    private final static String CHO = "CHO";
    private final static String MYA = "MYA";
    private final static String MYU = "MYU";
    private final static String MYO = "MYO";
    private final static String JA = "JA";
    private final static String JU = "JU";
    private final static String JO = "JO";
    private final static String ya = "ya";
    private final static String yu = "yu";
    private final static String yo = "yo";

    {
        latin2hiragana.put(A, "&#x3042;");
        latin2hiragana.put(I, "&#x3044;");
        latin2hiragana.put(U, "&#x3046;");
        latin2hiragana.put(E, "&#x3048;");
        latin2hiragana.put(O, "&#x304A;");
        latin2hiragana.put(KA, "&#x304B;");
        latin2hiragana.put(GA, "&#x304C;");
        latin2hiragana.put(KI, "&#x304D;");
        latin2hiragana.put(GI, "&#x304E;");
        latin2hiragana.put(KU, "&#x304F;");
        latin2hiragana.put(GU, "&#x3050;");
        latin2hiragana.put(KE, "&#x3051;");
        latin2hiragana.put(GE, "&#x3052;");
        latin2hiragana.put(KO, "&#x3053;");
        latin2hiragana.put(GO, "&#x3054;");
        latin2hiragana.put(SA, "&#x3055;");
        latin2hiragana.put(ZA, "&#x3056;");
        latin2hiragana.put(SHI, "&#x3057;");
        latin2hiragana.put(S_JI, "&#x3058;");
        latin2hiragana.put(SU, "&#x3059;");
        latin2hiragana.put(S_ZU, "&#x305A;");
        latin2hiragana.put(SE, "&#x305B;");
        latin2hiragana.put(ZE, "&#x305C;");
        latin2hiragana.put(SO, "&#x305D;");
        latin2hiragana.put(ZO, "&#x305E;");
        latin2hiragana.put(TA, "&#x305F;");
        latin2hiragana.put(DA, "&#x3060;");
        latin2hiragana.put(CHI, "&#x3061;");
        latin2hiragana.put(T_JI, "&#x3062;");
        latin2hiragana.put(TSU, "&#x3064;");
        latin2hiragana.put(T_ZU, "&#x3065;");
        latin2hiragana.put(TE, "&#x3066;");
        latin2hiragana.put(DE, "&#x3067;");
        latin2hiragana.put(TO, "&#x3068;");
        latin2hiragana.put(DO, "&#x3069;");
        latin2hiragana.put(NA, "&#x306A;");
        latin2hiragana.put(NI, "&#x306B;");
        latin2hiragana.put(NU, "&#x306C;");
        latin2hiragana.put(NE, "&#x306D;");
        latin2hiragana.put(NO, "&#x306E;");
        latin2hiragana.put(HA, "&#x306F;");
        latin2hiragana.put(BA, "&#x3070;");
        latin2hiragana.put(PA, "&#x3071;");
        latin2hiragana.put(HI, "&#x3072;");
        latin2hiragana.put(BI, "&#x3073;");
        latin2hiragana.put(PI, "&#x3074;");
        latin2hiragana.put(FU, "&#x3075;");
        latin2hiragana.put(BU, "&#x3076;");
        latin2hiragana.put(PU, "&#x3077;");
        latin2hiragana.put(HE, "&#x3078;");
        latin2hiragana.put(BE, "&#x3079;");
        latin2hiragana.put(PE, "&#x307A;");
        latin2hiragana.put(HO, "&#x307B;");
        latin2hiragana.put(BO, "&#x307C;");
        latin2hiragana.put(PO, "&#x307D;");
        latin2hiragana.put(MA, "&#x307E;");
        latin2hiragana.put(MI, "&#x307F;");
        latin2hiragana.put(MU, "&#x3080;");
        latin2hiragana.put(ME, "&#x3081;");
        latin2hiragana.put(MO, "&#x3082;");
        latin2hiragana.put(YA, "&#x3084;");
        latin2hiragana.put(YU, "&#x3086;");
        latin2hiragana.put(YO, "&#x3088;");
        latin2hiragana.put(RA, "&#x3089;");
        latin2hiragana.put(RI, "&#x308A;");
        latin2hiragana.put(RU, "&#x308B;");
        latin2hiragana.put(RE, "&#x308C;");
        latin2hiragana.put(RO, "&#x308D;");
        latin2hiragana.put(WA, "&#x308F;");
        latin2hiragana.put(WI, "&#x3090;");
        latin2hiragana.put(VU, "&#x3094;");
        latin2hiragana.put(WE, "&#x3091;");
        latin2hiragana.put(WO, "&#x3092;");
        latin2hiragana.put(N, "&#x3093;");
        latin2hiragana.put(ya, "&#x3083;");
        latin2hiragana.put(yu, "&#x3085;");
        latin2hiragana.put(yo, "&#x3087;");
        latin2hiragana.put(KYA, tsl(KI, ya));
        latin2hiragana.put(KYU, tsl(KI, yu));
        latin2hiragana.put(KYO, tsl(KI, yo));
        latin2hiragana.put(NYA, tsl(NI, ya));
        latin2hiragana.put(NYU, tsl(NI, yu));
        latin2hiragana.put(NYO, tsl(NI, yo));
        latin2hiragana.put(RYA, tsl(RI, ya));
        latin2hiragana.put(RYU, tsl(RI, yu));
        latin2hiragana.put(RYO, tsl(RI, yo));
        latin2hiragana.put(BYA, tsl(BI, ya));
        latin2hiragana.put(BYU, tsl(BI, yu));
        latin2hiragana.put(BYO, tsl(BI, yo));
        latin2hiragana.put(SHA, tsl(SHI, ya));
        latin2hiragana.put(SHU, tsl(SHI, yu));
        latin2hiragana.put(SHO, tsl(SHI, yo));
        latin2hiragana.put(HYA, tsl(HI, ya));
        latin2hiragana.put(HYU, tsl(HI, yu));
        latin2hiragana.put(HYO, tsl(HI, yo));
        latin2hiragana.put(GYA, tsl(GI, ya));
        latin2hiragana.put(GYU, tsl(GI, yu));
        latin2hiragana.put(GYO, tsl(GI, yo));
        latin2hiragana.put(PYA, tsl(PI, ya));
        latin2hiragana.put(PYU, tsl(PI, yu));
        latin2hiragana.put(PYO, tsl(PI, yo));
        latin2hiragana.put(CHA, tsl(CHI, ya));
        latin2hiragana.put(CHU, tsl(CHI, yu));
        latin2hiragana.put(CHO, tsl(CHI, yo));
        latin2hiragana.put(MYA, tsl(MI, ya));
        latin2hiragana.put(MYU, tsl(MI, yu));
        latin2hiragana.put(MYO, tsl(MI, yo));
        latin2hiragana.put(JA, tsl(S_JI, ya));
        latin2hiragana.put(JU, tsl(S_JI, yu));
        latin2hiragana.put(JO, tsl(S_JI, yo));
    }

    private String tsl(final String... alphas) {
        final StringBuilder sb = new StringBuilder();
        for (final String alpha : alphas) {
            sb.append(latin2hiragana.get(alpha));
        }
        return sb.toString();
    }

}
