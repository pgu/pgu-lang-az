package pgu.client.language.russian;

import java.util.ArrayList;

import pgu.client.language.Alphabet;
import pgu.client.utils.guava.HashBiMap;
import pgu.client.utils.guava.Lists;

public enum RussianAlphabet implements Alphabet {
    INSTANCE;

    private final HashBiMap<String, String> latin2russian = HashBiMap.create();
    private final ArrayList<String> availableLevels = Lists.newArrayList(GRP_1, GRP_2, GRP_3, GRP_4, GRP_5);

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

            if (GRP_1.equals(selectedLevel)) {
                availableSymbols.put(A, latin2russian.get(A));
                availableSymbols.put(B, latin2russian.get(B));
                availableSymbols.put(V, latin2russian.get(V));
                availableSymbols.put(G, latin2russian.get(G));
                availableSymbols.put(D, latin2russian.get(D));
                availableSymbols.put(a, latin2russian.get(a));
                availableSymbols.put(b, latin2russian.get(b));
                availableSymbols.put(v, latin2russian.get(v));
                availableSymbols.put(g, latin2russian.get(g));
                availableSymbols.put(d, latin2russian.get(d));

            } else if (GRP_2.equals(selectedLevel)) {
                availableSymbols.put(JE, latin2russian.get(JE));
                availableSymbols.put(JO, latin2russian.get(JO));
                availableSymbols.put(GE, latin2russian.get(GE));
                availableSymbols.put(Z, latin2russian.get(Z));
                availableSymbols.put(I, latin2russian.get(I));
                availableSymbols.put(J, latin2russian.get(J));
                availableSymbols.put(je, latin2russian.get(je));
                availableSymbols.put(jo, latin2russian.get(jo));
                availableSymbols.put(ge, latin2russian.get(ge));
                availableSymbols.put(z, latin2russian.get(z));
                availableSymbols.put(i, latin2russian.get(i));
                availableSymbols.put(j, latin2russian.get(j));

            } else if (GRP_3.equals(selectedLevel)) {
                availableSymbols.put(K, latin2russian.get(K));
                availableSymbols.put(L, latin2russian.get(L));
                availableSymbols.put(M, latin2russian.get(M));
                availableSymbols.put(N, latin2russian.get(N));
                availableSymbols.put(O, latin2russian.get(O));
                availableSymbols.put(k, latin2russian.get(k));
                availableSymbols.put(l, latin2russian.get(l));
                availableSymbols.put(m, latin2russian.get(m));
                availableSymbols.put(n, latin2russian.get(n));
                availableSymbols.put(o, latin2russian.get(o));

            } else if (GRP_4.equals(selectedLevel)) {
                availableSymbols.put(P, latin2russian.get(P));
                availableSymbols.put(R, latin2russian.get(R));
                availableSymbols.put(S, latin2russian.get(S));
                availableSymbols.put(T, latin2russian.get(T));
                availableSymbols.put(U, latin2russian.get(U));
                availableSymbols.put(F, latin2russian.get(F));
                availableSymbols.put(p, latin2russian.get(p));
                availableSymbols.put(r, latin2russian.get(r));
                availableSymbols.put(s, latin2russian.get(s));
                availableSymbols.put(t, latin2russian.get(t));
                availableSymbols.put(u, latin2russian.get(u));
                availableSymbols.put(f, latin2russian.get(f));

            } else if (GRP_5.equals(selectedLevel)) {
                availableSymbols.put(X, latin2russian.get(X));
                availableSymbols.put(TS, latin2russian.get(TS));
                availableSymbols.put(CH_IP, latin2russian.get(CH_IP));
                availableSymbols.put(SH_UT, latin2russian.get(SH_UT));
                availableSymbols.put(SH_EER, latin2russian.get(SH_EER));
                availableSymbols.put(I_SHORT, latin2russian.get(I_SHORT));
                availableSymbols.put(E, latin2russian.get(E));
                availableSymbols.put(JU, latin2russian.get(JU));
                availableSymbols.put(JA, latin2russian.get(JA));
                availableSymbols.put(x, latin2russian.get(x));
                availableSymbols.put(ts, latin2russian.get(ts));
                availableSymbols.put(ch_ip, latin2russian.get(ch_ip));
                availableSymbols.put(sh_ut, latin2russian.get(sh_ut));
                availableSymbols.put(sh_eer, latin2russian.get(sh_eer));
                availableSymbols.put(i_short, latin2russian.get(i_short));
                availableSymbols.put(e, latin2russian.get(e));
                availableSymbols.put(ju, latin2russian.get(ju));
                availableSymbols.put(ja, latin2russian.get(ja));

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
    private String a = null;
    private String b = null;
    private String v = null;
    private String g = null;
    private String d = null;
    private String je = null;
    private String jo = null;
    private String ge = null;
    private String z = null;
    private String i = null;
    private String j = null;
    private String k = null;
    private String l = null;
    private String m = null;
    private String n = null;
    private String o = null;
    private String p = null;
    private String r = null;
    private String s = null;
    private String t = null;
    private String u = null;
    private String f = null;
    private String x = null;
    private String ts = null;
    private String ch_ip = null;
    private String sh_ut = null;
    private String sh_eer = null;
    private String i_short = null;
    private String e = null;
    private String ju = null;
    private String ja = null;

    public static String _(final String w) {
        return w.replace("(", "<b>").replace(")", "</b>");
    }

    private static final String GRP_1 = "A - D";
    private static final String GRP_2 = "YE - Y";
    private static final String GRP_3 = "K - O";
    private static final String GRP_4 = "P - F";
    private static final String GRP_5 = "X - YA";

    private void putAll() {
        //        A = _("F(A)THER");
        //        B = _("(B)IT");
        //        V = _("(V)INE");
        //        G = _("(G)O");
        //        D = _("(D)O");
        //        JE = _("(YE)T");
        //        JO = _("(YO)LK");
        //        GE = _("PLEA(S)URE");
        //        Z = _("(Z)OO");
        //        I = _("M(E)");
        //        J = _("(Y)ES");
        //        K = _("(K)ITTEN");
        //        L = _("(L)AMP");
        //        M = _("(M)AP");
        //        N = _("(N)OT");
        //        O = _("M(O)RE");
        //        P = _("S(P)ELL");
        //        R = _("RRR");
        //        S = _("(S)EE");
        //        T = _("S(T)OOL");
        //        U = _("B(OO)T");
        //        F = _("(F)ACE");
        //        X = _("U(GH)");
        //        TS = _("SI(TS)");
        //        CH_IP = _("(CH)IP");
        //        SH_UT = _("(SH)UT");
        //        SH_EER = _("(SH)EER");
        //        I_SHORT = _("ROS(E)S");
        //        E = _("M(E)T");
        //        JU = _("(U)SE");
        //        JA = _("(YA)RD");
        //        a = _("f(a)ther");
        //        b = _("(b)it");
        //        v = _("(v)ine");
        //        g = _("(g)o");
        //        d = _("(d)o");
        //        je = _("(ye)t");
        //        jo = _("(yo)lk");
        //        ge = _("plea(s)ure");
        //        z = _("(z)oo");
        //        i = _("m(e)");
        //        j = _("(y)es");
        //        k = _("(k)itten");
        //        l = _("(l)amp");
        //        m = _("(m)ap");
        //        n = _("(n)ot");
        //        o = _("m(o)re");
        //        p = _("s(p)ell");
        //        r = _("rrr");
        //        s = _("(s)ee");
        //        t = _("s(t)ool");
        //        u = _("b(oo)t");
        //        f = _("(f)ace");
        //        x = _("U(gh)");
        //        ts = _("si(ts)");
        //        ch_ip = _("(ch)ip");
        //        sh_ut = _("(sh)ut");
        //        sh_eer = _("(sh)eer");
        //        i_short = _("ros(e)s");
        //        e = _("m(e)t");
        //        ju = _("(u)se");
        //        ja = _("(ya)rd");

        A = "A";
        B = "B";
        V = "V";
        G = "G";
        D = "D";
        JE = "YE";
        JO = "YO";
        GE = "J";
        Z = "Z";
        I = "I";
        J = "Y";
        K = "K";
        L = "L";
        M = "M";
        N ="N";
        O = "O";
        P = "P";
        R = "R";
        S = "S";
        T = "T";
        U = "U";
        F = "F";
        X = "H";
        TS = "TS";
        CH_IP = "TCH";
        SH_UT = "SH";
        SH_EER = "SH";
        I_SHORT = "I";
        E = "E";
        JU = "YU";
        JA = "YA";
        a = "a";
        b = "b";
        v = "v";
        g = "g";
        d = "d";
        je = "ye";
        jo = "yo";
        ge = "j";
        z = "z";
        i = "i";
        j = "y";
        k = "k";
        l = "l";
        m = "m";
        n = "n";
        o = "o";
        p = "p";
        r = "r";
        s = "s";
        t = "t";
        u = "u";
        f = "f";
        x = "h";
        ts = "ts";
        ch_ip = "tch";
        sh_ut = "sh";
        sh_eer = "sh";
        i_short = "i";
        e = "e";
        ju = "yu";
        ja = "ya";

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
        latin2russian.put(a, "&#x0430;");
        latin2russian.put(b, "&#x0431;");
        latin2russian.put(v, "&#x0432;");
        latin2russian.put(g, "&#x0433;");
        latin2russian.put(d, "&#x0434;");
        latin2russian.put(je, "&#x0435;");
        latin2russian.put(jo, "&#x0451;");
        latin2russian.put(ge, "&#x0436;");
        latin2russian.put(z, "&#x0437;");
        latin2russian.put(i, "&#x0438;");
        latin2russian.put(j, "&#x0439;");
        latin2russian.put(k, "&#x043A;");
        latin2russian.put(l, "&#x043B;");
        latin2russian.put(m, "&#x043C;");
        latin2russian.put(n, "&#x043D;");
        latin2russian.put(o, "&#x043E;");
        latin2russian.put(p, "&#x043F;");
        latin2russian.put(r, "&#x0440;");
        latin2russian.put(s, "&#x0441;");
        latin2russian.put(t, "&#x0442;");
        latin2russian.put(u, "&#x0443;");
        latin2russian.put(f, "&#x0444;");
        latin2russian.put(x, "&#x0445;");
        latin2russian.put(ts, "&#x0446;");
        latin2russian.put(ch_ip, "&#x0447;");
        latin2russian.put(sh_ut, "&#x0448;");
        latin2russian.put(sh_eer, "&#x0449;");
        latin2russian.put(i_short, "&#x044B;");
        latin2russian.put(e, "&#x044D;");
        latin2russian.put(ju, "&#x044E;");
        latin2russian.put(ja, "&#x044F;");
    }

}
