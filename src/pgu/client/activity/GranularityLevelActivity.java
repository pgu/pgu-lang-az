package pgu.client.activity;

import pgu.client.mvp.HasPlace;
import pgu.client.place.GranularityLevelPlace;
import pgu.client.ui.level.granularity.GranularityLevelView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class GranularityLevelActivity extends AbstractActivity implements GranularityLevelView.Presenter, HasPlace {

    @Inject
    private GranularityLevelView view;
    @Inject
    private PlaceController placeController;

    private GranularityLevelPlace place;

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        view.setPresenter(this);
        panel.setWidget(view.asWidget());
        view.displayGranularities(place.getGranularity(), place.getLanguage());
    }

    @Override
    public void goTo(final Place place) {
        placeController.goTo(place);
    }

    @Override
    public Activity place(final Place place) {
        this.place = (GranularityLevelPlace) place;
        return this;
    }

}
