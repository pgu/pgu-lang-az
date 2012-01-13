package pgu.client.ui.level.granularity;

import pgu.client.Pgu_game;
import pgu.client.enums.LabelHelper;
import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;
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
    private Language language;

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

        if (0 == granularitiesPanel.getWidgetCount()) {
            initGranularitiesPanel();
        }

        if (null != currentGranularity //
                && this.language == language) {

            selectCellForGranularity(currentGranularity);

        } else {
            this.language = language;
            deselectAllCells();
        }
    }

    private void selectCellForGranularity(final LanguageGranularity currentGranularity) {
        for (int i = 0; i < granularitiesPanel.getWidgetCount(); i++) {
            final HTML cell = (HTML) granularitiesPanel.getWidget(i);

            if (currentGranularity.label().equals(cell.getHTML())) {
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
        for (final String label : LabelHelper.labels(LanguageGranularity.values())) {
            granularitiesPanel.add(buildCellGranularity(label));
        }
    }

    private HTML buildCellGranularity(final String granularityLabel) {
        final HTML cell = new HTML(granularityLabel);
        cell.addStyleName(style.cell());
        cell.setPixelSize(100, 100);

        cell.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(final ClickEvent event) {
                goToNextStep(granularityLabel);
            }

        });
        return cell;
    }

    private void goToNextStep(final String granularityLabel) {
        presenter.goTo(new ThemeLevelPlace( //
                language, //
                LabelHelper.fromGranularity(granularityLabel), //
                Pgu_game.gameConfig.theme() //
                ));
    }
}
