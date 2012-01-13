package pgu.client.language;

import java.util.ArrayList;

import pgu.client.utils.guava.HashBiMap;

public interface HasLevels {

    public ArrayList<String> availableLevels();

    public HashBiMap<String, String> availableSymbols(final ArrayList<String> selectedLevels);
}
