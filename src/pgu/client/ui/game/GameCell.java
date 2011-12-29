package pgu.client.ui.game;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class GameCell extends Composite {

    private static GameCellUiBinder uiBinder = GWT.create(GameCellUiBinder.class);

    interface GameCellUiBinder extends UiBinder<Widget, GameCell> {
    }

    @UiField
    HTMLPanel cellExt, cellOut, cellMed, cellIn, cellSub;
    @UiField
    HTML cellText;

    public GameCell() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    private enum Skin {
        FIRE("#fe601e", "#eecd30"), ICE("#22afca", "#9ad5d8");

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
        return this;
    }

    public void applySkin(final Skin skin) {
        cellExt.getElement().getStyle().setProperty("webkitBoxShadow", skin.ext() + " 0 0 16px");

        final Style styleOut = cellOut.getElement().getStyle();
        styleOut.setProperty("border", "1px solid " + skin.mid());
        styleOut.setProperty("webkitBoxShadow", skin.mid() + " 0 0 16px");

        final Style styleIn = cellIn.getElement().getStyle();
        styleIn.setProperty("border", "1px solid " + skin.mid());
        styleIn.setProperty("webkitBoxShadow", "inset " + skin.mid() + " 0 0 15px");

        cellSub.getElement().getStyle().setProperty("webkitBoxShadow", "inset " + skin.ext() + " 0 0 6px");

        cellText.getElement().getStyle().setProperty("textShadow", "0 0 23px " + skin.mid());
    }

    public GameCell index(final int index) {
        cellText.setHTML("" + index);
        return this;
    }

    @Override
    public void setPixelSize(final int width, final int height) {
        cellExt.setPixelSize(width, height);
        cellOut.setPixelSize(width - 2, height - 2);
        cellMed.setPixelSize(width - 4, height - 4);
        cellIn.setPixelSize(width - 6, height - 6);
        cellSub.setPixelSize(width - 7, height - 7);

        cellText.getElement().getStyle().setTop(height / 2 - height / 4, Unit.PX);
        cellText.getElement().getStyle().setWidth(width - 7, Unit.PX);
    }

}
