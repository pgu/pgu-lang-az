package pgu.client;

import static pgu.client.enums.LabelHelper.fromGranularity;
import static pgu.client.enums.LabelHelper.fromLanguage;
import static pgu.client.enums.LabelHelper.fromTheme;

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

    public GameConfig language(final String label) {
        language = fromLanguage(label);
        return this;
    }

    public GameConfig granularity(final String label) {
        granularity = fromGranularity(label);
        return this;
    }

    public GameConfig theme(final String label) {
        theme = fromTheme(label);
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
