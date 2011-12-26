package pgu.client.ui.welcome;

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

    private void resize() {
        final int w = Window.getClientWidth();
        final int h = Window.getClientHeight();

        final boolean isPortrait = w < h;
        final int hMenu = isPortrait ? 200 : 100;
        menuArea.setPixelSize(w, hMenu);

        if (isPortrait) {
            // TODO PGU appliquer le css de portrait pour la menu bar
        } else {
            // TODO PGU appliquer le css de paysage pour la menu bar
        }

        // listArea.setPixelSize(w, h - 50);
        listArea.setWidth(w + "px");

        // final int hBtn = h * 30 / 100 + 4;
        // logo.setPixelSize(menuAreaWidth, h - 3 * hBtn - 13);

        // final int wBtn = menuAreaWidth - 4;

        final int wBtn = w / 3 - 20;
        final int hBtn = hMenu - 7;
        logo.setPixelSize(w - 3 * wBtn - 18, hBtn);
        login.setPixelSize(wBtn, hBtn);
        level.setPixelSize(wBtn, hBtn);
        start.setPixelSize(wBtn, hBtn);

        login.getElement().getStyle().setFontSize(6.5, Unit.EM);
        level.getElement().getStyle().setFontSize(6.5, Unit.EM);
        start.getElement().getStyle().setFontSize(6.5, Unit.EM);

        for (int i = 0; i < listArea.getWidgetCount(); i++) {
            final Score score = (Score) listArea.getWidget(i);
            score.setPixelSize(w - 20, 50);
        }
    }

}
