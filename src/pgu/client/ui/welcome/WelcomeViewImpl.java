package pgu.client.ui.welcome;

import pgu.client.enums.Orientation;
import pgu.client.ui.welcome.score.Score;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class WelcomeViewImpl extends Composite implements WelcomeView {

    interface WelcomeViewImplUiBinder extends UiBinder<Widget, WelcomeViewImpl> {
    }

    private static WelcomeViewImplUiBinder uiBinder = GWT.create(WelcomeViewImplUiBinder.class);

    @UiField
    HTMLPanel listArea;
    @UiField
    HTMLPanel menuArea;

    @UiField
    HTML logo;
    @UiField
    HTML login;
    @UiField
    HTML level;
    @UiField
    HTML start;

    public WelcomeViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));

        // logo.setText("LOGO");
        // login.setText("LOGIN");
        // level.setText("LEVEL");
        // start.setText("START");

        for (int i = 0; i < 100; i++) {
            listArea.add(new Score("Business girl", i + " min 30 sec, Jap > Hir > 1"));
            listArea.add(new Score("Awesome dude", i + "0 min 30 sec, Jap > Hir > 10"));
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
                GWT.log(" event.getScrollTop() " + event.getScrollTop());
                menuArea.getElement().getStyle().setTop(event.getScrollTop(), Unit.PX);
            }

        });
    }

    @Override
    public void setPresenter(final Presenter presenter) {
    }

    @Override
    public Widget asWidget() {
        resize();
        return super.asWidget();
    }

    private static final int MENU_HEIGHT_PORTRAIT = 200;

    private void resize() {
        final int w = Window.getClientWidth();
        final int h = Window.getClientHeight();

        final boolean isPortrait = w < h;
        final int hMenu = isPortrait ? MENU_HEIGHT_PORTRAIT : 100;
        menuArea.setPixelSize(w, hMenu);
        listArea.getElement().getStyle().setTop(hMenu, Unit.PX);

        final Orientation orientation = isPortrait ? Orientation.PORTRAIT : Orientation.PAYSAGE;
        final int btnTop = isPortrait ? 50 : 10;

        listArea.setWidth(w + "px");

        final int wBtn = w / 3 - 20;
        final int hBtn = hMenu - 7 - btnTop;

        logo.setPixelSize(w - 3 * wBtn - 18, hBtn + btnTop);
        login.setPixelSize(wBtn, hBtn);
        level.setPixelSize(wBtn, hBtn);
        start.setPixelSize(wBtn, hBtn);

        login.getElement().getStyle().setPaddingTop(btnTop, Unit.PX);
        level.getElement().getStyle().setPaddingTop(btnTop, Unit.PX);
        start.getElement().getStyle().setPaddingTop(btnTop, Unit.PX);

        for (int i = 0; i < listArea.getWidgetCount(); i++) {
            final Score score = (Score) listArea.getWidget(i);
            score.setScoreSize(orientation, w - 20, hMenu);
        }
    }

}
