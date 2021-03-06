package pgu.client.ui.utils;

import pgu.client.ui.style.PguGameResources;
import pgu.client.ui.style.PguGameResources.Style;

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

    private final Style style;

    @UiField
    HTMLPanel cellExt2, cellExt1, cellExt, cellMed, cellInt, cellInt1, cellInt2, cellInt2Bg;
    @UiField
    HTML cellText;

    public static enum Skin {
        ICE, FIRE
    }

    private Skin skin;

    private static int count = 0;
    private final String id;

    public AppCell(final Skin skin) {
        initWidget(uiBinder.createAndBindUi(this));

        style = PguGameResources.INSTANCE.style();

        id = "pgu-appCell-" + count++;
        getElement().setId(id);

        setSkin(skin);
    }

    public void setHTML(final String html) {
        cellText.setHTML(html);
    }

    public void setText(final String text) {
        cellText.setText(text);
    }

    public String getText() {
        return cellText.getText();
    }

    public void setSize(final int width, final int height) {
        int h = height;

        cellExt2.setSize(width + "px", h + "px");
        cellExt1.setHeight(h + "px");
        h = h - 2 * 1;

        cellExt.setHeight(h + "px");
        h = h - 2 * 2;

        cellMed.setHeight(h + "px");
        h = h - 2 * 1;

        cellInt.setHeight(h + "px");

        cellInt1.setHeight(h + "px");
        cellInt2.setHeight(h + "px");
        cellInt2Bg.setHeight(h + "px");

    }

    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(final Skin skin) {
        this.skin = skin;

        // clear styles
        cellExt1.setStyleName("");
        cellExt2.setStyleName("");
        cellExt.setStyleName("");
        cellMed.setStyleName("");
        cellInt.setStyleName("");

        cellInt1.setStyleName("");
        cellInt2Bg.setStyleName("");
        cellInt2.setStyleName("");

        //
        cellText.addStyleName(style.app_cell_text());
        cellInt1.addStyleName(style.app_cell_int1());
        cellInt2Bg.addStyleName(style.app_cell_intbg());
        cellInt2.addStyleName(style.app_cell_int2());

        if (Skin.ICE == skin) {

            cellExt1.addStyleName(style.app_cell_extx_ice());
            cellExt2.addStyleName(style.app_cell_extx_ice());
            cellExt.addStyleName(style.app_cell_ext_ice());
            cellMed.addStyleName(style.app_cell_med_ice());
            cellInt.addStyleName(style.app_cell_int_ice());

            cellInt1.addStyleName(style.app_cell_intx_ice());
            cellInt2Bg.addStyleName(style.app_cell_intx_ice());
            cellInt2.addStyleName(style.app_cell_intx_ice());

            cellInt2.addStyleName(style.app_cell_int2_ice());
            cellInt2Bg.addStyleName(style.app_cell_intbg_ice());

        } else if (Skin.FIRE == skin) {

            cellExt1.addStyleName(style.app_cell_extx_fire());
            cellExt2.addStyleName(style.app_cell_extx_fire());
            cellExt.addStyleName(style.app_cell_ext_fire());
            cellMed.addStyleName(style.app_cell_med_fire());
            cellInt.addStyleName(style.app_cell_int_fire());

            cellInt1.addStyleName(style.app_cell_intx_fire());
            cellInt2Bg.addStyleName(style.app_cell_intx_fire());
            cellInt2.addStyleName(style.app_cell_intx_fire());

            cellInt2.addStyleName(style.app_cell_int2_fire());
            cellInt2Bg.addStyleName(style.app_cell_intbg_fire());

        } else {
            throw new UnsupportedOperationException();
        }

    }

    public void fadeIn() {
        fadeInInternal(id);
    }

    private native void fadeInInternal(String id) /*-{
        $wnd.$('#' + id).fadeIn('slow');
    }-*/;

}
