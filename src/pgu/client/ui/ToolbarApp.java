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
    HTMLPanel panelRight;
    @UiField
    HTMLPanel panelRightInternal;
    @UiField
    HTML help;
    @UiField
    HTML time;
    @UiField
    HTML start;
    @UiField
    HTML login;
    @UiField
    HTML level;

    public ToolbarApp() {
        initWidget(uiBinder.createAndBindUi(this));
        toolbarApp.setHeight(HEIGHT + "px");

        help.setText("Help");
        level.setText("Japan > Hiragana > Level 1");
        time.setText("00:30");
        start.setText("Start");
        login.setText("Login");
    }

    public void resize(final int toolbarWidth) {
        // toolbarApp.setPixelSize(toolbarWidth, toolbarHeight);
        //
        // panelRight.setHeight(toolbarHeight - 5 + "px");
        // panelRightInternal.setHeight(toolbarHeight - 5 + "px");
        // start.setHeight(toolbarHeight - 35 + "px");
        // login.setHeight(toolbarHeight - 35 + "px");
        // login.getElement().setAttribute("paddingTop", "18px");
        // login.getElement().setAttribute("paddingBottom", "18px");

        // final int h = toolbarHeight - 8;
        // final String heightDemi = h / 2 + "px";
        // final String height = h + "px";
        // help.setHeight(height);
        // level.setHeight(heightDemi);
        // time.setHeight(heightDemi);
    }

    @UiHandler("login")
    public void login(final ClickEvent e) {
        login.setVisible(false);
    }

    private static final int HEIGHT = 50;

    public int getHeight() {
        return HEIGHT;
    }

}
