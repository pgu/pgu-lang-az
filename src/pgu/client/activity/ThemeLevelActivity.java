package pgu.client.activity;

import pgu.client.mvp.HasPlace;
import pgu.client.place.ThemeLevelPlace;
import pgu.client.ui.level.language.LanguageLevelView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class ThemeLevelActivity extends AbstractActivity implements LanguageLevelView.Presenter, HasPlace {

    @Inject
    private LanguageLevelView view;
    @Inject
    private PlaceController placeController;

    private ThemeLevelPlace place;

    @Override
    public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
        view.setPresenter(this);
        panel.setWidget(view.asWidget());
    }

    @Override
    public void goTo(final Place place) {
        placeController.goTo(place);
    }

    @Override
    public Activity place(final Place place) {
        this.place = (ThemeLevelPlace) place;
        return this;
    }

}
