package pgu.client.ui.level.granularity;

import pgu.client.Pgu_game;
import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;
import pgu.client.place.SubselectionLevelPlace;
import pgu.client.place.ThemeLevelPlace;
import pgu.client.ui.style.PguGameResources;
import pgu.client.ui.style.PguGameResources.Style;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class GranularityLevelViewImpl extends Composite implements GranularityLevelView {

    private static GranularityLevelViewImplUiBinder uiBinder = GWT.create(GranularityLevelViewImplUiBinder.class);

    interface GranularityLevelViewImplUiBinder extends UiBinder<Widget, GranularityLevelViewImpl> {
    }

    @UiField
    HTMLPanel granularitiesPanel;

    private final Style style;

    private Language currLanguage;
    private Language prevLanguage;

    public GranularityLevelViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
        style = PguGameResources.INSTANCE.style();
    }

    private Presenter presenter;

    @Override
    public void setPresenter(final Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayGranularities(final LanguageGranularity currentGranularity, final Language language) {

        currLanguage = language;

        if (0 == granularitiesPanel.getWidgetCount()) {
            initGranularitiesPanel();
        }

        if (null != currentGranularity //
                && prevLanguage == currLanguage) {

            selectCellForGranularity(currentGranularity);

        } else {
            deselectAllCells();
        }

        prevLanguage = currLanguage;
    }

    private void selectCellForGranularity(final LanguageGranularity currentGranularity) {
        final String granularityLabel = currentGranularity.label();

        for (int i = 0; i < granularitiesPanel.getWidgetCount(); i++) {
            final HTML cell = (HTML) granularitiesPanel.getWidget(i);

            if (granularityLabel.equals(cell.getHTML())) {
                cell.addStyleName(style.cellSelected());
            } else {
                cell.removeStyleName(style.cellSelected());
            }
        }
    }

    private void deselectAllCells() {
        for (int i = 0; i < granularitiesPanel.getWidgetCount(); i++) {
            granularitiesPanel.getWidget(i).removeStyleName(style.cellSelected());
        }
    }

    private void initGranularitiesPanel() {
        for (final LanguageGranularity granularity : LanguageGranularity.values()) {
            granularitiesPanel.add(buildCellGranularity(granularity));
        }
    }

    private HTML buildCellGranularity(final LanguageGranularity granularity) {
        final HTML cell = new HTML(granularity.label());
        cell.addStyleName(style.cell());
        cell.setPixelSize(100, 100);

        cell.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                goToNextStep(granularity);
            }

        });
        return cell;
    }

    private void goToNextStep(final LanguageGranularity granularity) {
        if (isRussianAlphabet(granularity) //
                || isGreekAlphabet(granularity) //
        ) {

            goToSubselectionLevel(granularity);

        } else {
            goToThemeLevel(granularity);
        }
    }

    private boolean isGreekAlphabet(final LanguageGranularity granularity) {
        return Language.GREEK == currLanguage //
                && LanguageGranularity.ALPHABET == granularity;
    }

    private boolean isRussianAlphabet(final LanguageGranularity granularity) {
        return Language.RUSSIAN == currLanguage //
                && LanguageGranularity.ALPHABET == granularity;
    }

    private void goToSubselectionLevel(final LanguageGranularity granularity) {
        presenter.goTo(new SubselectionLevelPlace( //
                currLanguage, //
                granularity, //
                null, // Theme
                Pgu_game.gameConfig.subselections() //
                ));
    }

    private void goToThemeLevel(final LanguageGranularity granularity) {
        presenter.goTo(new ThemeLevelPlace( //
                currLanguage, //
                granularity, //
                Pgu_game.gameConfig.theme() //
                ));
    }
}
