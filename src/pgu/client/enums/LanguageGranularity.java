package pgu.client.enums;

public enum LanguageGranularity implements HasLabel {

    ALPHABET("Alphabets"), //
    WORD("Words"), //
    SENTENCE("Sentences");

    private String label;

    private LanguageGranularity(final String label) {
        this.label = label;
    }

    @Override
    public String label() {
        return label;
    }
}
