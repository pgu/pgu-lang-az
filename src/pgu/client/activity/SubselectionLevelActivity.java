package pgu.client.activity;

import pgu.client.mvp.HasPlace;
import pgu.client.place.SubselectionLevelPlace;
import pgu.client.ui.level.subselection.SubselectionLevelView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class SubselectionLevelActivity extends AbstractActivity implements SubselectionLevelView.Presenter, HasPlace {

    @Inject
    private SubselectionLevelView view;
    @Inject
    private PlaceController placeController;

    private SubselectionLevelPlace place;

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        view.setPresenter(this);
        panel.setWidget(view.asWidget());
        view.displaySubselections( //
                place.getSubselections(), //
                place.getTheme(), //
                place.getGranularity(), //
                place.getLanguage());
    }

    @Override
    public void goTo(final Place place) {
        placeController.goTo(place);
    }

    @Override
    public Activity place(final Place place) {
        this.place = (SubselectionLevelPlace) place;
        return this;
    }

}
