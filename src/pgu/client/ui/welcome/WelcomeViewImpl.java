package pgu.client.ui.welcome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import pgu.client.enums.Language;
import pgu.client.language.HasLevels;
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

        levelBtn.setSize(520, 250);
        startBtn.setSize(320, 150);

        // lg cells
        for (final Language language : Language.values()) {

            final AppCell appCell = buildAppCellForLevel(AppCell.Skin.ICE, language.label());
            rowOfLanguages.add(appCell);

            cell2lg.put(appCell, language);
            lg2cell.put(language, appCell);
        }

        final ClickOnLanguageCell lgHandler = new ClickOnLanguageCell(this);
        for (int i = 0; i < rowOfLanguages.getWidgetCount(); i++) {
            ((AppCell) rowOfLanguages.getWidget(i)).addClickHandler(lgHandler);
        }

        // init values
        onStop();
    }

    @UiHandler("startBtn")
    public void clickStart(final ClickEvent e) {
        presenter.goToGame();
    }

    private final HashMap<AppCell, Language> cell2lg = new HashMap<AppCell, Language>();
    private final HashMap<Language, AppCell> lg2cell = new HashMap<Language, AppCell>();

    @UiHandler("levelBtn")
    public void clickLevel(final ClickEvent e) {
        rowOfCurrentLevel.setVisible(false);
        rowOfLevelSettings.setVisible(true);

        // alphabet
        final Language currentLanguage = presenter.getLanguage();

        for (final Entry<Language, AppCell> en : lg2cell.entrySet()) {
            final boolean isCurrent = en.getKey() == currentLanguage;
            en.getValue().setSkin(isCurrent ? AppCell.Skin.FIRE : AppCell.Skin.ICE);
        }

        // subselections
        fillRowOfSubSelections();
    }

    private void fillRowOfSubSelections() {
        rowOfSubSelections.clear();

        final Language language = presenter.getLanguage();
        final ArrayList<String> subSelections = presenter.getSubSelections();

        final HasLevels hasLevels = language.getHasLevels();

        for (final String level : hasLevels.availableLevels()) {
            final AppCell.Skin skin = subSelections.contains(level) ? AppCell.Skin.FIRE : AppCell.Skin.ICE;

            final AppCell appCell = buildAppCellForLevel(skin, level);
            rowOfSubSelections.add(appCell);
        }

        final ClickOnSubSelectionCell subHandler = new ClickOnSubSelectionCell(this);
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
            final Language oldLanguage = view.presenter.getLanguage();
            view.lg2cell.get(oldLanguage).setSkin(Skin.ICE);

            view.presenter.setLanguage(null);
            view.presenter.getSubSelections().clear();

            // select new language
            final Language newLanguage = view.cell2lg.get(cell);
            cell.setSkin(Skin.FIRE);

            view.presenter.setLanguage(newLanguage);
            view.presenter.getSubSelections().clear();


            // clear the subselections and re-populate according to the new language
            view.fillRowOfSubSelections();
        }

    }

    private static class ClickOnSubSelectionCell implements ClickHandler {

        WelcomeViewImpl view;

        public ClickOnSubSelectionCell(final WelcomeViewImpl view) {
            this.view =view;
        }

        @Override
        public void onClick(final ClickEvent event) {
            final AppCell cell = (AppCell) event.getSource();
            final String subSelection = cell.getText();

            final boolean isSelected = AppCell.Skin.FIRE == cell.getSkin();
            if (isSelected) {
                // deselect the subSelection
                view.presenter.getSubSelections().remove(subSelection);

                cell.setSkin(AppCell.Skin.ICE);

            } else {
                // select the subSelection
                view.presenter.getSubSelections().add(subSelection);

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

        for (int i = 0; i < rowOfLanguages.getWidgetCount(); i++) {
            final AppCell cell = (AppCell) rowOfLanguages.getWidget(i);

            if (cell.getSkin() == Skin.FIRE) {
                cell.setSkin(AppCell.Skin.ICE);
            }
        }

        rowOfSubSelections.clear();
    }

}
