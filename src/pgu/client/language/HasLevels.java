package pgu.client.language;

import java.util.List;

import pgu.client.utils.guava.HashBiMap;

public interface HasLevels {

    public List<String> availableLevels();

    public HashBiMap<String, String> availableSymbols(final List<String> selectedLevels);
}
