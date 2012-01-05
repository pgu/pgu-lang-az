package pgu.client.enums;

public enum Language {
    JAPANESE("Japanese"), //
    RUSSIAN("Russian");

    private String label;

    private Language(final String label) {
        this.label = label;
    }
}
