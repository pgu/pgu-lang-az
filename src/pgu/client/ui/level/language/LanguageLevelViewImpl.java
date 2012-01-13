package pgu.client.ui.level.language;

import pgu.client.Pgu_game;
import pgu.client.enums.LabelHelper;
import pgu.client.enums.Language;
import pgu.client.place.GranularityLevelPlace;
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

public class LanguageLevelViewImpl extends Composite implements LanguageLevelView {

    private static LanguageLevelViewImplUiBinder uiBinder = GWT.create(LanguageLevelViewImplUiBinder.class);

    interface LanguageLevelViewImplUiBinder extends UiBinder<Widget, LanguageLevelViewImpl> {
    }

    @UiField
    HTMLPanel languagesPanel;

    private final Style style;

    public LanguageLevelViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
        style = PguGameResources.INSTANCE.style();
    }

    private Presenter presenter;

    @Override
    public void setPresenter(final Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayLanguages(final Language currentLanguage) {
        if (0 == languagesPanel.getWidgetCount()) {
            initLanguagesPanel();
        }

        if (null == currentLanguage) {
            deselectAllCells();
        } else {
            selectCellForLanguage(currentLanguage);
        }
    }

    private void deselectAllCells() {
        for (int i = 0; i < languagesPanel.getWidgetCount(); i++) {
            languagesPanel.getWidget(i).removeStyleName(style.cellSelected());
        }
    }

    private void selectCellForLanguage(final Language currentLanguage) {

        for (int i = 0; i < languagesPanel.getWidgetCount(); i++) {
            final HTML cell = (HTML) languagesPanel.getWidget(i);

            if (currentLanguage.label().equals(cell.getHTML())) {
                cell.addStyleName(style.cellSelected());
            } else {
                cell.removeStyleName(style.cellSelected());
            }
        }
    }

    private void initLanguagesPanel() {
        for (final String languageLabel : LabelHelper.labels(Language.values())) {
            languagesPanel.add(buildCellLanguage(languageLabel));
        }
    }

    private HTML buildCellLanguage(final String languageLabel) {
        final HTML cell = new HTML(languageLabel);
        cell.addStyleName(style.cell());
        cell.setPixelSize(100, 100);

        cell.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                goToGranularityLevel(languageLabel);
            }

        });
        return cell;
    }

    private void goToGranularityLevel(final String languageLabel) {
        presenter.goTo(new GranularityLevelPlace( //
                LabelHelper.fromLanguage(languageLabel), //
                Pgu_game.gameConfig.granularity() //
                ));
    }

}
