package pgu.client.ui.game;

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
    private Skin defaultSkin = Skin.WHITE;

    private TuplePosition tuplePosition;
    private String character;

    private boolean isSelected = false;

    private final pgu.client.ui.style.PguGameResources.Style style;

    public GameCell(final GameCellFactory factory) {
        initWidget(uiBinder.createAndBindUi(this));

        style = PguGameResources.INSTANCE.style();

        this.factory = factory;
        applySkin(Skin.WHITE);
        console("builds a cell - white");
    }

    private static enum Skin {
        WHITE("#fff", "#fff") //
        , FIRE("#fe601e", "#eecd30") //
        , ICE("#22afca", "#9ad5d8") //
        , VIOLET("#ae22ca", "#d89ac7") //
        , GREEN("#22ca3b", "#9ad89e");

        private String colorExt;
        private String colorMid;

        private Skin(final String ext, final String mid) {
            colorExt = ext;
            colorMid = mid;
        }

        public String ext() {
            return colorExt;
        }

        public String mid() {
            return colorMid;
        }
    }

    public GameCell fire() {
        applySkin(Skin.FIRE);
        return this;
    }

    public GameCell ice() {
        applySkin(Skin.ICE);
        console("ice...");
        return this;
    }

    public GameCell green() {
        applySkin(Skin.GREEN);
        console("green...");
        return this;
    }

    public GameCell violet() {
        applySkin(Skin.VIOLET);
        console("violet...");
        return this;
    }

    public GameCell flagAsDefaultSkin() {
        console("setDefaultSkin... default: " + defaultSkin + ", current: " + currentSkin);
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

        if (Skin.ICE == skin) {

        } else if (Skin.FIRE == skin) {

        } else if (Skin.GREEN == skin) {
            // TODO PGU Mar 27, 2013
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
        cellExt.setPixelSize(factory.extW(), factory.extH());
        cellOut.setPixelSize(factory.outW(), factory.outH());
        cellMed.setPixelSize(factory.medW(), factory.medH());
        cellIn.setPixelSize(factory.inW(), factory.inH());
        cellSub.setPixelSize(factory.subW(), factory.subH());

        final Style styleText = cellText.getElement().getStyle();
        styleText.setWidth(factory.width() - 7, Unit.PX);

        final boolean isPortrait = factory.isPortrait();
        styleText.setTop(isPortrait ? 10 : 5, Unit.PX);

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

        fire();
        isSelected = true;

        factory.gameView().clicksOn(this);
    }

    public void deselect() {
        applySkin(defaultSkin);
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
        applySkin(Skin.WHITE);

        tuplePosition = null;
        character = "";

        cellText.setHTML("");

        isSelected = false;
    }

    private native void console(String msg) /*-{
        $wnd.console.log(msg);
    }-*/;

}
