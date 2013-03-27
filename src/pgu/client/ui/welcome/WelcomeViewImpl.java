package pgu.client.ui.welcome;

import pgu.client.Pgu_lang_az;
import pgu.client.enums.LabelHelper;
import pgu.client.enums.Language;
import pgu.client.place.GamePlace;
import pgu.client.ui.utils.AppCell;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
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

    @UiField(provided=true)
    AppCell levelBtn, startBtn;

    public WelcomeViewImpl() {

        levelBtn = new AppCell(AppCell.Skin.ICE);
        startBtn = new AppCell(AppCell.Skin.FIRE);

        initWidget(uiBinder.createAndBindUi(this));

        startBtn.setHTML("<div style=\"line-height:2;\"><p>START</p></div>");
        levelBtn.setSize(520, 350);
        startBtn.setSize(320, 150);

        onStop();
    }

    @UiHandler("startBtn")
    public void clickStart(final ClickEvent e) {
        presenter.goTo(new GamePlace());
    }

    @UiHandler("levelBtn")
    public void clickLevel(final ClickEvent e) {
        // TODO PGU Mar 27, 2013
        // TODO PGU Mar 27, 2013 remove LanguageLevelPlace
        // TODO PGU Mar 27, 2013 insert LanguageLevelView
        // TODO PGU Mar 27, 2013
        //        presenter.goTo(new LanguageLevelPlace(Pgu_lang_az.gameConfig.language()));

        rowOfCurrentLevel.setVisible(false);
        rowOfLevelSettings.setVisible(true);
        // TODO PGU Mar 27, 2013 build row of languages and select current languages

        for (final Language language : LabelHelper.sort(Language.values())) {
            final AppCell.Skin skin = language == Pgu_lang_az.gameConfig.language() ? AppCell.Skin.FIRE : AppCell.Skin.ICE;

            final AppCell appCell = new AppCell(skin);
            appCell.setSize(320, 150);
            appCell.setText(language.label());

            final Style style = appCell.getElement().getStyle();
            style.setFloat(Style.Float.LEFT);
            style.setMargin(20, Unit.PX);

            rowOfLevelSettings.add(appCell);
        }

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

        rowOfLevelSettings.clear();

    }

}
