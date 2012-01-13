package pgu.client;

import java.util.ArrayList;

import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;
import pgu.client.enums.Theme;
import pgu.client.utils.guava.Lists;

public class GameConfig {

    private Language language;
    private LanguageGranularity granularity;
    private Theme theme;
    private final ArrayList<String> subselections = Lists.newArrayList();

    public GameConfig language(final Language language) {
        this.language = language;
        return this;
    }

    public GameConfig granularity(final LanguageGranularity granularity) {
        this.granularity = granularity;
        return this;
    }

    public GameConfig theme(final Theme theme) {
        this.theme = theme;
        return this;
    }

    public Language language() {
        return language;
    }

    public LanguageGranularity granularity() {
        return granularity;
    }

    public Theme theme() {
        return theme;
    }

    public ArrayList<String> subselections() {
        return subselections;
    }

}
