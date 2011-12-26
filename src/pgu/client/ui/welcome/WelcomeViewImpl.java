package pgu.client.ui.welcome;

import pgu.client.ui.welcome.score.Score;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
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
            listArea.add(new Score(i + " min 30 sec, Jap/Hir/1, Toto"));
            listArea.add(new Score(i + "0 min 30 sec, Jap/Hir/10, Titi"));
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

        int btnTop = 0;
        double btnFontsize = 0;
        if (isPortrait) {
            btnTop = 50;
            btnFontsize = 5.5;
        } else {
            btnTop = 10;
            btnFontsize = 5.5;
            // TODO PGU appliquer le css de paysage pour la menu bar
        }

        listArea.setWidth(w + "px");

        final int wBtn = w / 3 - 20;
        final int hBtn = hMenu - 7 - btnTop;

        logo.setPixelSize(w - 3 * wBtn - 18, hBtn + btnTop);
        login.setPixelSize(wBtn, hBtn);
        level.setPixelSize(wBtn, hBtn);
        start.setPixelSize(wBtn, hBtn);

        final Style loginS = login.getElement().getStyle();
        final Style levelS = level.getElement().getStyle();
        final Style startS = start.getElement().getStyle();

        loginS.setFontSize(btnFontsize, Unit.EM);
        levelS.setFontSize(btnFontsize, Unit.EM);
        startS.setFontSize(btnFontsize, Unit.EM);

        loginS.setPaddingTop(btnTop, Unit.PX);
        levelS.setPaddingTop(btnTop, Unit.PX);
        startS.setPaddingTop(btnTop, Unit.PX);

        for (int i = 0; i < listArea.getWidgetCount(); i++) {
            final Score score = (Score) listArea.getWidget(i);
            score.setPixelSize(w - 20, 50);
        }
    }

}
