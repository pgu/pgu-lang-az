package pgu.client.ui.welcome;

import pgu.client.ui.welcome.score.Score;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class WelcomeViewImpl extends Composite implements WelcomeView {

    interface WelcomeViewImplUiBinder extends UiBinder<Widget, WelcomeViewImpl> {
    }

    private static WelcomeViewImplUiBinder uiBinder = GWT.create(WelcomeViewImplUiBinder.class);

    @UiField
    HTMLPanel toolbarApp;
    @UiField
    HTMLPanel westArea;
    @UiField
    HTMLPanel eastArea;
    @UiField
    HTMLPanel level;
    @UiField
    HTMLPanel scores;

    @UiField
    HTML login;
    @UiField
    HTML google;
    @UiField
    HTML twitter;
    @UiField
    HTML facebook;
    @UiField
    HTML linkedin;
    @UiField
    HTML start;

    public WelcomeViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));

        login.setText("Login");
        google.setText("google");
        twitter.setText("twitter");
        facebook.setText("facebook");
        linkedin.setText("linkedin");
        start.setText("start");

        final ListBox language = new ListBox();
        language.addItem("Japan");
        level.add(language);

        final ListBox alphabet = new ListBox();
        alphabet.addItem("Hiragani");
        level.add(alphabet);

        final InlineLabel levelNb = new InlineLabel();
        levelNb.setText("1");
        level.add(levelNb);

        scores.add(new Score("45 sec, Jap/Hir/20, Toto"));
        scores.add(new Score("2 min 30 sec, Jap/Hir/1, Titi"));
        scores.add(new Score("10 min, Jap/Hir/30, Toto"));
        scores.add(new Score("5 min 22 sec, Jap/Hir/11, Tata"));

    }

    @Override
    public void setPresenter(final Presenter presenter) {
    }

    @Override
    public Widget asWidget() {

        final int w = Window.getClientWidth();
        final int h = Window.getClientHeight() - toolbarApp.getOffsetHeight();

        final int westAreaWidth = w * 90 / 100 - 10;
        westArea.setPixelSize(westAreaWidth, h);

        final int eastAreaWidth = w * 10 / 100 - 10;
        eastArea.setPixelSize(eastAreaWidth, h);

        return super.asWidget();
    }

}
