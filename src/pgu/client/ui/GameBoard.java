package pgu.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class GameBoard extends Composite {

    private static GameBoardUiBinder uiBinder = GWT.create(GameBoardUiBinder.class);

    interface GameBoardUiBinder extends UiBinder<Widget, GameBoard> {
    }

    @UiField
    HTMLPanel board;

    @UiField
    HTML card01, card02, card03, card04, card05, card06, card07, card08, card09, card10, //
            card11, card12, card13, card14, card15, card16, card17, card18, card19, card20, //
            card21, card22, card23, card24, card25, card26, card27, card28, card29, card30, //
            card31, card32;

    private final List<HTML> cards = new ArrayList<HTML>();

    public GameBoard() {
        initWidget(uiBinder.createAndBindUi(this));

        cards.add(card01);
        cards.add(card02);
        cards.add(card03);
        cards.add(card04);
        cards.add(card05);
        cards.add(card06);
        cards.add(card07);
        cards.add(card08);
        cards.add(card09);
        cards.add(card10);
        cards.add(card11);
        cards.add(card12);
        cards.add(card13);
        cards.add(card14);
        cards.add(card15);
        cards.add(card16);
        cards.add(card17);
        cards.add(card18);
        cards.add(card19);
        cards.add(card20);
        cards.add(card21);
        cards.add(card22);
        cards.add(card23);
        cards.add(card24);
        cards.add(card25);
        cards.add(card26);
        cards.add(card27);
        cards.add(card28);
        cards.add(card29);
        cards.add(card30);
        cards.add(card31);
        cards.add(card32);
    }

    public void resize(final int availableWidth, final int availableHeight) {

        final boolean isLandscape = availableWidth > availableHeight;

        int factorW = 1;
        int factorH = 1;

        if (isLandscape) {
            factorW = 8;
            factorH = 4;
        } else {
            factorW = 4;
            factorH = 8;
        }
        final int w = availableWidth / factorW - 6;
        final int h = availableHeight / factorH - 8;

        board.setPixelSize(availableWidth, availableHeight);
        for (final HTML card : cards) {
            card.setPixelSize(w, h);
        }

    }

}
