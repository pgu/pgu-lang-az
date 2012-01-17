package pgu.client.language;

import java.util.ArrayList;

import pgu.client.utils.guava.HashBiMap;

public interface HasBiSymbols {

    HashBiMap<String, String> availableSymbols(final ArrayList<String> selectedLevels);
}
