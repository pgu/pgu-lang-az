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
    HTMLPanel westArea;
    @UiField
    HTMLPanel eastArea;

    @UiField
    HTMLPanel scores;

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

        logo.setText("LOGO");
        login.setText("LOGIN");
        level.setText("LEVEL");
        start.setText("START");

        scores.add(new Score("45 sec, Jap/Hir/20, Toto"));
        scores.add(new Score("2 min 30 sec, Jap/Hir/1, Titi"));
        scores.add(new Score("10 min, Jap/Hir/30, Toto"));
        scores.add(new Score("5 min 22 sec, Jap/Hir/11, Tata"));

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

        final boolean isPortrait = w < h;

        int eastAreaWidth = 0;
        if (isPortrait) {
            eastAreaWidth = w / 3;
        } else {
            eastAreaWidth = w / 4;
        }

        eastArea.setPixelSize(eastAreaWidth, h);
        westArea.setPixelSize(w - eastAreaWidth, h);

        final int hBtn = h * 30 / 100 + 4;
        logo.setPixelSize(eastAreaWidth, h - 3 * hBtn - 13);

        final int wBtn = eastAreaWidth - 4;
        login.setPixelSize(wBtn, hBtn);
        level.setPixelSize(wBtn, hBtn);
        start.setPixelSize(wBtn, hBtn);

    }

}
