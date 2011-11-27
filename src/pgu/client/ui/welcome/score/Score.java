package pgu.client.ui.welcome.score;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class Score extends Composite {

    private static ScoreUiBinder uiBinder = GWT.create(ScoreUiBinder.class);

    interface ScoreUiBinder extends UiBinder<Widget, Score> {
    }

    @UiField
    HTML score;

    public Score(final String text) {
        initWidget(uiBinder.createAndBindUi(this));
        score.setText(text);
    }

}
