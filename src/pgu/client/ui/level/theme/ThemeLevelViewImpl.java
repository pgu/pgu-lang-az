package pgu.client.ui.level.theme;

import static pgu.client.enums.LabelHelper.is;

import java.util.ArrayList;

import pgu.client.Pgu_game;
import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;
import pgu.client.enums.Theme;
import pgu.client.place.SubselectionLevelPlace;
import pgu.client.ui.style.PguGameResources;
import pgu.client.ui.style.PguGameResources.Style;
import pgu.client.utils.guava.Lists;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class ThemeLevelViewImpl extends Composite implements ThemeLevelView {

    private static ThemeLevelViewImplUiBinder uiBinder = GWT.create(ThemeLevelViewImplUiBinder.class);

    interface ThemeLevelViewImplUiBinder extends UiBinder<Widget, ThemeLevelViewImpl> {
    }

    @UiField
    HTMLPanel themesPanel;

    private final Style style;

    private Language currLanguage;
    private LanguageGranularity currGranularity;

    private Language prevLanguage;
    private LanguageGranularity prevGranularity;

    public ThemeLevelViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
        style = PguGameResources.INSTANCE.style();
    }

    private Presenter presenter;

    @Override
    public void setPresenter(final Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayGranularities( //
            final Theme currentTheme, //
            final LanguageGranularity granularity, //
            final Language language) {

        currLanguage = language;
        currGranularity = granularity;

        themesPanel.clear();
        fillThemesPanel();

        if (null != currentTheme //
                && prevGranularity == currGranularity //
                && prevLanguage == currLanguage) {

            selectCellForTheme(currentTheme);
        }

        prevLanguage = currLanguage;
        prevGranularity = currGranularity;
    }

    private void selectCellForTheme(final Theme currentTheme) {
        final String themeLabel = currentTheme.label();

        for (int i = 0; i < themesPanel.getWidgetCount(); i++) {
            final HTML cell = (HTML) themesPanel.getWidget(i);

            if (themeLabel.equals(cell.getHTML())) {
                cell.addStyleName(style.cellSelected());
            } else {
                cell.removeStyleName(style.cellSelected());
            }
        }
    }

    private void fillThemesPanel() {

        for (final Theme theme : getThemes()) {
            themesPanel.add(buildCellTheme(theme));
        }
    }

    private ArrayList<Theme> getThemes() {

        final ArrayList<Theme> themes = Lists.newArrayList();

        if (isJapaneseAlphabet()) {
            themes.add(Theme.HIRAGANA);
            themes.add(Theme.KATAKANA);
        }

        return themes;
    }

    private boolean isJapaneseAlphabet() {
        return is(currLanguage.label(), Language.JAPANESE) //
                && is(currGranularity.label(), LanguageGranularity.ALPHABET);
    }

    private Widget buildCellTheme(final Theme theme) {

        final HTML cell = new HTML(theme.label());
        cell.addStyleName(style.cell());
        cell.setPixelSize(100, 100);

        cell.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                goToSubselectionLevel(theme);
            }

        });
        return cell;
    }

    private void goToSubselectionLevel(final Theme theme) {

        presenter.goTo(new SubselectionLevelPlace( //
                currLanguage, //
                currGranularity, //
                theme, //
                Pgu_game.gameConfig.subselections() //
                ));
    }

}
