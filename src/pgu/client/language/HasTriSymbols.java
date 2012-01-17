package pgu.client.language;

import java.util.ArrayList;

import pgu.client.utils.guava.HashTriMap;

public interface HasTriSymbols {

    HashTriMap<String, String, String> availableSymbols(final ArrayList<String> selectedLevels);

}
