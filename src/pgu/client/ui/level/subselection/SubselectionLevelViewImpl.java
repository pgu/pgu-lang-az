package pgu.client.ui.level.subselection;

import java.util.ArrayList;
import java.util.Collections;

import pgu.client.Pgu_game;
import pgu.client.enums.GameSize;
import pgu.client.enums.Language;
import pgu.client.enums.LanguageGranularity;
import pgu.client.enums.Theme;
import pgu.client.language.HasLevels;
import pgu.client.language.chinese.ChineseWords;
import pgu.client.language.greek.GreekAlphabet;
import pgu.client.language.japanese.Hiragana;
import pgu.client.language.japanese.Katakana;
import pgu.client.language.russian.RussianAlphabet;
import pgu.client.place.GamePlace;
import pgu.client.ui.style.PguGameResources;
import pgu.client.ui.style.PguGameResources.Style;
import pgu.client.utils.guava.Lists;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class SubselectionLevelViewImpl extends Composite implements SubselectionLevelView {

    private static SubselectionLevelViewImplUiBinder uiBinder = GWT.create(SubselectionLevelViewImplUiBinder.class);

    interface SubselectionLevelViewImplUiBinder extends UiBinder<Widget, SubselectionLevelViewImpl> {
    }

    @UiField
    HTMLPanel container, subselectionsPanel;

    @UiField
    Button btnOk;

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

        this.language = language;
        this.granularity = granularity;
        this.theme = theme;

        container.setPixelSize(Window.getClientWidth(), Window.getClientHeight());

        subselectionsPanel.clear();
        fillSubselectionsPanel();

        if (!currentSubselections.isEmpty() //
                && Pgu_game.gameConfig.theme() == this.theme //
                && Pgu_game.gameConfig.granularity() == this.granularity //
                && Pgu_game.gameConfig.language() == this.language) {

            selectCellsForSubselections(currentSubselections);
        }
    }

    private void selectCellsForSubselections(final ArrayList<String> currentSubselections) {

        for (int i = 0; i < subselectionsPanel.getWidgetCount(); i++) {
            final CellSubselection cell = (CellSubselection) subselectionsPanel.getWidget(i);

            if (currentSubselections.contains(cell.getHTML())) {
                cell.select();
            } else {
                cell.deselect();
            }
        }
    }

    private void fillSubselectionsPanel() {
        for (final String subselection : getSubselections()) {
            subselectionsPanel.add(new CellSubselection(subselection));
        }
    }

    private ArrayList<String> getSubselections() {
        HasLevels hasLevels = null;

        if (theme == Theme.KATAKANA) {
            hasLevels = Katakana.INSTANCE;

        } else if (theme == Theme.HIRAGANA) {
            hasLevels = Hiragana.INSTANCE;

        } else if (isRussianAlphabet()) {
            hasLevels = RussianAlphabet.INSTANCE;

        } else if (isGreekAlphabet()) {
            hasLevels = GreekAlphabet.INSTANCE;

        } else if (isChineseWords()) {
            hasLevels = ChineseWords.INSTANCE;

        }

        final ArrayList<String> levels = hasLevels.availableLevels();
        Collections.sort(levels);
        return levels;
    }

    private boolean isRussianAlphabet() {
        return Language.RUSSIAN == language //
                && LanguageGranularity.ALPHABET == granularity;
    }

    private boolean isGreekAlphabet() {
        return Language.GREEK == language //
                && LanguageGranularity.ALPHABET == granularity;
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
                        deselect();

                    } else {
                        select();
                    }
                }

            });
        }

        public void select() {
            addStyleName(style.cellSelected());
            isSelected = true;
        }

        public void deselect() {
            removeStyleName(style.cellSelected());
            isSelected = false;
        }
    }

    @UiHandler("btnOk")
    public void clickOk(final ClickEvent e) {
        Pgu_game.gameConfig //
                .size(GameSize.BIG) //
                .language(language) //
                .granularity(granularity) //
                .theme(theme);

        Pgu_game.gameConfig.subselections().clear();
        Pgu_game.gameConfig.subselections().addAll(getSelectedSubselections());

        if (isChineseWords()) {
            Pgu_game.gameConfig.size(GameSize.MEDIUM);
        }

        presenter.goTo(new GamePlace());
    }

    private boolean isChineseWords() {
        return Language.CHINESE == language //
                && LanguageGranularity.WORD == granularity;
    }

    private ArrayList<String> getSelectedSubselections() {
        final ArrayList<String> selecteds = Lists.newArrayList();

        for (int i = 0; i < subselectionsPanel.getWidgetCount(); i++) {
            final CellSubselection cell = (CellSubselection) subselectionsPanel.getWidget(i);
            if (cell.isSelected) {
                selecteds.add(cell.getHTML());
            }
        }

        return selecteds;
    }

}
