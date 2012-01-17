package pgu.client.enums;

public enum LanguageGranularity implements HasLabel {

    ALPHABET("1 - Alphabet"), //
    WORD("2 - Words"), //
    SENTENCE("3 - Sentences");

    private String label;

    private LanguageGranularity(final String label) {
        this.label = label;
    }

    @Override
    public String label() {
        return label;
    }
}
