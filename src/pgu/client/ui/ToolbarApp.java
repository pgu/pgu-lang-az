package pgu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class ToolbarApp extends Composite {

    private static ToolbarAppUiBinder uiBinder = GWT.create(ToolbarAppUiBinder.class);

    interface ToolbarAppUiBinder extends UiBinder<Widget, ToolbarApp> {
    }

    public ToolbarApp() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiField
    HTMLPanel toolbarApp;
    @UiField
    HTML chat;

    public void resize(final int toolbarWidth, final int toolbarHeight) {
        toolbarApp.setPixelSize(toolbarWidth, toolbarHeight);
    }

}
