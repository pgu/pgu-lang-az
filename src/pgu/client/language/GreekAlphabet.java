package pgu.client.language;

import pgu.client.myguava.HashBiMap;

public class GreekAlphabet {
    private static final HashBiMap latin2greek = new HashBiMap();
    static {
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
}
