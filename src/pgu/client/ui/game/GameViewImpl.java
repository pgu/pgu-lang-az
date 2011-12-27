package pgu.client.ui.game;

import pgu.client.place.WelcomePlace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class GameViewImpl extends Composite implements GameView {

    private static final int BTN_HEIGHT_LANDSCAPE = 43;

    interface GameViewImplUiBinder extends UiBinder<Widget, GameViewImpl> {
    }

    private static GameViewImplUiBinder uiBinder = GWT.create(GameViewImplUiBinder.class);

    @UiField
    HTMLPanel menuArea, gridArea;

    @UiField
    HTML help, time, restart, exit;

    public GameViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));

        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(final ResizeEvent event) {
                resize();
            }
        });
    }

    private Presenter presenter;

    @Override
    public void setPresenter(final Presenter presenter) {
        this.presenter = presenter;
    }

    @UiHandler("exit")
    public void clickExit(final ClickEvent e) {
        presenter.goTo(new WelcomePlace());
    }

    @Override
    public Widget asWidget() {
        resize();
        return super.asWidget();
    }

    private void resize() {
        final int w = Window.getClientWidth();
        final int h = Window.getClientHeight();

        final boolean isPortrait = w < h;

        final int hMenu = isPortrait ? 100 : 50;
        menuArea.setPixelSize(w, hMenu);

        // TODO PGU
        // time.setWidth(w - help.getElement().getClientWidth() - restart.getElement().getClientWidth()
        // - exit.getElement().getClientWidth() - 20 + "px");

        // int hBtn = 0;
        int wHelp = 0;
        int wRestart = 0;
        int wExit = 0;

        int paddingTop = 0;

        if (isPortrait) {
            paddingTop = 25;
            // hBtn = 2 * BTN_HEIGHT_LANDSCAPE;
            wHelp = 115;
            wRestart = 195;
            wExit = 96;
        } else {
            paddingTop = 0;
            // hBtn = BTN_HEIGHT_LANDSCAPE;
            wHelp = 115;
            wRestart = 195;
            wExit = 96;
        }

        final int hBtn = hMenu - paddingTop - 7;
        help.setPixelSize(wHelp, hBtn);
        restart.setPixelSize(wRestart, hBtn);
        exit.setPixelSize(wExit, hBtn);

        final int others = wHelp + wRestart + wExit;
        time.setPixelSize(w - 20 - others, hBtn);

        help.getElement().getStyle().setPaddingTop(paddingTop, Unit.PX);
        restart.getElement().getStyle().setPaddingTop(paddingTop, Unit.PX);
        exit.getElement().getStyle().setPaddingTop(paddingTop, Unit.PX);
        time.getElement().getStyle().setPaddingTop(paddingTop, Unit.PX);

    }

}
