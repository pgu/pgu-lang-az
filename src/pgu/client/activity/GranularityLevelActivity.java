package pgu.client.activity;

import pgu.client.ui.level.language.LanguageLevelView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class GranularityLevelActivity extends AbstractActivity implements LanguageLevelView.Presenter {

    @Inject
    private LanguageLevelView view;
    @Inject
    private PlaceController placeController;

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        view.setPresenter(this);
        panel.setWidget(view.asWidget());
    }

    @Override
    public void goTo(final Place place) {
        placeController.goTo(place);
    }

}
