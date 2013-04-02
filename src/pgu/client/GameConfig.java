package pgu.client;

import java.util.ArrayList;

import pgu.client.enums.GameSize;
import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;
import pgu.client.enums.Theme;
import pgu.client.utils.guava.Lists;

public class GameConfig {

    private String str_lgName;
    private String str_lgSel;

    private GameSize                size;
    private Language                language;
    private LanguageGranularity     granularity;
    private Theme                   theme;
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

    public GameConfig size(final GameSize size) {
        this.size = size;
        return this;
    }

    public GameSize size() {
        return size;
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
        return new ArrayList<String>(subselections);
    }

    public void subselections(final ArrayList<String> selections) {
        subselections.clear();
        subselections.addAll(selections);
    }

    private boolean isBlank(final String str) {
        return str == null || str.trim().isEmpty();
    }

    public void str_lgName(final String str_lgName) {
        this.str_lgName = str_lgName;
    }

    public void str_lgSel(final String str_lgSel) {
        this.str_lgSel = str_lgSel;
    }

    public String str_lgName() {
        return str_lgName;
    }

    public String str_lgSel() {
        return str_lgSel;
    }


}
