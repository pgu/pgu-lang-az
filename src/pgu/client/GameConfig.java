package pgu.client;

public class GameConfig {

    private String language;
    private String type;
    private String theme;
    private String subselection;

    public GameConfig language(final String text) {
        language = text;
        return this;
    }

    public GameConfig type(final String text) {
        type = text;
        return this;
    }

    public GameConfig theme(final String text) {
        theme = text;
        return this;
    }

    public GameConfig subselection(final String text) {
        subselection = text;
        return this;
    }

    public String language() {
        return language;
    }

    public String type() {
        return type;
    }

    public String theme() {
        return theme;
    }

    public String subselection() {
        return subselection;
    }

}
