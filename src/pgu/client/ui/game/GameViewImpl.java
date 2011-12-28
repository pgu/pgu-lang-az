package pgu.client.ui.game;

import pgu.client.place.WelcomePlace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
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

    private static final int MENU_HEIGHT_PORTRAIT = 100;
    private static final int MENU_HEIGHT_LANDSCAPE = 50;

    private final static int W_HELP = 115;
    private final static int W_RESTART = 195;
    private final static int W_EXIT = 96;

    private boolean isPortrait_old = true;
    private boolean isPortrait = true;

    private void resize() {
        final int w = Window.getClientWidth();
        final int h = Window.getClientHeight();

        isPortrait = w < h;

        final int hMenu = isPortrait ? MENU_HEIGHT_PORTRAIT : MENU_HEIGHT_LANDSCAPE;
        menuArea.setPixelSize(w, hMenu);
        gridArea.getElement().getStyle().setTop(hMenu, Unit.PX);
        gridArea.setWidth(w + "px");
        gridArea.setHeight(h - hMenu + "px");

        final int paddingTop = isPortrait ? 25 : 0;

        final int hBtn = hMenu - paddingTop - 7;

        help.setPixelSize(W_HELP, hBtn);
        restart.setPixelSize(W_RESTART, hBtn);
        exit.setPixelSize(W_EXIT, hBtn);

        time.setPixelSize(w - 20 - (W_HELP + W_RESTART + W_EXIT), hBtn);

        help.getElement().getStyle().setPaddingTop(paddingTop, Unit.PX);
        restart.getElement().getStyle().setPaddingTop(paddingTop, Unit.PX);
        exit.getElement().getStyle().setPaddingTop(paddingTop, Unit.PX);
        time.getElement().getStyle().setPaddingTop(paddingTop, Unit.PX);

        displayGame();
    }

    int counter = 0;

    private void displayGame() {
        final int nbRows = isPortrait ? 8 : 4;
        counter = 0;

        if (0 == gridArea.getWidgetCount()) {
            for (int i = 0; i < nbRows; i++) {
                gridArea.add(createRow());
            }
            return;
        }

        if (isPortrait_old != isPortrait) {
            gridArea.clear();
            for (int i = 0; i < nbRows; i++) {
                gridArea.add(createRow());
            }
            isPortrait_old = isPortrait;
            return;
        }

    }

    private FlowPanel createRow() {
        final FlowPanel row = new FlowPanel();

        final int nbCells = isPortrait ? 4 : 8;
        for (int i = 0; i < nbCells; i++) {
            row.add(createCell());
        }

        // clear floating
        final HTML divClearingFloat = new HTML();
        divClearingFloat.getElement().getStyle().setProperty("clear", "both");
        row.add(divClearingFloat);
        return row;
    }

    private GameCell createCell() {
        counter++;
        final GameCell cell = new GameCell().index(counter);
        final Style style = cell.getElement().getStyle();
        // TODO PGU
        style.setWidth(50, Unit.PX);
        style.setHeight(50, Unit.PX);
        return cell;
    }

}
