package pgu.client.ui.welcome;

import java.util.ArrayList;
import java.util.HashMap;

import pgu.client.Pgu_lang_az;
import pgu.client.enums.Language;
import pgu.client.language.HasLevels;
import pgu.client.place.GamePlace;
import pgu.client.ui.utils.AppCell;
import pgu.client.ui.utils.AppCell.Skin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class WelcomeViewImpl extends Composite implements WelcomeView {

    interface WelcomeViewImplUiBinder extends UiBinder<Widget, WelcomeViewImpl> {
    }

    private static WelcomeViewImplUiBinder uiBinder = GWT.create(WelcomeViewImplUiBinder.class);

    @UiField
    HTMLPanel menuArea;
    @UiField
    HTMLPanel rowOfCurrentLevel, rowOfLevelSettings, rowOfStart;
    @UiField
    HTMLPanel rowOfLanguages, rowOfSubSelections;

    @UiField(provided=true)
    AppCell levelBtn, startBtn;

    public WelcomeViewImpl() {

        levelBtn = new AppCell(AppCell.Skin.ICE);
        startBtn = new AppCell(AppCell.Skin.FIRE);

        initWidget(uiBinder.createAndBindUi(this));

        startBtn.setHTML("<div><p>START</p></div>");
        levelBtn.setSize(520, 350);
        startBtn.setSize(320, 150);

        onStop();
    }

    @UiHandler("startBtn")
    public void clickStart(final ClickEvent e) {
        presenter.goTo(new GamePlace());
    }

    private final HashMap<AppCell, Language> cell2lg = new HashMap<AppCell, Language>();
    private final HashMap<Language, AppCell> lg2cell = new HashMap<Language, AppCell>();

    @UiHandler("levelBtn")
    public void clickLevel(final ClickEvent e) {
        //        presenter.goTo(new LanguageLevelPlace(Pgu_lang_az.gameConfig.language()));

        rowOfCurrentLevel.setVisible(false);
        rowOfLevelSettings.setVisible(true);

        // lazy init on rowOfLanguages
        // alphabet
        final Language currentLanguage = Pgu_lang_az.gameConfig.language();

        for (final Language language : Language.values()) {
            final AppCell.Skin skin = language == currentLanguage ? AppCell.Skin.FIRE : AppCell.Skin.ICE;

            final AppCell appCell = buildAppCellForLevel(skin, language.label());
            rowOfLanguages.add(appCell);

            cell2lg.put(appCell, language);
            lg2cell.put(language, appCell);
        }

        final ClickOnLanguageCell lgHandler = new ClickOnLanguageCell(this);
        for (int i = 0; i < rowOfLanguages.getWidgetCount(); i++) {
            ((AppCell) rowOfLanguages.getWidget(i)).addClickHandler(lgHandler);
        }


        // subselections
        fillRowOfSubSelections(currentLanguage);
    }

    private void fillRowOfSubSelections(final Language currentLanguage) {
        rowOfSubSelections.clear();

        final HasLevels currentHasLevels = currentLanguage.getHasLevels();
        final ArrayList<String> currentSubselections = Pgu_lang_az.gameConfig.subselections();

        for (final String level : currentHasLevels.availableLevels()) {
            final AppCell.Skin skin = currentSubselections.contains(level) ? AppCell.Skin.FIRE : AppCell.Skin.ICE;

            final AppCell appCell = buildAppCellForLevel(skin, level);
            rowOfSubSelections.add(appCell);
        }

        final ClickOnSubSelectionCell subHandler = new ClickOnSubSelectionCell();
        for (int i = 0; i < rowOfSubSelections.getWidgetCount(); i++) {
            ((AppCell) rowOfSubSelections.getWidget(i)).addClickHandler(subHandler);
        }
    }

    private static class ClickOnLanguageCell implements ClickHandler {

        WelcomeViewImpl view;

        public ClickOnLanguageCell(final WelcomeViewImpl view) {
            this.view =view;
        }

        @Override
        public void onClick(final ClickEvent event) {
            final AppCell cell = (AppCell) event.getSource();

            final boolean isSelected = AppCell.Skin.FIRE == cell.getSkin();
            if (isSelected) {
                return;
            }

            // deselect current language
            final Language oldLanguage = Pgu_lang_az.gameConfig.language();
            view.lg2cell.get(oldLanguage).setSkin(Skin.ICE);

            Pgu_lang_az.gameConfig.language(null);
            Pgu_lang_az.gameConfig.subselections().clear();

            // select new language
            final Language newLanguage = view.cell2lg.get(cell);
            Pgu_lang_az.gameConfig.language(newLanguage);
            cell.setSkin(Skin.FIRE);

            // clear the subselections and re-populate according to the new language
            view.fillRowOfSubSelections(newLanguage);
        }

    }

    private static class ClickOnSubSelectionCell implements ClickHandler {

        @Override
        public void onClick(final ClickEvent event) {
            final AppCell cell = (AppCell) event.getSource();
            final String subSelection = cell.getText();

            final boolean isSelected = AppCell.Skin.FIRE == cell.getSkin();
            if (isSelected) {
                // deselect the subSelection
                Pgu_lang_az.gameConfig.subselections().remove(subSelection);

                cell.setSkin(AppCell.Skin.ICE);

            } else {
                // select the subSelection
                Pgu_lang_az.gameConfig.subselections().add(subSelection);

                cell.setSkin(AppCell.Skin.FIRE);
            }
        }
    }

    private AppCell buildAppCellForLevel(final AppCell.Skin skin, final String text) {

        final AppCell appCell = new AppCell(skin);
        appCell.setSize(320, 150);
        appCell.setText(text);

        final Style style = appCell.getElement().getStyle();
        style.setFloat(Style.Float.LEFT);
        style.setMargin(20, Unit.PX);

        return appCell;
    }

    private Presenter presenter;

    @Override
    public void setPresenter(final Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Widget asWidget() {
        // resize();
        // setHeight(Window.getClientHeight() + "px");
        return super.asWidget();
    }

    // private static final int MENU_HEIGHT_PORTRAIT = 100;
    // private static final int MENU_HEIGHT_LANDSCAPE = 100;

    private void resize() {
        // final int w = Window.getClientWidth();
        // final int h = Window.getClientHeight();
        //
        // final boolean isPortrait = w < h;
        // final int hMenu = isPortrait ? MENU_HEIGHT_PORTRAIT : MENU_HEIGHT_LANDSCAPE;
        // menuArea.setPixelSize(w, hMenu);
        // listArea.getElement().getStyle().setTop(hMenu, Unit.PX);
        // listArea.setWidth(w + "px");

        // final Orientation orientation = isPortrait ? Orientation.PORTRAIT : Orientation.LANDSCAPE;
        // final int btnTop = isPortrait ? 50 : 10;
        //
        // final int wBtn = w / 3 - 30;
        // final int hBtn = hMenu - 15 - btnTop;
        //
        // logo.setPixelSize(w - 3 * wBtn - 38, hMenu);
        // login.setPixelSize(wBtn, hBtn);
        // level.setPixelSize(wBtn, hBtn);
        // start.setPixelSize(wBtn, hBtn);
        //
        // final int marginTop = (hMenu - hBtn) / 2;
        // login.getElement().getStyle().setMarginTop(marginTop, Unit.PX);
        // level.getElement().getStyle().setMarginTop(marginTop, Unit.PX);
        // start.getElement().getStyle().setMarginTop(marginTop, Unit.PX);
        //
        // for (int i = 0; i < listArea.getWidgetCount(); i++) {
        // final Score score = (Score) listArea.getWidget(i);
        // score.setScoreSize(orientation, w - 50, hMenu);
        // }
    }

    @Override
    public void setCurrentLevel(final String currentLevel) {
        levelBtn.setHTML(currentLevel);
    }

    @Override
    public void onStop() {
        levelBtn.setHTML("");
        rowOfCurrentLevel.setVisible(true);
        rowOfLevelSettings.setVisible(false);
        rowOfStart.setVisible(true);

        rowOfLanguages.clear();
        rowOfSubSelections.clear();

    }

}
