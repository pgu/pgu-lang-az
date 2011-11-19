package pgu.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class WestArea extends Composite {

    private static WestAreaUiBinder uiBinder = GWT.create(WestAreaUiBinder.class);

    interface WestAreaUiBinder extends UiBinder<Widget, WestArea> {
    }

    public WestArea() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiField
    HTMLPanel westArea;
    @UiField
    HTML liveScore;

    public void resize(final int width, final int height) {
        westArea.setPixelSize(width - 50, height);
    }

}
