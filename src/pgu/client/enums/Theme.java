package pgu.client.enums;

public enum Theme implements HasLabel {

    HIRAGANA("Hiragana"), KATAKANA("Katakana");

    private String label;

    private Theme(final String label) {
        this.label = label;
    }

    @Override
    public String label() {
        return label;
    }

}
