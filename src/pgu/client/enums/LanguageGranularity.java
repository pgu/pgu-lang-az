package pgu.client.enums;

public enum LanguageGranularity implements HasLabel {

    ALPHABET("Alphabet"), //
    WORD("Word"), //
    SENTENCE("Sentence");

    private String label;

    private LanguageGranularity(final String label) {
        this.label = label;
    }

    @Override
    public String label() {
        return label;
    }
}
