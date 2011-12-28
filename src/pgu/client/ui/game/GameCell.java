package pgu.client.ui.game;

import com.google.gwt.core.client.GWT;
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
    HTMLPanel cell;
    @UiField
    HTML cellText;

    public GameCell() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public GameCell index(final int index) {
        cellText.setHTML("" + index);
        return this;
    }

}
