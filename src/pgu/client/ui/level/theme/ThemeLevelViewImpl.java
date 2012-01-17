package pgu.client.ui.level.theme;

import java.util.ArrayList;

import pgu.client.Pgu_game;
import pgu.client.enums.LabelHelper;
import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;
import pgu.client.enums.Theme;
import pgu.client.place.GamePlace;
import pgu.client.place.SubselectionLevelPlace;
import pgu.client.ui.style.PguGameResources;
import pgu.client.ui.style.PguGameResources.Style;
import pgu.client.utils.guava.Lists;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
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

    private Language language;
    private LanguageGranularity granularity;

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

        this.language = language;
        this.granularity = granularity;

        themesPanel.setPixelSize(Window.getClientWidth(), Window.getClientHeight());

        themesPanel.clear();
        fillThemesPanel();

        if (null != currentTheme //
                && Pgu_game.gameConfig.granularity() == this.granularity //
                && Pgu_game.gameConfig.language() == language) {

            selectCellForTheme(currentTheme);
        }

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

        } else if (Language.CHINESE == language) {
            themes.add(Theme.CHINESE_LESSON_1);
        }

        return LabelHelper.sort(themes);
    }

    private boolean isJapaneseAlphabet() {
        return Language.JAPANESE == language //
                && LanguageGranularity.ALPHABET == granularity;
    }

    private Widget buildCellTheme(final Theme theme) {

        final HTML cell = new HTML(theme.label());
        cell.addStyleName(style.cell());
        cell.setPixelSize(100, 100);

        cell.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                if (isChineseWords()) {
                    startsGame(theme);

                } else {
                    goToSubselectionLevel(theme);
                }
            }

        });
        return cell;
    }

    private void startsGame(final Theme theme) {
        Pgu_game.gameConfig //
                .language(language) //
                .granularity(granularity) //
                .theme(theme);

        Pgu_game.gameConfig.subselections().clear();

        presenter.goTo(new GamePlace());
    }

    private boolean isChineseWords() {
        return Language.CHINESE == language //
                && LanguageGranularity.WORD == granularity;
    }

    private void goToSubselectionLevel(final Theme theme) {

        presenter.goTo(new SubselectionLevelPlace( //
                language, //
                granularity, //
                theme, //
                Pgu_game.gameConfig.subselections() //
                ));
    }

}
