package pgu.client.ui.welcome;

import pgu.client.ui.welcome.score.Score;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
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
        login.setText("LOGIN");
        level.setText("LEVEL");
        start.setText("START");

        for (int i = 0; i < 10; i++) {
            listArea.add(new Score("45 sec, Jap/Hir/20, Toto"));
            listArea.add(new Score("2 min 30 sec, Jap/Hir/1, Titi"));
            listArea.add(new Score("10 min, Jap/Hir/30, Toto"));
            listArea.add(new Score("5 min 22 sec, Jap/Hir/11, Tata"));

        }

        Window.addResizeHandler(new ResizeHandler() {

            @Override
            public void onResize(final ResizeEvent event) {
                resize();
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

        // final boolean isPortrait = w < h;

        // int menuAreaWidth = 0;
        // if (isPortrait) {
        // menuAreaWidth = w / 3;
        // } else {
        // menuAreaWidth = w / 4;
        // }

        menuArea.setPixelSize(w, 50);
        listArea.setPixelSize(w, h - 50);

        // final int hBtn = h * 30 / 100 + 4;
        // logo.setPixelSize(menuAreaWidth, h - 3 * hBtn - 13);

        // final int wBtn = menuAreaWidth - 4;

        final int wBtn = w / 3 - 20;
        logo.setPixelSize(w - 3 * wBtn - 18, 40);
        login.setPixelSize(wBtn, 40);
        level.setPixelSize(wBtn, 40);
        start.setPixelSize(wBtn, 40);

        for (int i = 0; i < listArea.getWidgetCount(); i++) {
            final Score score = (Score) listArea.getWidget(i);
            score.setPixelSize(w - 20, 50);
        }
    }

}
