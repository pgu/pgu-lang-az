package pgu.client.activity;

import pgu.client.AppHelper;
import pgu.client.mvp.HasPlace;
import pgu.client.place.GamePlace;
import pgu.client.place.WelcomePlace;
import pgu.client.ui.game.GameView;
import pgu.client.utils.guava.HashBiMap;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class GameActivity extends AbstractActivity implements GameView.Presenter, HasPlace {

    @Inject
    private GameView          view;
    @Inject
    private PlaceController   placeController;

    private GamePlace         place;

    private final AppHelper   h             = new AppHelper();

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        view.setPresenter(this);

        h.gc().language(place.getLanguage());
        h.gc().subselections(place.getSubselections());

        if (h.areInvalidGameSettings()) {

            h.resetGameSettings();
            placeController.goTo(new WelcomePlace());
            return;
        }

        panel.setWidget(view.asWidget());

        // view.resize();
        // view.buildGridGame();
        view.fillGridWithSymbols();
        view.startChrono();
    }

    @Override
    public Activity place(final Place place) {
        this.place = (GamePlace) place;
        return this;
    }

    @Override
    public void onStop() {
        view.onStop();

        view = null;
        placeController = null;

        place = null;
        lg = null;
        subSelections = null;
    }

    @Override
    public HashBiMap<String, String> getAvailableSymbols() {
        return lg.getAlphabet().availableSymbols(subSelections);
    }

    @Override
    public void goToWelcomePage() {
        placeController.goTo(new WelcomePlace(lg, subSelections));
    }

    @Override
    public void reload() {
        placeController.goTo(new GamePlace(lg, subSelections));
    }

}
