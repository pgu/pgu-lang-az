package pgu.client.ui.level.subselection;

import java.util.ArrayList;

import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;
import pgu.client.enums.Theme;
import pgu.client.ui.style.PguGameResources;
import pgu.client.ui.style.PguGameResources.Style;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class SubselectionLevelViewImpl extends Composite implements SubselectionLevelView {

    private static SubselectionLevelViewImplUiBinder uiBinder = GWT.create(SubselectionLevelViewImplUiBinder.class);

    interface SubselectionLevelViewImplUiBinder extends UiBinder<Widget, SubselectionLevelViewImpl> {
    }

    @UiField
    HTMLPanel subselectionsPanel;

    private final Style style;
    private Language language;
    private LanguageGranularity granularity;
    private Theme theme;

    public SubselectionLevelViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
        style = PguGameResources.INSTANCE.style();
    }

    private Presenter presenter;

    @Override
    public void setPresenter(final Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displaySubselections( //
            final ArrayList<String> currentSubselections, //
            final Theme theme, //
            final LanguageGranularity granularity, //
            final Language language) {

        subselectionsPanel.clear();
        fillSubselectionsPanel(language, granularity, theme);

        if (!currentSubselections.isEmpty() //
                && this.theme == theme //
                && this.granularity == granularity //
                && this.language == language) {

            selectCellsForSubselections(currentSubselections);

        } else {
            this.language = language;
            this.granularity = granularity;
            this.theme = theme;
            deselectAllCells();
        }

    }

    private void selectCellsForSubselections(final ArrayList<String> currentSubselections) {
        // TODO PGU
    }

    private void deselectAllCells() {
        // TODO PGU
    }

    private void fillSubselectionsPanel(final Language language2, final LanguageGranularity granularity2,
            final Theme theme2) {
        // TODO PGU
    }

}
