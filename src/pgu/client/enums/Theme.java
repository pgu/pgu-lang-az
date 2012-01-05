package pgu.client.enums;

public enum Theme {

    HIRAGANA("Hiragana"), RUSSIAN("Russian");

    private String label;

    private Theme(final String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    public static Theme fromLabel(final String label) {

        for (final Theme theme : values()) {
            if (theme.label.equalsIgnoreCase(label)) {
                return theme;
            }
        }

        throw new IllegalArgumentException("No Theme associated to the label: " + label);
    }
}
