package pgu.client.activity;

import pgu.client.GameConfig;
import pgu.client.Pgu_lang_az;
import pgu.client.ui.welcome.WelcomeView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class WelcomeActivity extends AbstractActivity implements WelcomeView.Presenter {

    @Inject
    private WelcomeView view;
    @Inject
    private PlaceController placeController;

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        view.setPresenter(this);

        final GameConfig gc = Pgu_lang_az.gameConfig;
        final String currentLevel= "<div><p>" + gc.language() + "</p><p>" + gc.subselections() +"</p></div>";
        view.setCurrentLevel(currentLevel);

        panel.setWidget(view.asWidget());
    }

    @Override
    public void goTo(final Place place) {
        placeController.goTo(place);
    }

    @Override
    public void onStop() {
        view.onStop();

        view = null;
        placeController = null;
    }

}
