package pgu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class ToolbarApp extends Composite {

    private static ToolbarAppUiBinder uiBinder = GWT.create(ToolbarAppUiBinder.class);

    interface ToolbarAppUiBinder extends UiBinder<Widget, ToolbarApp> {
    }

    @UiField
    HTMLPanel toolbarApp;
    @UiField
    HTML help;
    @UiField
    HTML start;
    @UiField
    HTML login;
    @UiField
    HTML level;

    public ToolbarApp() {
        initWidget(uiBinder.createAndBindUi(this));
        help.setText("Help");
        start.setText("Start");
        login.setText("Login");
        level.setText("Japan > Hiragana > Level 1");
    }

    public void resize(final int toolbarWidth, final int toolbarHeight) {
        toolbarApp.setPixelSize(toolbarWidth, toolbarHeight);
    }

    @UiHandler("login")
    public void login(final ClickEvent e) {
        login.setText("guilcher.pascal@gmail.com");
    }

}
