package pgu.client.activity;

import pgu.client.ui.game.GameView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class GameActivity extends AbstractActivity implements GameView.Presenter {

    @Inject
    private GameView view;
    @Inject
    private PlaceController placeController;

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        view.setPresenter(this);
        panel.setWidget(view.asWidget());
        view.resize();
        view.buildGridGame();
        view.fillGridWithSymbols();
    }

    @Override
    public void goTo(final Place place) {
        placeController.goTo(place);
    }

}
