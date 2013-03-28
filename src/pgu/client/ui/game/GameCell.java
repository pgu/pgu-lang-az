package pgu.client.ui.game;

import pgu.client.AppHelper;
import pgu.client.ui.style.PguGameResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class GameCell extends Composite {

    private static GameCellUiBinder uiBinder = GWT.create(GameCellUiBinder.class);

    interface GameCellUiBinder extends UiBinder<Widget, GameCell> {
    }

    @UiField
    HTMLPanel cellExt2, cellExt1, cellExt;
    @UiField
    HTMLPanel cellMed;
    @UiField
    HTMLPanel cellInt2Bg, cellInt2, cellInt1, cellInt;
    @UiField
    HTML cellText;

    private final GameCellFactory factory;
    private int index;

    private Skin currentSkin = null;
    private Skin defaultSkin = Skin.ICE;

    private TuplePosition tuplePosition;
    private String character;

    private boolean isSelected = false;

    private final pgu.client.ui.style.PguGameResources.Style style;

    private final AppHelper   h             = new AppHelper();

    public GameCell(final GameCellFactory factory) {

        //        final long start = new Date().getTime();
        //        h.console("start " + start);

        initWidget(uiBinder.createAndBindUi(this));

        //        h.console("stop1 " + (new Date().getTime() - start) + " ms");

        style = PguGameResources.INSTANCE.style();

        //        h.console("stop2 " + (new Date().getTime() - start) + " ms");

        this.factory = factory;
        //        applySkin(Skin.ICE);
        //        console("builds a cell - ice");
    }

    private static enum Skin {
        FIRE //
        , ICE//
        , BLUE//
        , GREEN;
        //        WHITE("#fff", "#fff") //
        //        , FIRE("#fe601e", "#eecd30") //
        //        , ICE("#22afca", "#9ad5d8") //
        //        , VIOLET("#ae22ca", "#d89ac7") //
        //        , GREEN("#22ca3b", "#9ad89e");

        //        private String colorExt;
        //        private String colorMid;
        //
        //        private Skin(final String ext, final String mid) {
        //            colorExt = ext;
        //            colorMid = mid;
        //        }
        //
        //        public String ext() {
        //            return colorExt;
        //        }
        //
        //        public String mid() {
        //            return colorMid;
        //        }
    }

    public GameCell fire() {
        applySkin(Skin.FIRE);
        return this;
    }

    public GameCell ice() {
        applySkin(Skin.ICE);
        //        console("ice...");
        return this;
    }

    public GameCell green() {
        applySkin(Skin.GREEN);
        //        console("green...");
        return this;
    }

    public GameCell blue() {
        applySkin(Skin.BLUE);
        //        console("blue...");
        return this;
    }

    //    public GameCell violet() {
    //        applySkin(Skin.VIOLET);
    //        console("violet...");
    //        return this;
    //    }

    public GameCell flagAsDefaultSkin() {
        //        console("setDefaultSkin... default: " + defaultSkin + ", current: " + currentSkin);
        defaultSkin = currentSkin;
        return this;
    }

    public void applySkin(final Skin skin) {
        currentSkin = skin;

        // clear styles
        cellExt2.setStyleName("");
        cellExt1.setStyleName("");
        cellExt.setStyleName("");
        cellMed.setStyleName("");
        cellInt.setStyleName("");
        cellInt1.setStyleName("");
        cellInt2.setStyleName("");
        cellInt2Bg.setStyleName("");
        cellText.setStyleName("");

        //

        final Style style2 = cellExt2.getElement().getStyle();
        style2.setFloat(Style.Float.LEFT);
        style2.setMargin(20, Unit.PX);

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

        } else if (Skin.GREEN == skin) {

            cellExt1.addStyleName(style.app_cell_extx_green());
            cellExt2.addStyleName(style.app_cell_extx_green());
            cellExt.addStyleName(style.app_cell_ext_green());
            cellMed.addStyleName(style.app_cell_med_green());
            cellInt.addStyleName(style.app_cell_int_green());

            cellInt1.addStyleName(style.app_cell_intx_green());
            cellInt2Bg.addStyleName(style.app_cell_intx_green());
            cellInt2.addStyleName(style.app_cell_intx_green());

            cellInt2.addStyleName(style.app_cell_int2_green());
            cellInt2Bg.addStyleName(style.app_cell_intbg_green());

        } else if (Skin.BLUE == skin) {

            cellExt1.addStyleName(style.app_cell_extx_blue());
            cellExt2.addStyleName(style.app_cell_extx_blue());
            cellExt.addStyleName(style.app_cell_ext_blue());
            cellMed.addStyleName(style.app_cell_med_blue());
            cellInt.addStyleName(style.app_cell_int_blue());

            cellInt1.addStyleName(style.app_cell_intx_blue());
            cellInt2Bg.addStyleName(style.app_cell_intx_blue());
            cellInt2.addStyleName(style.app_cell_intx_blue());

            cellInt2.addStyleName(style.app_cell_int2_blue());
            cellInt2Bg.addStyleName(style.app_cell_intbg_blue());

        }

        //        cellExt.getElement().getStyle().setProperty("webkitBoxShadow", skin.ext() + " 0 0 16px");
        //
        //        final Style styleOut = cellOut.getElement().getStyle();
        //        styleOut.setProperty("border", "1px solid " + skin.mid());
        //        styleOut.setProperty("webkitBoxShadow", skin.mid() + " 0 0 16px");
        //
        //        final Style styleIn = cellIn.getElement().getStyle();
        //        styleIn.setProperty("border", "1px solid " + skin.mid());
        //        styleIn.setProperty("webkitBoxShadow", "inset " + skin.mid() + " 0 0 15px");
        //
        //        cellSub.getElement().getStyle().setProperty("webkitBoxShadow", "inset " + skin.ext() + " 0 0 6px");
        //
        //        cellText.getElement().getStyle().setProperty("textShadow", "0 0 23px " + skin.mid());
    }

    public GameCell index(final int index) {
        this.index = index;
        return this;
    }

    public void size() {

        int h = factory.height();
        final int width = factory.width();

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

        //
        //        cellExt.setPixelSize(factory.extW(), factory.extH());
        //        cellOut.setPixelSize(factory.outW(), factory.outH());
        //        cellMed.setPixelSize(factory.medW(), factory.medH());
        //        cellIn.setPixelSize(factory.inW(), factory.inH());
        //        cellSub.setPixelSize(factory.subW(), factory.subH());

        //        final Style styleText = cellText.getElement().getStyle();
        //        styleText.setWidth(factory.width() - 7, Unit.PX);
        //
        //        final boolean isPortrait = factory.isPortrait();
        //        styleText.setTop(isPortrait ? 10 : 5, Unit.PX);

        // cellExt.getElement().getStyle().setMarginTop(isPortrait ? 5 : 10, Unit.PX);
    }

    @Override
    public void setPixelSize(final int width, final int height) {
        throw new UnsupportedOperationException("Use the method size(), thx.");
    }

    public enum TuplePosition {
        FIRST, SECOND, THIRD;
    }

    public void setCharacter(final String character, final TuplePosition tuplePosition) {
        this.tuplePosition = tuplePosition;
        this.character = character;

        cellText.setHTML("" + character);
    }

    @UiHandler("cellText")
    public void clickCell(final ClickEvent e) {
        if (isSelected) {
            return;
        }

        setSelectUI();
        isSelected = true;

        factory.gameView().clicksOn(this);
    }

    private void setSelectUI() {
        cellInt2Bg.addStyleName(style.app_cell_intbg_selected());
    }

    public void deselect() {
        cellInt2Bg.removeStyleName(style.app_cell_intbg_selected());
        isSelected = false;
    }

    public String getCharacter() {
        return character;
    }

    public int index() {
        return index;
    }

    public TuplePosition tuplePosition() {
        return tuplePosition;
    }

    public void onStop() {
        currentSkin = null;
        applySkin(Skin.ICE);

        tuplePosition = null;
        character = "";

        cellText.setHTML("");

        isSelected = false;
    }

    private native void console(String msg) /*-{
        $wnd.console.log(msg);
    }-*/;

}
