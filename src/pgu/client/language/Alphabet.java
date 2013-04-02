package pgu.client.language;

import pgu.client.utils.guava.HashBiMap;

public interface Alphabet extends HasLevels, HasBiSymbols {

    HashBiMap<String, String> getAllLatin2Symbol();

}
