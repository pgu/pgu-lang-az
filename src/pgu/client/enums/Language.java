package pgu.client.enums;

import pgu.client.language.HasLevels;
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
    private HasLevels hasLevels;

    private Language(final String label, final HasLevels hasLevels) {
        this.label = label;
        this.hasLevels = hasLevels;
    }

    @Override
    public String label() {
        return label;
    }

    public HasLevels getHasLevels() {
        return hasLevels;
    }

}
