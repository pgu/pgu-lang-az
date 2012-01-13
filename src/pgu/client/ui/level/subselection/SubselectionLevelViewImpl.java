package pgu.client.ui.level.subselection;

import static pgu.client.enums.LabelHelper.is;

import java.util.ArrayList;

import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;
import pgu.client.enums.Theme;
import pgu.client.language.GreekAlphabet;
import pgu.client.language.HasLevels;
import pgu.client.language.Hiragana;
import pgu.client.language.Katakana;
import pgu.client.language.RussianAlphabet;
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
        for (int i = 0; i < subselectionsPanel.getWidgetCount(); i++) {
            subselectionsPanel.getWidget(i).removeStyleName(style.cellSelected());
        }
    }

    private void fillSubselectionsPanel(final Language language, final LanguageGranularity granularity,
            final Theme theme) {

        for (final String subselection : getSubselections(language, granularity, theme)) {
            subselectionsPanel.add(new CellSubselection(subselection));
        }
    }

    private ArrayList<String> getSubselections(final Language language, final LanguageGranularity granularity,
            final Theme theme) {
        HasLevels hasLevels = null;

        if (theme == Theme.KATAKANA) {
            hasLevels = Katakana.INSTANCE;

        } else if (theme == Theme.HIRAGANA) {
            hasLevels = Hiragana.INSTANCE;

        } else if (isRussianAlphabet(language, granularity)) {
            hasLevels = RussianAlphabet.INSTANCE;

        } else if (isGreekAlphabet(language, granularity)) {
            hasLevels = GreekAlphabet.INSTANCE;

        }

        return hasLevels.availableLevels();
    }

    private static boolean isRussianAlphabet(final Language language, final LanguageGranularity granularity) {
        return is(language.label(), Language.RUSSIAN) //
                && is(granularity.label(), LanguageGranularity.ALPHABET);
    }

    private static boolean isGreekAlphabet(final Language language, final LanguageGranularity granularity) {
        return is(language.label(), Language.GREEK) //
                && is(granularity.label(), LanguageGranularity.ALPHABET);
    }

    private static class CellSubselection extends HTML {

        private boolean isSelected = false;

        private final Style style = PguGameResources.INSTANCE.style();

        public CellSubselection(final String subselection) {
            super(subselection);

            addStyleName(style.cell());
            setPixelSize(100, 100);

            addClickHandler(new ClickHandler() {

                @Override
                public void onClick(final ClickEvent event) {

                    if (isSelected) {
                        removeStyleName(style.cellSelected());
                        isSelected = false;

                    } else {
                        addStyleName(style.cellSelected());
                        isSelected = true;
                    }
                }

            });
        }
    }
}
