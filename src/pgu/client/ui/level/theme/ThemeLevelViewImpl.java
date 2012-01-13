package pgu.client.ui.level.theme;

import static pgu.client.enums.LabelHelper.is;

import java.util.ArrayList;

import pgu.client.Pgu_game;
import pgu.client.enums.LabelHelper;
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

        themesPanel.clear();
        fillThemesPanel(language, granularity);

        if (null != currentTheme //
                && this.granularity == granularity //
                && this.language == language) {

            selectCellForTheme(currentTheme);

        } else {
            this.language = language;
            this.granularity = granularity;
            deselectAllCells();
        }
    }

    private void deselectAllCells() {
        // TODO PGU
    }

    private void selectCellForTheme(final Theme currentTheme) {
        // TODO PGU
    }

    private void fillThemesPanel(final Language language, final LanguageGranularity granularity) {

        final ArrayList<Theme> themes = Lists.newArrayList();

        if (isJapaneseAlphabet(language, granularity)) {
            themes.add(Theme.HIRAGANA);
            themes.add(Theme.KATAKANA);
        }

        for (final String label : LabelHelper.labels(themes)) {
            themesPanel.add(buildCellTheme(label));
        }
    }

    private static boolean isJapaneseAlphabet(final Language language, final LanguageGranularity granularity) {
        return is(language.label(), Language.JAPANESE) //
                && is(granularity.label(), LanguageGranularity.ALPHABET);
    }

    private Widget buildCellTheme(final String themeLabel) {
        final HTML cell = new HTML(themeLabel);
        cell.addStyleName(style.cell());
        cell.setPixelSize(100, 100);

        cell.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                goToSubselectionLevel(themeLabel);
            }

        });
        return cell;
    }

    private void goToSubselectionLevel(final String themeLabel) {
        presenter.goTo(new SubselectionLevelPlace( //
                language, //
                granularity, //
                LabelHelper.fromTheme(themeLabel), //
                Pgu_game.gameConfig.subselections() //
                ));
    }

}
