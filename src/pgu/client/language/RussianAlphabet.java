package pgu.client.language;

import java.util.ArrayList;

import pgu.client.utils.guava.HashBiMap;
import pgu.client.utils.guava.Lists;

public enum RussianAlphabet implements HasLevels {
    INSTANCE;

    private final HashBiMap<String, String> latin2russian = HashBiMap.create();
    private final ArrayList<String> availableLevels = Lists.newArrayList(G_1, G_2, G_3, G_4, G_5);

    private RussianAlphabet() {
        putAll();
    }

    @Override
    public ArrayList<String> availableLevels() {
        return availableLevels;
    }

    @Override
    public final HashBiMap<String, String> availableSymbols(final ArrayList<String> selectedLevels) {
        final HashBiMap<String, String> availableSymbols = HashBiMap.create();

        for (final String selectedLevel : selectedLevels) {

            if (G_1.equals(selectedLevel)) {
                availableSymbols.put(A, latin2russian.get(A));
                availableSymbols.put(B, latin2russian.get(B));
                availableSymbols.put(V, latin2russian.get(V));
                availableSymbols.put(G, latin2russian.get(G));
                availableSymbols.put(D, latin2russian.get(D));

            } else if (G_2.equals(selectedLevel)) {
                availableSymbols.put(JE, latin2russian.get(JE));
                availableSymbols.put(JO, latin2russian.get(JO));
                availableSymbols.put(GE, latin2russian.get(GE));
                availableSymbols.put(Z, latin2russian.get(Z));
                availableSymbols.put(I, latin2russian.get(I));
                availableSymbols.put(J, latin2russian.get(J));

            } else if (G_3.equals(selectedLevel)) {
                availableSymbols.put(K, latin2russian.get(K));
                availableSymbols.put(L, latin2russian.get(L));
                availableSymbols.put(M, latin2russian.get(M));
                availableSymbols.put(N, latin2russian.get(N));
                availableSymbols.put(O, latin2russian.get(O));

            } else if (G_4.equals(selectedLevel)) {
                availableSymbols.put(P, latin2russian.get(P));
                availableSymbols.put(R, latin2russian.get(R));
                availableSymbols.put(S, latin2russian.get(S));
                availableSymbols.put(T, latin2russian.get(T));
                availableSymbols.put(U, latin2russian.get(U));
                availableSymbols.put(F, latin2russian.get(F));

            } else if (G_5.equals(selectedLevel)) {
                availableSymbols.put(X, latin2russian.get(X));
                availableSymbols.put(TS, latin2russian.get(TS));
                availableSymbols.put(CH_IP, latin2russian.get(CH_IP));
                availableSymbols.put(SH_UT, latin2russian.get(SH_UT));
                availableSymbols.put(SH_EER, latin2russian.get(SH_EER));
                availableSymbols.put(I_SHORT, latin2russian.get(I_SHORT));
                availableSymbols.put(E, latin2russian.get(E));
                availableSymbols.put(JU, latin2russian.get(JU));
                availableSymbols.put(JA, latin2russian.get(JA));

            } else {
                throw new IllegalArgumentException("Unknown level: " + selectedLevel);
            }
        }

        return availableSymbols;
    }

    private String A = null;
    private String B = null;
    private String V = null;
    private String G = null;
    private String D = null;
    private String JE = null;
    private String JO = null;
    private String GE = null;
    private String Z = null;
    private String I = null;
    private String J = null;
    private String K = null;
    private String L = null;
    private String M = null;
    private String N = null;
    private String O = null;
    private String P = null;
    private String R = null;
    private String S = null;
    private String T = null;
    private String U = null;
    private String F = null;
    private String X = null;
    private String TS = null;
    private String CH_IP = null;
    private String SH_UT = null;
    private String SH_EER = null;
    private String I_SHORT = null;
    private String E = null;
    private String JU = null;
    private String JA = null;

    public static String _(final String w) {
        return w.replace("(", "<b>").replace(")", "</b>");
    }

    private static final String G_1 = "A - D";
    private static final String G_2 = "YE - Y";
    private static final String G_3 = "K - O";
    private static final String G_4 = "P - F";
    private static final String G_5 = "X - YA";

    private void putAll() {
        A = _("f(a)ther");
        B = _("(b)it");
        V = _("(v)ine");
        G = _("(g)o");
        D = _("(d)o");
        JE = _("(ye)t");
        JO = _("(yo)lk");
        GE = _("plea(s)ure");
        Z = _("(z)oo");
        I = _("m(e)");
        J = _("(y)es");
        K = _("(k)itten");
        L = _("(l)amp");
        M = _("(m)ap");
        N = _("(n)ot");
        O = _("m(o)re");
        P = _("s(p)ell");
        R = _("rrr");
        S = _("(s)ee");
        T = _("s(t)ool");
        U = _("b(oo)t");
        F = _("(f)ace");
        X = _("U(gh)");
        TS = _("si(ts)");
        CH_IP = _("(ch)ip");
        SH_UT = _("(sh)ut");
        SH_EER = _("(sh)eer");
        I_SHORT = _("[&#x0268;]");
        E = _("m(e)t");
        JU = _("(u)se");
        JA = _("(ya)rd");

        latin2russian.put(A, "&#x0410;");
        latin2russian.put(B, "&#x0411;");
        latin2russian.put(V, "&#x0412;");
        latin2russian.put(G, "&#x0413;");
        latin2russian.put(D, "&#x0414;");
        latin2russian.put(JE, "&#x0415;");
        latin2russian.put(JO, "&#x0401;");
        latin2russian.put(GE, "&#x0416;");
        latin2russian.put(Z, "&#x0417;");
        latin2russian.put(I, "&#x0418;");
        latin2russian.put(J, "&#x0419;");
        latin2russian.put(K, "&#x041A;");
        latin2russian.put(L, "&#x041B;");
        latin2russian.put(M, "&#x041C;");
        latin2russian.put(N, "&#x041D;");
        latin2russian.put(O, "&#x041E;");
        latin2russian.put(P, "&#x041F;");
        latin2russian.put(R, "&#x0420;");
        latin2russian.put(S, "&#x0421;");
        latin2russian.put(T, "&#x0422;");
        latin2russian.put(U, "&#x0423;");
        latin2russian.put(F, "&#x0424;");
        latin2russian.put(X, "&#x0425;");
        latin2russian.put(TS, "&#x0426;");
        latin2russian.put(CH_IP, "&#x0427;");
        latin2russian.put(SH_UT, "&#x0428;");
        latin2russian.put(SH_EER, "&#x0429;");
        latin2russian.put(I_SHORT, "&#x042B;");
        latin2russian.put(E, "&#x042D;");
        latin2russian.put(JU, "&#x042E;");
        latin2russian.put(JA, "&#x042F;");
    }

}
