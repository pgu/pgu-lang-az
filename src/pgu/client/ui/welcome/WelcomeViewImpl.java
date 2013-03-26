package pgu.client.ui.welcome;

import pgu.client.GameConfig;
import pgu.client.Pgu_lang_az;
import pgu.client.place.GamePlace;
import pgu.client.place.LanguageLevelPlace;
import pgu.client.ui.utils.AppCell;

import com.google.gwt.core.client.GWT;
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
    // HTMLPanel listArea

    //    @UiField
    //    HTML logo;
    // , loginText, levelText, startText
    // @UiField
    // HTMLPanel login, level, start;
    @UiField(provided=true)
    AppCell levelBtn, startBtn;

    public WelcomeViewImpl() {

        levelBtn = new AppCell("ice");
        startBtn = new AppCell("fire");

        initWidget(uiBinder.createAndBindUi(this));

        //        loginTest.setText("LOGIN");
        //        levelBtn.setText("LEVEL");
        startBtn.setHTML("<div style=\"line-height:2;\"><p>START</p></div>");
        //        loginTest.setSize(175, 100);
        levelBtn.setSize(520, 350);
        startBtn.setSize(320, 150);

        for (int i = 0; i < 100; i++) {
            // listArea.add(new Score("Business girl", i + " min 30 sec, Jap > Hir > 1"));
            // listArea.add(new Score("Awesome dude", i + "0 min 30 sec, Jap > Hir > 10"));
        }

        // Window.addResizeHandler(new ResizeHandler() {
        //
        // @Override
        // public void onResize(final ResizeEvent event) {
        // resize();
        // }
        // });

        final GameConfig gc = Pgu_lang_az.gameConfig;
        final String currentLevel= "<div style=\"line-height:2;\"><p>" + gc.language() + "</p><p>" + gc.theme() + "</p><p>" + gc.subselections() +"</p></div>";
        levelBtn.setHTML(currentLevel);

    }

    @UiHandler("startBtn")
    public void clickStart(final ClickEvent e) {
        presenter.goTo(new GamePlace());
    }

    @UiHandler("levelBtn")
    public void clickLevel(final ClickEvent e) {
        presenter.goTo(new LanguageLevelPlace(Pgu_lang_az.gameConfig.language()));
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

}
