package pgu.client;

import java.util.List;

import pgu.client.myguava.Lists;

public class GameConfig {

    private String language;
    private String type;
    private String theme;
    private final List<String> subselections = Lists.newArrayList();

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

    public String language() {
        return language;
    }

    public String type() {
        return type;
    }

    public String theme() {
        return theme;
    }

    public List<String> subselections() {
        return subselections;
    }

}
