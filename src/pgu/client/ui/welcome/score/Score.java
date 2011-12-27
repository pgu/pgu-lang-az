package pgu.client.ui.welcome.score;

import pgu.client.enums.Orientation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class Score extends Composite {

    private static ScoreUiBinder uiBinder = GWT.create(ScoreUiBinder.class);

    interface ScoreUiBinder extends UiBinder<Widget, Score> {
    }

    @UiField
    HTMLPanel container;
    @UiField
    HTML card;

    public Score(final String name, final String score) {
        initWidget(uiBinder.createAndBindUi(this));
        card.setHTML(name + //
                "<br/>" + //
                score);
    }

    public void setScoreSize(final Orientation orientation, final int width, final int height) {
        super.setPixelSize(width, height);

        int offsetContainer = 0;
        int marginTop = 0;

        if (Orientation.PAYSAGE == orientation) {
            offsetContainer = 5;
            marginTop = 5;

        } else if (Orientation.PORTRAIT == orientation) {
            offsetContainer = 10;
            marginTop = 50;

        }

        container.setHeight(height - 8 - offsetContainer + "px");
        container.getElement().getStyle().setPaddingTop(offsetContainer, Unit.PX);

        final Style cardS = card.getElement().getStyle();
        cardS.setMarginTop(marginTop, Unit.PX);
    }

}
