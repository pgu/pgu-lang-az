package pgu.client.enums;

public enum Language implements HasLabel {
    JAPANESE("Japanese"), //
    RUSSIAN("Russian");

    private String label;

    private Language(final String label) {
        this.label = label;
    }

    @Override
    public String label() {
        return label;
    }
}
