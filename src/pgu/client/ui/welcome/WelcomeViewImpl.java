package pgu.client.ui.welcome;

import pgu.client.enums.Orientation;
import pgu.client.place.GamePlace;
import pgu.client.ui.welcome.level.LevelPanel;
import pgu.client.ui.welcome.score.Score;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class WelcomeViewImpl extends Composite implements WelcomeView {

    interface WelcomeViewImplUiBinder extends UiBinder<Widget, WelcomeViewImpl> {
    }

    private static WelcomeViewImplUiBinder uiBinder = GWT.create(WelcomeViewImplUiBinder.class);

    @UiField
    HTMLPanel listArea, menuArea;

    @UiField
    HTML logo, loginText, levelText, startText;
    @UiField
    HTMLPanel login, level, start;

    public WelcomeViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));

        for (int i = 0; i < 100; i++) {
            // listArea.add(new Score("Business girl", i + " min 30 sec, Jap > Hir > 1"));
            // listArea.add(new Score("Awesome dude", i + "0 min 30 sec, Jap > Hir > 10"));
        }

        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(final ResizeEvent event) {
                resize();
            }
        });

        Window.addWindowScrollHandler(new ScrollHandler() {

            @Override
            public void onWindowScroll(final ScrollEvent event) {
                // TODO PGU a voir si a utiliser ou non...
                // menuArea.getElement().getStyle().setTop(event.getScrollTop(), Unit.PX);
            }

        });
    }

    @UiHandler("startText")
    public void clickStart(final ClickEvent e) {
        presenter.goTo(new GamePlace());
    }

    @UiHandler("levelText")
    public void clickLevel(final ClickEvent e) {
        showLevels();
    }

    private Presenter presenter;

    @Override
    public void setPresenter(final Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Widget asWidget() {
        resize();
        return super.asWidget();
    }

    private static final int MENU_HEIGHT_PORTRAIT = 200;
    private static final int MENU_HEIGHT_LANDSCAPE = 100;

    private void resize() {
        final int w = Window.getClientWidth();
        final int h = Window.getClientHeight();

        final boolean isPortrait = w < h;
        final int hMenu = isPortrait ? MENU_HEIGHT_PORTRAIT : MENU_HEIGHT_LANDSCAPE;
        menuArea.setPixelSize(w, hMenu);
        listArea.getElement().getStyle().setTop(hMenu, Unit.PX);
        listArea.setWidth(w + "px");

        final Orientation orientation = isPortrait ? Orientation.PORTRAIT : Orientation.LANDSCAPE;
        final int btnTop = isPortrait ? 50 : 10;

        final int wBtn = w / 3 - 30;
        final int hBtn = hMenu - 15 - btnTop;

        logo.setPixelSize(w - 3 * wBtn - 38, hMenu);
        login.setPixelSize(wBtn, hBtn);
        level.setPixelSize(wBtn, hBtn);
        start.setPixelSize(wBtn, hBtn);

        final int marginTop = (hMenu - hBtn) / 2;
        login.getElement().getStyle().setMarginTop(marginTop, Unit.PX);
        level.getElement().getStyle().setMarginTop(marginTop, Unit.PX);
        start.getElement().getStyle().setMarginTop(marginTop, Unit.PX);

        for (int i = 0; i < listArea.getWidgetCount(); i++) {
            final Score score = (Score) listArea.getWidget(i);
            score.setScoreSize(orientation, w - 50, hMenu);
        }
    }

    // ////////////////////////////////////////////// LEVELS

    private LevelPanel levelPanel;

    private void createLevelPanel() {
        final PopupPanel popupLevel = new PopupPanel();
        popupLevel.hide();
        popupLevel.setAnimationEnabled(true);
        popupLevel.setAutoHideOnHistoryEventsEnabled(true);
        popupLevel.setGlassEnabled(true);
        // popupLevel.setModal(true);
        popupLevel.setModal(false);
        levelPanel = new LevelPanel(popupLevel);
        popupLevel.add(levelPanel);
    }

    public void showLevels() {
        if (null == levelPanel) {
            createLevelPanel();
        }
        levelPanel.show();
    }

}
