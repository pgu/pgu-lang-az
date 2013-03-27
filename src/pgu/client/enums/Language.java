package pgu.client.enums;

import pgu.client.language.Alphabet;
import pgu.client.language.greek.GreekAlphabet;
import pgu.client.language.japanese.Hiragana;
import pgu.client.language.japanese.Katakana;
import pgu.client.language.russian.RussianAlphabet;

public enum Language implements HasLabel {
    // CHINESE("Chinese"), //
    // JAPANESE("Japanese"), //
    HIRAGANA("Hiragana", Hiragana.INSTANCE), //
    KATAKANA("Katakana", Katakana.INSTANCE), //
    RUSSIAN("Russian", RussianAlphabet.INSTANCE), //
    GREEK("Greek", GreekAlphabet.INSTANCE);

    private String    label;
    private Alphabet alphabet;

    private Language(final String label, final Alphabet alphabet) {
        this.label = label;
        this.alphabet = alphabet;
    }

    @Override
    public String label() {
        return label;
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

}
