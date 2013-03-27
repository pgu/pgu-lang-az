package pgu.client.language.greek;

import java.util.ArrayList;

import pgu.client.language.Alphabet;
import pgu.client.utils.guava.HashBiMap;
import pgu.client.utils.guava.Lists;

public enum GreekAlphabet implements Alphabet {
    INSTANCE;

    private static final String omega = "omega";
    private static final String psi = "psi";
    private static final String chi = "chi";
    private static final String phi = "phi";
    private static final String upsilon = "upsilon";
    private static final String tau = "tau";
    private static final String sigma = "sigma";
    private static final String roh = "roh";
    private static final String pi = "pi";
    private static final String omicron = "omicron";
    private static final String xi = "xi";
    private static final String nu = "nu";
    private static final String mu = "mu";
    private static final String lambda = "lambda";
    private static final String kappa = "kappa";
    private static final String iota = "iota";
    private static final String theta = "theta";
    private static final String eta = "eta";
    private static final String zeta = "zeta";
    private static final String epsilon = "epsilon";
    private static final String delta = "delta";
    private static final String gamma = "gamma";
    private static final String beta = "beta";
    private static final String alpha = "alpha";
    private static final String OMEGA = "OMEGA";
    private static final String PSI = "PSI";
    private static final String CHI = "CHI";
    private static final String PHI = "PHI";
    private static final String UPSILON = "UPSILON";
    private static final String TAU = "TAU";
    private static final String SIGMA = "SIGMA";
    private static final String ROH = "ROH";
    private static final String PI = "PI";
    private static final String OMICRON = "OMICRON";
    private static final String XI = "XI";
    private static final String NU = "NU";
    private static final String MU = "MU";
    private static final String LAMBDA = "LAMBDA";
    private static final String KAPPA = "KAPPA";
    private static final String IOTA = "IOTA";
    private static final String THETA = "THETA";
    private static final String ETA = "ETA";
    private static final String ZETA = "ZETA";
    private static final String EPSILON = "EPSILON";
    private static final String DELTA = "DELTA";
    private static final String GAMMA = "GAMMA";
    private static final String BETA = "BETA";
    private static final String ALPHA = "ALPHA";

    private final HashBiMap<String, String> latin2greek = HashBiMap.create();
    private final ArrayList<String> availableLevels = Lists.newArrayList(A_Z, ET_M, N_S, T_0M);

    private static final String A_Z = "A - Z";
    private static final String ET_M = "ET - M";
    private static final String N_S = "N - S";
    private static final String T_0M = "T - OM";

    {
        latin2greek.put(ALPHA, "&#x0391;");
        latin2greek.put(BETA, "&#x0392;");
        latin2greek.put(GAMMA, "&#x0393;");
        latin2greek.put(DELTA, "&#x0394;");
        latin2greek.put(EPSILON, "&#x0395;");
        latin2greek.put(ZETA, "&#x0396;");
        latin2greek.put(ETA, "&#x0397;");
        latin2greek.put(THETA, "&#x0398;");
        latin2greek.put(IOTA, "&#x0399;");
        latin2greek.put(KAPPA, "&#x039a;");
        latin2greek.put(LAMBDA, "&#x039b;");
        latin2greek.put(MU, "&#x039c;");
        latin2greek.put(NU, "&#x039d;");
        latin2greek.put(XI, "&#x039e;");
        latin2greek.put(OMICRON, "&#x039f;");
        latin2greek.put(PI, "&#x03a0;");
        latin2greek.put(ROH, "&#x03a1;");
        latin2greek.put(SIGMA, "&#x03a3;");
        latin2greek.put(TAU, "&#x03a4;");
        latin2greek.put(UPSILON, "&#x03a5;");
        latin2greek.put(PHI, "&#x03a6;");
        latin2greek.put(CHI, "&#x03a7;");
        latin2greek.put(PSI, "&#x03a8;");
        latin2greek.put(OMEGA, "&#x03a9;");
        latin2greek.put(alpha, "&#x03B1;");
        latin2greek.put(beta, "&#x03B2;");
        latin2greek.put(gamma, "&#x03B3;");
        latin2greek.put(delta, "&#x03B4;");
        latin2greek.put(epsilon, "&#x03B5;");
        latin2greek.put(zeta, "&#x03B6;");
        latin2greek.put(eta, "&#x03B7;");
        latin2greek.put(theta, "&#x03B8;");
        latin2greek.put(iota, "&#x03B9;");
        latin2greek.put(kappa, "&#x03BA;");
        latin2greek.put(lambda, "&#x03BB;");
        latin2greek.put(mu, "&#x03BC;");
        latin2greek.put(nu, "&#x03BD;");
        latin2greek.put(xi, "&#x03BE;");
        latin2greek.put(omicron, "&#x03BF;");
        latin2greek.put(pi, "&#x03C0;");
        latin2greek.put(roh, "&#x03C1;");
        latin2greek.put(sigma, "&#x03C3;");
        latin2greek.put(tau, "&#x03C4;");
        latin2greek.put(upsilon, "&#x03C5;");
        latin2greek.put(phi, "&#x03C6;");
        latin2greek.put(chi, "&#x03C7;");
        latin2greek.put(psi, "&#x03C8;");
        latin2greek.put(omega, "&#x03C9;");
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
                availableSymbols.put(ALPHA, latin2greek.get(ALPHA));
                availableSymbols.put(BETA, latin2greek.get(BETA));
                availableSymbols.put(GAMMA, latin2greek.get(GAMMA));
                availableSymbols.put(DELTA, latin2greek.get(DELTA));
                availableSymbols.put(EPSILON, latin2greek.get(EPSILON));
                availableSymbols.put(ZETA, latin2greek.get(ZETA));
                availableSymbols.put(alpha, latin2greek.get(alpha));
                availableSymbols.put(beta, latin2greek.get(beta));
                availableSymbols.put(gamma, latin2greek.get(gamma));
                availableSymbols.put(delta, latin2greek.get(delta));
                availableSymbols.put(epsilon, latin2greek.get(epsilon));
                availableSymbols.put(zeta, latin2greek.get(zeta));

            } else if (ET_M.equals(selectedLevel)) {
                availableSymbols.put(ETA, latin2greek.get(ETA));
                availableSymbols.put(THETA, latin2greek.get(THETA));
                availableSymbols.put(IOTA, latin2greek.get(IOTA));
                availableSymbols.put(KAPPA, latin2greek.get(KAPPA));
                availableSymbols.put(LAMBDA, latin2greek.get(LAMBDA));
                availableSymbols.put(MU, latin2greek.get(MU));
                availableSymbols.put(eta, latin2greek.get(eta));
                availableSymbols.put(theta, latin2greek.get(theta));
                availableSymbols.put(iota, latin2greek.get(iota));
                availableSymbols.put(kappa, latin2greek.get(kappa));
                availableSymbols.put(lambda, latin2greek.get(lambda));
                availableSymbols.put(mu, latin2greek.get(mu));

            } else if (N_S.equals(selectedLevel)) {
                availableSymbols.put(NU, latin2greek.get(NU));
                availableSymbols.put(XI, latin2greek.get(XI));
                availableSymbols.put(OMICRON, latin2greek.get(OMICRON));
                availableSymbols.put(PI, latin2greek.get(PI));
                availableSymbols.put(ROH, latin2greek.get(ROH));
                availableSymbols.put(SIGMA, latin2greek.get(SIGMA));
                availableSymbols.put(nu, latin2greek.get(nu));
                availableSymbols.put(xi, latin2greek.get(xi));
                availableSymbols.put(omicron, latin2greek.get(omicron));
                availableSymbols.put(pi, latin2greek.get(pi));
                availableSymbols.put(roh, latin2greek.get(roh));
                availableSymbols.put(sigma, latin2greek.get(sigma));

            } else if (T_0M.equals(selectedLevel)) {
                availableSymbols.put(TAU, latin2greek.get(TAU));
                availableSymbols.put(UPSILON, latin2greek.get(UPSILON));
                availableSymbols.put(PHI, latin2greek.get(PHI));
                availableSymbols.put(CHI, latin2greek.get(CHI));
                availableSymbols.put(PSI, latin2greek.get(PSI));
                availableSymbols.put(OMEGA, latin2greek.get(OMEGA));
                availableSymbols.put(tau, latin2greek.get(tau));
                availableSymbols.put(upsilon, latin2greek.get(upsilon));
                availableSymbols.put(phi, latin2greek.get(phi));
                availableSymbols.put(chi, latin2greek.get(chi));
                availableSymbols.put(psi, latin2greek.get(psi));
                availableSymbols.put(omega, latin2greek.get(omega));

            } else {
                throw new IllegalArgumentException("Unknown level: " + selectedLevel);
            }
        }
        return availableSymbols;
    }

}
