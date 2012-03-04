package pgu.client.ui.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class AppCell extends Composite implements HasClickHandlers {

    private static AppCellUiBinder uiBinder = GWT.create(AppCellUiBinder.class);

    interface AppCellUiBinder extends UiBinder<Widget, AppCell> {
    }

    @UiField
    HTMLPanel cellExt2, cellExt1, cellExt, cellMed, cellInt, cellInt1, cellInt2;
    @UiField
    HTML cellText;

    public AppCell() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void setText(final String text) {
        cellText.setText(text);
    }

    public void setSize(final int width, final int height) {
        int h = height;

        cellExt2.setSize(width + "px", h + "px");
        cellExt1.setHeight(h + "px");
        h = h - 2 * 1;

        cellExt.setHeight(h + "px");
        h = h - 2 * 3;

        cellMed.setHeight(h + "px");
        h = h - 2 * 1;

        cellInt.setHeight(h + "px");

        cellInt1.setHeight(h + "px");
        cellInt2.setHeight(h + "px");

    }

    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }

}
